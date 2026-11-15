# factorio: 30 colors, high detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/factorio/base.jpg 30 high
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
30 colors, 4220 regions, high detail
  1 #1B1815
  2 #321F18
  3 #2A2416
  4 #292723
  5 #3F2F18
  6 #333236
  7 #582B1E
  8 #3E372E
  9 #343D25
 10 #54401C
 11 #594939
 12 #504C4A
 13 #3E5C2F
 14 #755126
 15 #635A3B
 16 #656056
 17 #9F4539
 18 #7C6A36
 19 #836F57
 20 #787776
 21 #6E805B
 22 #A07232
 23 #66964F
 24 #D87D2D
 25 #AA924C
 26 #A09575
 27 #A6A4A1
 28 #E1C95B
 29 #D7D3AA
 30 #E6E4E5
```
