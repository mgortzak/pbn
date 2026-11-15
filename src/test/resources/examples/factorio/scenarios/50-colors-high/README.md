# factorio: 50 colors, high detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/factorio/base.jpg 50 high
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
50 colors, 5423 regions, high detail
  1 #191614
  2 #231E14
  3 #242220
  4 #311F19
  5 #312615
  6 #482115
  7 #2E2D23
  8 #342E2C
  9 #2D2F39
 10 #2A341D
 11 #423016
 12 #413628
 13 #403F3B
 14 #5A3B19
 15 #4F431C
 16 #5C4139
 17 #364F29
 18 #444B36
 19 #56472F
 20 #4F493D
 21 #7D3D33
 22 #545151
 23 #6C5626
 24 #635A4B
 25 #645F3E
 26 #745A41
 27 #865A24
 28 #AE3F34
 29 #67645F
 30 #4D763D
 31 #8B6C3F
 32 #837538
 33 #757779
 34 #7F7668
 35 #6B7E5A
 36 #837758
 37 #A27834
 38 #BC7131
 39 #8E8E8C
 40 #6A9D50
 41 #B98473
 42 #A59063
 43 #A39B80
 44 #E8842A
 45 #95AF7D
 46 #C2A657
 47 #B2B1B0
 48 #E7D355
 49 #DFD8B0
 50 #DDDCDC
```
