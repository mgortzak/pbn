# avenger_doomsday: 50 colors, high detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/avenger_doomsday/base.jpg 50 high
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
50 colors, 2174 regions, high detail
  1 #1A1A18
  2 #1B1D1B
  3 #1D2221
  4 #1D231E
  5 #21251F
  6 #232B26
  7 #4D1E1E
  8 #243120
  9 #28342C
 10 #2D3B29
 11 #254123
 12 #2E4035
 13 #344833
 14 #254D25
 15 #3B4F2C
 16 #484F49
 17 #39543C
 18 #275B28
 19 #415F3C
 20 #4C622E
 21 #446A4D
 22 #29742E
 23 #5A6A65
 24 #4D7045
 25 #267D2A
 26 #3E7C48
 27 #647932
 28 #5D7E48
 29 #677C69
 30 #DB3A39
 31 #5C8259
 32 #47884A
 33 #6E8383
 34 #709464
 35 #7E9958
 36 #7A997A
 37 #8C9A46
 38 #849DA4
 39 #43AF49
 40 #5FAE76
 41 #8CAA78
 42 #9DAFAD
 43 #A2B676
 44 #A8C096
 45 #BFC7B9
 46 #C0CC96
 47 #C4D689
 48 #C4D4B4
 49 #DDE4B7
 50 #ECF2D8
```
