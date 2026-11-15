# avenger_doomsday: 20 colors, high detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/avenger_doomsday/base.jpg 20 high
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
20 colors, 1303 regions, high detail
  1 #1B1B19
  2 #1F2621
  3 #23361E
  4 #2B382E
  5 #354B35
  6 #36522B
  7 #525D58
  8 #2A6C2D
  9 #476945
 10 #556E35
 11 #D43938
 12 #6C8281
 13 #6D9161
 14 #7D9247
 15 #3EA245
 16 #93A8AC
 17 #A2BA90
 18 #ACBE7C
 19 #D6DFB5
 20 #DFE4D3
```
