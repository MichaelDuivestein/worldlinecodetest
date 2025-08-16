package worldline.service.model;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record TaskListModel(
        UUID id,
        Instant createdDate,
        UUID createdByUser,
        String createdByUserName,
        Instant updatedDate,
        UUID updatedByUser,
        String updatedByUserName,
        String listName,
        List<TaskModel> tasks
) {}
