# dyson_sphere_program: 30 colors, high detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/dyson_sphere_program/base.jpg 30 high
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
30 colors, 1134 regions, high detail
  1 #281211
  2 #36170F
  3 #331A28
  4 #2A1D3A
  5 #491E12
  6 #322450
  7 #362C6B
  8 #2B3854
  9 #652D1A
 10 #3C4191
 11 #7F3A1E
 12 #714B6F
 13 #42568A
 14 #415BB2
 15 #9E4C26
 16 #3F6884
 17 #6D625B
 18 #4377C7
 19 #B17042
 20 #C76A31
 21 #5D87BF
 22 #5F9ECA
 23 #EF9140
 24 #B2A7AC
 25 #79C1DC
 26 #EEB772
 27 #76E2EC
 28 #C6DEA9
 29 #B8E6ED
 30 #F6F6F4
```
