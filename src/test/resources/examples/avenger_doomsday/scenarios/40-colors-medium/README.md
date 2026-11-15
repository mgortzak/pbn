# avenger_doomsday: 40 colors, medium detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/avenger_doomsday/base.jpg 40 medium
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
39 colors, 727 regions, medium detail (of the 40 requested; each other color was too rare, or covered only regions too small to paint)
  1 #1B1B19
  2 #1C1F1C
  3 #1F2522
  4 #252E22
  5 #27322A
  6 #2D3D33
  7 #33442A
  8 #334430
  9 #2A4A25
 10 #364E38
 11 #2C5A2C
 12 #415836
 13 #355A35
 14 #4B564D
 15 #475B2F
 16 #406143
 17 #356B36
 18 #597031
 19 #507149
 20 #327933
 21 #C53C39
 22 #5D7268
 23 #617A66
 24 #62875C
 25 #528C5F
 26 #32923A
 27 #6F8D52
 28 #7B8B40
 29 #728F8E
 30 #7D9F70
 31 #90A894
 32 #90A6A8
 33 #99AE6E
 34 #9BB487
 35 #B3C6A1
 36 #BCCA8E
 37 #CCD0C0
 38 #D2DBAB
 39 #E3EACE
```
