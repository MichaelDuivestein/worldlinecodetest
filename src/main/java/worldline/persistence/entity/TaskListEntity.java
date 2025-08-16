package worldline.persistence.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;
import worldline.service.model.TaskListModel;
import worldline.service.model.TaskModel;

@Data
@Getter
@AllArgsConstructor
public class TaskListEntity {
    @ColumnName("id")
    private final UUID id;

    @ColumnName("created_date")
    private final Instant createdDate;

    @ColumnName("created_by_user")
    private final UUID createdByUser;

    @ColumnName("created_by_user_name")
    private final String createdByUserName;

    @ColumnName("updated_date")
    private final Instant updatedDate;

    @ColumnName("updated_by_user")
    private final UUID updatedByUser;

    @ColumnName("updated_by_user_name")
    private final String updatedByUserName;

    @ColumnName("list_name")
    private final String listName;

    @NonNull
    List<TaskEntity> tasks = new ArrayList<>();

    @JdbiConstructor
    public TaskListEntity(UUID id, Instant createdDate, UUID createdByUser, String createdByUserName, String listName,
                          Instant updatedDate, UUID updatedByUser, String updatedByUserName) {
        this.id = id;
        this.createdDate = createdDate;
        this.createdByUser = createdByUser;
        this.createdByUserName = createdByUserName;
        this.updatedDate = updatedDate;
        this.updatedByUser = updatedByUser;
        this.updatedByUserName = updatedByUserName;
        this.listName = listName;
    }

    public TaskListModel toModel() {
        List<TaskModel> taskModels = new ArrayList<>();

        if (!tasks.isEmpty()) {
            taskModels = tasks.stream().map(TaskEntity::toModel).toList();
        }

        return new TaskListModel(
                id, createdDate, createdByUser, createdByUserName, updatedDate, updatedByUser, updatedByUserName,
                listName, taskModels);
    }
}
