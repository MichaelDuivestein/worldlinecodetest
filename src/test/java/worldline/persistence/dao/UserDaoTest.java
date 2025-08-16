package worldline.persistence.dao;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import worldline.service.model.UserModel;


import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserDaoTest {
    @Autowired
    public UserDao userDao;

    @Autowired
    private Flyway flyway;

    @AfterEach
    public void tearDown(){
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void getUserByNameShouldWork(){
        String name = "Charlie";
        UserModel charlie = userDao.getUserByName(name);

        assertNotNull(charlie);
        assertNotNull(charlie.id());
        assertNotNull(charlie.createdDate());
        assertNull(charlie.updatedDate());
        assertNull(charlie.updatedByUser());
        assertNotNull(charlie.userEmail());
        assertEquals(name, charlie.userDisplayName());

    }

}