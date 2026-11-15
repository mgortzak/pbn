# avenger_doomsday: 10 colors, medium detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/avenger_doomsday/base.jpg 10 medium
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
10 colors, 406 regions, medium detail
  1 #1C1E1B
  2 #2B372C
  3 #395036
  4 #496C40
  5 #2E8334
  6 #D43E3D
  7 #769259
  8 #7A8F8E
  9 #AFC28D
 10 #D8E0C2
```
