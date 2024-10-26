# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

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

| Version | As a ...               | I want to ...                                  | So that I can ...                                        |
|---------|------------------------|------------------------------------------------|----------------------------------------------------------|
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
