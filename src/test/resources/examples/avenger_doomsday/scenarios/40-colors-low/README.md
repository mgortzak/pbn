# avenger_doomsday: 40 colors, low detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/avenger_doomsday/base.jpg 40 low
```

The program writes its results beside the input image. The results were then moved into this folder.

| File | Content |
|---|---|
| `base-numbers.png` | The outlines, with a number in each region. |
| `base-legend.png` | Each number with a swatch of its color and the hex value. |
| `base-preview.png` | Each region filled with its color. |

## Output of the command

```
Wrote base-numbers.png
Wrote base-legend.png
Wrote base-preview.png
39 colors, 400 regions, low detail (of the 40 requested; each other color was too rare, or covered only regions too small to paint)
  1 #1B1C19
  2 #1D201C
  3 #1F2622
  4 #2A3324
  5 #28332B
  6 #2D3D33
  7 #364932
  8 #364E38
  9 #3E4F30
 10 #355133
 11 #295529
 12 #49544A
 13 #385B32
 14 #465B2F
 15 #4C623D
 16 #426445
 17 #2B6A2F
 18 #517149
 19 #5C7230
 20 #2E7C35
 21 #5B7266
 22 #C53C39
 23 #58775D
 24 #358038
 25 #487E52
 26 #678A60
 27 #7A8A40
 28 #718C75
 29 #6F8C87
 30 #718F54
 31 #7FA070
 32 #8AA39F
 33 #95AB6B
 34 #97B083
 35 #AFC49D
 36 #BAC98F
 37 #CFCFBF
 38 #D1DAAB
 39 #DDE6C9
```
