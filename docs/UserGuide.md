# User Guide

## Introduction

Overwhelmed by all your editorial tasks at the publishing company? Sick of having to manually keep track of all the work
that your ~~slaves~~ manga artists have to do? Fret not, as `Tantou` is here!

`Tantou` keeps tracks of the artists under your charge, showing you all mangas they have written, and their deadlines. 
As an editor, it is also your right to know how much your ~~slaves~~ artists are earning! `Tantou` takes note of how
many copies have been sold by each of the mangas under your charge and calculates the revenue for you, increasing your 
efficiency at work.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 17 or above installed.
1. Down the latest version of `Tantou` from [here](http://link.to/duke).

## Features

### Adding Authors: `catalog -a`

The `catalog -a` command allows you to add `Author`s to your catalog so that Tantou can keep track of all the manga created 
by the authors under your management. Once an author is added, Tantou will monitor the details of the manga they have published, 
including sales data, deadlines, and earnings, making it easy to manage multiple artists at once.

Format: `catalog -a [AUTHOR_NAME]`

* The `AUTHOR_NAME` must not be longer than 40 characters.
* The `Author` to add should not already exist in the catalog.

Examples of usage:
* `catalog -a Kubo Tite`
* `catalog -a Oda Eiichiro`


### Adding Mangas: `catalog -a -m`

The `catalog -a -m` command creates a new `Manga` before associating it with an `Author` in the catalog. By adding mangas, Tantou can keep track of each title, 
including deadlines and sales data, allowing you to monitor all the works an author is responsible for.

Format: `catalog -a [AUTHOR_NAME] -m [MANGA_NAME]`

* The `AUTHOR_NAME` must not be longer than 40 characters.
* The `MANGA_NAME` must not be longer than 40 characters.
* The `Manga` to add should not already exist in the catalog.
* `Tantou` will create the associated `Author` if it does not already exist within the catalog.
* Note that two `Manga`s will be considered as different only if they have different titles and authors.

Examples of usage:
* `catalog -a Kubo Tite -m Bleach`
* `catalog -m One Piece -a Oda Eiichiro`

### Deleting Authors and Mangas: `catalog -a -d` and `catalog -a -m -d`

The `catalog -a -d` and `catalog -a -m -d` commands allow you to remove authors and mangas from your catalog in `Tantou`. 
This feature is useful for managing your catalog by removing inactive authors or discontinued manga titles.

Format:
* `catalog -a [AUTHOR_NAME] -d`
  * deletes the `Author` and all associated `Mangas` from the catalog.
* `catalog -a [AUTHOR_NAME] -m [MANGA_NAME] -d`
  * only deletes the specific `Manga` associated with the `Author`, leaving the author and their other works in the catalog.


* The `Author` or `Manga` to delete must exist within the catalog.


Examples of usage:
* `catalog -a Kubo Tite -d`
* `catalog -d -a Kubo Tite -m Bleach`


### Add sales data to a manga: `sales`
Adds sales data to an existing manga. Sales data consists of quantity of manga sold, the unit price per manga, and the 
total revenue. The total revenue is calculated by `Tantou`, through the multiplication of the manga's `unitPrice` and
`quantitySold`.

Format: `sales -a [AUTHOR_NAME] -m [MANGA_NAME] -q [QUANTITY_SOLD] -p [PRICE_PER_UNIT]`

* The `QUANTITY_SOLD` must be a positive Integer less than 1000000000.
* The `PRICE_PER_UNIT` must be a Double less than 1000000000.

Example of usage: 

`sales -a Kubo Tite -m Bleach -q 10000 -p 11.90`

`sales -a Izumi Tsubaki -m Gekkan Shoujo Nozaki-kun -q 1700 -p 12.90`


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
