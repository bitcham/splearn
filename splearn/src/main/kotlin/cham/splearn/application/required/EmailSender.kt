package cham.splearn.application.required

import cham.splearn.domain.Email
import org.springframework.stereotype.Component

/**
 * Email sending functionality.
 */
interface EmailSender {
    fun send(email: Email, subject: String, body: String)
}