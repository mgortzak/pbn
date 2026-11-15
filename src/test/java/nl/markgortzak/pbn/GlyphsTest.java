package nl.markgortzak.pbn;

import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GlyphsTest {

    @Test
    void theWidthGrowsWithEachCharacter() {
        assertEquals(Glyphs.width("1", 30) * 2 + Glyphs.gap(30), Glyphs.width("11", 30), 1e-9);
    }

    @Test
    void everySupportedCharacterDrawsInkInsideItsBox() {
        for (char ch : "0123456789ABCDEF#".toCharArray()) {
            BufferedImage image = white(60, 60);
            Graphics2D g = image.createGraphics();
            Glyphs.drawCentered(g, String.valueOf(ch), 30, 30, 30, Color.BLACK);
            g.dispose();

            int ink = 0;
            for (int y = 0; y < 60; y++) {
                for (int x = 0; x < 60; x++) {
                    if ((image.getRGB(x, y) & 0xFF) < 128) {
                        ink++;
                        assertTrue(x >= 15 && x <= 45 && y >= 13 && y <= 47, ch + " has ink at " + x + "," + y);
                    }
                }
            }
            assertTrue(ink > 20, ch + " drew only " + ink + " px");
        }
    }

    @Test
    void anUnknownCharacterIsRefused() {
        Graphics2D g = white(10, 10).createGraphics();

        assertThrows(IllegalArgumentException.class, () -> Glyphs.drawCentered(g, "Z", 5, 5, 8, Color.BLACK));
    }

    static BufferedImage white(int w, int h) {
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, w, h);
        g.dispose();
        return image;
    }
}
