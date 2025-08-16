package worldline.service;

import java.util.List;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import worldline.exception.BadDataException;
import worldline.service.model.TaskListModel;
import worldline.service.model.TaskModel;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class TaskListServiceTest {

    @Autowired
    public TaskListService taskListService;

    @Autowired
    private Flyway flyway;

    @AfterEach
    public void tearDown() {
        flyway.clean();
        flyway.migrate();
    }

    // getAllTaskLists tests:
    @Test
    public void getAllTaskListsShouldReturnAllTaskListsWithItems() {
        List<TaskListModel> taskLists = taskListService.getTaskListModel(true);

        assertNotNull(taskLists);
        assertEquals(3, taskLists.size());

        TaskListModel team1TodoList = taskLists.getFirst();
        assertEquals("Team One TODO List", team1TodoList.listName());
        assertNotNull(team1TodoList.id());
        assertNotNull(team1TodoList.createdDate());
        assertEquals("Alice", team1TodoList.createdByUserName());
        assertNull(team1TodoList.updatedDate());
        assertNull(team1TodoList.updatedByUser());
        assertNull(team1TodoList.updatedByUserName());

        assertNotNull(team1TodoList.tasks());
        assertEquals(4, team1TodoList.tasks().size());

        TaskModel team1TodoTaskOne = team1TodoList.tasks().getFirst();
        assertEquals("Some Task One", team1TodoTaskOne.taskName());
        assertNotNull(team1TodoTaskOne.createdDate());
        assertEquals("Charlie", team1TodoTaskOne.createdByUserName());
        assertNull(team1TodoTaskOne.updatedDate());
        assertNull(team1TodoTaskOne.updatedByUser());
        assertNull(team1TodoTaskOne.updatedByUserName());
        assertEquals("Task one created for `Team One TODO List`", team1TodoTaskOne.taskDescription());

        TaskModel team1TodoTaskTwo = team1TodoList.tasks().get(1);
        assertEquals("Some Task Two", team1TodoTaskTwo.taskName());
        assertNotNull(team1TodoTaskTwo.createdDate());
        assertEquals("Alice", team1TodoTaskTwo.createdByUserName());
        assertNull(team1TodoTaskTwo.updatedDate());
        assertNull(team1TodoTaskTwo.updatedByUser());
        assertNull(team1TodoTaskTwo.updatedByUserName());
        assertEquals("Task two created for `Team One TODO List`", team1TodoTaskTwo.taskDescription());

        TaskModel team1TodoTaskThree = team1TodoList.tasks().get(2);
        assertEquals("Some Task Three", team1TodoTaskThree.taskName());
        assertNotNull(team1TodoTaskThree.createdDate());
        assertEquals("Alice", team1TodoTaskThree.createdByUserName());
        assertNull(team1TodoTaskThree.updatedDate());
        assertNull(team1TodoTaskThree.updatedByUser());
        assertNull(team1TodoTaskThree.updatedByUserName());
        assertEquals("Task three created for `Team One TODO List`", team1TodoTaskThree.taskDescription());

        TaskModel team1TodoTaskFour = team1TodoList.tasks().get(3);
        assertEquals("Some Task Four", team1TodoTaskFour.taskName());
        assertNotNull(team1TodoTaskFour.createdDate());
        assertEquals("Bob", team1TodoTaskFour.createdByUserName());
        assertNull(team1TodoTaskFour.updatedDate());
        assertNull(team1TodoTaskFour.updatedByUser());
        assertNull(team1TodoTaskFour.updatedByUserName());
        assertEquals("Task four created for `Team One TODO List`", team1TodoTaskFour.taskDescription());


        TaskListModel charliesList = taskLists.get(1);
        assertEquals("Charlie`s Personal List", charliesList.listName());
        assertNotNull(charliesList.id());
        assertNotNull(charliesList.createdDate());
        assertEquals("Alice", charliesList.createdByUserName());
        assertNull(charliesList.updatedDate());
        assertNull(charliesList.updatedByUser());
        assertNull(charliesList.updatedByUserName());

        assertNotNull(charliesList.tasks());
        assertEquals(2, charliesList.tasks().size());

        TaskModel charliesFirstTask = charliesList.tasks().getFirst();
        assertEquals("Some Task Five", charliesFirstTask.taskName());
        assertNotNull(charliesFirstTask.createdDate());
        assertEquals("Charlie", charliesFirstTask.createdByUserName());
        assertNull(charliesFirstTask.updatedDate());
        assertNull(charliesFirstTask.updatedByUser());
        assertNull(charliesFirstTask.updatedByUserName());
        assertEquals("Task one created for `Charlie`s Personal List`", charliesFirstTask.taskDescription());

        TaskModel charliesSecondTask = charliesList.tasks().get(1);
        assertEquals("Some Task Six", charliesSecondTask.taskName());
        assertNotNull(charliesSecondTask.createdDate());
        assertEquals("Alice", charliesSecondTask.createdByUserName());
        assertNull(charliesSecondTask.updatedDate());
        assertNull(charliesSecondTask.updatedByUser());
        assertNull(charliesSecondTask.updatedByUserName());
        assertEquals("Task two created for `Charlie`s Personal List`", charliesSecondTask.taskDescription());


        TaskListModel aliceAndBobsList = taskLists.get(2);
        assertEquals("Alice and Bob`s List", aliceAndBobsList.listName());
        assertNotNull(aliceAndBobsList.id());
        assertNotNull(aliceAndBobsList.createdDate());
        assertEquals("Alice", aliceAndBobsList.createdByUserName());
        assertNull(aliceAndBobsList.updatedDate());
        assertNull(aliceAndBobsList.updatedByUser());
        assertNull(aliceAndBobsList.updatedByUserName());
        assertNotNull(aliceAndBobsList.tasks());
        assertEquals(0, aliceAndBobsList.tasks().size());
    }

    @Test
    public void getAllTaskListsShouldReturnAllTaskListsWithoutItems() {
        List<TaskListModel> taskLists = taskListService.getTaskListModel(false);

        assertNotNull(taskLists);
        assertEquals(3, taskLists.size());

        TaskListModel team1TodoList = taskLists.getFirst();
        assertEquals("Team One TODO List", team1TodoList.listName());

        assertNotNull(team1TodoList.id());
        assertNotNull(team1TodoList.createdDate());
        assertEquals("Alice", team1TodoList.createdByUserName());
        assertNull(team1TodoList.updatedDate());
        assertNull(team1TodoList.updatedByUser());
        assertNull(team1TodoList.updatedByUserName());

        assertNotNull(team1TodoList.tasks());
        assertEquals(0, team1TodoList.tasks().size());

        TaskListModel charliesList = taskLists.get(1);
        assertEquals("Charlie`s Personal List", charliesList.listName());

        assertNotNull(charliesList.id());
        assertNotNull(charliesList.createdDate());
        assertEquals("Alice", charliesList.createdByUserName());
        assertNull(charliesList.updatedDate());
        assertNull(charliesList.updatedByUser());
        assertNull(charliesList.updatedByUserName());

        assertNotNull(charliesList.tasks());
        assertEquals(0, charliesList.tasks().size());

        TaskListModel aliceAndBobsList = taskLists.get(2);
        assertEquals("Alice and Bob`s List", aliceAndBobsList.listName());

        assertNotNull(aliceAndBobsList.id());
        assertNotNull(aliceAndBobsList.createdDate());
        assertEquals("Alice", aliceAndBobsList.createdByUserName());
        assertNull(aliceAndBobsList.updatedDate());
        assertNull(aliceAndBobsList.updatedByUser());
        assertNull(aliceAndBobsList.updatedByUserName());

        assertNotNull(aliceAndBobsList.tasks());
        assertEquals(0, aliceAndBobsList.tasks().size());
    }

    //createTaskList tests:

    @Test
    public void createTaskListShouldCreateANewTaskList() {
        String taskListName = "A tasklist to create";
        String userName = "Alice";

        TaskListModel taskList = null;
        try {
            taskList = taskListService.createTaskList(userName, taskListName);
        } catch (BadDataException e) {
            fail("createTaskList should not throw an exception");
        }
        assertNotNull(taskList);
        assertEquals(taskListName, taskList.listName());
        assertEquals(userName, taskList.createdByUserName());

        assertNotNull(taskList.id());
        assertNotNull(taskList.createdDate());
        assertNotNull(taskList.createdByUser());
        assertNull(taskList.updatedDate());
        assertNull(taskList.updatedByUser());
        assertNull(taskList.updatedByUserName());
        assertNotNull(taskList.tasks());
        assertEquals(0, taskList.tasks().size());
    }

    @Test
    public void createTaskListShouldThrowBadDataExceptionIfUserNameIsNullOrEmptyOrBlank() {
        Exception exception = assertThrows(
                BadDataException.class,
                () -> taskListService.createTaskList(null, "Not important"));
        assertNotNull(exception);
        assertEquals("user is required", exception.getMessage());

        exception = assertThrows(
                BadDataException.class,
                () -> taskListService.createTaskList("", "Not important"));
        assertNotNull(exception);
        assertEquals("user is required", exception.getMessage());

        exception = assertThrows(
                BadDataException.class,
                () -> taskListService.createTaskList("    ", "Not important"));
        assertNotNull(exception);
        assertEquals("user is required", exception.getMessage());
    }

    @Test
    public void createTaskListShouldThrowBadDataExceptionIfListNameIsNullOrEmptyOrBlank() {
        Exception exception = assertThrows(
                BadDataException.class,
                () -> taskListService.createTaskList("Not important", null));
        assertNotNull(exception);
        assertEquals("listName is required", exception.getMessage());

        exception = assertThrows(
                BadDataException.class,
                () -> taskListService.createTaskList("Not important", ""));
        assertNotNull(exception);
        assertEquals("listName is required", exception.getMessage());

        exception = assertThrows(
                BadDataException.class,
                () -> taskListService.createTaskList("Not important", "    "));
        assertNotNull(exception);
        assertEquals("listName is required", exception.getMessage());
    }

    @Test
    public void createTaskListShouldThrowBadDataExceptionIfUserNameDoesNotExistInDatabase() {
        Exception exception = assertThrows(
                BadDataException.class,
                () -> taskListService.createTaskList("Debbie", "Not important"));
        assertNotNull(exception);
        assertEquals("User does not exist", exception.getMessage());
    }
}