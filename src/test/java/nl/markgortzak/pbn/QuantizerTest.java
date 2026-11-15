package nl.markgortzak.pbn;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuantizerTest {

    private static final int[] FOUR = {0xFF0000, 0x00FF00, 0x0000FF, 0xFFFF00};

    @Test
    void fourFlatColorsGiveBackThoseFourColors() {
        float[] lab = Quantizer.toLab(pixels(FOUR, 4000));

        float[] centers = Quantizer.fit(lab, 4, 1);

        Set<Integer> found = new HashSet<>();
        for (int c = 0; c < centers.length / 3; c++) {
            found.add(Lab.toRgb(centers[3 * c], centers[3 * c + 1], centers[3 * c + 2]));
        }
        assertEquals(Set.of(0xFF0000, 0x00FF00, 0x0000FF, 0xFFFF00), found);
    }

    @Test
    void theSameSeedGivesTheSameCenters() {
        float[] lab = Quantizer.toLab(noise(20_000, 7));

        assertArrayEquals(Quantizer.fit(lab, 12, 1), Quantizer.fit(lab, 12, 1));
    }

    @Test
    void neverGivesMoreCentersThanRequested() {
        float[] lab = Quantizer.toLab(noise(20_000, 3));

        assertEquals(10, Quantizer.fit(lab, 10, 1).length / 3);
    }

    @Test
    void twoColorsGiveAtMostTwoCentersWhenTenAreRequested() {
        float[] lab = Quantizer.toLab(pixels(new int[] {0x000000, 0xFFFFFF}, 1000));

        assertEquals(2, Quantizer.fit(lab, 10, 1).length / 3);
    }

    @Test
    void eachPixelGoesToItsNearestCenter() {
        int[] rgb = pixels(FOUR, 400);
        float[] lab = Quantizer.toLab(rgb);
        float[] centers = Quantizer.fit(lab, 4, 1);

        int[] labels = Quantizer.assign(lab, centers);

        for (int i = 0; i < rgb.length; i++) {
            int c = labels[i];
            assertEquals(rgb[i], Lab.toRgb(centers[3 * c], centers[3 * c + 1], centers[3 * c + 2]));
        }
    }

    @Test
    void aLargeImageIsFittedOnASample() {
        float[] lab = Quantizer.toLab(noise(300_000, 5));

        long start = System.nanoTime();
        float[] centers = Quantizer.fit(lab, 30, 1);

        assertEquals(30, centers.length / 3);
        assertTrue(System.nanoTime() - start < 10_000_000_000L, "the fit took too long");
    }

    /** Each color repeated {@code each} times, interleaved. */
    private static int[] pixels(int[] colors, int each) {
        int[] rgb = new int[colors.length * each];
        for (int i = 0; i < rgb.length; i++) {
            rgb[i] = colors[i % colors.length];
        }
        return rgb;
    }

    private static int[] noise(int count, long seed) {
        Random random = new Random(seed);
        int[] rgb = new int[count];
        Arrays.setAll(rgb, i -> random.nextInt(0x1000000));
        return rgb;
    }
}
