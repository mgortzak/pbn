package nl.markgortzak.pbn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * The colors that the cleaned label map still uses, numbered 1..N from dark to light.
 *
 * <p>{@code index[i]} is the position of the swatch of pixel i in {@code swatches}, so the
 * number of that pixel is {@code index[i] + 1}.
 */
record Palette(List<Swatch> swatches, int[] index) {

    /**
     * The color of a swatch is the mean of its pixels in Lab, and not the center the quantizer
     * found, because the merge of small regions moves pixels to another label.
     */
    static Palette of(int[] labels, float[] lab) {
        int max = Arrays.stream(labels).max().orElse(0);
        double[] sums = new double[(max + 1) * 3];
        int[] sizes = new int[max + 1];
        for (int i = 0; i < labels.length; i++) {
            int c = labels[i];
            sizes[c]++;
            sums[3 * c] += lab[3 * i];
            sums[3 * c + 1] += lab[3 * i + 1];
            sums[3 * c + 2] += lab[3 * i + 2];
        }
        List<double[]> used = new ArrayList<>();
        for (int c = 0; c <= max; c++) {
            if (sizes[c] > 0) {
                used.add(new double[] {c, sums[3 * c] / sizes[c], sums[3 * c + 1] / sizes[c], sums[3 * c + 2] / sizes[c]});
            }
        }
        used.sort(Comparator.comparingDouble((double[] u) -> u[1]).thenComparingDouble(u -> u[0]));
        int[] position = new int[max + 1];
        List<Swatch> swatches = new ArrayList<>();
        for (int p = 0; p < used.size(); p++) {
            double[] u = used.get(p);
            position[(int) u[0]] = p;
            swatches.add(new Swatch(p + 1, Lab.toRgb(u[1], u[2], u[3])));
        }
        int[] index = new int[labels.length];
        for (int i = 0; i < labels.length; i++) {
            index[i] = position[labels[i]];
        }
        return new Palette(List.copyOf(swatches), index);
    }
}
