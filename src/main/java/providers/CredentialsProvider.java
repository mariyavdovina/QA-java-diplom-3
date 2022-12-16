package providers;

import models.Credentials;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;

public class CredentialsProvider {
    @Step("Credentials for random user")
    public static Credentials getRandom() {
        Faker faker = new Faker();
        String login = faker.bothify("??????####@domain.com");
        String pwd = faker.regexify("[a-z1-9]{10}");
        return new Credentials(login,pwd);
        }
}
