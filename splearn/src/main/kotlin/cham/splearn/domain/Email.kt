package cham.splearn.domain

import jakarta.persistence.Embeddable

@Embeddable
@ConsistentCopyVisibility
data class Email private constructor(val address: String) {

    companion object {
        private val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

        fun of(email: String): Email {
            require(email.isNotBlank()) { "Email cannot be blank" }
            require(EMAIL_REGEX.matches(email)) { "Invalid email format: $email" }
            return Email(email)
        }
    }

    override fun toString(): String = address
}
