# Jillo is a Project management software  

## This repository is for the API of Jillo, the code is written in Java with Spring boot

### Initialization
- Clone the repo `git clone https://github.com/tanjonaaa/Jillo-API.git`
- Open the project with your IDE and update the dependencies (We use Maven)
- Set up the database by executing the `init.sql` file in the `MCD` directory
- Change database credentials in `src/main/resources/application.properties`
- Start the server by running the `JilloApplication` file in `src/main/java/co/tanjona/man/jillo`

### Features
- CRUDs:
  - CRUD users: `CREATE - READ - UPDATE - DELETE users`
  - CRUD projects: `CREATE - READ - UPDATE - DELETE projects`
  - CRUD tasks: `CREATE - READ - UPDATE - DELETE tasks`
  - CRUD status: `CREATE - READ - UPDATE - DELETE status`
- Add `users` to a project
- Remove `users` from a project
- Assign `tasks` to one or multiple `users` 
- Change the status of a `task` (Planned - In progress - Done)

