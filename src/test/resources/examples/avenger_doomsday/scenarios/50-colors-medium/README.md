# avenger_doomsday: 50 colors, medium detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/avenger_doomsday/base.jpg 50 medium
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
49 colors, 765 regions, medium detail (of the 50 requested; each other color was too rare, or covered only regions too small to paint)
  1 #1B1B19
  2 #1C1E1A
  3 #1D2220
  4 #1E241F
  5 #212825
  6 #263129
  7 #283423
  8 #29382E
  9 #2F4337
 10 #334530
 11 #254923
 12 #36472C
 13 #434944
 14 #364E38
 15 #3D5030
 16 #40542C
 17 #355733
 18 #335B30
 19 #225E23
 20 #3E5C3F
 21 #2D6732
 22 #525F59
 23 #4D632F
 24 #576755
 25 #4D6F49
 26 #5F756D
 27 #CB3D3B
 28 #657932
 29 #5A7C4F
 30 #5B7B5F
 31 #2F8834
 32 #6E7D73
 33 #3D9842
 34 #759154
 35 #6F9265
 36 #72908F
 37 #5B9968
 38 #799B6B
 39 #849987
 40 #8D9D51
 41 #8BA3A1
 42 #92AE82
 43 #98AF72
 44 #AABF93
 45 #BBC9AF
 46 #BDCB8E
 47 #C4D5B5
 48 #D1DAAA
 49 #E9EFD3
```
