package cham.splearn

import cham.splearn.application.required.EmailSender
import cham.splearn.domain.Email
import cham.splearn.domain.PasswordEncoder
import cham.splearn.domain.createPasswordEncoder
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class SplearnTestConfiguration{
    @Bean
    fun emailSender(): EmailSender {
        return object : EmailSender {
            override fun send(email: Email, subject: String, body: String) {
                println("Stub EmailSender: $email, $subject, $body")
            }
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return createPasswordEncoder()
    }

}