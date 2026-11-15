# dyson_sphere_program: 50 colors, low detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/dyson_sphere_program/base.jpg 50 low
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
49 colors, 329 regions, low detail (of the 50 requested; each other color was too rare, or covered only regions too small to paint)
  1 #281311
  2 #2D1525
  3 #361710
  4 #3B1D24
  5 #341E39
  6 #332049
  7 #451D12
  8 #262848
  9 #332761
 10 #2D3047
 11 #5E2917
 12 #3A357B
 13 #303F67
 14 #773218
 15 #474745
 16 #394F66
 17 #5B4376
 18 #67455F
 19 #3F4A9C
 20 #3A5588
 21 #82452B
 22 #924422
 23 #4462B5
 24 #5867A0
 25 #3564D4
 26 #3F6F98
 27 #AF582A
 28 #A46539
 29 #618889
 30 #4D82CD
 31 #6889BA
 32 #978687
 33 #CD7338
 34 #4694BF
 35 #9389A6
 36 #6C997E
 37 #519DD9
 38 #CF8A12
 39 #E57F34
 40 #83A7CB
 41 #F39B4C
 42 #73C0DB
 43 #E5AE70
 44 #9BC5DC
 45 #ACD8B2
 46 #7DE1EC
 47 #DBD6D4
 48 #BAEAED
 49 #FBEDB3
```
