import io.restassured.response.Response;
import model.AuthResponseModel;
import model.UserRequestModel;
import org.junit.Test;
import util.BaseTest;

import static org.junit.Assert.assertEquals;

public class OrderGetTest extends BaseTest {

    @Test
    public void getOrdersForAuthorizedUserTest() {
        UserRequestModel artur = new UserRequestModel("mukh" + getUniqueId() + "@mail.ru", "1234", "Artur");
        postNewUser(artur);
        AuthResponseModel authResponseModel = authorizeUser(artur).as(AuthResponseModel.class);

        Response orders = getOrders(authResponseModel.getAccessToken());

        assertEquals("Request should be successful!", 200, orders.statusCode());
    }

    @Test
    public void getOrdersForUnauthorizedUserTest() {
        UserRequestModel artur = new UserRequestModel("mukh" + getUniqueId() + "@mail.ru", "1234", "Artur");
        postNewUser(artur);

        Response orders = getOrders("");

        assertEquals("Request should have failed!", 401, orders.statusCode());
    }
}