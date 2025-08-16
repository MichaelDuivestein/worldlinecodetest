package worldline.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.UUID;
import worldline.service.model.TaskModel;

public record TaskDto(
        @JsonProperty("id")
        UUID id,

        @JsonProperty("created_date")
        Instant createdDate,

        @JsonProperty("created_by_user")
        UUID createdByUser,

        @JsonProperty("created_by_user_name")
        String createdByUserName,

        @JsonProperty("updated_date")
        Instant updatedDate,

        @JsonProperty("updated_by_user")
        UUID updatedByUser,

        @JsonProperty("updated_by_user_name")
        String updatedByUserName,

        @JsonProperty("task_name")
        String taskName,

        @JsonProperty("task_description")
        String taskDescription
) {

    public TaskDto(TaskModel taskModel) {
        this(taskModel.id(), taskModel.createdDate(), taskModel.createdByUser(), taskModel.createdByUserName(),
                taskModel.updatedDate(), taskModel.updatedByUser(), taskModel.updatedByUserName(),
                taskModel.taskName(), taskModel.taskDescription());
    }
}