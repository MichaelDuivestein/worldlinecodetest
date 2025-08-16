package worldline.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import worldline.exception.BadDataException;
import worldline.persistence.dao.TaskListDao;
import worldline.persistence.dao.UserDao;
import worldline.service.model.TaskListModel;
import worldline.service.model.UserModel;

@Slf4j
@Service
public class TaskListService {
    private final TaskListDao taskListDao;
    private final UserDao userDao;

    @Autowired
    public TaskListService(TaskListDao taskListDao, UserDao userDao) {
        this.taskListDao = taskListDao;
        this.userDao = userDao;
    }

    public List<TaskListModel> getTaskListModel(boolean includeTasks) {
        return taskListDao.getAllTaskLists(includeTasks);
    }

    public TaskListModel createTaskList(String userName, String listName) throws BadDataException {
        if (userName == null || userName.isBlank()) {
            throw new BadDataException("user is required");
        }
        if (listName == null || listName.isBlank()) {
            throw new BadDataException("listName is required");
        }

        UserModel user = userDao.getUserByName(userName);

        if (user == null) {
            throw new BadDataException("User does not exist");
        }

        return taskListDao.createTaskList(user.id(), listName);
    }
}
