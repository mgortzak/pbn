# avenger_doomsday: 20 colors, medium detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/avenger_doomsday/base.jpg 20 medium
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
20 colors, 573 regions, medium detail
  1 #1B1B19
  2 #1D211E
  3 #263026
  4 #2E3C33
  5 #364932
  6 #304F26
  7 #3F5C3E
  8 #2E672E
  9 #58685F
 10 #517043
 11 #D64240
 12 #338E3B
 13 #718641
 14 #709464
 15 #789293
 16 #99AEA9
 17 #98B286
 18 #A8BA77
 19 #CAD6A9
 20 #DFE6CD
```
