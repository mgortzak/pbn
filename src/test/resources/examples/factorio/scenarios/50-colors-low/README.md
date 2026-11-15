# factorio: 50 colors, low detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/factorio/base.jpg 50 low
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
50 colors, 822 regions, low detail
  1 #23211C
  2 #32261C
  3 #2B2821
  4 #42261B
  5 #322D2E
  6 #34311F
  7 #3E2E1B
  8 #383633
  9 #3F352A
 10 #582D1E
 11 #35402A
 12 #503B21
 13 #5F381B
 14 #474436
 15 #4E432E
 16 #3F464E
 17 #514520
 18 #4C4640
 19 #584339
 20 #604421
 21 #5D5041
 22 #635136
 23 #50573B
 24 #645326
 25 #5C5853
 26 #705432
 27 #8A483D
 28 #AB3B2A
 29 #7D584D
 30 #825E29
 31 #6E6446
 32 #736436
 33 #526F41
 34 #676A68
 35 #726D60
 36 #647551
 37 #A0612B
 38 #927454
 39 #9E7332
 40 #628A4B
 41 #888063
 42 #847F7B
 43 #A58D50
 44 #979699
 45 #E1832C
 46 #A49E77
 47 #ABA9AA
 48 #B5B3B4
 49 #E0CF56
 50 #E0DBB8
```
