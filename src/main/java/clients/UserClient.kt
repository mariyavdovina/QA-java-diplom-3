package clients

import io.qameta.allure.Step
import io.restassured.RestAssured
import io.restassured.module.kotlin.extensions.*
import io.restassured.response.ValidatableResponse
import models.Credentials
import models.User

class UserClient : Client() {
    @Step("User creation")
    fun create(user: User): ValidatableResponse {
        return RestAssured.given()
                .spec(spec)
                .body(user)
                .`when`()
                .post(PATH + "register/")
                .then()
    }

    fun delete(accessToken: String): ValidatableResponse {
        return RestAssured.given()
                .header("authorization", "bearer $accessToken")
                .spec(spec)
                .`when`()
                .delete(PATH + "user/")
                .then()
    }

    fun login(credentials: Credentials): ValidatableResponse {
        return RestAssured.given()
                .spec(spec)
                .body(credentials)
                .`when`()
                .post(PATH + "login/")
                .then()
    }
    fun extractToken(credentials: Credentials):String{
        val token:String=
                Given{
                    spec(spec)
                    body(credentials)}When{
                    post(PATH + "login/")
                }Then{
                    statusCode(200)
                }Extract{
                    path("accessToken")
                }
        return token.substring(6).trim()
    }

    companion object {
        private const val PATH = "api/auth/"
    }
}