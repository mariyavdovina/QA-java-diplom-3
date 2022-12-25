package models

class Credentials(email: String, password: String) {
    @JvmField
    var email: String
    @JvmField
    var password: String

    init {
        this.email = email
        this.password = password
    }

    companion object {
        @JvmStatic
        fun from(courier: User): Credentials {
            return Credentials(courier.email, courier.password)
        }
    }
}