# dyson_sphere_program: 10 colors, low detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/dyson_sphere_program/base.jpg 10 low
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
10 colors, 110 regions, low detail
  1 #341714
  2 #362E52
  3 #5D2817
  4 #414E9C
  5 #944925
  6 #837977
  7 #5887B9
  8 #DF8641
  9 #84CCE1
 10 #E3E5DE
```
