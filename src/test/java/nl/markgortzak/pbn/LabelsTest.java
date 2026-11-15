package nl.markgortzak.pbn;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LabelsTest {

    @Test
    void eachRegionGetsOneLabelWithItsNumber() {
        int w = 40;
        int h = 20;
        int[] index = new int[w * h];
        for (int i = 0; i < index.length; i++) {
            index[i] = (i % w) < 20 ? 0 : 2;
        }

        List<Labels.Label> labels = Labels.place(index, w, h);

        assertEquals(2, labels.size());
        assertEquals(List.of(1, 3), labels.stream().map(Labels.Label::number).sorted().toList());
    }

    @Test
    void theLabelOfASquareSitsAtItsCenter() {
        int w = 21;
        int[] index = new int[w * w];

        Labels.Label label = Labels.place(index, w, w).getFirst();

        assertEquals(10, label.x(), 0.01);
        assertEquals(10, label.y(), 0.01);
        assertEquals(11, label.radius(), 0.01);
    }

    @Test
    void anLShapedRegionPutsItsLabelInTheThickPart() {
        int w = 60;
        int h = 60;
        int[] index = new int[w * h];
        // Region 0 is a thick block on the left and a thin arm along the bottom; label 1 fills the rest.
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                boolean block = x < 30;
                boolean arm = y >= 52;
                index[y * w + x] = block || arm ? 0 : 1;
            }
        }

        Labels.Label label = Labels.place(index, w, h).stream().filter(l -> l.number() == 1).findFirst().orElseThrow();

        assertTrue(label.x() < 30, "the label is at x " + label.x());
        assertEquals(0, index[(int) label.y() * w + (int) label.x()]);
    }

    @Test
    void theTextHeightFitsTheRadiusAndStaysInItsRange() {
        assertEquals(Labels.MIN_HEIGHT, Labels.textHeight("12", 1), 1e-9);
        assertEquals(Labels.MAX_HEIGHT, Labels.textHeight("1", 500), 1e-9);
        double h = Labels.textHeight("12", 14);
        double halfDiagonal = Math.hypot(Glyphs.width("12", h) / 2, h / 2);
        assertEquals(14 * Labels.FILL, halfDiagonal, 0.01);
    }
}
