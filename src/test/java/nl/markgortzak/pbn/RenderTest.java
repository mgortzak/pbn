package nl.markgortzak.pbn;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RenderTest {

    private static final int W = 30;
    private static final int H = 20;

    /** Left half is index 0, right half is index 1. */
    private static int[] halves() {
        int[] index = new int[W * H];
        for (int i = 0; i < index.length; i++) {
            index[i] = (i % W) < 15 ? 0 : 1;
        }
        return index;
    }

    @Test
    void theOutlineIsExactlyWhereTheIndexChanges() {
        int[] index = halves();

        BufferedImage numbered = Render.numbered(index, W, H, List.of());

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                int expected = x == 14 ? Render.OUTLINE : 0xFFFFFF;
                assertEquals(expected, numbered.getRGB(x, y) & 0xFFFFFF, "at " + x + "," + y);
            }
        }
    }

    @Test
    void theNumberedImageDrawsEachLabel() {
        int[] index = halves();

        BufferedImage numbered = Render.numbered(index, W, H, Labels.place(index, W, H));

        assertTrue(dark(numbered, 0, 13), "no number in the left half");
        assertTrue(dark(numbered, 16, 29), "no number in the right half");
    }

    @Test
    void eachPreviewPixelHasItsPaletteColorOrADarkerOutline() {
        int[] index = halves();
        List<Swatch> swatches = List.of(new Swatch(1, 0x204080), new Swatch(2, 0xE0C040));

        BufferedImage preview = Render.preview(index, W, H, swatches);

        assertEquals(0x204080, preview.getRGB(3, 3) & 0xFFFFFF);
        assertEquals(0xE0C040, preview.getRGB(20, 3) & 0xFFFFFF);
        assertEquals(Render.darker(0x204080), preview.getRGB(14, 3) & 0xFFFFFF);
    }

    @Test
    void theLegendHasOneRowForEachSwatch() {
        List<Swatch> swatches = swatches(5);

        BufferedImage legend = Render.legend(swatches);

        assertEquals(2 * Render.PAD + 5 * Render.ROW, legend.getHeight());
        for (int row = 0; row < 5; row++) {
            Render.Point p = Render.swatchCenter(row, 5);
            assertEquals(swatches.get(row).rgb(), legend.getRGB(p.x(), p.y()) & 0xFFFFFF, "row " + row);
        }
    }

    @Test
    void moreThanFifteenSwatchesUseTwoColumns() {
        List<Swatch> swatches = swatches(20);

        BufferedImage legend = Render.legend(swatches);

        assertEquals(2 * Render.PAD + 10 * Render.ROW, legend.getHeight());
        assertEquals(2 * Render.COLUMN + 2 * Render.PAD, legend.getWidth());
        Render.Point last = Render.swatchCenter(19, 20);
        assertTrue(last.x() > Render.COLUMN);
        assertEquals(swatches.get(19).rgb(), legend.getRGB(last.x(), last.y()) & 0xFFFFFF);
    }

    @Test
    void fiftySwatchesUseFourColumns() {
        List<Swatch> swatches = swatches(50);

        BufferedImage legend = Render.legend(swatches);

        assertEquals(2 * Render.PAD + 13 * Render.ROW, legend.getHeight());
        assertEquals(4 * Render.COLUMN + 2 * Render.PAD, legend.getWidth());
        Render.Point last = Render.swatchCenter(49, 50);
        assertEquals(swatches.get(49).rgb(), legend.getRGB(last.x(), last.y()) & 0xFFFFFF);
    }

    private static List<Swatch> swatches(int n) {
        List<Swatch> swatches = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            swatches.add(new Swatch(i + 1, (i * 37 % 256) << 16 | (i * 91 % 256) << 8 | (i * 53 % 256)));
        }
        return swatches;
    }

    private static boolean dark(BufferedImage image, int fromX, int toX) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = fromX; x <= toX; x++) {
                if ((image.getRGB(x, y) & 0xFF) < 0x80) {
                    return true;
                }
            }
        }
        return false;
    }
}
