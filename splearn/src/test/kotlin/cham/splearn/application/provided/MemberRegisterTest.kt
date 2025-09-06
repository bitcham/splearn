package cham.splearn.application.provided

import cham.splearn.SplearnTestConfiguration
import cham.splearn.domain.DuplicateEmailException
import cham.splearn.domain.MemberStatus.PENDING
import cham.splearn.domain.createMemberRegisterRequest
import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
@Transactional
@Import(SplearnTestConfiguration::class)
class MemberRegisterTest {

    @Autowired lateinit var memberRegister: MemberRegister

    @Test
    fun register(){
        val member = memberRegister.register(createMemberRegisterRequest())

        assertThat(member.id).isNotNull
        assertThat(member.status).isEqualTo(PENDING)
    }

    @Test
    fun duplicateEmailFail(){
        val member = memberRegister.register(createMemberRegisterRequest())

        assertThatThrownBy {
            memberRegister.register(createMemberRegisterRequest())
        }.isInstanceOf(DuplicateEmailException::class.java)
    }

}