package nl.markgortzak.pbn;

import java.util.ArrayList;
import java.util.List;

/**
 * Finds where the number of each region goes.
 *
 * <p>The number goes at the pixel of the region that is farthest from its border, which is the
 * pole of inaccessibility. The centroid is wrong for a curved region: it can fall outside it.
 */
final class Labels {

    static final double MIN_HEIGHT = 8;
    static final double MAX_HEIGHT = 24;

    /** The share of the free radius that the text may cover, so the text keeps a margin to the outline. */
    static final double FILL = 0.85;

    /**
     * One number to draw. {@code x} and {@code y} are the column and the row of a pixel, and
     * {@code radius} is its distance to the border of the region, in pixels.
     */
    record Label(int number, int x, int y, double radius) {

        double textHeight() {
            return Labels.textHeight(String.valueOf(number), radius);
        }
    }

    private Labels() {
    }

    /** {@code index} holds the palette position of each pixel, so a number is the position plus one. */
    static List<Label> place(int[] index, int w, int h) {
        Regions.Components c = Regions.components(index, w, h);
        int[] distance = Regions.chamfer(index, w, h);
        int[] best = new int[c.count()];
        int[] bestDistance = new int[c.count()];
        for (int i = 0; i < index.length; i++) {
            int r = c.region()[i];
            if (distance[i] > bestDistance[r]) {
                bestDistance[r] = distance[i];
                best[r] = i;
            }
        }
        List<Label> labels = new ArrayList<>(c.count());
        for (int r = 0; r < c.count(); r++) {
            labels.add(new Label(c.label()[r] + 1, best[r] % w, best[r] / w, bestDistance[r] / 3.0));
        }
        return labels;
    }

    /** The largest text height whose box fits in {@code FILL} of the radius, inside the range. */
    static double textHeight(String text, double radius) {
        double ratio = Glyphs.width(text, 100) / 100;
        double height = 2 * FILL * radius / Math.sqrt(ratio * ratio + 1);
        return Math.max(MIN_HEIGHT, Math.min(MAX_HEIGHT, height));
    }
}
