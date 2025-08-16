package worldline.api.v1.dto;

import java.time.Instant;
import java.util.UUID;
import worldline.service.model.UserModel;

public record UserDto(
        UUID id,
        Instant createdDate,
        Instant updatedDate,
        UUID updatedByUser,
        String updatedByUserName,
        String userDisplayName
) {

    public UserDto(UserModel model) {
        this(
                model.id(),
                model.createdDate(),
                model.updatedDate(),
                model.updatedByUser(),
                "TODO",
                model.userDisplayName());
    }
}
