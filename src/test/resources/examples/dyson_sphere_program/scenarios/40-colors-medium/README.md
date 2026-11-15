# dyson_sphere_program: 40 colors, medium detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/dyson_sphere_program/base.jpg 40 medium
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
40 colors, 474 regions, medium detail
  1 #2C1411
  2 #311723
  3 #3C1910
  4 #321B36
  5 #31224B
  6 #2A2C46
  7 #562515
  8 #362A66
  9 #3B3C89
 10 #354571
 11 #75341C
 12 #62426D
 13 #37526C
 14 #5C4D48
 15 #914220
 16 #4458AB
 17 #915533
 18 #4E679C
 19 #3963D0
 20 #746958
 21 #4D7B85
 22 #B45D2D
 23 #4375C6
 24 #8E6F8C
 25 #438DB8
 26 #BD7C48
 27 #D57535
 28 #5698D5
 29 #719F7D
 30 #7C99C1
 31 #ADA1A2
 32 #F0923E
 33 #69B9D9
 34 #F1AA60
 35 #9BC7DD
 36 #78DCE9
 37 #A5DAAB
 38 #E3E0DE
 39 #BAEBEE
 40 #F6E8B4
```
