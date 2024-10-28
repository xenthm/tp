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

### Add sales data to a manga: `sales`
Adds sales data to an existing manga. Sales data consists of quantity of manga sold, the unit price per manga, and the 
total revenue. The total revenue is calculated by `Tantou`, through the multiplication of the manga's `unitPrice` and
`quantitySold`.

Format: `sales -a [AUTHOR_NAME] -m [MANGA_NAME] -q [QUANTITY_SOLD] -p [PRICE_PER_UNIT]`

* The `QUANTITY_SOLD` must be a positive integer less than 1000000000.
* The `PRICE_PER_UNIT` must be a double less than 999999999.

Example of usage: 

`sales -a Kubo Tite -m Bleach -q 10000 -p 11.90`

`sales -a Izumi Tsubaki -m Gekkan Shoujo Nozaki-kun -q 1700 -p 12.90`


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
