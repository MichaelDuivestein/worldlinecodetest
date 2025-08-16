package worldline.persistence.entity;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import worldline.service.model.TaskModel;

@Data
@Getter
@AllArgsConstructor
public class TaskEntity {
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

    @ColumnName("task_list_id")
    private final UUID taskListId;

    @ColumnName("task_name")
    private final String taskName;

    @ColumnName("task_description")
    private final String taskDescription;

    public TaskModel toModel() {
        return new TaskModel(
                id, createdDate, createdByUser, createdByUserName, updatedDate, updatedByUser, updatedByUserName,
                taskName, taskDescription
        );
    }
}
