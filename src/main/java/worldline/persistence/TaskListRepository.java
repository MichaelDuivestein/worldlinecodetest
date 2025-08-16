package worldline.persistence;

import java.util.List;
import java.util.UUID;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import worldline.persistence.entity.TaskEntity;
import worldline.persistence.entity.TaskListEntity;

public interface TaskListRepository {
    @SqlQuery("""
            SELECT
                tl.id, tl.created_date, tl.created_by_user, cbu.user_display_name AS created_by_user_name,
                tl.updated_date, tl.updated_by_user, ubu.user_display_name AS updated_by_user_name, tl.list_name
            FROM task_list tl
            LEFT JOIN users cbu ON tl.created_by_user = cbu.id
            LEFT JOIN users ubu ON tl.updated_by_user = ubu.id
            ORDER BY GREATEST(tl.created_date, COALESCE(tl.updated_date, tl.created_date)) DESC
            """)
    @RegisterConstructorMapper(TaskListEntity.class)
    List<TaskListEntity> getAllTaskLists();

    @SqlQuery("""
            SELECT
                tl.id, tl.created_date, tl.created_by_user, cbu.user_display_name AS created_by_user_name,
                tl.updated_date, tl.updated_by_user, ubu.user_display_name AS updated_by_user_name, tl.list_name
            FROM task_list tl
            LEFT JOIN users cbu ON tl.created_by_user = cbu.id
            LEFT JOIN users ubu ON tl.updated_by_user = ubu.id
            WHERE tl.id = :taskListId
            """)
    @RegisterConstructorMapper(TaskListEntity.class)
    TaskListEntity getTaskListById(@Bind("taskListId") UUID taskListId);

    @SqlQuery("""
            SELECT
                tli.id, tli.created_date, tli.created_by_user, cbu.user_display_name AS created_by_user_name,
                tli.updated_date, tli.updated_by_user, ubu.user_display_name AS updated_by_user_name, task_list_id,
                tli.task_name, tli.task_description
            FROM task_list_item tli
            LEFT JOIN users cbu ON tli.created_by_user = cbu.id
            LEFT JOIN users ubu ON tli.updated_by_user = ubu.id
            WHERE task_list_id IN (<taskListIds>)
            ORDER BY GREATEST(tli.created_date, COALESCE(tli.updated_date, tli.created_date)) DESC
            """)
    @RegisterConstructorMapper(TaskEntity.class)
    List<TaskEntity> getTasksForTaskListIds(@BindList("taskListIds") List<UUID> taskListIds);


    @SqlUpdate("""
            INSERT INTO task_list (created_by_user, list_name)
            VALUES (:userId, :listName)
            """)

    @GetGeneratedKeys("id")
    UUID insertTaskList(@Bind("userId") UUID userId, @Bind("listName") String listName);
}
