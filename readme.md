# Worldline Back-End Test

Create a simple REST service for a task board that allows a user to create and manage different lists of tasks which
are persisted between devices. The application should implement the following user stories:

1) As a user, I can view all lists and tasks which have been created
2) As a user, I can create an empty list with a name property
3) As a user, I can add new tasks to an existing list with a name and description property.
4) As a user, I can update the name and description of a tasks within a list
5) As a user, I can delete a task from a list
6) As a user, I can delete an entire list with all its tasks
7) As a user, I can move tasks to a different list

The user stories are in priority order, please complete as many as you can but don’t spend more than 3 hours on the
task. This test will be used for further discussion during the technical interview, so it’s not graded based on
completion. Please write the application in Java. We will be reviewing how you structure the new applications, which
processes, practices and standards you put in place. Some of the things we may look for are:

- Test coverage and types of tests
- Architectural design choices
- How to build and run the application (Docker? Directly on host? Cloud provider?)
- Relational or non-relational database
- Developer onboarding experience

When you are finished with everything, please push the code to a public git repository. Include a README file with
directions on how to install and start the application. Then send us the link. Thank you and we do hope you will have
fun doing this assignment!

## Running the application
The application can be run from the console with `./gradlew BootRun`. No special arguments are needed.
This is a pretty basic setup. The app is configured to run on `localhost:8080` (with tests being run on a random port). 
The ports can be changed in `application.properties`.

### Endpoints
- GET: `http://localhost:8080/api/v1/tasklist/all?include_tasks=true`
    - This returns a list containing all of the taskLists in the database.
    - Setting `include_tasks` to `true` will return the taskLists and the associated tasks.
    - Setting `include_tasks` to `false` will return only the taskLists.

- POST: `http://localhost:8080/api/v1/tasklist/create`
    - This creates a new taskList. Required parameters are a name for the task list and the name of the user inserting 
        the task list. The users "Alice", "Bob", and "Charlie" are inserted into the database on startup. Any of those 
        users can be used to insert a new list. 
    - Example:
        ```
        {
	        "list_name": "List Name Goes Here",
	        "user_name": "Bob"
        }
        ```
  - This will return 400 "Bad Request" if the body is empty, `list_name` is missing, or `user_name` is missing. 
 

## Notes
- The code test uses an in-memory database. On startup, the database is recreated by Flyway, using scripts found in 
`src/main/resources/db/migration/`.
- `users.user_display_name` is unique. This is to provide a shortcut for inserting new data.
- Due to time constraints, this project is not set up to run directly on the host.

## Assumptions
- Setting up the project and infrastructure (github, gradle, Spring Boot, etc) aren't considered as part of the time 
    limit.
- Writing this readme doesn't count towards the time limit.

## Things that could be improved on
- 3 hours is not enough time to showcase my test-heavy code style. I tried to showcase it with the`createTaskList` 
    tests, however there isn't much to test. 
- `task_list.name` table should hve been unique; simply to reduce potential confusion with names.
