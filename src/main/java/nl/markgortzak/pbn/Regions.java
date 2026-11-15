package nl.markgortzak.pbn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Cleans a label map so that each region is large enough to paint.
 *
 * <p>A label is a palette index. A region is one 4-connected area of one label. Two regions of
 * the same label that do not touch are two regions, and each gets its own number.
 */
final class Regions {

    static final int MIN_AREA_FLOOR = 64;
    static final double MIN_AREA_SHARE = 0.0002;
    static final int SMOOTH_PASSES = 2;

    /** A region narrower than this radius, in pixels, cannot hold a brush stroke and merges, whatever its area. */
    static final double MIN_RADIUS = 3;

    /** {@code region[i]} is the region of pixel i; {@code size} and {@code label} are indexed by region. */
    record Components(int[] region, int count, int[] size, int[] label) {
    }

    private Regions() {
    }

    static int minArea(int pixels) {
        return Math.max(MIN_AREA_FLOOR, (int) Math.round(pixels * MIN_AREA_SHARE));
    }

    /** Runs the majority filter, and then merges every region below {@code minArea} into a neighbor. */
    static int[] clean(int[] labels, int w, int h, int minArea) {
        int[] current = labels;
        for (int pass = 0; pass < SMOOTH_PASSES; pass++) {
            current = smooth(current, w, h);
        }
        return mergeSmall(current, w, h, minArea);
    }

    /** One pass of a 3×3 majority filter. A tie keeps the label of the pixel. */
    static int[] smooth(int[] labels, int w, int h) {
        int[] out = new int[labels.length];
        int[] seen = new int[9];
        int[] votes = new int[9];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int distinct = 0;
                for (int dy = -1; dy <= 1; dy++) {
                    int ny = y + dy;
                    if (ny < 0 || ny >= h) {
                        continue;
                    }
                    for (int dx = -1; dx <= 1; dx++) {
                        int nx = x + dx;
                        if (nx < 0 || nx >= w) {
                            continue;
                        }
                        int label = labels[ny * w + nx];
                        int slot = 0;
                        while (slot < distinct && seen[slot] != label) {
                            slot++;
                        }
                        if (slot == distinct) {
                            seen[distinct] = label;
                            votes[distinct++] = 0;
                        }
                        votes[slot]++;
                    }
                }
                int own = labels[y * w + x];
                int best = own;
                int bestVotes = 0;
                for (int slot = 0; slot < distinct; slot++) {
                    if (seen[slot] == own) {
                        bestVotes = votes[slot];
                    }
                }
                for (int slot = 0; slot < distinct; slot++) {
                    if (votes[slot] > bestVotes) {
                        bestVotes = votes[slot];
                        best = seen[slot];
                    }
                }
                out[y * w + x] = best;
            }
        }
        return out;
    }

    static Components components(int[] labels, int w, int h) {
        int[] region = new int[labels.length];
        Arrays.fill(region, -1);
        int[] queue = new int[labels.length];
        int[] size = new int[16];
        int[] label = new int[16];
        int count = 0;
        for (int start = 0; start < labels.length; start++) {
            if (region[start] >= 0) {
                continue;
            }
            if (count == size.length) {
                size = Arrays.copyOf(size, count * 2);
                label = Arrays.copyOf(label, count * 2);
            }
            int own = labels[start];
            int head = 0;
            int tail = 0;
            queue[tail++] = start;
            region[start] = count;
            while (head < tail) {
                int i = queue[head++];
                int x = i % w;
                int y = i / w;
                if (x > 0 && region[i - 1] < 0 && labels[i - 1] == own) {
                    region[i - 1] = count;
                    queue[tail++] = i - 1;
                }
                if (x < w - 1 && region[i + 1] < 0 && labels[i + 1] == own) {
                    region[i + 1] = count;
                    queue[tail++] = i + 1;
                }
                if (y > 0 && region[i - w] < 0 && labels[i - w] == own) {
                    region[i - w] = count;
                    queue[tail++] = i - w;
                }
                if (y < h - 1 && region[i + w] < 0 && labels[i + w] == own) {
                    region[i + w] = count;
                    queue[tail++] = i + w;
                }
            }
            size[count] = tail;
            label[count] = own;
            count++;
        }
        return new Components(region, count, Arrays.copyOf(size, count), Arrays.copyOf(label, count));
    }

    /**
     * The 3-4 chamfer distance to the nearest border pixel, in units of a third of a pixel. A
     * border pixel touches another label or the edge of the image, and has distance 3.
     */
    static int[] chamfer(int[] labels, int w, int h) {
        int[] d = new int[labels.length];
        int far = Integer.MAX_VALUE / 2;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int i = y * w + x;
                boolean border = x == 0 || y == 0 || x == w - 1 || y == h - 1
                        || labels[i - 1] != labels[i] || labels[i + 1] != labels[i] || labels[i - w] != labels[i] || labels[i + w] != labels[i];
                d[i] = border ? 3 : far;
            }
        }
        for (int y = 1; y < h; y++) {
            for (int x = 1; x < w - 1; x++) {
                int i = y * w + x;
                d[i] = Math.min(d[i], Math.min(Math.min(d[i - 1] + 3, d[i - w] + 3), Math.min(d[i - w - 1] + 4, d[i - w + 1] + 4)));
            }
        }
        for (int y = h - 2; y >= 0; y--) {
            for (int x = w - 2; x >= 1; x--) {
                int i = y * w + x;
                d[i] = Math.min(d[i], Math.min(Math.min(d[i + 1] + 3, d[i + w] + 3), Math.min(d[i + w + 1] + 4, d[i + w - 1] + 4)));
            }
        }
        return d;
    }

    /** The largest chamfer distance inside each region, in pixels. */
    static double[] radius(Components c, int[] distance) {
        double[] radius = new double[c.count()];
        for (int i = 0; i < distance.length; i++) {
            int r = c.region()[i];
            radius[r] = Math.max(radius[r], distance[i] / 3.0);
        }
        return radius;
    }

    /**
     * Merges small regions in rounds until none is left. In one round, the regions go from the
     * smallest up, and a region that receives a merge cannot merge itself, so every merge in the
     * round is real and the smallest region always merges. The count of regions then falls in each
     * round, and the loop ends.
     */
    private static int[] mergeSmall(int[] labels, int w, int h, int minArea) {
        int[] current = labels.clone();
        while (true) {
            Components c = components(current, w, h);
            Integer[] small = smallRegions(c, radius(c, chamfer(current, w, h)), minArea);
            if (small.length == 0 || c.count() == 1) {
                return current;
            }
            Map<Integer, Map<Integer, Integer>> borders = borders(c, w, h, small);
            boolean[] locked = new boolean[c.count()];
            boolean[] merging = new boolean[c.count()];
            int[] newLabel = c.label().clone();
            boolean changed = false;
            for (int r : small) {
                if (locked[r]) {
                    continue;
                }
                int target = longestBorder(borders.get(r));
                if (target < 0 || merging[target]) {
                    continue;
                }
                newLabel[r] = c.label()[target];
                merging[r] = true;
                locked[target] = true;
                changed = true;
            }
            if (!changed) {
                return current;
            }
            for (int i = 0; i < current.length; i++) {
                current[i] = newLabel[c.region()[i]];
            }
        }
    }

    private static Integer[] smallRegions(Components c, double[] radius, int minArea) {
        return java.util.stream.IntStream.range(0, c.count())
                .filter(r -> c.size()[r] < minArea || radius[r] < MIN_RADIUS)
                .boxed()
                .sorted((a, b) -> c.size()[a] != c.size()[b] ? Integer.compare(c.size()[a], c.size()[b]) : Integer.compare(a, b))
                .toArray(Integer[]::new);
    }

    /** For each small region, the length of the border it shares with each neighbor region. */
    private static Map<Integer, Map<Integer, Integer>> borders(Components c, int w, int h, Integer[] small) {
        Map<Integer, Map<Integer, Integer>> borders = new HashMap<>();
        for (int r : small) {
            borders.put(r, new HashMap<>());
        }
        int[] region = c.region();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int i = y * w + x;
                if (x < w - 1 && region[i] != region[i + 1]) {
                    count(borders, region[i], region[i + 1]);
                }
                if (y < h - 1 && region[i] != region[i + w]) {
                    count(borders, region[i], region[i + w]);
                }
            }
        }
        return borders;
    }

    private static void count(Map<Integer, Map<Integer, Integer>> borders, int a, int b) {
        Map<Integer, Integer> ofA = borders.get(a);
        if (ofA != null) {
            ofA.merge(b, 1, Integer::sum);
        }
        Map<Integer, Integer> ofB = borders.get(b);
        if (ofB != null) {
            ofB.merge(a, 1, Integer::sum);
        }
    }

    /** The neighbor with the longest border; a tie goes to the lower region index, so the result is stable. */
    private static int longestBorder(Map<Integer, Integer> neighbors) {
        int best = -1;
        int bestLength = 0;
        for (Map.Entry<Integer, Integer> e : neighbors.entrySet()) {
            int length = e.getValue();
            if (length > bestLength || (length == bestLength && e.getKey() < best)) {
                best = e.getKey();
                bestLength = length;
            }
        }
        return best;
    }
}
