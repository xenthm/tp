# Sarah Chow Wan Xuan - Project Portfolio Page
## Overview of Project
`MangaTantou` is a desktop Command Line Interface (CLI) application that serves editors in Manga Publishing Houses! 
Its job is to keep track of authors and their works (known as mangas), monitoring each of the mangas' deadlines and profits.

## Summary of Contributions
### Code Contribution
Refer to
this [link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=t10-3&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=sarahchow03&tabRepo=AY2425S1-CS2113-T10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
for my code and documentation contributions.

### Enhancements Implemented
#### Implementation of Deletion for Manga and Author
In the first iteration [v1.0] of `MangaTantou`, I was in charge of handling the deletion of authors and mangas. My task was to ensure that the command was properly formed by the user and checks for the necessary fields were made, before the command can be run successfully. I also wrote JUnit tests for the delete commands.
> **_NOTE:_**
>- The delete functionality in **v1.0** was `delete -a <AUTHOR_NAME> -m <MANGA_NAME>`, for the deletion of a manga.
>- This functionality has been updated to `catalog -a <AUTHOR_NAME> -m <MANGA_NAME> -d` from **v2.0** onwards.

#### Implementation of Sales Data
From **v2.0** onwards, I was in charge of all tasks related to the adding of sales data. My task was to ensure that the sales command is parsed correctly, performing all the checks necessary. A unique aspect of handling numerical data is having to handle potential overflow of numbers, negative numbers, and even number format errors.

Through this assignment, I was able to exercise most topics taught by the CS2113 course including but not limited to: object-oriented programming, documentation of User and Developer Guide and test creation.

### Contributions to the UG
I wrote the:
- Introduction
- Part of the notes in the Features section
- Part of the FAQ, explaining the lack of an edit function
- Part of the Command Summary section
- `sales` command sections

### Contributions to the DG
I worked on the:
- Command processing sequence diagram
- Sales command section
- User stories, specifically on deletion and sales data functions

### Contributions to Team-Based Tasks
- Testing and identifying major bugs in the project
- Sending out reminders on critical tasks from the course website
- Reviewed and commented on my groupmate's PRs
- Organized issues from the PE Dry Run
- Took charge of writing-heavy tasks

### Review/Mentoring Contributions
`MangaTantou` PRs I reviewed (with non-trivial comments): [#99](https://github.com/AY2425S1-CS2113-T10-3/tp/pull/99), [#73](https://github.com/AY2425S1-CS2113-T10-3/tp/pull/73) and [#24](https://github.com/AY2425S1-CS2113-T10-3/tp/pull/24)

### Contributions Beyond the Project Team
As a course exercise, I also reviewed other team's PRs: [TutorLink](https://github.com/nus-cs2113-AY2425S1/tp/pull/13)
