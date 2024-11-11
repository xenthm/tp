# Donovan Neo Jia Jie - Project Portfolio Page
## Overview of Project
`MangaTantou` is a desktop Command Line Interface (CLI) application intended for editors of manga publishing companies.
It aims to provide a one-stop-shop for all their author and manga management needs.

`MangaTantou` boasts three main features:
1. Managing a team of authors by adding and deleting entries.
2. Managing the manga written by these authors.
3. Viewing the above details in a well-formatted table.

As a computing student, this course project has exposed me to programming in Java, as well as OOP principles.
Furthermore, it has given me invaluable experience in the whole software development life cycle. From defining our
target audience and problem statement, to generating user stories, and actually working together as a development
team to build, test, and deploy a functioning Java application. This has given me the opportunity to apply and hone
the theoretical knowledge learned from the textbook on a real-life product.

## Summary of Contributions
### Code Contribution
Refer to
this [link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=t10-3&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=xenthm&tabRepo=AY2425S1-CS2113-T10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
for my code contributions (functional and test code).

### Enhancements Implemented
#### Argument Extraction with RegEx
I found that the previous method of parsing user inputs was very strict (it required users to enter flags in
a fixed order e.g. `sales -a -m -q -p`), and it also had trouble when flags were included in the provided arguments,
or there were redundant spaces in the input. In trying to improve this function, I had the idea of using RegEx. I came up with a pattern that can extract the relevant information
after a specified flag. More details can be found in the codebase. Now, users have much more flexibility in entering commands and names. 

#### Viewing Data with `view`
Initially, we had a simple `view` system in which every item in the list was printed with the item number prepended. This was adequate for v1.0, when we did not have deadline or sales information for manga. When we added those, we just appended the extra information to the end. This made the lists hard to read, especially if the entries had different lengths. As such, I looked into presenting the information in a tabular form. There are plenty of third-party libraries that can achieve this, but I wanted to try my hand at writing one from scratch. My system allows developers to customize the order of the columns easily, with user support for selective inclusion of columns via the use of flags. It is also easily reusable for other lists, not just those utilized in `MangaTantou`.

#### Data Storage
I used the Gson JSON de/serializer to store data to standardize the way the information is presented in the data file. It decouples the reading of data from our parser. Gson's default settings initially did not work with the way our project stores data (due to circular references between `Author` and `Manga`). Additionally, the default deserializers did not provide the user with enough details about errors in the data file. Therefore, I implemented a custom class attribute exclusion strategy to ignore the reference causing issues. I also wrote custom deserializers for the different data classes used in `MangaTantou` so that error messages could be shown to the user, allowing them to pinpoint where they went wrong in manually editing their data file. 

### Contributions to the UG 
- `view` command
- Saving Data
- Manually Editing Data File
- Command Summary table

### Contributions to the DG 
- Acknowledgements
- Overall data structure of `MangaTantou`, including diagrams
- ArgumentFinder RegEx
- Saving Data, including Storage Structure and diagrams, Storage Behaviour, Gson De/serialization, and Errors in Data File subsections
- Displaying Data, including the Ui Structure and diagrams
- View command
- Product Scope
- Some entries of the User Stories table

### Contributions to Team-Based Tasks
- Set up the project GitHub repository. 
- Updated parts of the DG not related to features (see above). 
- Wrote PlantUML configuration files that can be included to standardize the look of the teams' UML diagrams, while ensuring they follow the format that was taught in this course.
- Reminded the team about code authorship tasks (Code authorship [#49](https://github.com/AY2425S1-CS2113-T10-3/tp/issues/49))
- Tested an alternative workflow to merging upstream changes to feature branches for teammates to use (Test rebasing fork's development branch [#18](https://github.com/AY2425S1-CS2113-T10-3/tp/issues/18))

### Review/Mentoring Contributions
Here are some prominent PRs opened by my teammates I reviewed: [#42](https://github.com/AY2425S1-CS2113-T10-3/tp/pull/42), [#53](https://github.com/AY2425S1-CS2113-T10-3/tp/pull/53), [#54](https://github.com/AY2425S1-CS2113-T10-3/tp/pull/54), [#63](https://github.com/AY2425S1-CS2113-T10-3/tp/pull/63), [#87](https://github.com/AY2425S1-CS2113-T10-3/tp/pull/87), [#98](https://github.com/AY2425S1-CS2113-T10-3/tp/pull/98), and [#109](https://github.com/AY2425S1-CS2113-T10-3/tp/pull/109). 
I also answered teammates' queries on my own PRs: [#44](https://github.com/AY2425S1-CS2113-T10-3/tp/pull/44).

### Contributions Beyond the Project Team
I reviewed another teams' DG ([#8](https://github.com/nus-cs2113-AY2425S1/tp/pull/8#pullrequestreview-2407200428)) and provided detailed suggestions for them to improve. 

I also reported a bug in the course website ([#11](https://github.com/nus-cs2113-AY2425S1/forum/issues/11)), and asked some theoretical questions on the course forum that would help others in their understanding ([#33](https://github.com/nus-cs2113-AY2425S1/forum/issues/33) and [#41](https://github.com/nus-cs2113-AY2425S1/forum/issues/41)). 