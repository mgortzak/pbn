# dyson_sphere_program: 20 colors, medium detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/dyson_sphere_program/base.jpg 20 medium
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
20 colors, 272 regions, medium detail
  1 #331714
  2 #32203E
  3 #4E2114
  4 #382C67
  5 #2D3E5D
  6 #7E3B1F
  7 #3F4DA1
  8 #4F578A
  9 #766161
 10 #AF582A
 11 #4674C4
 12 #598BBC
 13 #BB7846
 14 #E88B3F
 15 #71B4D6
 16 #AFAAB8
 17 #91C493
 18 #94DEEA
 19 #F6DB9D
 20 #E8E8E5
```
