# avenger_doomsday: 20 colors, low detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/avenger_doomsday/base.jpg 20 low
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
20 colors, 336 regions, low detail
  1 #1B1B19
  2 #1D211E
  3 #283327
  4 #2F3C33
  5 #394C34
  6 #335228
  7 #425F40
  8 #326B33
  9 #56695D
 10 #2D7432
 11 #547345
 12 #D64240
 13 #728743
 14 #728D89
 15 #719467
 16 #8DA79B
 17 #96B184
 18 #A8BB79
 19 #C9D5A8
 20 #E1E7CF
```
