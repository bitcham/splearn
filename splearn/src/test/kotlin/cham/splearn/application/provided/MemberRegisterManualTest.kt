package cham.splearn.application.provided

import cham.splearn.application.MemberService
import cham.splearn.application.required.EmailSender
import cham.splearn.application.required.MemberRepository
import cham.splearn.domain.Email
import cham.splearn.domain.Member
import cham.splearn.domain.MemberStatus.PENDING
import cham.splearn.domain.createMemberRegisterRequest
import cham.splearn.domain.createPasswordEncoder
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.collections.shouldHaveSize
import io.mockk.*
import org.springframework.test.util.ReflectionTestUtils


class MemberRegisterManualTest : FunSpec() {

    init {
        test("registerTestStub") {
            val register = MemberService(MemberRepositoryStud(), EmailSenderStud(), createPasswordEncoder())

            val member = register.register(createMemberRegisterRequest())

            member.id shouldNotBe null
            member.status shouldBe PENDING
        }

        test("registerTestMock") {
            val emailSenderMock = EmailSenderMock()

            val register = MemberService(MemberRepositoryStud(), emailSenderMock, createPasswordEncoder())

            val member = register.register(createMemberRegisterRequest())

            member.id shouldNotBe null
            member.status shouldBe PENDING

            emailSenderMock.tos shouldHaveSize 1
            emailSenderMock.tos[0] shouldBe member.email
        }

        test("registerTestMockK") {
            val emailSenderMock = mockk<EmailSender>()
            every { emailSenderMock.send(any(), any(), any()) } just Runs

            val register = MemberService(MemberRepositoryStud(), emailSenderMock, createPasswordEncoder())

            val member = register.register(createMemberRegisterRequest())

            member.id shouldNotBe null
            member.status shouldBe PENDING

            verify { emailSenderMock.send(member.email, any(), any()) }
        }
    }

    class MemberRepositoryStud : MemberRepository {
        override fun save(member: Member): Member {
            ReflectionTestUtils.setField(member, "id", 1L)
            return member
        }

        override fun findByEmail(email: Email): Member? {
            return null
        }
    }

    class EmailSenderStud : EmailSender {
        override fun send(email: Email, subject: String, body: String) {
        }
    }

    class EmailSenderMock : EmailSender {
        val tos: MutableList<Email> = mutableListOf()

        override fun send(email: Email, subject: String, body: String) {
            tos.add(email)
        }
    }
}