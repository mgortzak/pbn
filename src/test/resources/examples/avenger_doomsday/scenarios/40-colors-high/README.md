# avenger_doomsday: 40 colors, high detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/avenger_doomsday/base.jpg 40 high
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
40 colors, 1964 regions, high detail
  1 #1A1A18
  2 #1C1E1B
  3 #1D2120
  4 #212823
  5 #23301F
  6 #4D1E1E
  7 #28332C
  8 #2C3A28
  9 #213F20
 10 #2D3F34
 11 #344832
 12 #3C512D
 13 #38533B
 14 #28572A
 15 #49504B
 16 #426241
 17 #4A6537
 18 #2B6B2D
 19 #586F30
 20 #5C6C67
 21 #287C2C
 22 #507755
 23 #597D4E
 24 #DB3D3B
 25 #45864D
 26 #6B876D
 27 #708585
 28 #788E45
 29 #4E985D
 30 #739563
 31 #869FA6
 32 #41B848
 33 #90AE81
 34 #A2B578
 35 #A5B562
 36 #A3B4B1
 37 #B1C69F
 38 #CED89F
 39 #D9DECF
 40 #E0E8C6
```
