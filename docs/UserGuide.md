# User Guide

## Introduction
Overwhelmed by all your editorial tasks at the publishing company? Sick of having to manually keep track of all the work
that your ~~slaves~~ manga authors have to do? Fret not, as `MangaTantou` is here!

`MangaTantou` keeps tracks of the authors under your charge in a catalog, showing you all mangas they have written, and their deadlines. 
As an editor, it is also your right to know how much your ~~slaves~~ authors are earning! `MangaTantou` takes note of how
many copies have been sold by each of the mangas under your charge and calculates the revenue for you, increasing your 
efficiency at work.

## Quick Start
1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `MangaTantou` from [here](https://github.com/AY2425S1-CS2113-T10-3/tp/releases).
3. Open your favourite terminal app and run `java -jar <path to MangaTantou.jar file>`

## Features
> **_NOTE:_**
> - Command flags can be specified in any order. If the flag requires a field, ensure that it is placed right after the flag after a space (e.g. `catalog -d -a Kubo Tite` and `catalog -a Kubo Tite -d` both delete the author `Kubo Tite` from the catalog).
> - `MangaTantou`'s parser is designed to look for its valid flags (i.e. `-a`, `-d`, `-s`) and will stop at each flag and process the inputs between the valid flags as arguments for the preceding flag (i.e. `catalog -a Kubo Tite -a Bleach` will give just `Kubo Tite`). In other words, the parser is <ins>**smart enough**</ins> to ignore duplicate flags where it is not expecting them and will only <ins>**take the first**</ins> valid input it encounters. Should the user require an input that contains a flag (i.e. `Kubo -a Tite` is the author's name), we recommend that the user prepends the offending part of the name with an additional dash (i.e. `Kubo --a Tite`). This is a tradeoff that `MangaTantou`'s parser takes for flexibility in argument inputs.
> - Whenever you encounter `AUTHOR_NAME` or `MANGA_NAME`, ensure that they are not longer than 40 characters long.
> - Names of authors and mangas are <ins>**case-sensitive**</ins>. For instance `Attack on Titan` and `ATTACK on Titan` will be considered as two different mangas. This is due to the unique use case of names in publishing houses, where copyright and branding through stylization of names is extremely important in the industry. 

### Adding Authors: `catalog -a`
The `catalog -a` command allows you to add `Author`s to your catalog so that `MangaTantou` can keep track of all the manga created 
by the authors under your management. Once an author is added,`MangaTantou` will monitor the details of the manga they have published, 
including sales data, deadlines, and earnings, making it easy to manage multiple authors at once.

Format: `catalog -a <AUTHOR_NAME>`
* The `Author` to add should not already exist in the catalog.

Examples of usage:
* `catalog -a Kubo Tite`
* `catalog -a Oda Eiichiro`

### Adding Mangas: `catalog -a -m`
The `catalog -a -m` command creates a new `Manga` before associating it with an `Author` in the catalog. By adding mangas, `MangaTantou` can keep track of each title, 
including deadlines and sales data, allowing you to monitor all the works an author is responsible for.

Format: `catalog -a <AUTHOR_NAME> -m <MANGA_NAME>`
* The `Manga` to add should not already exist in the catalog.
* `MangaTantou` will create the associated `Author` if it does not already exist within the catalog.
* Note that two `Manga`s will be considered as different only if they have different titles and authors.

Examples of usage:
* `catalog -a Kubo Tite -m Bleach`
* `catalog -m One Piece -a Oda Eiichiro`

### Deleting Authors and Mangas: `catalog -a -d` and `catalog -a -m -d`
The `catalog -a -d` and `catalog -a -m -d` commands allow you to remove authors and mangas from your catalog in `MangaTantou`. 
This feature is useful for managing your catalog by removing inactive authors or discontinued manga titles.

Format:
* `catalog -a <AUTHOR_NAME> -d`
  * deletes the `Author` and all associated `Mangas` from the catalog.
* `catalog -a <AUTHOR_NAME> -m <MANGA_NAME> -d`
  * only deletes the specific `Manga` associated with the `Author`, leaving the author and their other works in the catalog.


* The `Author` or `Manga` to delete must exist within the catalog.

Examples of usage:
* `catalog -a Kubo Tite -d`
* `catalog -d -a Kubo Tite -m Bleach`

### Add Sales Data to a Manga: `sales`
Adds sales data to an existing manga. This command will overwrite the previous sales data if ran again.
Sales data consists of quantity of manga sold, the unit price per manga, and the 
total revenue. The total revenue is calculated by `MangaTantou`, through the multiplication of the manga's `unitPrice` and
`quantitySold`.

Format: `sales -a <AUTHOR_NAME> -m <MANGA_NAME> -q <QUANTITY_SOLD> -p <PRICE_PER_UNIT>`

* The `QUANTITY_SOLD` must be a non-negative Integer less than 1000000000.
* The `PRICE_PER_UNIT` must be a non-negative Double less than 1000000000.

Examples of usage:

`sales -a Kubo Tite -m Bleach -q 10000 -p 11.90`

`sales -a Izumi Tsubaki -m Gekkan Shoujo Nozaki-kun -q 1700 -p 12.90`

### Add Deadline to a Manga: `schedule`
Adds a deadline to an existing manga. `-b` stands for by-date (in case you are wondering why it is not `-d`, which is used for deleting in other commands).
The deadline is set to 'None' by default. Whenever this is called, the previous deadline is overwritten.

Format: `schedule -a <AUTHOR_NAME> -m <MANGA_NAME> -b <DEADLINE>`

* The `DEADLINE` must be a String.

Examples of usage:

`schedule -a Hirohiko Araki -m Phantom Blood -b January 1, 1987`

`schedule -a Hirohiko Araki -m Stone Ocean -b December 7, 1999`

> **_NOTE:_**
The deadline does not need to follow any set format.
This allows for greater flexibility in setting deadlines (e.g. `20 Jan 2024`, `Tuesday`, `After Mum's bday`, `in 2 days`).
It is up to the user to define what is valid.


### Viewing Authors: `view`
The `view` command allows you to view all the `Author`s in your catalog in a nicely formatted table.

Format: `view`

Example output:
```
no. | Author Name
----------------------------------------------
  1 | Kubo Tite
  2 | Oda Eiichiro
```

### Viewing Mangas and Other Related Information: `view -a [-s] [-b]`
The `view -a` command allows you to view all the `Manga`s authored by the provided `Author` in a nicely formatted table.
If the `-b` flag (short for "by date" or "deadline") is specified, the deadlines of each `Manga` is also shown.
If the `-s` flag (short for "sales data") is specified, the sales data of each `Manga` is also shown.
These flags are additive, so specifying both will lead to both sets of information to be shown.

Format: `view -a <AUTHOR_NAME> [-s] [-b]`

Examples of usage:
* `view -a Kubo Tite`
* `view -a Oda Eiichiro -s`
* `view -a Hirohiko Araki -s -b`

Example output:
```
view -a Hirohiko Araki -s -b
Mangas authored by Hirohiko Araki, Total: 2
no. | Manga Name                               | Deadline             | Unit Price | Units Sold | Revenue
----------------------------------------------------------------------------------------------------------------------
  1 | Phantom Blood                            | January 1, 1987      | 2.50       | 10         | 25.00
  2 | Stone Ocean                              | December 7, 1999     | 9.00       | 50         | 450.00

```

### Exiting the Program: `bye`
To stop using `MangaTantou` and exit the app while saving your data, enter `bye` to the command line. 

### Saving Your Data
There is no need to manually save your data. `MangaTantou` automatically does so for you every time a command that modifies the catalog is performed (e.g. deleting an author/manga, adding sales data),
or when `bye` is entered to close the app. 

### Manually Editing the Data File
`MangaTantou`'s catalog data is saved in a JSON file in the path `[JAR file location]/data/catalog.json`. 
Advanced users can manually update data directly by editing the file.

> **_CAUTION:_**
> It is recommended to make a backup of your data file before manually editing it. 
> To avoid the risk of your data not being restored, exercise caution when editing the file. 
> `MangaTantou` checks for formatting and value errors and tries to discard the corrupted entry while keeping valid information if possible. 
> If that fails, the catalog will be deleted and a new empty one will be used instead.

> **_LIMITATIONS:_**
> - If you decide to manually edit the data file such that an entry contains a field that is impossible to add via normal commands (i.e. authorName `Kubo -a Tite` instead of `Kubo --a Tite`), you <ins>**will not be**</ins> able to access this entry in via normal commands in the future unless this change is corrected.
> - Specifying duplicate key-value pairs in the data file will cause the pair closest to the end of the file to be taken. This is a limitation of the Gson 2.11.0 library. The following snippet will create an author with name "Hirohiko Araki".  
> ```
> {
>   "authorName": "Kubo Tite",
>   "authorName": "Hirohiko Araki",
>   "mangaList": []
> }
> ```

## FAQ
**Q**: How do I transfer my data to another computer?
<br>
**A**: First, exit from `MangaTantou`. Then, copy the `data` and `logs` folder (located in the same folder as the `.jar` file) into the new location of the `.jar` file.  

**Q**: Why does `MangaTantou` not have an edit function?
<br>
**A**: Published authors and mangas in `MangaTantou` are generally not required to change their names due to potential confusion that could be generated with audiences. If the editor still has strong wishes to change the names of authors or mangas, they can do so by editing the `catalog.json` file directly, while following our naming conventions and guidelines.

## Command Summary

| Action         | Format and Examples                                                                                                                         |
|----------------|---------------------------------------------------------------------------------------------------------------------------------------------|
| Add Author     | `catalog -a <AUTHOR_NAME>` <br/> e.g. `catalog -a Kubo Tite`                                                                                |
| Add Manga      | `catalog -a <AUTHOR_NAME> -m <MANGA_NAME>` <br/> e.g. `catalog -a Kubo Tite -m Bleach`                                                      |
| Delete Author  | `catalog -a <AUTHOR_NAME> -d` <br/> e.g. `catalog -d -a Kubo Tite`                                                                          |
| Delete Manga   | `catalog -a <AUTHOR_NAME> -m <MANGA_NAME> -d` <br/> e.g. `catalog -d -a Kubo Tite -m Bleach`                                                |
| Add Sales Data | `sales -a <AUTHOR_NAME> -m <MANGA_NAME> -q <QUANTITY_SOLD> -p <PRICE_PER_UNIT>` <br/> e.g. `sales -a Kubo Tite -m Bleach -q 10000 -p 11.90` |
| Add Deadline   | `schedule -a <AUTHOR_NAME> -m <MANGA_NAME> -b <DEADLINE>` <br/> e.g. `schedule -a Kubo Tite -m Bleach -b Monday November 11`                |
| View Authors   | `view`                                                                                                                                      |
| View Mangas    | `view -a <AUTHOR_NAME> [-b] [-s]` <br/> e.g. `view -a Hirohiko Araki -s -b`                                                                 |
| Exit           | `bye`                                                                                                                                        |
