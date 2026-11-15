# factorio: 20 colors, medium detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/factorio/base.jpg 20 medium
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
20 colors, 1180 regions, medium detail
  1 #211D19
  2 #34261B
  3 #302E2C
  4 #333321
  5 #49371D
  6 #5B2F21
  7 #4A423B
  8 #5A4F39
  9 #654E26
 10 #455934
 11 #605D58
 12 #9A473A
 13 #786C4E
 14 #927035
 15 #61884D
 16 #83817E
 17 #C7752C
 18 #A29A78
 19 #CBB45B
 20 #D4D2CE
```
