# factorio: 10 colors, high detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/factorio/base.jpg 10 high
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
10 colors, 1885 regions, high detail
  1 #24201C
  2 #392A1A
  3 #4A463F
  4 #5A4A28
  5 #7F3C2C
  6 #797366
  7 #5F824C
  8 #93733B
  9 #D38F3B
 10 #D1CCBD
```
