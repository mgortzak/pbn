# factorio: 20 colors, low detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/factorio/base.jpg 20 low
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
20 colors, 661 regions, low detail
  1 #231F1A
  2 #37291D
  3 #312E2C
  4 #353523
  5 #572C1F
  6 #48371E
  7 #4B423A
  8 #5B4F39
  9 #664E27
 10 #485935
 11 #605C57
 12 #964739
 13 #776B4C
 14 #8F6F35
 15 #5E814B
 16 #82817F
 17 #C0702B
 18 #A19B7A
 19 #CFBA62
 20 #C6C4C1
```
