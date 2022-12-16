package providers;

import models.User;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;

public class UserProvider {
    @Step("Random user")
    public static User getRandom() {
        Faker faker = new Faker();
        return new User(CredentialsProvider.getRandom().getEmail(), CredentialsProvider.getRandom().getPassword(), faker.name().firstName());
    }
}
