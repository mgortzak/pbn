package nl.markgortzak.pbn;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

/** Reads one image file, and refuses a file that is too large before it decodes the pixels. */
final class ImageInput {

    static final long MAX_BYTES = 25L * 1024 * 1024;
    static final long MAX_PIXELS = 40_000_000L;

    /** A file the program cannot use. The message is one sentence for the user. */
    static final class Refused extends Exception {
        Refused(String message) {
            super(message);
        }
    }

    private ImageInput() {
    }

    static BufferedImage read(Path file) throws Refused {
        return read(file, MAX_BYTES, MAX_PIXELS);
    }

    /**
     * Reads the width and the height from the header first, because a small compressed file can
     * hold an image that fills all the memory when it is decoded.
     */
    static BufferedImage read(Path file, long maxBytes, long maxPixels) throws Refused {
        if (!Files.isRegularFile(file)) {
            throw new Refused("The file does not exist: " + file);
        }
        try {
            long size = Files.size(file);
            if (size > maxBytes) {
                throw new Refused("The file is larger than " + maxBytes / (1024 * 1024) + " MB: " + file);
            }
            try (ImageInputStream in = ImageIO.createImageInputStream(file.toFile())) {
                Iterator<ImageReader> readers = in == null ? null : ImageIO.getImageReaders(in);
                if (readers == null || !readers.hasNext()) {
                    throw new Refused("The format is not PNG, JPEG, BMP or GIF: " + file);
                }
                ImageReader reader = readers.next();
                try {
                    reader.setInput(in, true, true);
                    long w = reader.getWidth(0);
                    long h = reader.getHeight(0);
                    if (w * h > maxPixels) {
                        throw new Refused("The image has " + w + " × " + h + " pixels, and the limit is " + maxPixels / 1_000_000 + " megapixels: " + file);
                    }
                    return reader.read(0);
                } finally {
                    reader.dispose();
                }
            }
        } catch (IOException e) {
            throw new Refused("The image cannot be read: " + file + " (" + e.getMessage() + ")");
        }
    }
}
