package nl.markgortzak.pbn;

/**
 * Converts between sRGB and CIELAB with the D65 white point.
 *
 * <p>A distance in Lab is close to the difference a person sees, so the quantizer joins colors
 * that look alike and keeps apart colors that look different.
 */
final class Lab {

    private static final double XN = 0.95047;
    private static final double YN = 1.0;
    private static final double ZN = 1.08883;
    private static final double EPSILON = 216.0 / 24389.0;
    private static final double KAPPA = 24389.0 / 27.0;

    private static final double[] LINEAR = new double[256];

    static {
        for (int i = 0; i < 256; i++) {
            double c = i / 255.0;
            LINEAR[i] = c <= 0.04045 ? c / 12.92 : Math.pow((c + 0.055) / 1.055, 2.4);
        }
    }

    private Lab() {
    }

    /** Gives {@code {L, a, b}} for a packed {@code 0xRRGGBB} color. The alpha byte is ignored. */
    static double[] fromRgb(int rgb) {
        double r = LINEAR[(rgb >> 16) & 0xFF];
        double g = LINEAR[(rgb >> 8) & 0xFF];
        double b = LINEAR[rgb & 0xFF];
        double x = (0.4124564 * r + 0.3575761 * g + 0.1804375 * b) / XN;
        double y = (0.2126729 * r + 0.7151522 * g + 0.0721750 * b) / YN;
        double z = (0.0193339 * r + 0.1191920 * g + 0.9503041 * b) / ZN;
        double fx = f(x);
        double fy = f(y);
        double fz = f(z);
        return new double[] {116 * fy - 16, 500 * (fx - fy), 200 * (fy - fz)};
    }

    /** Gives the packed {@code 0xRRGGBB} color, clamped to the sRGB gamut. */
    static int toRgb(double l, double a, double b) {
        double fy = (l + 16) / 116;
        double fx = fy + a / 500;
        double fz = fy - b / 200;
        double x = finv(fx) * XN;
        double y = (l > KAPPA * EPSILON ? fy * fy * fy : l / KAPPA) * YN;
        double z = finv(fz) * ZN;
        double r = 3.2404542 * x - 1.5371385 * y - 0.4985314 * z;
        double g = -0.9692660 * x + 1.8760108 * y + 0.0415560 * z;
        double bl = 0.0556434 * x - 0.2040259 * y + 1.0572252 * z;
        return (encode(r) << 16) | (encode(g) << 8) | encode(bl);
    }

    private static double f(double t) {
        return t > EPSILON ? Math.cbrt(t) : (KAPPA * t + 16) / 116;
    }

    private static double finv(double t) {
        double cube = t * t * t;
        return cube > EPSILON ? cube : (116 * t - 16) / KAPPA;
    }

    private static int encode(double linear) {
        double c = linear <= 0.0031308 ? 12.92 * linear : 1.055 * Math.pow(linear, 1 / 2.4) - 0.055;
        return (int) Math.round(Math.max(0, Math.min(1, c)) * 255);
    }
}
