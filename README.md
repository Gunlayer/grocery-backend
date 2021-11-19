# Introduction 
This is the source code behind the Grocery Shop web-site, which is being developed as an internal project. 

# Getting Started
After cloning the source code, please check you have following installed:
+ Java 11;
+ Maven 3.8.3;
+ Tomcat 9 (optional);
+ Postman (optional);
+ Environment to open the source code and to access the database;

After opening the project, please, check in `pom.xml` and `application.properties` the database you are using.
It's suggested you use "local" profile with following credentials (H2DB):
+ user: root
+ passcode:
+ url: jdbc:h2:file:./test;DB_CLOSE_ON_EXIT=FALSE

Databases under "remote" profile have restrictions to use. _Demo_ version is saved for QA team, while _Dev_ version, kept for UI team, shall have as few interactions as possible.
Only final changes from _develop_ branch should be migrated into _Dev_-DB.

After you set the profile, update Maven dependencies.

!! Please remember to switch back to "remote" before committing and merging in _develop_ branch or just choose not to add the file to the commit. Thank you!


To perform migrations, it's a good choice to apply Maven `flyway:clean` first.
After that, the application is ready to be run. 

Once you run the application, you can check [documentation](http://localhost:8080/api/swagger-ui.html) or `specifications.json` file to see the functionality which application offers.

# Build and Test
+ While writing the code, please use the [Standard Naming Conventions](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html).
+ For tests, the internal convention should be discussed. 
+ Specifications and documentation should follow already existing convention.
+ Any changes in database (adding new data, editing existing, ect.) must be performed in separate migration files, named correspondingly.
+ Before pushing any changes, please use Maven install, so you make sure your version of application and tests run. Even if you changed just one line. Even if you changed just a symbol.
+ Commit early and commit often is a good approach locally, but before pushing, please, consider squashing the commits. As well, amend commits are preferred for a branch in PR _(rebasing multiple commits hurts, guys)_.

# Contribute
Let's not give away our approvals so recklessly. Let's try to review each other's code. At least use that thingamajig and run both app and tests. 

Feel free to comment the source code, but please be aware the dev-team will be reluctant to change it. :) Have a great day!

If you want to learn more about creating good readme files then refer the following [guidelines](https://docs.microsoft.com/en-us/azure/devops/repos/git/create-a-readme?view=azure-devops). You can also seek inspiration from the below readme files:
- [ASP.NET Core](https://github.com/aspnet/Home)
- [Visual Studio Code](https://github.com/Microsoft/vscode)
- [Chakra Core](https://github.com/Microsoft/ChakraCore)