package cham.splearn.adapter.integration

import cham.splearn.application.required.EmailSender
import cham.splearn.domain.Email
import org.springframework.stereotype.Component

@Component
class DummyEmailSender: EmailSender {
    override fun send(email: Email, subject: String, body: String) {
        println("DummyEmailSender: To=$email, Subject=$subject, Body=$body")
    }

}