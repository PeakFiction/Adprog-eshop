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
1. Percival's Questions

**Do I have enough functional tests to reassure myself that my application really works, from the point of view of the user?**
There's always room for improvement as you will never know which tests cover which certain situations or cases that we didn't take account for. Although at the moment, these functional tests should suffice as a surface-level starter.

**Am I testing all the edge cases thoroughly?**
From what I did, I should have been testing all the edge cases thoroughly, such as testing for valid or invalid voucher codes, setting payment statuses and whatnot. Albeit, there is room for improvement, always. But it is also good to remember that thinking of all possible edge cases is challenging, which is why user's feedback would be very helpful.

**Do I have tests that check whether all my components fit together properly? Could some integrated tests do this, or are functional tests enough?**
I have made individual unit tests for individual components such as PaymentService and PaymentRepository. I am sure that these will work in an isolated environment. Although, I may have not properly test it if they will work together in a complete and integrated environment. Functional tests might work to give some assurance that the components are working together properly, but they might not cover all integration scenarios in detail. They're essential, but not sufficient enough on their own.

**Are my tests giving me the confidence to refactor my code, fearlessly and frequently?**
I'd like to think that these tests are well made enough such that if I were to refactor my code, I can do it effortlessly, fearlessly and frequently. By having a suite of tests, I'd be able to make changes and refactor the code, without making much of a mess to my original codebase. If there are errors, the plethora of tests will be able to detect it.

**Are my tests helping me to drive out a good design? If I have a lot of integration tests but less unit tests, do I need to make more unit tests to get better feedback on my code design?**
Yes, having more unit tests would provide better feedback on individual components, guiding a more modular and maintainable design.

**Are my feedback cycles as fast as I would like them? When do I get warned about bugs, and is there any practical way to make that happen sooner?**
The feedback cycles could be faster, especially if bugs are discovered late in the development process. Writing more focused unit tests and automating test execution can help detect bugs sooner.

**Is there some way that I could write faster integration tests that would get me feedback quicker?**
Optimizing the setup and teardown process for integration tests and using mock objects or test doubles can help reduce execution time and provide quicker feedback.

**Can I run a subset of the full test suite when I need to?**
Yes, by organizing tests into categories or groups and using test runners or frameworks that support selective test execution, one would be able to run specific subsets of the test suite as needed.

**Am I spending too much time waiting for tests to run, and thus less time in a productive flow state?**
Although the tests doesn't take too long for it to finish running. If they do practicing a parallel test execution, optimising test setup and teardown, and using test isolation techniques can help reduce waiting time for the tests.










2. F.I.R.S.T. Principle Reflection:
As per FIRST, they should be implemented properly as they're:
- Fast: My tests execute quickly, which is crucial for maintaining a fast feedback loop during development.
- Isolated: Each test is isolated from the others, meaning they don't depend on each other's state or execution order. However, I could further enhance isolation by using more mocks or stubs to isolate dependencies completely.
- Repeatable: My tests should produce the same results each time they are run. I have achieved this by using fixed inputs and mocks/stubs for external dependencies. However, I need to ensure that my tests are not affected by external factors such as environment configuration.
- Self-validating: My tests assert the expected behavior of the code, providing clear pass/fail feedback. However, I could improve the readability of my test assertions by using more descriptive messages.
- Timely: I have written tests alongside the development of my code, which ensures that any issues are identified early. However, I need to ensure that I continue to maintain and update my tests as the codebase evolves