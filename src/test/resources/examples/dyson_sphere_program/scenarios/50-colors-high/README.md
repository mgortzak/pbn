# dyson_sphere_program: 50 colors, high detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/dyson_sphere_program/base.jpg 50 high
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
50 colors, 1570 regions, high detail
  1 #281312
  2 #36160F
  3 #311827
  4 #301B3C
  5 #222341
  6 #461D11
  7 #312152
  8 #362C6D
  9 #2E394D
 10 #5F2A18
 11 #2B3A63
 12 #5C3957
 13 #3A3C8B
 14 #773319
 15 #5C4943
 16 #60487A
 17 #434F88
 18 #3F50A5
 19 #3A5982
 20 #874B2F
 21 #944422
 22 #3E6A7C
 23 #4564B6
 24 #3B62D2
 25 #75685F
 26 #4C6DA7
 27 #B35928
 28 #946C86
 29 #B16739
 30 #427DCE
 31 #3F8BB7
 32 #6E84B5
 33 #D17335
 34 #5298D7
 35 #CA905C
 36 #85A3C7
 37 #5BAAD6
 38 #EF893A
 39 #B2A5A3
 40 #E39916
 41 #F29C4F
 42 #69BBD7
 43 #90C595
 44 #A3CAE0
 45 #7AD4E7
 46 #F5C67D
 47 #77EAEF
 48 #B9EAEF
 49 #F4F1C5
 50 #F3F3F2
```
