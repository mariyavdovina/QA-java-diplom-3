package providers

import com.github.javafaker.Faker
import io.qameta.allure.Step
import models.User
import providers.CredentialsProvider.Companion.getRandom

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
fun main(){
    var user: User = UserProvider.getRandom()
    println("Name: ${user.name}, email: ${user.email}, password: ${user.password}")

}