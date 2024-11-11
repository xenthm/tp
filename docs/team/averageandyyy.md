# Cheng Jia Wei Andy - Project Portfolio Page
## Project: MangaTantou
MangaTantou is a CLI application that allows manga editors to track their employees (manga authors) 
and various details about their works (mangas), such as their deadlines and sales revenue. The application
is targetted towards editors that can type fast, allow them to quickly create and look up entries of interest!

## Summary of Contributions
- **New Feature**: Addition of Authors and Mangas to Catalog
  - What it does: This feature allows editors to easily add both authors and their respective works (mangas) to an 
  organized catalog using the catalog command. Through simple command inputs, editors can quickly populate a record 
  of existing and prospective authors, as well as their mangas, for easy reference and monitoring.
  - Justification: Authors and their manga works represent core assets within the manga industry. For editors, 
  managing these assets is essential to making informed business decisions, planning collaborations, and tracking 
  work progress. The ability to efficiently catalog author and manga details helps editors maintain a clear overview 
  of their roster and project timelines, supporting both strategic planning and day-to-day management.
  - Highlights: Recognizing that editors often work under pressure and may occasionally make mistakes, MangaTantou 
  includes built-in checks to prevent errors like duplicate entries. For instance, if an editor tries to add an 
  author or manga that already exists in the catalog, the application will alert them to the duplication. This 
  functionality enhances data integrity within the catalog and reduces potential confusion caused by redundant entries.
- **General Contributions**: Base Implementation of Core Classes and Application Structure
  1. **Abstract Classes and Inheritance Patterns**: I developed foundational abstract classes such as `Command`, 
  `ArgumentFinder` and `TantouException`, which served as templates for implementing concrete subclasses 
  (e.g., AddAuthorCommand and AuthorArgumentFinder). By establishing these abstractions, I provided a clear and 
  standardized approach for further development of functionalities within the application. This approach not only made the 
  code more modular and reusable but also reduced redundant code by centralizing common logic, which my teammates 
  could then easily extend in their respective features, simplifying the development of addtional functionalities.
  2. **Program Structure and Packaging for Enhanced Organization**: Beyond coding individual features, I organized the
  project's structural layout by dividing it into several logical packages, including but not limited to the `commands` package for 
  command-related classes and the `constants` package for storing shared constants. By mapping out the initial structure and defining 
  interactions between core components, I facilitated a cohesive codebase where team members could develop their features independently 
  while ensuring compatibility. This structured approach also gave team members a clearer understanding of the application’s organization and dependencies, 
  streamlining their development process by establishing a consistent pattern for adding new components. This modular design allowed for incremental feature 
  development while maintaining the cohesiveness of our codebase, resulting in a more efficient integration process.
- **Others**:
  - In **v1.0**, I had tried using the Apache Commons CLI Library as part of `MangaTantou`'s parsing architecture. While it worked as intended,
    the functionalities provided by the library were not as defensive as we had liked and I led the `Parser` [rehaul](https://github.com/AY2425S1-CS2113-T10-3/tp/pull/63) to a
    simpler but more defensive implementation.
- **Code Contributed**: [RepoSense Link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=andy&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20&tabOpen=true&tabType=authorship&tabAuthor=averageandyyy&tabRepo=AY2425S1-CS2113-T10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
- **Documentation**:
  - **User Guide**
    - Added details regarding the usage of the `catalog` command [#88](https://github.com/AY2425S1-CS2113-T10-3/tp/pull/88/)
  - **Developer Guide**
    - Added implementation details and development guidelines regarding the `Command` class and subclasses [#88](https://github.com/AY2425S1-CS2113-T10-3/tp/pull/88/)
    - Added implementation details regarding the addition and deletion of `Author`s and `Manga`s [#92](https://github.com/AY2425S1-CS2113-T10-3/tp/pull/92)
    - Added implementation details and development guidelines regarding the general parsing architecture [#109](https://github.com/AY2425S1-CS2113-T10-3/tp/pull/109)
    - Added details regarding testing [#123](https://github.com/AY2425S1-CS2113-T10-3/tp/pull/123)
  - **Project Management**
    - Coordinated internal milestones and tracked team progress to ensure deadlines were met
    - Organized and led team meetings to review project status, address challenges, and align on next steps
  - **Community**
    - PRs reviewed (with non-trivial comments): [#29](https://github.com/AY2425S1-CS2113-T10-3/tp/pull/29), [#44](https://github.com/AY2425S1-CS2113-T10-3/tp/pull/44), [#48](https://github.com/AY2425S1-CS2113-T10-3/tp/pull/48)
    - Contributed to forum discussions (examples: [1](https://github.com/nus-cs2113-AY2425S1/forum/issues/14#issuecomment-2335161310), [2](https://github.com/nus-cs2113-AY2425S1/forum/issues/23), [3](https://github.com/nus-cs2113-AY2425S1/forum/issues/35))