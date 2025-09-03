package cham.splearn.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class EmailTest {

    @Test
    fun equality(){
        val email1 = Email.of("cham@splearn.app")
        val email2 = Email.of("cham@splearn.app")

        assertThat(email1).isEqualTo(email2)
    }

}