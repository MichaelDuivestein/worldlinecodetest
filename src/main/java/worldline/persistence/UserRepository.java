package worldline.persistence;

import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import worldline.persistence.entity.UserEntity;

public interface UserRepository {
    @SqlQuery("""
            SELECT * FROM users
            """)
    @RegisterConstructorMapper(UserEntity.class)
    List<UserEntity> getAllUsers();

    @SqlQuery("""
            SELECT TOP 1 id, created_date, updated_date, updated_by_user, user_email, user_display_name
            FROM users
            WHERE user_display_name = :userName
            """)
    @RegisterConstructorMapper(UserEntity.class)
    UserEntity getUserByName(@Bind("userName") String userName);
}
