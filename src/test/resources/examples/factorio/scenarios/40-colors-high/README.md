# factorio: 40 colors, high detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/factorio/base.jpg 40 high
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
40 colors, 5024 regions, high detail
  1 #191614
  2 #24211C
  3 #311F18
  4 #2A2315
  5 #2A2930
  6 #4C2214
  7 #312E2A
  8 #29331E
  9 #3F2D15
 10 #3C3426
 11 #413F3C
 12 #503B33
 13 #57411B
 14 #52442B
 15 #3E4E2C
 16 #4F4C3E
 17 #7C443A
 18 #565353
 19 #6A574A
 20 #6B5C29
 21 #3F6832
 22 #645F44
 23 #825624
 24 #A63F34
 25 #795F3D
 26 #69665F
 27 #6A7E58
 28 #847860
 29 #7A7B7B
 30 #A27633
 31 #917E48
 32 #689A50
 33 #D97D2D
 34 #92A076
 35 #AB9B7E
 36 #A09F9D
 37 #C1A658
 38 #E7D355
 39 #DFDAB2
 40 #E2E1E1
```
