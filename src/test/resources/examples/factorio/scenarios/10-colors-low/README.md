# factorio: 10 colors, low detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/factorio/base.jpg 10 low
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
10 colors, 437 regions, low detail
  1 #29221B
  2 #3D3830
  3 #524225
  4 #723828
  5 #605A51
  6 #886B36
  7 #5D7D49
  8 #928A74
  9 #C4863A
 10 #B8B6B2
```
