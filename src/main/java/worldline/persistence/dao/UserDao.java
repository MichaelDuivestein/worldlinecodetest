package worldline.persistence.dao;

import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import worldline.persistence.UserRepository;
import worldline.persistence.entity.UserEntity;
import worldline.service.model.UserModel;

@Slf4j
@Component
public class UserDao {
    private final Jdbi jdbi;

    @Autowired
    public UserDao(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public UserModel getUserByName(String userName) {

        UserEntity userEntity = jdbi.withHandle(handle ->
                handle.attach(UserRepository.class).getUserByName(userName));

        if (userEntity == null) {
            return null;
        }

        return userEntity.toModel();
    }
}
