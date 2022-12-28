package providers

import com.github.javafaker.Faker
import models.User

class UserProvider {

    companion object{
        @JvmStatic
        // @Step("Random user")
        fun getRandom(): User{
            val faker = Faker()
            return User(CredentialsProvider.getRandom().email, CredentialsProvider.getRandom().password, faker.name().firstName())
        }
    }
}
