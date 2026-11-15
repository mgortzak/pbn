# dyson_sphere_program: 40 colors, low detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/dyson_sphere_program/base.jpg 40 low
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
39 colors, 296 regions, low detail (of the 40 requested; each other color was too rare, or covered only regions too small to paint)
  1 #2C1411
  2 #321823
  3 #311A35
  4 #3C1910
  5 #32214A
  6 #2C2E49
  7 #552515
  8 #372A66
  9 #3A3C89
 10 #51453E
 11 #374672
 12 #76351C
 13 #60426D
 14 #3A526B
 15 #914220
 16 #4459AC
 17 #3D5CC3
 18 #945735
 19 #516B9F
 20 #497586
 21 #B45D2E
 22 #4376C6
 23 #90708C
 24 #448DB9
 25 #C07E4A
 26 #D57534
 27 #5799D5
 28 #719F7D
 29 #7F9BC1
 30 #F0923E
 31 #6DBBDA
 32 #B7B1BD
 33 #F2AB61
 34 #96C0DA
 35 #A6D9A9
 36 #7DDDE9
 37 #DDD8D6
 38 #BBEAED
 39 #F9E6AC
```
