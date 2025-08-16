package worldline.api.v1.dto;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class CreateListRequestTest {

    @Test
    void isValidShouldReturnNullIfRequestIsValid() {
        CreateListRequest request = new CreateListRequest("abc", "def");
        assertNull(request.isValid());
    }

    @Test
    void isValidShouldReturnStringIfRequestIsNotValid() {
        // TODO: Refactor to parametrised test.

        CreateListRequest request = new CreateListRequest(null, "def");
        String message = request.isValid();
        assertNotNull(message);
        assertEquals("list_name is required", message);

        request = new CreateListRequest("abc", null);
        message = request.isValid();
        assertNotNull(message);
        assertEquals("user_name is required", message);

        request = new CreateListRequest(null, null);
        message = request.isValid();
        assertNotNull(message);
        assertEquals("list_name is required, user_name is required", message);
    }
}