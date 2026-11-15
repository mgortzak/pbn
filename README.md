# pbn

pbn makes a paint-by-numbers set from one image. It writes three PNG files beside the input:

| File | Content |
|---|---|
| `<name>-numbers.png` | A white canvas with the outline of each region and a number in each region. |
| `<name>-legend.png` | Each number with a swatch of its color and the hex value, so you can mix the paint or choose the pencil. |
| `<name>-preview.png` | Each region filled with its color. It shows the result before you paint. |

## Build

pbn needs Eclipse Temurin 25. A build with an older Java stops with one message.

```bash
sdk use java 25.0.5.0+8-tem
mvn package
```

## Use

```bash
java -jar target/pbn.jar <image> [colors]
```

- `image` is a PNG, JPEG, BMP or GIF file, at most 25 MB and 40 megapixels.
- `colors` is the size of the palette, from 2 to 30. The default is 16.

Example:

```bash
$ java -jar target/pbn.jar photo.jpg 20
Wrote /home/me/photo-numbers.png
Wrote /home/me/photo-legend.png
Wrote /home/me/photo-preview.png
20 colors, 29 regions
  1 #593E28
  2 #782A1E
  ...
```

The palette can have fewer colors than you request. This occurs when the image has fewer distinct colors, or when a color covers only regions that are too small to paint.

## How it works

1. Scale the image down to 1600 px on the long side, if it is larger. A transparent pixel counts as white.
2. Blur with a 3×3 box, so the noise of a JPEG does not make small regions.
3. Find the palette with k-means++ in the CIELAB color space, with a fixed seed. The same image always gives the same result.
4. Clean the regions. A majority filter removes noise. A region below 0.02% of the image (at least 64 px), or narrower than about 6 px, merges into the neighbor with which it shares the longest border.
5. Number the colors from dark to light. Put each number at the point of its region that is farthest from the outline.
6. Draw the numbers with built-in stroke glyphs, so pbn needs no installed font.

pbn has no dependency at run time. It uses only the JDK.
