package worldline.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import worldline.service.model.TaskListModel;

public record TaskListResponse(
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

        @JsonProperty("list_name")
        String listName,

        @NonNull
        List<TaskDto> tasks
) {

    public TaskListResponse(TaskListModel model) {
        this(model.id(), model.createdDate(), model.createdByUser(), model.createdByUserName(), model.updatedDate(),
                model.updatedByUser(), model.updatedByUserName(), model.listName(),
                model.tasks().stream().map(TaskDto::new).toList());
    }
}
