# factorio: 10 colors, medium detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/factorio/base.jpg 10 medium
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
10 colors, 766 regions, medium detail
  1 #27201A
  2 #3C382F
  3 #503F23
  4 #733829
  5 #615B51
  6 #866935
  7 #5C7E48
  8 #908873
  9 #C78A3B
 10 #C2C0B9
```
