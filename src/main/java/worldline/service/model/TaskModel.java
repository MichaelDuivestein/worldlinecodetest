package worldline.service.model;

import java.time.Instant;
import java.util.UUID;

public record TaskModel (
        UUID id,
        Instant createdDate,
        UUID createdByUser,
        String createdByUserName,
        Instant updatedDate,
        UUID updatedByUser,
        String updatedByUserName,
        String taskName,
        String taskDescription
){}