package nl.markgortzak.pbn;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaintByNumbersTest {

    @Test
    void threeNoisyBlocksGiveThreeColorsAndThreeRegions() {
        BufferedImage image = blocks(240, 120, new Random(2));

        PaintByNumbers.Result result = PaintByNumbers.make(image, 3);

        assertEquals(3, result.swatches().size());
        assertEquals(3, result.regions());
        assertEquals(240, result.numbered().getWidth());
        assertEquals(120, result.preview().getHeight());
        assertEquals(240, result.preview().getWidth());
    }

    @Test
    void thePaletteNeverHoldsMoreColorsThanRequested() {
        BufferedImage image = noise(200, 150, new Random(9));

        PaintByNumbers.Result result = PaintByNumbers.make(image, 10);

        assertTrue(result.swatches().size() <= 10);
        assertTrue(result.swatches().size() >= 2);
    }

    @Test
    void theSameImageGivesTheSameResult() {
        BufferedImage image = noise(160, 120, new Random(1));

        assertEquals(PaintByNumbers.make(image, 12).swatches(), PaintByNumbers.make(image, 12).swatches());
    }

    @Test
    void aLargeImageIsScaledToTheWorkingSize() {
        BufferedImage image = new BufferedImage(3200, 400, BufferedImage.TYPE_INT_RGB);

        BufferedImage scaled = PaintByNumbers.workingImage(image);

        assertEquals(PaintByNumbers.WORKING_SIZE, scaled.getWidth());
        assertEquals(200, scaled.getHeight());
    }

    @Test
    void scalingAveragesTheSourcePixels() {
        BufferedImage image = new BufferedImage(3200, 2, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < 3200; x++) {
            image.setRGB(x, 0, x % 2 == 0 ? 0x000000 : 0xC8C8C8);
            image.setRGB(x, 1, x % 2 == 0 ? 0x000000 : 0xC8C8C8);
        }

        int rgb = PaintByNumbers.workingImage(image).getRGB(10, 0) & 0xFFFFFF;

        assertEquals(0x646464, rgb);
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

        assertThrows(IllegalArgumentException.class, () -> PaintByNumbers.make(image, 1));
        assertThrows(IllegalArgumentException.class, () -> PaintByNumbers.make(image, 31));
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
