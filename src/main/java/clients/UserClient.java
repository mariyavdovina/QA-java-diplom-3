package clients;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.Credentials;
import models.User;
import static io.restassured.RestAssured.given;

public class UserClient extends Client {
    private static final String PATH = "api/auth/";
    @Step("User creation")
    public ValidatableResponse create(User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(PATH+"register/")
                .then();
    }
    public ValidatableResponse delete(String accessToken) {
        return given()
                .header("authorization", "bearer " + accessToken)
                .spec(getSpec())
                .when()
                .delete(PATH + "user/")
                .then();
    }

    public ValidatableResponse login(Credentials credentials) {
        return given()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post(PATH + "login/")
                .then();
    }
}
