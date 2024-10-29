# Developer Guide

# Acknowledgements
Tantou's structure has been greatly inspired by the developer's respective iPs as listed below:
1. [Donovan](https://github.com/xenthm/ip)
2. [Sarah](https://github.com/sarahchow03/ip)
3. [Ian](https://github.com/iaso1774/ip)
4. [Andy](https://github.com/averageandyyy/ip)

# Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
## Overall Architecture
### Commands Overview
![Command Inheritance](/docs/uml/images/Command.png)
The current list of viable `Commands` are as follows:

1. `AddAuthorCommand`
2. `AddMangaCommand`
3. `DeleteAuthorCommand`
4. `DeleteMangaCommand`
5. `ViewAuthorsCommand`
6. `ViewMangasCommand`
7. `GreetCommand`
8. `ByeCommand`
#### Command Structure

All child `Command` classes must inherit from the abstract `Command` class. Each child class is required to implement the abstract `execute` method.

While child classes may or may not modify the `AuthorList`, they are encouraged to utilize the `Ui` class to interact with users, such as displaying success messages.

#### Guidelines for Future Developers

When adding new command classes, developers must follow the same method of implementation by 
inheriting from the abstract `Command` class. Ensure that each new command class includes 
an implementation of the `execute` method and appropriately interacts with the `Ui` class 
for user feedback. Additionally, developers should update the `Parser` class to gather the 
relevant arguments from the user for their commands.

## Interacting with the user
### AddAuthorCommand
#### Overview
The `AddAuthorCommand` is responsible for adding new `Author`s to `Tantou`. The command creates a new `Author` instance and verifies its existence. If it
is a new and undocumented `Author`, it is then added to `Tantou`'s `AuthorList`, allowing the user to keep track
of their manga authors. The `AuthorList` is saved via `Storage` for data persistency.
#### Interaction
The following diagram illustrates the interactions that take place when the
user provides `"catalog -a Kubo Tite"` as an input.
![add author sequence diagram](/docs/uml/images/AddAuthorSequence.png)
If the `Author` instance already exists, a `TantouException` is thrown, informing the user that
they are already tracking this employee.

### AddMangaCommand
#### Overview
The `AddMangaCommand` is responsible for adding new `Manga`s to `Author`s in `Tantou`. The command first creates a new `Author` and `Manga` instance.
If the newly created `Author` is undocumented by `Tantou`, the `Author` is added to the `AuthorList` and the newly created `Manga` is added to
the `Author`'s `MangaList`. If the `Author` already exists, `Tantou` will check for the existence of the newly created `Manga`. If there is an existing
association between the `Manga` and `Author`, a `TantouException` is thrown, informing the user that they are adding an existing `Manga`. Otherwise,
the `Manga` is similary added to the `Author`'s `MangaList` and the current state of `AuthorList` is saved via `Storage` for
data persistency.
#### Interaction
The following diagram illustrates the interactions that take place when the
user provides `"catalog -a Kubo Tite -m Bleach"` as an input.
![add manga sequence diagram](/docs/uml/images/AddMangaSequence.png)

### DeleteAuthorCommand
#### Overview
The `DeleteAuthorCommand` is responsible for removing `Author`s from `Tantou`. The command creates a new `Author` instance and verifies its existence. If it
is a new and undocumented `Author`, a `TantouException` is thrown, informing the user that this `Author` does not exist and hence cannot be removed.
Otherwise, the `Author` is removed from the `AuthorList`, which is then saved via `Storage` for data persistency.
#### Interaction
The following diagram illustrates the interactions that take place when the
user provides `"catalog -a Kubo Tite -d"` as an input.
![delete author sequence diagram](/docs/uml/images/DeleteAuthorSequence.png)

### DeleteMangaCommand
#### Overview
The `DeleteMangaCommand` is responsible for removing `Manga`s from `Author`s in `Tantou`. The command first creates a new `Author` and `Manga` instance.
If the newly created `Author` is undocumented by `Tantou`, a `TantouException` is thrown, informing the user that this `Author` does not exist and the `Manga` cannot be removed.
If the `Author` instead exists, `Tantou` will check for the existence of the newly created `Manga`. If there is no existing
association between the `Manga` and `Author`, a `TantouException` is thrown, informing the user that they are deleting a non-existing `Manga`. Otherwise,
the `Manga` is removed from the `Author`'s `MangaList` and the current state of `AuthorList` is saved via `Storage` for
data persistency.
#### Interaction
The following diagram illustrates the interactions that take place when the
user provides `"catalog -a Kubo Tite -m Bleach -d"` as an input.
![add manga sequence diagram](/docs/uml/images/DeleteMangaSequence.png)

### AddSalesCommand
#### Overview
The AddSalesCommand is responsible for adding sales data to a Manga. The Sale data consists of three attributes,  
`quantitySold`, `unitPrice` and `totalRevenue`. The `quantitySold` and `unitPrice` are inputs from the user, while
`totalRevenue` is calculated by multiplying `quantitySold` and `unitPrice`.


For the AddSalesCommand to be successful, the manga that the sales data is associated with must exist. If the `sales`
command is successful, the `Sales` data is then saved via Storage.

![mangasales_class.png](uml%2Fimages%2Fmangasales_class.png)
#### Interaction

The following sequence diagram illustrates the interactions that occur when the parser creates a new `AddSalesCommand`.

![addsalesdata.png](uml%2Fimages%2Faddsalesdata.png)

The following object diagram illustrates object structure after the above interaction is successfully run
with the input `sales -a Kubo Tite -m Bleach -q 10000 -p 11.90`.

![mangasales_object.png](uml%2Fimages%2Fmangasales_object.png)


## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...                                             |
|--------|----------|---------------|---------------------------------------------------------------|
|v1.0|editor of a manga company|add authors to a list| keep track of my authors and potentially their work progress. |
|v1.0|editor|add mangas to their respective authors| keep track of what each of my authors are working on.         |
| v1.0    | editor                 | delete authors under my charge                 | discharge under-performing authors under my charge       |
| v1.0    | editor                 | delete mangas under an author                  | discontinue a series that is unpopular with the audiences |
| v2.0    | business-minded editor | add the quantity of copies sold for a manga    | track the manga's popularity amongst audiences           |
| v2.0    | business-minded editor | add the unit price of each copy sold for manga | calculate the revenue earned by the series               |
| v2.0    | editor                 | delete the quantity and unit price for a manga | reset the sales data from the manga                      |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
