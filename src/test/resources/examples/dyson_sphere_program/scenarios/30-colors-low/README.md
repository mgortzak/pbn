# dyson_sphere_program: 30 colors, low detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/dyson_sphere_program/base.jpg 30 low
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
30 colors, 232 regions, low detail
  1 #2C1514
  2 #3B1910
  3 #321B30
  4 #342450
  5 #562515
  6 #2E3553
  7 #393378
  8 #444742
  9 #78371E
 10 #6C4870
 11 #3A5C79
 12 #45588B
 13 #924321
 14 #4257AC
 15 #745751
 16 #B65E2E
 17 #A6683C
 18 #487AC7
 19 #708CB6
 20 #4B96CA
 21 #D87836
 22 #719F7C
 23 #A79AA4
 24 #F09441
 25 #79BBD7
 26 #EAAF6D
 27 #A4D8A5
 28 #9EE4EC
 29 #DBD8D6
 30 #FBEFB8
```
