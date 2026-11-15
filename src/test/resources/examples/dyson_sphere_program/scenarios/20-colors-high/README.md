# dyson_sphere_program: 20 colors, high detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/dyson_sphere_program/base.jpg 20 high
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
20 colors, 786 regions, high detail
  1 #301512
  2 #2E1D3B
  3 #4E2113
  4 #362B67
  5 #2B3954
  6 #7F3B1F
  7 #405387
  8 #404EA2
  9 #724A6E
 10 #787169
 11 #B25F30
 12 #4575C5
 13 #587BA7
 14 #5F9FCE
 15 #E88A3E
 16 #B7B6C2
 17 #7BC8E0
 18 #EABA79
 19 #A4E8E4
 20 #F4F5F1
```
