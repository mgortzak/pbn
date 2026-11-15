package nl.markgortzak.pbn;

import java.util.Random;

/**
 * Finds a small palette for an image with k-means++ in Lab.
 *
 * <p>Colors are flat {@code float} arrays with three values for each pixel, because a 1600 px
 * image has two million pixels and an array for each pixel would use too much memory.
 */
final class Quantizer {

    static final int MAX_SAMPLE = 50_000;
    static final int MAX_ITERATIONS = 30;

    /** A center that moves less than this distance in Lab counts as stopped. */
    private static final double SETTLED = 0.05;

    private Quantizer() {
    }

    static float[] toLab(int[] rgb) {
        float[] lab = new float[rgb.length * 3];
        for (int i = 0; i < rgb.length; i++) {
            double[] c = Lab.fromRgb(rgb[i]);
            lab[3 * i] = (float) c[0];
            lab[3 * i + 1] = (float) c[1];
            lab[3 * i + 2] = (float) c[2];
        }
        return lab;
    }

    /**
     * Gives at most {@code k} centers. It gives fewer when the image holds fewer distinct colors,
     * because k-means++ stops when every remaining pixel already sits on a center.
     */
    static float[] fit(float[] lab, int k, long seed) {
        Random random = new Random(seed);
        float[] sample = sample(lab, random);
        float[] centers = seed(sample, k, random);
        int n = sample.length / 3;
        int count = centers.length / 3;
        int[] labels = new int[n];
        for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
            for (int i = 0; i < n; i++) {
                labels[i] = nearest(sample, i, centers);
            }
            double[] sums = new double[count * 3];
            int[] sizes = new int[count];
            for (int i = 0; i < n; i++) {
                int c = labels[i];
                sizes[c]++;
                sums[3 * c] += sample[3 * i];
                sums[3 * c + 1] += sample[3 * i + 1];
                sums[3 * c + 2] += sample[3 * i + 2];
            }
            double moved = 0;
            for (int c = 0; c < count; c++) {
                if (sizes[c] == 0) {
                    continue;
                }
                for (int d = 0; d < 3; d++) {
                    float next = (float) (sums[3 * c + d] / sizes[c]);
                    moved = Math.max(moved, Math.abs(next - centers[3 * c + d]));
                    centers[3 * c + d] = next;
                }
            }
            if (moved < SETTLED) {
                break;
            }
        }
        return centers;
    }

    static int[] assign(float[] lab, float[] centers) {
        int[] labels = new int[lab.length / 3];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = nearest(lab, i, centers);
        }
        return labels;
    }

    private static float[] sample(float[] lab, Random random) {
        int n = lab.length / 3;
        if (n <= MAX_SAMPLE) {
            return lab;
        }
        float[] sample = new float[MAX_SAMPLE * 3];
        for (int s = 0; s < MAX_SAMPLE; s++) {
            int i = random.nextInt(n);
            System.arraycopy(lab, 3 * i, sample, 3 * s, 3);
        }
        return sample;
    }

    /** The k-means++ start: each next center is a pixel chosen with a weight of its squared distance. */
    private static float[] seed(float[] sample, int k, Random random) {
        int n = sample.length / 3;
        float[] centers = new float[k * 3];
        System.arraycopy(sample, 3 * random.nextInt(n), centers, 0, 3);
        int count = 1;
        double[] nearest = new double[n];
        for (int i = 0; i < n; i++) {
            nearest[i] = distance(sample, i, centers, 0);
        }
        while (count < k) {
            double total = 0;
            for (double d : nearest) {
                total += d;
            }
            if (total == 0) {
                break;
            }
            double target = random.nextDouble() * total;
            int chosen = n - 1;
            for (int i = 0; i < n; i++) {
                target -= nearest[i];
                if (target < 0) {
                    chosen = i;
                    break;
                }
            }
            System.arraycopy(sample, 3 * chosen, centers, 3 * count, 3);
            for (int i = 0; i < n; i++) {
                nearest[i] = Math.min(nearest[i], distance(sample, i, centers, count));
            }
            count++;
        }
        float[] used = new float[count * 3];
        System.arraycopy(centers, 0, used, 0, used.length);
        return used;
    }

    private static int nearest(float[] lab, int i, float[] centers) {
        int best = 0;
        double bestDistance = Double.MAX_VALUE;
        for (int c = 0; c < centers.length / 3; c++) {
            double d = distance(lab, i, centers, c);
            if (d < bestDistance) {
                bestDistance = d;
                best = c;
            }
        }
        return best;
    }

    private static double distance(float[] lab, int i, float[] centers, int c) {
        double dl = lab[3 * i] - centers[3 * c];
        double da = lab[3 * i + 1] - centers[3 * c + 1];
        double db = lab[3 * i + 2] - centers[3 * c + 2];
        return dl * dl + da * da + db * db;
    }
}
