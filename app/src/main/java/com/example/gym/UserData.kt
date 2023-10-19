package com.example.gym

data class UserData(
    val id: String? = null,
    val email: String? = null,
    val username: String? = null,
    val password: String? = null,
    val age: Int? = null,
    val height: Double? = null,
    val weight: Int? = null,
    val gender: Gender? = null,
    val trainer: Boolean = false
)
enum class Gender {
    MALE, FEMALE
}

