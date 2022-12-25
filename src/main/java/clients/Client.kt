package clients

import io.restassured.builder.RequestSpecBuilder
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification

open class Client {
    protected val spec: RequestSpecification
        protected get() = RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build()

    companion object {
        private const val BASE_URL = "https://stellarburgers.nomoreparties.site/"
    }
}