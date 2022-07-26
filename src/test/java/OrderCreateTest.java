import io.restassured.response.Response;
import model.AuthResponseModel;
import model.UserRequestModel;
import org.junit.Test;
import util.BaseTest;
import util.OrderDataProducer;

import static org.junit.Assert.assertEquals;

public class OrderCreateTest extends BaseTest {

    @Test
    public void createOrderWithAuthorizedUser() {
        UserRequestModel artur = new UserRequestModel("mukh" + getUniqueId() + "@mail.ru", "1234", "Artur");
        postNewUser(artur);
        AuthResponseModel authResponseModel = authorizeUser(artur).as(AuthResponseModel.class);

        Response response = createOrder(OrderDataProducer.getCorrectOrder(), authResponseModel.getAccessToken());

        assertEquals("Order should have been created!", 200, response.getStatusCode());
    }

    @Test
    public void createOrderWithUnauthorizedUser() {
        UserRequestModel artur = new UserRequestModel("mukh" + getUniqueId() + "@mail.ru", "1234", "Artur");
        postNewUser(artur);

        Response response = createOrder(OrderDataProducer.getCorrectOrder(), "");

        assertEquals("Order should have been created!", 200, response.getStatusCode());
    }

    @Test
    public void createOrderWithoutIngredients() {
        UserRequestModel artur = new UserRequestModel("mukh" + getUniqueId() + "@mail.ru", "1234", "Artur");
        postNewUser(artur);
        AuthResponseModel authResponseModel = authorizeUser(artur).as(AuthResponseModel.class);

        Response response = createOrder(OrderDataProducer.getEmptyOrder(), authResponseModel.getAccessToken());

        assertEquals("Order without ingredients cannot be created!", 400, response.getStatusCode());
    }

    @Test
    public void createOrderWithWrongIngredients() {
        UserRequestModel artur = new UserRequestModel("mukh" + getUniqueId() + "@mail.ru", "1234", "Artur");
        postNewUser(artur);
        AuthResponseModel authResponseModel = authorizeUser(artur).as(AuthResponseModel.class);

        Response response = createOrder(OrderDataProducer.getIncorrectOrder(), authResponseModel.getAccessToken());

        assertEquals("Order with wrong ingredients cannot be created!", 500, response.getStatusCode());
    }
}