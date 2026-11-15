package nl.markgortzak.pbn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DetailTest {

    @Test
    void theMinimumAreaHasAFloor() {
        assertEquals(64, Detail.MEDIUM.minArea(100 * 100));
        assertEquals(341, Detail.MEDIUM.minArea(1600 * 1067));
        assertEquals(16, Detail.HIGH.minArea(100 * 100));
    }

    @Test
    void moreDetailKeepsSmallerAndNarrowerRegions() {
        int pixels = 2400 * 1600;
        assertTrue(Detail.HIGH.minArea(pixels) < Detail.MEDIUM.minArea(pixels));
        assertTrue(Detail.MEDIUM.minArea(pixels) < Detail.LOW.minArea(pixels));
        assertTrue(Detail.HIGH.minRadius < Detail.MEDIUM.minRadius);
        assertTrue(Detail.MEDIUM.minRadius < Detail.LOW.minRadius);
        assertTrue(Detail.HIGH.workingSize > Detail.MEDIUM.workingSize);
    }

    @Test
    void readsTheWordInAnyCase() {
        assertEquals(Detail.HIGH, Detail.parse("High"));
        assertEquals(Detail.LOW, Detail.parse(" low "));
    }

    @Test
    void refusesAnUnknownWord() {
        IllegalArgumentException refused = assertThrows(IllegalArgumentException.class, () -> Detail.parse("max"));

        assertEquals("detail must be low, medium or high, but was: max", refused.getMessage());
    }
}
