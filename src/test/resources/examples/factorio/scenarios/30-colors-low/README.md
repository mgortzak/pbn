# factorio: 30 colors, low detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/factorio/base.jpg 30 low
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
30 colors, 719 regions, low detail
  1 #221E1A
  2 #2D2821
  3 #3A271D
  4 #322C1A
  5 #333232
  6 #42382A
  7 #5A2F22
  8 #4B381F
  9 #374529
 10 #534521
 11 #4C463F
 12 #564C36
 13 #6B4722
 14 #6B4E42
 15 #575453
 16 #96483B
 17 #6C5D41
 18 #715D30
 19 #646051
 20 #5A6F47
 21 #706F6A
 22 #996D31
 23 #648C4C
 24 #957F45
 25 #C8762C
 26 #938A6B
 27 #A4A1A0
 28 #C5B24F
 29 #B7B5B5
 30 #E0D9B2
```
