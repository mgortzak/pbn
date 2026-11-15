# avenger_doomsday: 10 colors, high detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/avenger_doomsday/base.jpg 10 high
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
10 colors, 882 regions, high detail
  1 #1B1D1B
  2 #2A362C
  3 #374E33
  4 #41672C
  5 #51744B
  6 #36963E
  7 #7D8D8D
  8 #839E62
  9 #B9CA98
 10 #DFE6CE
```
