package nl.markgortzak.pbn;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;

/** {@code java -jar pbn.jar <image> [colors]}. The only class that writes a file. */
public final class Main {

    static final String USAGE = "Usage: java -jar pbn.jar <image> [colors]   (colors: "
            + PaintByNumbers.MIN_COLORS + " to " + PaintByNumbers.MAX_COLORS + ", default " + PaintByNumbers.DEFAULT_COLORS + ")";

    private Main() {
    }

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "true");
        System.exit(run(args, System.out, System.err));
    }

    static int run(String[] args, PrintStream out, PrintStream err) {
        if (args.length < 1 || args.length > 2) {
            err.println(USAGE);
            return 1;
        }
        int colors = PaintByNumbers.DEFAULT_COLORS;
        if (args.length == 2) {
            try {
                colors = Integer.parseInt(args[1].trim());
            } catch (NumberFormatException e) {
                colors = -1;
            }
            if (colors < PaintByNumbers.MIN_COLORS || colors > PaintByNumbers.MAX_COLORS) {
                err.println("colors must be a whole number from " + PaintByNumbers.MIN_COLORS + " to " + PaintByNumbers.MAX_COLORS + ", but was: " + args[1]);
                return 1;
            }
        }
        Path input = Path.of(args[0]).toAbsolutePath().normalize();
        try {
            BufferedImage image = ImageInput.read(input);
            PaintByNumbers.Result result = PaintByNumbers.make(image, colors);
            Path numbers = write(result.numbered(), sibling(input, "numbers"));
            Path legend = write(result.legend(), sibling(input, "legend"));
            Path preview = write(result.preview(), sibling(input, "preview"));
            out.println("Wrote " + numbers);
            out.println("Wrote " + legend);
            out.println("Wrote " + preview);
            int used = result.swatches().size();
            String fewer = used < colors ? " (of the " + colors + " requested; each other color was too rare, or covered only regions too small to paint)" : "";
            out.println(used + " colors, " + result.regions() + " regions" + fewer);
            for (Swatch swatch : result.swatches()) {
                out.printf("%3d %s%n", swatch.number(), swatch.hex());
            }
            return 0;
        } catch (ImageInput.Refused e) {
            err.println(e.getMessage());
            return 1;
        } catch (IOException e) {
            err.println("An output file cannot be written: " + e.getMessage());
            return 1;
        }
    }

    /** {@code photo.jpg} gives {@code photo-numbers.png} in the same folder. */
    static Path sibling(Path input, String kind) {
        String name = input.getFileName().toString();
        int dot = name.lastIndexOf('.');
        String stem = dot > 0 ? name.substring(0, dot) : name;
        return input.resolveSibling(stem + "-" + kind + ".png");
    }

    private static Path write(BufferedImage image, Path file) throws IOException {
        if (!ImageIO.write(image, "png", file.toFile())) {
            throw new IOException("no PNG writer for " + file);
        }
        return file;
    }
}
