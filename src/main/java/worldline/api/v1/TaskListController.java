package worldline.api.v1;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import worldline.api.v1.dto.CreateListRequest;
import worldline.api.v1.dto.TaskListResponse;
import worldline.exception.BadDataException;
import worldline.service.TaskListService;
import worldline.service.model.TaskListModel;

@RestController
@RequestMapping("/api/v1/tasklist")
public class TaskListController {
    private final TaskListService taskListService;

    @Autowired
    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @GetMapping("/all")
    public List<TaskListResponse> getAllTaskLists(@RequestParam("include_tasks") boolean includeTasks) {
        List<TaskListModel> model = taskListService.getTaskListModel(includeTasks);

        if (model == null || model.isEmpty()) {
            return new ArrayList<>();
        }

        return model.stream().map(TaskListResponse::new).toList();
    }

    @PostMapping("/create")
    public TaskListResponse createTaskList(@RequestBody CreateListRequest request) throws BadDataException {
        if (request == null) {
            throw new BadDataException("request body is required");
        }

        String errors = request.isValid();

        if (errors != null) {
            throw new BadDataException("Error validating request: " + errors);
        }

        TaskListModel model = taskListService.createTaskList(request.userName(), request.listName());
        if (model == null) {
            return null;
        }

        return new TaskListResponse(model);
    }
}
