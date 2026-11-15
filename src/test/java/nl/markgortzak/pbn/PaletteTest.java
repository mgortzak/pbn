package nl.markgortzak.pbn;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaletteTest {

    @Test
    void numbersRunFromOneInLightnessOrderAndSkipUnusedLabels() {
        int[] rgb = {0xFFFFFF, 0x000000, 0x808080, 0xFFFFFF};
        int[] labels = {7, 2, 4, 7};

        Palette palette = Palette.of(labels, Quantizer.toLab(rgb));

        assertEquals(List.of(new Swatch(1, 0x000000), new Swatch(2, 0x808080), new Swatch(3, 0xFFFFFF)), palette.swatches());
        assertArrayEquals(new int[] {2, 0, 1, 2}, palette.index());
    }

    @Test
    void theColorOfASwatchIsTheMeanOfItsPixels() {
        int[] rgb = {0x000000, 0x646464};
        int[] labels = {0, 0};

        Palette palette = Palette.of(labels, Quantizer.toLab(rgb));

        int mean = palette.swatches().getFirst().rgb();
        int gray = mean & 0xFF;
        assertEquals(gray, (mean >> 8) & 0xFF);
        assertTrue(gray > 0 && gray < 0x64, "mean gray " + gray);
    }

    @Test
    void aSwatchWritesItsHexValue() {
        assertEquals("#0A1BFF", new Swatch(3, 0x0A1BFF).hex());
    }
}
