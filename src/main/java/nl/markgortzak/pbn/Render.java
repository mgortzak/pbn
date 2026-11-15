package nl.markgortzak.pbn;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

/** Draws the three output images. */
final class Render {

    static final int OUTLINE = 0x9A9A9A;
    static final Color NUMBER = new Color(0x404040);

    static final int PAD = 24;
    static final int ROW = 48;
    static final int ROWS_IN_ONE_COLUMN = 15;
    static final int NUMBER_WIDTH = 48;
    static final int SWATCH_W = 96;
    static final int SWATCH_H = 36;
    static final int GAP = 16;
    static final double TEXT = 20;
    static final int COLUMN = NUMBER_WIDTH + GAP + SWATCH_W + GAP + (int) Math.ceil(Glyphs.width("#FFFFFF", TEXT)) + GAP;

    record Point(int x, int y) {
    }

    private Render() {
    }

    /** Image 2: a white canvas, the outlines, and a number in each region. */
    static BufferedImage numbered(int[] index, int w, int h, List<Labels.Label> labels) {
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        boolean[] outline = outline(index, w, h);
        for (int i = 0; i < index.length; i++) {
            image.setRGB(i % w, i / w, outline[i] ? OUTLINE : 0xFFFFFF);
        }
        Graphics2D g = image.createGraphics();
        for (Labels.Label label : labels) {
            Glyphs.drawCentered(g, String.valueOf(label.number()), label.x() + 0.5, label.y() + 0.5, label.textHeight(), NUMBER);
        }
        g.dispose();
        return image;
    }

    /** Image 4: each region filled with its palette color, with a darker outline. */
    static BufferedImage preview(int[] index, int w, int h, List<Swatch> swatches) {
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        boolean[] outline = outline(index, w, h);
        for (int i = 0; i < index.length; i++) {
            int rgb = swatches.get(index[i]).rgb();
            image.setRGB(i % w, i / w, outline[i] ? darker(rgb) : rgb);
        }
        return image;
    }

    /** Image 3: one row for each swatch, with its number, its color and its hex value. */
    static BufferedImage legend(List<Swatch> swatches) {
        int n = swatches.size();
        int columns = columns(n);
        int rows = rowsInColumn(n);
        BufferedImage image = new BufferedImage(2 * PAD + columns * COLUMN, 2 * PAD + rows * ROW, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        for (int k = 0; k < n; k++) {
            Swatch swatch = swatches.get(k);
            int left = PAD + (k / rows) * COLUMN;
            int top = PAD + (k % rows) * ROW;
            double middle = top + ROW / 2.0;
            String number = String.valueOf(swatch.number());
            Glyphs.draw(g, number, left + NUMBER_WIDTH - Glyphs.width(number, TEXT), middle - TEXT / 2, TEXT, NUMBER);
            int swatchLeft = left + NUMBER_WIDTH + GAP;
            int swatchTop = (int) Math.round(middle - SWATCH_H / 2.0);
            g.setColor(new Color(swatch.rgb()));
            g.fillRect(swatchLeft, swatchTop, SWATCH_W, SWATCH_H);
            g.setColor(new Color(OUTLINE));
            g.drawRect(swatchLeft, swatchTop, SWATCH_W - 1, SWATCH_H - 1);
            Glyphs.draw(g, swatch.hex(), swatchLeft + SWATCH_W + GAP, middle - TEXT / 2, TEXT, NUMBER);
        }
        g.dispose();
        return image;
    }

    /** The center of the swatch in row {@code k} of a legend that holds {@code n} swatches. */
    static Point swatchCenter(int k, int n) {
        int rows = rowsInColumn(n);
        int left = PAD + (k / rows) * COLUMN + NUMBER_WIDTH + GAP;
        int top = PAD + (k % rows) * ROW;
        return new Point(left + SWATCH_W / 2, top + ROW / 2);
    }

    static int darker(int rgb) {
        int r = ((rgb >> 16) & 0xFF) * 3 / 5;
        int g = ((rgb >> 8) & 0xFF) * 3 / 5;
        int b = (rgb & 0xFF) * 3 / 5;
        return r << 16 | g << 8 | b;
    }

    /** A pixel is on the outline when its right or lower neighbor has another index, so each border is 1 px wide. */
    static boolean[] outline(int[] index, int w, int h) {
        boolean[] outline = new boolean[index.length];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int i = y * w + x;
                outline[i] = (x < w - 1 && index[i + 1] != index[i]) || (y < h - 1 && index[i + w] != index[i]);
            }
        }
        return outline;
    }

    private static int columns(int n) {
        return Math.max(1, (n + ROWS_IN_ONE_COLUMN - 1) / ROWS_IN_ONE_COLUMN);
    }

    private static int rowsInColumn(int n) {
        return (n + columns(n) - 1) / columns(n);
    }
}
