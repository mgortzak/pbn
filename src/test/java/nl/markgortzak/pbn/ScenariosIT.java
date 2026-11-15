package nl.markgortzak.pbn;

import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Runs the command in the README of each scenario again, and compares the result with the files in that scenario.
 *
 * <p>The README is the specification: its command gives the arguments, and its output block gives the expected output.
 */
class ScenariosIT {

    private static final Path EXAMPLES = Path.of("src", "test", "resources", "examples");
    private static final Pattern COMMAND = Pattern.compile("(?m)^java -jar target/pbn\\.jar (\\S+) (\\S+) (\\S+)$");
    private static final Pattern OUTPUT = Pattern.compile("(?s)## Output of the command\\s+```\\n(.*?)\\n```");
    private static final List<String> KINDS = List.of("numbers", "legend", "preview");

    @TempDir
    Path folder;

    static Stream<Path> scenarios() throws IOException {
        try (Stream<Path> files = Files.walk(EXAMPLES)) {
            List<Path> scenarios = files.filter(p -> p.getFileName().toString().equals("README.md"))
                    .map(Path::getParent)
                    .filter(p -> p.getParent().getFileName().toString().equals("scenarios"))
                    .sorted()
                    .toList();
            assertEquals(45, scenarios.size(), "the number of scenarios");
            return scenarios.stream();
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("scenarios")
    void theProgramGivesTheResultOfTheScenario(Path scenario) throws IOException {
        String readme = Files.readString(scenario.resolve("README.md"));
        Matcher command = find(COMMAND, readme, scenario);
        Matcher output = find(OUTPUT, readme, scenario);
        Path input = folder.resolve("base.jpg");
        Files.copy(Path.of(command.group(1)), input);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        int status = Main.run(new String[] {input.toString(), command.group(2), command.group(3)},
                new PrintStream(out, true, StandardCharsets.UTF_8), new PrintStream(err, true, StandardCharsets.UTF_8));

        assertEquals(0, status, err.toString(StandardCharsets.UTF_8));
        String printed = out.toString(StandardCharsets.UTF_8).replace(folder.toAbsolutePath().normalize() + "/", "");
        assertEquals(output.group(1) + "\n", printed);
        for (String kind : KINDS) {
            String name = "base-" + kind + ".png";
            assertSamePixels(ImageIO.read(scenario.resolve(name).toFile()), ImageIO.read(folder.resolve(name).toFile()), name);
        }
    }

    private static Matcher find(Pattern pattern, String readme, Path scenario) {
        Matcher matcher = pattern.matcher(readme);
        assertTrue(matcher.find(), "the README of " + scenario + " has no match for " + pattern);
        return matcher;
    }

    private static void assertSamePixels(BufferedImage expected, BufferedImage actual, String name) {
        assertEquals(expected.getWidth() + "x" + expected.getHeight(), actual.getWidth() + "x" + actual.getHeight(), "the size of " + name);
        int w = expected.getWidth();
        int h = expected.getHeight();
        int[] want = expected.getRGB(0, 0, w, h, null, 0, w);
        int[] got = actual.getRGB(0, 0, w, h, null, 0, w);
        int first = -1;
        int differ = 0;
        for (int i = 0; i < want.length; i++) {
            if (want[i] != got[i]) {
                differ++;
                if (first < 0) {
                    first = i;
                }
            }
        }
        if (differ > 0) {
            fail(String.format("%s: %d of %d pixels differ; the first is at (%d, %d): expected #%06X, but was #%06X",
                    name, differ, want.length, first % w, first / w, want[first] & 0xFFFFFF, got[first] & 0xFFFFFF));
        }
    }
}
