package worldline.persistence.entity;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;
import worldline.service.model.UserModel;

@AllArgsConstructor(onConstructor_ = @JdbiConstructor)
public class UserEntity {
    @ColumnName("id")
    private final UUID id;

    @ColumnName("created_date")
    private final Instant createdDate;

    @ColumnName("updated_date")
    private final Instant updatedDate;

    @ColumnName("updated_by_user")
    private final UUID updatedByUser;

    @ColumnName("user_email")
    private final String userEmail;

    @ColumnName("user_display_name")
    private final String userDisplayName;

    public UserModel toModel() {
        return new UserModel(id, createdDate, updatedDate, updatedByUser, userEmail, userDisplayName);
    }
}
