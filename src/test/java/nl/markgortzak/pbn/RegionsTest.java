package nl.markgortzak.pbn;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegionsTest {

    @Test
    void theMinimumAreaHasAFloor() {
        assertEquals(64, Regions.minArea(100 * 100));
        assertEquals(384, Regions.minArea(1600 * 1200));
    }

    @Test
    void componentsCountsEachConnectedArea() {
        int[] labels = {
                0, 0, 1, 1,
                0, 0, 1, 1,
                1, 1, 0, 0};

        Regions.Components components = Regions.components(labels, 4, 3);

        // The two areas of label 1 touch only at a corner, and so do the two areas of label 0.
        assertEquals(4, components.count());
        assertEquals(4, components.size()[components.region()[0]]);
        assertEquals(4, components.size()[components.region()[2]]);
        assertEquals(2, components.size()[components.region()[8]]);
        assertEquals(2, components.size()[components.region()[10]]);
    }

    @Test
    void theMajorityFilterRemovesALonePixel() {
        int[] labels = new int[25];
        labels[12] = 1;

        int[] smoothed = Regions.smooth(labels, 5, 5);

        assertEquals(0, smoothed[12]);
    }

    @Test
    void aSpeckInsideALargeRegionMergesIntoThatRegion() {
        int w = 40;
        int h = 40;
        int[] labels = new int[w * h];
        for (int y = 18; y < 22; y++) {
            for (int x = 18; x < 22; x++) {
                labels[y * w + x] = 1;
            }
        }

        int[] cleaned = Regions.clean(labels, w, h, 64);

        for (int label : cleaned) {
            assertEquals(0, label);
        }
    }

    @Test
    void twoLargeRegionsStay() {
        int w = 40;
        int h = 40;
        int[] labels = new int[w * h];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = (i % w) < 20 ? 0 : 1;
        }

        int[] cleaned = Regions.clean(labels, w, h, 64);

        assertEquals(2, Regions.components(cleaned, w, h).count());
        assertEquals(0, cleaned[0]);
        assertEquals(1, cleaned[w - 1]);
    }

    @Test
    void aSmallRegionMergesIntoTheNeighborWithTheLongestBorder() {
        int w = 30;
        int h = 20;
        int[] labels = new int[w * h];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                labels[y * w + x] = x < 10 ? 0 : x < 13 ? 2 : 1;
            }
        }
        // The strip of label 2 has a border of 20 px on each side; this notch makes the right border 21 px.
        labels[12] = 1;

        int[] cleaned = Regions.clean(labels, w, h, 64);

        assertEquals(1, cleaned[5 * w + 11]);
    }

    @Test
    void aLongThinStripMergesAlthoughItsAreaIsLargeEnough() {
        int w = 100;
        int h = 100;
        int[] labels = new int[w * h];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                labels[y * w + x] = x < 50 ? 0 : x < 52 ? 2 : 1;
            }
        }

        int[] cleaned = Regions.clean(labels, w, h, 64);

        assertEquals(2, Regions.components(cleaned, w, h).count());
        for (int y = 0; y < h; y++) {
            assertTrue(cleaned[y * w + 50] != 2 && cleaned[y * w + 51] != 2, "the strip stays at row " + y);
        }
    }

    @Test
    void theRadiusOfASquareIsHalfItsSide() {
        int w = 21;
        int[] labels = new int[w * w];

        int[] distance = Regions.chamfer(labels, w, w);

        assertEquals(33, distance[10 * w + 10]);
        assertEquals(3, distance[0]);
    }

    @Test
    void noiseLeavesNoRegionBelowTheMinimumArea() {
        int w = 120;
        int h = 90;
        Random random = new Random(4);
        int[] labels = new int[w * h];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = random.nextInt(6);
        }

        int[] cleaned = Regions.clean(labels, w, h, 64);

        Regions.Components components = Regions.components(cleaned, w, h);
        for (int r = 0; r < components.count(); r++) {
            assertTrue(components.size()[r] >= 64, "region " + r + " has " + components.size()[r] + " px");
        }
    }
}
