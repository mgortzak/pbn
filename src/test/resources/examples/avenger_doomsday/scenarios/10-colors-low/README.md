# avenger_doomsday: 10 colors, low detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/avenger_doomsday/base.jpg 10 low
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
10 colors, 246 regions, low detail
  1 #1C1F1B
  2 #2C3A2D
  3 #3B5238
  4 #4B6E42
  5 #2D7932
  6 #D43E3D
  7 #778C89
  8 #7A965D
  9 #B2C48F
 10 #D7E0C1
```
