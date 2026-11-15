package nl.markgortzak.pbn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @TempDir
    Path folder;

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();

    private int run(String... args) {
        return Main.run(args, new PrintStream(out, true, StandardCharsets.UTF_8), new PrintStream(err, true, StandardCharsets.UTF_8));
    }

    @Test
    void noArgumentPrintsTheUsageAndFails() {
        assertEquals(1, run());
        assertEquals(Main.USAGE + "\n", err.toString(StandardCharsets.UTF_8));
    }

    @Test
    void tooManyArgumentsPrintTheUsageAndFail() {
        assertEquals(1, run("a.png", "12", "extra"));
        assertEquals(Main.USAGE + "\n", err.toString(StandardCharsets.UTF_8));
    }

    @Test
    void aColorCountThatIsNotANumberFails() {
        assertEquals(1, run("a.png", "many"));
        assertEquals("colors must be a whole number from 2 to 30, but was: many\n", err.toString(StandardCharsets.UTF_8));
    }

    @Test
    void aColorCountOutsideTheRangeFails() {
        assertEquals(1, run("a.png", "40"));
        assertEquals("colors must be a whole number from 2 to 30, but was: 40\n", err.toString(StandardCharsets.UTF_8));
    }

    @Test
    void aMissingFileFailsWithOneLine() {
        assertEquals(1, run(folder.resolve("none.png").toString()));
        String message = err.toString(StandardCharsets.UTF_8);
        assertTrue(message.startsWith("The file does not exist: "), message);
        assertEquals(1, message.lines().count());
    }

    @Test
    void writesTheThreeImagesAndPrintsThePalette() throws IOException {
        Path photo = folder.resolve("photo.jpg");
        ImageIO.write(PaintByNumbersTest.blocks(240, 120, new Random(3)), "jpg", photo.toFile());

        assertEquals(0, run(photo.toString(), "3"));

        assertEquals(240, ImageIO.read(folder.resolve("photo-numbers.png").toFile()).getWidth());
        assertEquals(240, ImageIO.read(folder.resolve("photo-preview.png").toFile()).getWidth());
        assertTrue(Files.size(folder.resolve("photo-legend.png")) > 0);
        String printed = out.toString(StandardCharsets.UTF_8);
        assertTrue(printed.contains("3 colors, 3 regions"), printed);
        assertEquals(3, printed.lines().filter(line -> line.matches("\\s*\\d+ #[0-9A-F]{6}")).count(), printed);
    }

    @Test
    void saysSoWhenItUsesFewerColorsThanRequested() throws IOException {
        Path photo = folder.resolve("flat.png");
        BufferedImage flat = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 100; x++) {
                flat.setRGB(x, y, x < 50 ? 0x000000 : 0xFFFFFF);
            }
        }
        ImageIO.write(flat, "png", photo.toFile());

        assertEquals(0, run(photo.toString(), "8"));

        String printed = out.toString(StandardCharsets.UTF_8);
        assertTrue(printed.contains("2 colors, 2 regions (of the 8 requested"), printed);
    }

    @Test
    void theOutputNameDropsOnlyTheLastExtension() {
        assertEquals(Path.of("/a/my.photo-numbers.png"), Main.sibling(Path.of("/a/my.photo.jpg"), "numbers"));
        assertEquals(Path.of("/a/scan-legend.png"), Main.sibling(Path.of("/a/scan"), "legend"));
    }
}
