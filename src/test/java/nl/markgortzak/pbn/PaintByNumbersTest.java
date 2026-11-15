package nl.markgortzak.pbn;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaintByNumbersTest {

    @Test
    void threeNoisyBlocksGiveThreeColorsAndThreeRegions() {
        BufferedImage image = blocks(240, 120, new Random(2));

        PaintByNumbers.Result result = PaintByNumbers.make(image, 3, Detail.MEDIUM);

        assertEquals(3, result.swatches().size());
        assertEquals(3, result.regions());
        assertEquals(1600, result.numbered().getWidth());
        assertEquals(800, result.preview().getHeight());
        assertEquals(1600, result.preview().getWidth());
    }

    @Test
    void thePaletteNeverHoldsMoreColorsThanRequested() {
        BufferedImage image = noise(200, 150, new Random(9));

        PaintByNumbers.Result result = PaintByNumbers.make(image, 10, Detail.MEDIUM);

        assertTrue(result.swatches().size() <= 10);
        assertTrue(result.swatches().size() >= 2);
    }

    @Test
    void theSameImageGivesTheSameResult() {
        BufferedImage image = noise(160, 120, new Random(1));

        assertEquals(PaintByNumbers.make(image, 12, Detail.HIGH).swatches(), PaintByNumbers.make(image, 12, Detail.HIGH).swatches());
    }

    @Test
    void aLargeImageIsScaledDownToTheWorkingSize() {
        PaintByNumbers.Pixels scaled = PaintByNumbers.working(new int[3200 * 400], 3200, 400, 1600);

        assertEquals(1600, scaled.w());
        assertEquals(200, scaled.h());
    }

    @Test
    void aSmallImageIsScaledUpToTheWorkingSize() {
        PaintByNumbers.Pixels scaled = PaintByNumbers.working(new int[265 * 376], 265, 376, 2400);

        assertEquals(1691, scaled.w());
        assertEquals(2400, scaled.h());
    }

    @Test
    void scalingDownAveragesTheSourcePixels() {
        int[] rgb = new int[3200 * 2];
        for (int i = 0; i < rgb.length; i++) {
            rgb[i] = i % 2 == 0 ? 0x000000 : 0xC8C8C8;
        }

        assertEquals(0x646464, PaintByNumbers.working(rgb, 3200, 2, 1600).rgb()[10]);
    }

    @Test
    void scalingUpRepeatsEachPixelAndMakesNoNewColor() {
        int[] rgb = {0x000000, 0xC8C8C8};

        PaintByNumbers.Pixels scaled = PaintByNumbers.working(rgb, 2, 1, 4);

        assertEquals(2, scaled.h());
        int[] row = {0x000000, 0x000000, 0xC8C8C8, 0xC8C8C8};
        assertArrayEquals(new int[] {row[0], row[1], row[2], row[3], row[0], row[1], row[2], row[3]}, scaled.rgb());
    }

    @Test
    void aTransparentPixelCountsAsWhite() {
        BufferedImage image = new BufferedImage(4, 4, BufferedImage.TYPE_INT_ARGB);

        int[] rgb = PaintByNumbers.pixels(image);

        assertEquals(0xFFFFFF, rgb[0]);
    }

    @Test
    void aColorCountOutsideTheRangeIsRefused() {
        BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);

        assertThrows(IllegalArgumentException.class, () -> PaintByNumbers.make(image, 1, Detail.MEDIUM));
        assertThrows(IllegalArgumentException.class, () -> PaintByNumbers.make(image, 51, Detail.MEDIUM));
    }

    static BufferedImage blocks(int w, int h, Random random) {
        int[] colors = {0xC03020, 0x2050C0, 0xF0E060};
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int base = colors[x * 3 / w];
                image.setRGB(x, y, jitter(base, random));
            }
        }
        return image;
    }

    private static BufferedImage noise(int w, int h, Random random) {
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                image.setRGB(x, y, random.nextInt(0x1000000));
            }
        }
        return image;
    }

    private static int jitter(int rgb, Random random) {
        int out = 0;
        for (int shift = 16; shift >= 0; shift -= 8) {
            int c = ((rgb >> shift) & 0xFF) + random.nextInt(21) - 10;
            out |= Math.max(0, Math.min(255, c)) << shift;
        }
        return out;
    }
}
