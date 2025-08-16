package worldline.persistence.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import worldline.persistence.TaskListRepository;
import worldline.persistence.entity.TaskEntity;
import worldline.persistence.entity.TaskListEntity;
import worldline.service.model.TaskListModel;

@Slf4j
@Component
public class TaskListDao {
    private final Jdbi jdbi;

    @Autowired
    public TaskListDao(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public List<TaskListModel> getAllTaskLists(boolean getTasks) {
        return jdbi.withHandle(handle -> {
            TaskListRepository repo = handle.attach(TaskListRepository.class);
            List<TaskListEntity> result = repo.getAllTaskLists();

            List<TaskListModel> taskLists = result.stream().map(TaskListEntity::toModel).toList();

            if (!getTasks) {
                return taskLists;
            }

            Map<UUID, TaskListModel> taskListsMap = taskLists.stream()
                    .collect(Collectors.toMap(TaskListModel::id, Function.identity()));

            List<TaskEntity> tasks = repo.getTasksForTaskListIds(new ArrayList<>(taskListsMap.keySet()));
            if (tasks == null || tasks.isEmpty()) {
                return taskLists;
            }

            for (TaskEntity task : tasks) {
                // taskList is guaranteed to never be null
                @NonNull TaskListModel taskList = taskListsMap.get(task.getTaskListId());
                taskList.tasks().add(task.toModel());
            }

            //return taskListsMap.values().stream().toList();
            return taskLists;
        });
    }

    public TaskListModel createTaskList(UUID userId, String listName) {
        UUID taskListId = jdbi.withHandle(handle ->
                handle.attach(TaskListRepository.class)
                        .insertTaskList(userId, listName));

        if (taskListId == null) {
            return null;
        }

        TaskListEntity entity = jdbi.withHandle(handle ->
                handle.attach(TaskListRepository.class)
                        .getTaskListById(taskListId));

        if (entity == null) {
            return null;
        }

        return entity.toModel();
    }
}
