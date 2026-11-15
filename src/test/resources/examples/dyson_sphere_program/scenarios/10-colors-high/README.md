# dyson_sphere_program: 10 colors, high detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/dyson_sphere_program/base.jpg 10 high
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
10 colors, 403 regions, high detail
  1 #3A1913
  2 #322348
  3 #3E4F76
  4 #833E21
  5 #404F9F
  6 #8E867F
  7 #5C91C4
  8 #D9813E
  9 #84CFE3
 10 #EEF3EC
```
