# factorio: 50 colors, medium detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/factorio/base.jpg 50 medium
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
50 colors, 1587 regions, medium detail
  1 #201D1A
  2 #2F231A
  3 #282620
  4 #2E292B
  5 #41231A
  6 #302C1C
  7 #3A2B18
  8 #55291A
  9 #373632
 10 #3F3429
 11 #4F391E
 12 #344128
 13 #4A3F2A
 14 #474537
 15 #494440
 16 #50441F
 17 #574138
 18 #3E4752
 19 #634621
 20 #72411C
 21 #5D5243
 22 #635136
 23 #51593A
 24 #8A443A
 25 #765247
 26 #5E5A56
 27 #6C5B29
 28 #7B5E3B
 29 #4A6D3B
 30 #845F28
 31 #786837
 32 #73694C
 33 #B54838
 34 #736D60
 35 #6B6F70
 36 #6B8059
 37 #9F7332
 38 #B77030
 39 #83817F
 40 #9B7B5E
 41 #648F4B
 42 #8C856B
 43 #9A9694
 44 #AF9652
 45 #E8852A
 46 #ABA47E
 47 #ACAAAB
 48 #BEBCBD
 49 #DCC955
 50 #E3DCB6
```
