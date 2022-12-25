package models

class User (email: String, password: String, name: String){
    @JvmField
    var email:String
    @JvmField
    var password:String
    @JvmField
    var name:String
    init {
        this.email = email
        this.password = password
        this.name = name
    }

}