package nl.markgortzak.pbn;

/** One color of the palette: the number the user paints, and the color as {@code 0xRRGGBB}. */
public record Swatch(int number, int rgb) {

    public String hex() {
        return String.format("#%06X", rgb & 0xFFFFFF);
    }
}
