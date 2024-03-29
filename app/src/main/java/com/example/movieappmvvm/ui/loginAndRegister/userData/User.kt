package com.example.movieappmvvm.ui.loginAndRegister.userData

data class User(
    val name: String? = null,
    val email: String? = null,
    val password:String? = null,
    val repeatPassword: String? = null
) {
    fun isCorrectInputLogin() = email!!.isNotEmpty() && password!!.isNotEmpty()
    fun isCorrectInputRegister() =
        email!!.isNotEmpty() && password!!.isNotEmpty() && repeatPassword!!.isNotEmpty()

    fun isPasswordContains() =
        password == repeatPassword && password!!.contains(Regex(".{6,}")) && password.contains(Regex("(?=.*[A-Z])"))
}
