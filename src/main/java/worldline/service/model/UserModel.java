package worldline.service.model;

import java.time.Instant;
import java.util.UUID;

public record UserModel(
        UUID id,
        Instant createdDate,
        Instant updatedDate,
        UUID updatedByUser,
        String userEmail,
        String userDisplayName
) {}
