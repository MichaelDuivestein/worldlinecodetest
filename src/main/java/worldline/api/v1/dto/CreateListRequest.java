package worldline.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public record CreateListRequest(
        @JsonProperty("list_name")
        String listName,
        @JsonProperty("user_name")
        String userName
) {
    public String isValid() {
        List<String> errors = new ArrayList<>();
        if (listName == null || listName.isBlank()) {
            errors.add("list_name is required");
        }

        if (userName == null || userName.isBlank()) {
            errors.add("user_name is required");
        }

        if (errors.isEmpty()) {
            return null;
        }

        return String.join(", ", errors);
    }
}
