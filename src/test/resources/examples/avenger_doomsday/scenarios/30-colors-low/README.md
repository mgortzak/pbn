# avenger_doomsday: 30 colors, low detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/avenger_doomsday/base.jpg 30 low
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
30 colors, 359 regions, low detail
  1 #1C1C19
  2 #1D201D
  3 #222924
  4 #29352D
  5 #2C3925
  6 #314231
  7 #3B4F2F
  8 #35552B
  9 #3D5539
 10 #345836
 11 #4F5A50
 12 #24642A
 13 #456746
 14 #506539
 15 #566B60
 16 #327D36
 17 #637935
 18 #D64240
 19 #5E8156
 20 #69827A
 21 #849650
 22 #7A9B6A
 23 #7E9A98
 24 #829B8A
 25 #8AA97A
 26 #A1B578
 27 #B0C59C
 28 #C1CBB5
 29 #CFD7A2
 30 #DCE5C2
```
