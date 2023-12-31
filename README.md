# Jillo is a Project management software  

## This repository is for the API of Jillo, the code is written in Java with Spring boot

### Initialization
- Clone the repo `git clone https://github.com/tanjonaaa/Jillo-API.git`
- Open the project with your IDE and update the dependencies (We use Maven)
- Update the following files according to your Database Credentials : 
  - `src/main/resources/application.properties`
  - `src/main/java/config/DataSourceConfig.java`
- Set up the database by executing the sql files in the `MCD` directory **IN THE FOLLOWING ORDER** `init.sql`>`migrations.sql`>`mocks.sql`(This one is optional, only if you need some mocks)
- Start the server by running the `JilloApplication` file in `src/main/java/co/tanjona/man/jillo`

### Features
- CRUDs:
  - CRUD users: `CREATE - READ - UPDATE - DELETE users`
  - CRUD projects: `CREATE - READ - UPDATE - DELETE projects`
  - CRUD tasks: `CREATE - READ - UPDATE - DELETE tasks`
  - CRUD status: `CREATE - READ - UPDATE - DELETE status`
- Add `users` to a project's collaborators
- See collaborators of a project
- See all the projects where a user is a `collaborator`
- Assign `tasks` to one or multiple `users`
- Change the status of a `task` (Draft - Planned - In progress - Done -...)

