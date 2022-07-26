import io.restassured.response.Response;
import model.AuthResponseModel;
import model.PatchResponseModel;
import model.UserRequestModel;
import org.junit.Test;
import util.BaseTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserChangingTest extends BaseTest {

    @Test
    public void changeUserWithAuth() {
        UserRequestModel artur = new UserRequestModel("mukh" + getUniqueId() + "@mail.ru", "1234", "Artur");
        postNewUser(artur);
        AuthResponseModel authResponseModel = authorizeUser(artur).as(AuthResponseModel.class);

        String newName = "Artur Mukh";
        artur.setName(newName);

        Response response1 = updateUser(artur, authResponseModel.getAccessToken());

        assertEquals("Code should be 200!", 200, response1.getStatusCode());
        assertTrue("User update should have succeeded!", response1.as(PatchResponseModel.class).isSuccess());
        assertEquals("This field should have been updated!", newName, response1.as(PatchResponseModel.class).getUser().getName());


        String newEmail = "newemail" + getUniqueId() + "@gmail.com";
        artur.setEmail(newEmail);

        Response response2 = updateUser(artur, authResponseModel.getAccessToken());

        assertEquals("Code should be 200!", 200, response2.getStatusCode());
        assertTrue("User update should have succeeded!", response2.as(PatchResponseModel.class).isSuccess());
        assertEquals("This field should have been updated!", newEmail, response2.as(PatchResponseModel.class).getUser().getEmail());


        String newPassword = "123456";
        artur.setPassword(newPassword);

        Response response3 = updateUser(artur, authResponseModel.getAccessToken());

        assertEquals("Code should be 200!", 200, response3.getStatusCode());
        assertTrue("User update should have succeeded!", response3.as(PatchResponseModel.class).isSuccess());

        Response authWithNewPassword = authorizeUser(artur);

        assertEquals("Should have authorized with new password!",
                200,
                authWithNewPassword.getStatusCode());
    }

    @Test
    public void changeUserWithoutAuth() {
        UserRequestModel artur = new UserRequestModel("mukh" + getUniqueId() + "@mail.ru", "1234", "Artur");
        UserRequestModel updatedArtur = new UserRequestModel("mukh" + getUniqueId() + "@mail.ru", "1234", "Artur");
        postNewUser(artur);

        String newName = "Artur Mukh";
        updatedArtur.setName(newName);

        Response response1 = updateUser(updatedArtur, "");

        assertEquals("Code should be 401!", 401, response1.getStatusCode());


        String newEmail = "newemail" + getUniqueId() + "@gmail.com";
        updatedArtur.setEmail(newEmail);

        Response response2 = updateUser(updatedArtur, "");

        assertEquals("Code should be 401!", 401, response2.getStatusCode());


        String newPassword = "123456";
        updatedArtur.setPassword(newPassword);

        Response response3 = updateUser(updatedArtur, "");

        assertEquals("Code should be 401!", 401, response3.getStatusCode());
    }
}