package providers

import com.github.javafaker.Faker
import models.Credentials

class CredentialsProvider {
    companion object{
        @JvmStatic
        fun getRandom(): Credentials{
            val faker = Faker()
            val login = faker.bothify("??????####@domain.com")
            val pwd = faker.regexify("[a-z1-9]{10}")
            return Credentials(login, pwd)
        }
    }

}