package nl.markgortzak.pbn;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.Map;

/**
 * Draws {@code 0-9}, {@code A-F} and {@code #} as strokes.
 *
 * <p>The program draws its own characters, because AWT text needs a font, and a minimal Linux or
 * WSL installation can have none. Each glyph sits on a grid 4 wide and 6 high.
 */
final class Glyphs {

    private static final double GRID_W = 4;
    private static final double GRID_H = 6;

    private static final Map<Character, double[][]> STROKES = Map.ofEntries(
            Map.entry('0', new double[][] {{1, 0, 3, 0, 4, 1, 4, 5, 3, 6, 1, 6, 0, 5, 0, 1, 1, 0}}),
            Map.entry('1', new double[][] {{1, 1, 2, 0, 2, 6}, {1, 6, 3, 6}}),
            Map.entry('2', new double[][] {{0, 1, 1, 0, 3, 0, 4, 1, 4, 2, 0, 6, 4, 6}}),
            Map.entry('3', new double[][] {{0, 0, 4, 0, 2, 2.5, 3, 2.5, 4, 3.5, 4, 5, 3, 6, 1, 6, 0, 5}}),
            Map.entry('4', new double[][] {{3, 6, 3, 0, 0, 4, 4, 4}}),
            Map.entry('5', new double[][] {{4, 0, 0, 0, 0, 2.5, 3, 2.5, 4, 3.5, 4, 5, 3, 6, 0, 6}}),
            Map.entry('6', new double[][] {{3, 0, 1, 0, 0, 1, 0, 5, 1, 6, 3, 6, 4, 5, 4, 3.5, 3, 2.5, 0, 2.5}}),
            Map.entry('7', new double[][] {{0, 0, 4, 0, 1.5, 6}}),
            Map.entry('8', new double[][] {{1, 0, 3, 0, 4, 1, 4, 2, 3, 3, 1, 3, 0, 4, 0, 5, 1, 6, 3, 6, 4, 5, 4, 4, 3, 3, 1, 3, 0, 2, 0, 1, 1, 0}}),
            Map.entry('9', new double[][] {{4, 3.5, 1, 3.5, 0, 2.5, 0, 1, 1, 0, 3, 0, 4, 1, 4, 5, 3, 6, 1, 6}}),
            Map.entry('A', new double[][] {{0, 6, 0, 2, 2, 0, 4, 2, 4, 6}, {0, 3.5, 4, 3.5}}),
            Map.entry('B', new double[][] {{0, 0, 0, 6, 3, 6, 4, 5, 4, 4, 3, 3, 0, 3}, {0, 0, 3, 0, 4, 1, 4, 2, 3, 3}}),
            Map.entry('C', new double[][] {{4, 1, 3, 0, 1, 0, 0, 1, 0, 5, 1, 6, 3, 6, 4, 5}}),
            Map.entry('D', new double[][] {{0, 0, 0, 6, 2.5, 6, 4, 4.5, 4, 1.5, 2.5, 0, 0, 0}}),
            Map.entry('E', new double[][] {{4, 0, 0, 0, 0, 6, 4, 6}, {0, 3, 3, 3}}),
            Map.entry('F', new double[][] {{4, 0, 0, 0, 0, 6}, {0, 3, 3, 3}}),
            Map.entry('#', new double[][] {{1.3, 0.5, 0.7, 5.5}, {3.3, 0.5, 2.7, 5.5}, {0, 2, 4, 2}, {0, 4, 4, 4}}));

    private Glyphs() {
    }

    static double width(String text, double height) {
        return text.length() * charWidth(height) + Math.max(0, text.length() - 1) * gap(height);
    }

    static double gap(double height) {
        return height * 0.25;
    }

    /** The height is the height of the ink; the stroke is drawn inside it. */
    static void drawCentered(Graphics2D g, String text, double cx, double cy, double height, Color color) {
        draw(g, text, cx - width(text, height) / 2, cy - height / 2, height, color);
    }

    static void draw(Graphics2D g, String text, double left, double top, double height, Color color) {
        double stroke = Math.max(1, height / 9);
        double inner = height - stroke;
        double scale = inner / GRID_H;
        Path2D path = new Path2D.Double();
        double x = left + stroke / 2;
        for (char ch : text.toCharArray()) {
            double[][] strokes = STROKES.get(ch);
            if (strokes == null) {
                throw new IllegalArgumentException("no glyph for " + ch);
            }
            AffineTransform at = new AffineTransform(scale, 0, 0, scale, x, top + stroke / 2);
            for (double[] line : strokes) {
                Path2D one = new Path2D.Double();
                one.moveTo(line[0], line[1]);
                for (int p = 2; p < line.length; p += 2) {
                    one.lineTo(line[p], line[p + 1]);
                }
                path.append(at.createTransformedShape(one), false);
            }
            x += charWidth(height) + gap(height);
        }
        Object antialias = g.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(color);
        g.setStroke(new BasicStroke((float) stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.draw(path);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antialias == null ? RenderingHints.VALUE_ANTIALIAS_DEFAULT : antialias);
    }

    private static double charWidth(double height) {
        double stroke = Math.max(1, height / 9);
        return (height - stroke) * GRID_W / GRID_H + stroke;
    }
}
