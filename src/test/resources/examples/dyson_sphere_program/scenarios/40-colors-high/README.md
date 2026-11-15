# dyson_sphere_program: 40 colors, high detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/dyson_sphere_program/base.jpg 40 high
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
40 colors, 1381 regions, high detail
  1 #281312
  2 #36160F
  3 #32192C
  4 #222341
  5 #321F48
  6 #471D11
  7 #362B69
  8 #2F384B
  9 #622B19
 10 #2D3E67
 11 #7B351A
 12 #3C4191
 13 #6E486D
 14 #45568C
 15 #3C637D
 16 #8C5031
 17 #9A4823
 18 #445DAF
 19 #6F6059
 20 #3B5FCB
 21 #5877AB
 22 #4279CA
 23 #C4662D
 24 #BA6F3E
 25 #408DB9
 26 #5399D8
 27 #8199C1
 28 #EA8539
 29 #AD9C9B
 30 #64AFD5
 31 #E79E1F
 32 #F19B4E
 33 #8EC191
 34 #E3AC6F
 35 #72CAE2
 36 #A0C6DD
 37 #79E8EF
 38 #B8E9EF
 39 #F5EDBA
 40 #F6F6F5
```
