# dyson_sphere_program: 30 colors, medium detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/dyson_sphere_program/base.jpg 30 medium
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
30 colors, 371 regions, medium detail
  1 #2C1514
  2 #321A30
  3 #3B1910
  4 #33234F
  5 #552415
  6 #2B3250
  7 #393479
  8 #77371D
  9 #69466E
 10 #395B78
 11 #43578A
 12 #924422
 13 #4257AC
 14 #7C605C
 15 #597064
 16 #B65E2E
 17 #A7693C
 18 #497BC8
 19 #718BB7
 20 #4A97CA
 21 #D87836
 22 #76A67D
 23 #B3A7B1
 24 #F29641
 25 #79BDD9
 26 #E9AE6C
 27 #A4D9A6
 28 #9BE4ED
 29 #E3E2E0
 30 #F5EEBD
```
