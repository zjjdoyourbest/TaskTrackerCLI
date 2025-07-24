# Task CLI Application

This is a simple command-line interface (CLI) application for managing tasks. You can add, update, delete, mark, and list tasks directly from the terminal.

## Features

- **Add a Task:** Add a new task with a description.
- **Update a Task:** Update the description of an existing task.
- **Delete a Task:** Remove a task by its ID.
- **Mark a Task:** Mark a task as "in progress" or "done."
- **List Tasks:** List all tasks or filter them by status (e.g., `todo`, `in progress`, `done`).

## Installation

1. **Clone the repository:**
  
  ```bash
  git clone https://github.com/lephuocloc1729/task_tracker_cli](https://github.com/zjjdoyourbest/TaskTrackerCLI
  cd TaskTrackerCLI
  ```
  
2. **Compile the source code:**

  ```bash
  mvn clean package
  ```
  
3. **Run the application:**
  
  ```bash
  java -jar target/TaskTrackerCLI-1.0-SNAPSHOT.jar
  ```
  
  ## Usage
  
  ```bash
  # Adding a new task
  task-cli add "Buy groceries"
  # Output: Task added successfully (ID: 1)
  ```
  
```bash
# Updating a task

task-cli update 1 "Buy groceries and cook dinner"

# Output: Task updated successfully (ID: 1)

# Deleting a task

task-cli delete 1

# Output: Task deleted successfully (ID: 1)

# Marking a task as in progress

task-cli mark-in-progress 1

# Output: Task marked as in progress (ID: 1)

# Marking a task as done

task-cli mark-done 1

# Output: Task marked as done (ID: 1)

# Listing all tasks

task-cli list

# Output: List of all tasks

# Listing tasks by status

task-cli list todo
task-cli list in-progress
task-cli list done

#exit 

exit
```
