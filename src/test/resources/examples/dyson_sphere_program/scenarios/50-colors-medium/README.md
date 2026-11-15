# dyson_sphere_program: 50 colors, medium detail

The three images in this folder come from this command, run from the project root:

```bash
java -jar target/pbn.jar src/test/resources/examples/dyson_sphere_program/base.jpg 50 medium
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
49 colors, 538 regions, medium detail (of the 50 requested; each other color was too rare, or covered only regions too small to paint)
  1 #281311
  2 #2D1525
  3 #36170F
  4 #391B21
  5 #331D38
  6 #451D11
  7 #332049
  8 #252646
  9 #332660
 10 #2D3045
 11 #5C2816
 12 #2E3C64
 13 #3B377D
 14 #763218
 15 #384E65
 16 #5B4273
 17 #404A9D
 18 #7F4329
 19 #395385
 20 #5B524A
 21 #6E4A63
 22 #934422
 23 #4462B5
 24 #54659F
 25 #406F99
 26 #3965D5
 27 #AF582A
 28 #A5673A
 29 #587F81
 30 #4B81CC
 31 #6687B8
 32 #958380
 33 #CD7238
 34 #4594BF
 35 #958AAB
 36 #4F9CD9
 37 #73A180
 38 #CF8A12
 39 #E78035
 40 #7EA5C9
 41 #F39B4C
 42 #6CBEDB
 43 #E4AC6E
 44 #9BC6DD
 45 #A5DBA9
 46 #78E0EC
 47 #E3E0DE
 48 #BBEBEF
 49 #F6EDB8
```
