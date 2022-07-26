import io.restassured.response.Response;
import model.AuthResponseModel;
import model.UserRequestModel;
import org.junit.Test;
import util.BaseTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserAuthTest extends BaseTest {

    @Test
    public void authorizeExistingUserTest() {
        UserRequestModel artur = new UserRequestModel("mukh" + getUniqueId() + "@mail.ru", "1234", "Artur");
        postNewUser(artur);

        Response response = authorizeUser(artur);

        assertEquals("Code should be 200!", 200, response.getStatusCode());
        assertTrue("User auth should have succeeded!", response.as(AuthResponseModel.class).isSuccess());
    }

    @Test
    public void authorizeExistingUserWithWrongCredentialsTest() {
        UserRequestModel artur = new UserRequestModel("mukh" + getUniqueId() + "@mail.ru", "1234", "Artur");
        postNewUser(artur);

        Response response = authorizeUser(new UserRequestModel("wrongEmail@mail.ru", "wrongPassword", "Artur"));

        assertEquals("User auth should have failed because of wrong credentials!",
                401,
                response.getStatusCode());
    }
}