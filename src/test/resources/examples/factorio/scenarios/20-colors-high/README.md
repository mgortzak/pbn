# factorio: 20 colors, high detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/factorio/base.jpg 20 high
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
20 colors, 3463 regions, high detail
  1 #1D1916
  2 #2D2417
  3 #2F2D2C
  4 #4B261B
  5 #463519
  6 #413A2D
  7 #394B2A
  8 #524D4A
  9 #664E24
 10 #635540
 11 #944435
 12 #6F6C66
 13 #837957
 14 #987335
 15 #648F50
 16 #95928C
 17 #D67C2E
 18 #BBB38D
 19 #D4BC5A
 20 #E4E2DF
```
