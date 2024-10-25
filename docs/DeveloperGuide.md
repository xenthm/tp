# Developer Guide

## Acknowledgements
Tantou's structure has been greatly inspired by the developer's respective iPs as listed below:
1. [Donovan](https://github.com/xenthm/ip)
2. [Sarah](https://github.com/sarahchow03/ip)
3. [Ian](https://github.com/iaso1774/ip)
4. [Andy](https://github.com/averageandyyy/ip)

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
### AddAuthorCommand
#### Overview
The `AddAuthorCommand` is responsible for adding new `Author`s to `Tantou`. The command creates a new `Author` instance and verifies its existence. If it
is a new and undocumented `Author`, it is then added to `Tantou`'s `AuthorList`, allowing the user to keep track
of their manga authors. The `AuthorList` is saved via `Storage` for data persistency.
#### Interaction
The following diagram illustrates the interactions that take place when the
user provides `"catalog -a Kubo Tite"` as an input.
![add author sequence diagram](/docs/uml/addauthor.png)
If the `Author` instance already exists, a `TantouException` is thrown, informing the user that
they are already tracking this employee.

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
|v2.0|user|find a to-do item by name| locate a to-do without having to go through the entire list   |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
