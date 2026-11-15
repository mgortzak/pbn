package nl.markgortzak.pbn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImageInputTest {

    @TempDir
    Path folder;

    @Test
    void readsAPng() throws Exception {
        Path file = folder.resolve("a.png");
        ImageIO.write(new BufferedImage(7, 5, BufferedImage.TYPE_INT_RGB), "png", file.toFile());

        BufferedImage image = ImageInput.read(file);

        assertEquals(7, image.getWidth());
        assertEquals(5, image.getHeight());
    }

    @Test
    void refusesAMissingFile() {
        ImageInput.Refused refused = assertThrows(ImageInput.Refused.class, () -> ImageInput.read(folder.resolve("none.png")));

        assertTrue(refused.getMessage().startsWith("The file does not exist: "), refused.getMessage());
    }

    @Test
    void refusesAFileThatIsNotAnImage() throws IOException {
        Path file = Files.writeString(folder.resolve("notes.png"), "hello");

        ImageInput.Refused refused = assertThrows(ImageInput.Refused.class, () -> ImageInput.read(file));

        assertTrue(refused.getMessage().startsWith("The format is not PNG, JPEG, BMP or GIF: "), refused.getMessage());
    }

    @Test
    void refusesAFileAboveTheByteCap() throws IOException {
        Path file = folder.resolve("a.png");
        ImageIO.write(new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB), "png", file.toFile());

        ImageInput.Refused refused = assertThrows(ImageInput.Refused.class, () -> ImageInput.read(file, 10, ImageInput.MAX_PIXELS));

        assertTrue(refused.getMessage().startsWith("The file is larger than "), refused.getMessage());
    }

    @Test
    void refusesAHeaderThatClaimsTooManyPixelsBeforeItDecodes() throws IOException {
        Path file = Files.write(folder.resolve("bomb.png"), pngHeader(10_000, 10_000));

        ImageInput.Refused refused = assertThrows(ImageInput.Refused.class, () -> ImageInput.read(file));

        assertTrue(refused.getMessage().startsWith("The image has 10000 × 10000 pixels"), refused.getMessage());
    }

    /** A PNG signature and an IHDR chunk only: enough for a reader to give the size, and no pixel data. */
    private static byte[] pngHeader(int width, int height) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        out.write(new byte[] {(byte) 0x89, 'P', 'N', 'G', '\r', '\n', 0x1A, '\n'});
        ByteArrayOutputStream chunk = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(chunk);
        data.writeBytes("IHDR");
        data.writeInt(width);
        data.writeInt(height);
        data.write(new byte[] {8, 2, 0, 0, 0});
        byte[] body = chunk.toByteArray();
        out.writeInt(body.length - 4);
        out.write(body);
        CRC32 crc = new CRC32();
        crc.update(body);
        out.writeInt((int) crc.getValue());
        return bytes.toByteArray();
    }
}
