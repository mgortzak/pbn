package nl.markgortzak.pbn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LabTest {

    @Test
    void whiteIsFullLightnessWithNoColor() {
        assertArrayEquals(new double[] {100, 0, 0}, Lab.fromRgb(0xFFFFFF), 0.01);
    }

    @Test
    void blackIsZeroLightness() {
        assertArrayEquals(new double[] {0, 0, 0}, Lab.fromRgb(0x000000), 0.01);
    }

    @Test
    void pureRedHasTheReferenceValues() {
        assertArrayEquals(new double[] {53.24, 80.09, 67.20}, Lab.fromRgb(0xFF0000), 0.05);
    }

    @Test
    void aColorSurvivesTheRoundTrip() {
        for (int rgb : new int[] {0x123456, 0xFF8800, 0x00C0A0, 0x7F7F7F, 0xFFFFFF, 0x000000}) {
            double[] lab = Lab.fromRgb(rgb);
            assertEquals(rgb, Lab.toRgb(lab[0], lab[1], lab[2]), "for " + Integer.toHexString(rgb));
        }
    }

    @Test
    void aColorOutsideTheGamutIsClamped() {
        assertEquals(0xFFFFFF, Lab.toRgb(120, 0, 0));
    }
}
