# avenger_doomsday: 50 colors, low detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/avenger_doomsday/base.jpg 50 low
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
49 colors, 424 regions, low detail (of the 50 requested; each other color was too rare, or covered only regions too small to paint)
  1 #1B1C19
  2 #1C1E1B
  3 #1E2321
  4 #1F261F
  5 #222926
  6 #27322A
  7 #2A3A2E
  8 #2F3B28
  9 #2F4338
 10 #2E482C
 11 #354731
 12 #414B41
 13 #374F39
 14 #245525
 15 #3E5033
 16 #31572D
 17 #42552E
 18 #485C38
 19 #4A5C47
 20 #416041
 21 #306731
 22 #4D642F
 23 #52605A
 24 #A93A35
 25 #3F6A42
 26 #40763E
 27 #53744F
 28 #5C7267
 29 #637166
 30 #647532
 31 #2F8034
 32 #587A5D
 33 #4C7D55
 34 #5B7F51
 35 #6C8A85
 36 #7D8D81
 37 #729469
 38 #7B945B
 39 #8C9A52
 40 #7F9F70
 41 #849C98
 42 #8AA67B
 43 #90A96E
 44 #A6BC90
 45 #B8C4AB
 46 #BECB90
 47 #BFCB9A
 48 #C3D4B5
 49 #E4EBCE
```
