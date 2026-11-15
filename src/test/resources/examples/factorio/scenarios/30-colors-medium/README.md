# factorio: 30 colors, medium detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/factorio/base.jpg 30 medium
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
30 colors, 1357 regions, medium detail
  1 #1F1C18
  2 #2B2620
  3 #36241B
  4 #2F2A19
  5 #343233
  6 #562B1E
  7 #48351C
  8 #403729
  9 #344328
 10 #4B463F
 11 #564722
 12 #574D37
 13 #6C4721
 14 #6D4E42
 15 #5A595A
 16 #635E50
 17 #9B4539
 18 #745F30
 19 #716246
 20 #586E45
 21 #996D31
 22 #797874
 23 #978245
 24 #679650
 25 #978E6F
 26 #D57B2D
 27 #9F9D9C
 28 #D0B954
 29 #C8C6C7
 30 #D3CEA5
```
