# avenger_doomsday: 30 colors, high detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/avenger_doomsday/base.jpg 30 high
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
30 colors, 1697 regions, high detail
  1 #1A1B19
  2 #1E221F
  3 #252E28
  4 #24321F
  5 #2B3A2F
  6 #21411F
  7 #324430
  8 #474E49
  9 #395338
 10 #445A2F
 11 #2D622E
 12 #436545
 13 #5E6E69
 14 #2D7B2F
 15 #5D7533
 16 #54774A
 17 #D53B3A
 18 #658667
 19 #72898B
 20 #6C8F5B
 21 #55A567
 22 #8A9D51
 23 #3EB146
 24 #8DA4A9
 25 #8DAA77
 26 #AFBBB6
 27 #ACC29A
 28 #B8C788
 29 #DAE2BA
 30 #E8EDDB
```
