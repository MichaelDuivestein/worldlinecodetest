package worldline.config;

import javax.sql.DataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.jdbi.v3.core.statement.SqlStatements;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JDBIConfig {
    @Qualifier("taskListDatasource")
    private final DataSource taskListDatasource;

    @Autowired
    public JDBIConfig(DataSource taskListDatasource) {
        this.taskListDatasource = taskListDatasource;
    }

    @Bean
    public Jdbi configureTaskListJdbi() {
        Jdbi jdbi = Jdbi.create(taskListDatasource)
                .installPlugin(new H2DatabasePlugin())
                .installPlugin(new SqlObjectPlugin());

        jdbi.getConfig(SqlStatements.class).setUnusedBindingAllowed(true);

        return jdbi;
    }
}
