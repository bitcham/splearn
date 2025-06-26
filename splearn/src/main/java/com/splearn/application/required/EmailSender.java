package com.splearn.application.required;

import com.splearn.domain.Email;

/**
 * This interface represents the contract for email sending functionality.
 */
public interface EmailSender {
    void send(Email email, String subject, String body);
}
