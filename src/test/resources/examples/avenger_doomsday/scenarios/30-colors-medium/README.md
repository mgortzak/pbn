# avenger_doomsday: 30 colors, medium detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/avenger_doomsday/base.jpg 30 medium
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
30 colors, 658 regions, medium detail
  1 #1B1B19
  2 #1C1F1C
  3 #212823
  4 #293525
  5 #29352D
  6 #304131
  7 #374C2A
  8 #30542C
  9 #3A5237
 10 #4D564F
 11 #316033
 12 #276629
 13 #4C6235
 14 #426444
 15 #5B6F67
 16 #5F7635
 17 #5A7E53
 18 #D64240
 19 #398D40
 20 #6C827C
 21 #739562
 22 #82944B
 23 #849FA2
 24 #8DA695
 25 #8BA97B
 26 #A1B576
 27 #AEC29A
 28 #CED7A1
 29 #D0D8C4
 30 #DEE7C6
```
