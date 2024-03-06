# Reflection 1

Clean code principles as well as secure coding practices have been applied to my codes. As I have been doing ever since I started coding, I've been naming variables properly that refer to their usage and true functions, as well as writing it in a camel format, making it look and read better. And I've utilized git branches to organize the features set within the application.

1. I think unit tests are necessary when time is available. As in, when in a hurry, one must make the necessary unit tests such that at the very least the code is working properly and as it should be. Not only that but checking the necessary tests and work our way there is, what I think, the best way to do it. I.e. core features first and then secondary features, etc.

2. I believe the cleanliness of the code of the new functional test suite will need to be the same or rather, use the same name variables as the ones from before. One might say, we can reuse it, and repurpose it. Some issues might pop up such as inconsistencies with the naming conventions, or variable namings, that makes it much harder to discern the true purpose of the original code. It is of the utmost importance to think of the cleanliness of the code whenever possible.

# Reflection 2
1. Several aspects needed fixing, although none were critical. Going into detail on each might seem unnecessary. One notable issue was the presence of unnecessary imports, so we focused on tidying up the code to improve efficiency. We also simplified the system's structure to boost performance while maintaining functionality. Dealing with these issues helped things run more smoothly and boosted productivity in solving the underlying problems.

2. After examining the CI/CD workflows, I've noticed that the current setup effectively follows its intended procedures. This observation stems from the smooth execution seen whenever adjustments are made to the codebase. Specifically, when changes are initiated, the system promptly runs automated tests as part of the Continuous Integration (CI) phase. Then, it smoothly moves into the Continuous Deployment (CD) phase, deploying the updated code automatically. This coordinated workflow highlights the successful integration and operation of the CI/CD pipeline in the development setup. Overall, the seamless coordination of these procedures boosts development efficiency and guarantees swift deployment of high-quality code updates.

Link: https://adprog-eshop-infinitum-peakfiction.koyeb.app/

# Reflection 3
## Principles Applied in Project
1. SRP - Meaning each class or module has a single responsibility or reason to change. e.g. createProductPage method in ProductController.java is responsible for rendering the create product page.
2. OCP - Meaning the project is designed to be open for modifications and extensions. e.g. new methods can be added to handle product search requests without modifying the exiting CRUD Operations
3. LSP - Meaning the code must be designed in a way that allows for the interchangeability of implementations. While there are no direct subclassing relationships in the provided code, the controller methods accept and return objects based on interfaces (ProductService, CarService), allowing for interchangeable use of different implementations.
4. ISP - Meaning the interfaces are tailored to specific client needs, making sure that the clients aren’t forced to depend on methods that they don’t use.
5. DIP - Meaning high level modules depend on abstractions rather than concrete implementations. Which allows for flexibility and easier testing. Which means each dependencies can be easily swapped out with different implementations.

## Advantages of Using SOLID Principles
- Flexibility and Maintainability:
    - By using SOLID principles, the code or project can be easily maintainable and very flexible. Adding new features would be a cinch and can be done without risking side effects.
- Testability:
    - It makes the codebase and overall project more testable for unit tests etc. And it can be written more effectively by mocking dependencies and focusing on testing individual components in isolation
- Scalability:
    - It promotes modular designs that can facilitate the scalability of the project as the project grows. Same as the first point, the codebase can grow by adding new modules or components without changing much of the existing code, or risking side effects.
- Code Reusability:
    - It can lead to reusable codes, i.e. service implementations can be reused across different parts of the project.
- Cool Name:
    - SOLID reminds you of so many things including the Code Name for the Legendary Soldier; Solid Snake from the Metal Gear Solid series. Imagine telling other people your codebase uses SOLID while they don’t.

## Disadvantages of NOT Using SOLID Principles
- Rigidity and Fragility, Dangers in Scalability:
    - Once not using SOLID, the code won’t be as easily maintainable or flexible. Adding anything, including scaling the project might cause problems in the long run.
- Testing Difficulties:
    - Non-SOLID projects won’t be as easy to test. Creating tests for it would also be hard, leading to more complex and brittle test scenarios.
- Not as Reusable:
    - Codebase that doesn’t follow SOLID may suffer from poor modularity, reducing its chances and opportunities for its codes to be reused. Meaning, duplicated code, and increased development effort in the future.

# Reflection 3
1. TDD Flow Reflection: The TDD flow has been useful for ensuring code functionality, but improvements can be made. Next time, I'll focus on breaking down requirements into smaller tasks and writing tests for each, ensuring better coverage and clearer objectives.

2. F.I.R.S.T. Principle Reflection: The tests have partially followed the F.I.R.S.T. principle, but there's room for improvement. Next time, I'll focus on making tests more independent, ensuring they run in any order, and refining assertions for clearer feedback.