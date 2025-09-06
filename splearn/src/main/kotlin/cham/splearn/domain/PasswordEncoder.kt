package cham.splearn.domain

import org.springframework.stereotype.Component


@Component
interface PasswordEncoder {
    fun encode(password: String) : String
    fun matches(password: String, passwordHash: String) : Boolean
    
    companion object {
        val NoOp = object : PasswordEncoder {
            override fun encode(password: String): String = password
            override fun matches(password: String, passwordHash: String): Boolean = password == passwordHash
        }
    }
}