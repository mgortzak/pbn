# factorio: 40 colors, low detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/factorio/base.jpg 40 low
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
40 colors, 752 regions, low detail
  1 #221F1B
  2 #2C2821
  3 #37261C
  4 #342F1D
  5 #322E2F
  6 #522B1F
  7 #413529
  8 #49361F
  9 #3C3A36
 10 #37452B
 11 #46412F
 12 #4F431F
 13 #51443B
 14 #4A4A4B
 15 #694925
 16 #5F4E35
 17 #595446
 18 #64562C
 19 #785348
 20 #994439
 21 #686149
 22 #66615B
 23 #805C2B
 24 #516C3E
 25 #7B613E
 26 #935826
 27 #6D6F6B
 28 #7D6D3B
 29 #697955
 30 #996E31
 31 #5E8148
 32 #9C7D5E
 33 #8B826A
 34 #AA9252
 35 #989695
 36 #9F9B74
 37 #DF822E
 38 #BAB8B9
 39 #D7C75B
 40 #DFDAB6
```
