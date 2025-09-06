package cham.splearn.application.provided

import cham.splearn.application.MemberService
import cham.splearn.application.required.EmailSender
import cham.splearn.application.required.MemberRepository
import cham.splearn.domain.Email
import cham.splearn.domain.Member
import cham.splearn.domain.MemberStatus.PENDING
import cham.splearn.domain.createMemberRegisterRequest
import cham.splearn.domain.createPasswordEncoder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.*
import org.springframework.test.util.ReflectionTestUtils


class MemberRegisterManualTest {

    @Test
    fun registerTestStub(){
        val register = MemberService(MemberRepositoryStud(), EmailSenderStud(), createPasswordEncoder())

        val member = register.register(createMemberRegisterRequest())

        assertThat(member.id).isNotNull
        assertThat(member.status).isEqualTo(PENDING)
    }

    @Test
    fun registerTestMock(){
        val emailSenderMock = EmailSenderMock()

        val register = MemberService(MemberRepositoryStud(), emailSenderMock, createPasswordEncoder())

        val member = register.register(createMemberRegisterRequest())

        assertThat(member.id).isNotNull
        assertThat(member.status).isEqualTo(PENDING)

        assertThat(emailSenderMock.tos).hasSize(1)
        assertThat(emailSenderMock.tos[0]).isEqualTo(member.email)
    }

    @Test
    fun registerTestMockito(){
        val emailSenderMock = Mockito.mock(EmailSender::class.java)

        val register = MemberService(MemberRepositoryStud(), emailSenderMock, createPasswordEncoder())

        val member = register.register(createMemberRegisterRequest())

        assertThat(member.id).isNotNull
        assertThat(member.status).isEqualTo(PENDING)

        Mockito.verify(emailSenderMock).send(
            eq(member.email),
            any(),
            any()
        )
    }



    class MemberRepositoryStud(): MemberRepository {
        override fun save(member: Member): Member{
            ReflectionTestUtils.setField(member, "id", 1L)
            return member;
        }

        override fun findByEmail(email: Email): Member? {
            return null
        }

    }

    class EmailSenderStud: EmailSender {

        override fun send(email: Email, subject: String, body: String){

        }
    }

    class EmailSenderMock: EmailSender {
        val tos: MutableList<Email> = mutableListOf()

        override fun send(email: Email, subject: String, body: String){
            tos.add(email)
        }
    }
}