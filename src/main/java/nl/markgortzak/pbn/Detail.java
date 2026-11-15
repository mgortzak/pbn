package nl.markgortzak.pbn;

import java.util.Locale;

/**
 * How much detail the result keeps. More detail gives more regions, and smaller ones to paint.
 *
 * <p>Every size here applies to the working image, so the same setting gives the same result for
 * a small image and a large one.
 */
public enum Detail {

    LOW(1600, 0.0004, 96, 4.0, 2),
    MEDIUM(1600, 0.0002, 64, 3.0, 2),
    HIGH(2400, 0.00005, 16, 1.5, 1);

    /** The long side of the working image, in pixels. */
    final int workingSize;
    final double minAreaShare;
    final int minAreaFloor;

    /** A region narrower than this radius, in pixels, merges into a neighbor, whatever its area. */
    final double minRadius;

    final int smoothPasses;

    Detail(int workingSize, double minAreaShare, int minAreaFloor, double minRadius, int smoothPasses) {
        this.workingSize = workingSize;
        this.minAreaShare = minAreaShare;
        this.minAreaFloor = minAreaFloor;
        this.minRadius = minRadius;
        this.smoothPasses = smoothPasses;
    }

    int minArea(int pixels) {
        return Math.max(minAreaFloor, (int) Math.round(pixels * minAreaShare));
    }

    /** Reads {@code low}, {@code medium} or {@code high}, in any case. */
    static Detail parse(String text) {
        try {
            return valueOf(text.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("detail must be low, medium or high, but was: " + text);
        }
    }

    String word() {
        return name().toLowerCase(Locale.ROOT);
    }
}
