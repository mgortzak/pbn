# dyson_sphere_program: 20 colors, low detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/dyson_sphere_program/base.jpg 20 low
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
20 colors, 181 regions, low detail
  1 #331714
  2 #32213E
  3 #4E2114
  4 #382C67
  5 #304160
  6 #7F3B1F
  7 #404EA2
  8 #515689
  9 #7A6064
 10 #AF582A
 11 #4473C4
 12 #5A8ABA
 13 #BD7946
 14 #E88A3E
 15 #B0A5B2
 16 #71B4D6
 17 #91C491
 18 #95DEE9
 19 #F6D99D
 20 #E4E3E1
```
