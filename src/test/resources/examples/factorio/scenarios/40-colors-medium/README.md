# factorio: 40 colors, medium detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/factorio/base.jpg 40 medium
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
40 colors, 1462 regions, medium detail
  1 #1F1C19
  2 #332319
  3 #2A2620
  4 #2E2B2D
  5 #312C1B
  6 #52291D
  7 #3F3327
  8 #47341C
  9 #393834
 10 #46422F
 11 #37472A
 12 #4F431E
 13 #51443C
 14 #61411F
 15 #4D4C4D
 16 #604F35
 17 #595446
 18 #754E44
 19 #67592C
 20 #9B4035
 21 #69655F
 22 #835E2B
 23 #6D664D
 24 #4F703E
 25 #7F6440
 26 #9F5F29
 27 #7E6E3B
 28 #677954
 29 #797A77
 30 #A07533
 31 #628B4B
 32 #8B846C
 33 #A48467
 34 #989695
 35 #B0954F
 36 #E5842B
 37 #AAA47E
 38 #C6C5C5
 39 #DAC75B
 40 #E1DBB4
```
