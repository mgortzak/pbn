package nl.markgortzak.pbn;

import java.awt.image.BufferedImage;
import java.util.List;

/** The whole pipeline: scale, blur, quantize, clean, number, and draw. It does no file I/O. */
public final class PaintByNumbers {

    public static final int MIN_COLORS = 2;
    public static final int MAX_COLORS = 50;
    public static final int DEFAULT_COLORS = 16;

    static final long SEED = 1;

    /** {@code regions} is the count of areas to paint, which is more than the count of colors. */
    public record Result(BufferedImage numbered, BufferedImage legend, BufferedImage preview, List<Swatch> swatches, int regions) {
    }

    /** Packed {@code 0xRRGGBB} pixels, row by row. */
    record Pixels(int[] rgb, int w, int h) {
    }

    private PaintByNumbers() {
    }

    public static Result make(BufferedImage source, int colors, Detail detail) {
        if (colors < MIN_COLORS || colors > MAX_COLORS) {
            throw new IllegalArgumentException("colors must be from " + MIN_COLORS + " to " + MAX_COLORS + ", but was " + colors + ".");
        }
        Pixels image = working(pixels(source), source.getWidth(), source.getHeight(), detail.workingSize);
        int w = image.w();
        int h = image.h();
        float[] lab = Quantizer.toLab(blur(image.rgb(), w, h));
        int[] labels = Quantizer.assign(lab, Quantizer.fit(lab, colors, SEED));
        int[] cleaned = Regions.clean(labels, w, h, detail);
        Palette palette = Palette.of(cleaned, lab);
        List<Labels.Label> labelList = Labels.place(palette.index(), w, h);
        return new Result(Render.numbered(palette.index(), w, h, labelList), Render.legend(palette.swatches()),
                Render.preview(palette.index(), w, h, palette.swatches()), palette.swatches(), labelList.size());
    }

    /**
     * Scales the image so that its long side is {@code size}. A small image is scaled up too,
     * because every limit of {@link Detail} is in pixels of the working image: a small image kept
     * at its own size loses most of its regions, and its numbers have no space.
     */
    static Pixels working(int[] rgb, int sw, int sh, int size) {
        int longSide = Math.max(sw, sh);
        if (longSide == size) {
            return new Pixels(rgb, sw, sh);
        }
        int tw = Math.max(1, (int) Math.round((double) sw * size / longSide));
        int th = Math.max(1, (int) Math.round((double) sh * size / longSide));
        return longSide > size ? areaAverage(rgb, sw, sh, tw, th) : nearest(rgb, sw, sh, tw, th);
    }

    private static Pixels areaAverage(int[] rgb, int sw, int sh, int tw, int th) {
        int[] out = new int[tw * th];
        for (int ty = 0; ty < th; ty++) {
            int y0 = (int) ((long) ty * sh / th);
            int y1 = Math.max(y0 + 1, (int) ((long) (ty + 1) * sh / th));
            for (int tx = 0; tx < tw; tx++) {
                int x0 = (int) ((long) tx * sw / tw);
                int x1 = Math.max(x0 + 1, (int) ((long) (tx + 1) * sw / tw));
                long r = 0;
                long g = 0;
                long b = 0;
                for (int y = y0; y < y1; y++) {
                    for (int x = x0; x < x1; x++) {
                        int p = rgb[y * sw + x];
                        r += (p >> 16) & 0xFF;
                        g += (p >> 8) & 0xFF;
                        b += p & 0xFF;
                    }
                }
                long n = (long) (y1 - y0) * (x1 - x0);
                out[ty * tw + tx] = (int) (r / n) << 16 | (int) (g / n) << 8 | (int) (b / n);
            }
        }
        return new Pixels(out, tw, th);
    }

    /**
     * Repeats each source pixel. Interpolation would turn each sharp edge into a gradient, and the
     * steps of that gradient would become false colors and false regions.
     */
    private static Pixels nearest(int[] rgb, int sw, int sh, int tw, int th) {
        int[] out = new int[tw * th];
        for (int ty = 0; ty < th; ty++) {
            int sy = (int) ((long) ty * sh / th);
            for (int tx = 0; tx < tw; tx++) {
                out[ty * tw + tx] = rgb[sy * sw + (int) ((long) tx * sw / tw)];
            }
        }
        return new Pixels(out, tw, th);
    }

    /** The pixels as {@code 0xRRGGBB}, with each transparent part laid over white. */
    static int[] pixels(BufferedImage image) {
        int w = image.getWidth();
        int[] argb = image.getRGB(0, 0, w, image.getHeight(), null, 0, w);
        for (int i = 0; i < argb.length; i++) {
            int a = (argb[i] >>> 24);
            if (a == 255) {
                argb[i] &= 0xFFFFFF;
                continue;
            }
            int out = 0;
            for (int shift = 16; shift >= 0; shift -= 8) {
                int c = (argb[i] >> shift) & 0xFF;
                out |= ((c * a + 255 * (255 - a)) / 255) << shift;
            }
            argb[i] = out;
        }
        return argb;
    }

    /** A 3×3 box blur, so the noise of a JPEG does not become small regions. */
    private static int[] blur(int[] rgb, int w, int h) {
        int[] out = new int[rgb.length];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int r = 0;
                int g = 0;
                int b = 0;
                int n = 0;
                for (int ny = Math.max(0, y - 1); ny <= Math.min(h - 1, y + 1); ny++) {
                    for (int nx = Math.max(0, x - 1); nx <= Math.min(w - 1, x + 1); nx++) {
                        int p = rgb[ny * w + nx];
                        r += (p >> 16) & 0xFF;
                        g += (p >> 8) & 0xFF;
                        b += p & 0xFF;
                        n++;
                    }
                }
                out[y * w + x] = (r / n) << 16 | (g / n) << 8 | (b / n);
            }
        }
        return out;
    }
}
