package cham.splearn.application.provided

import cham.splearn.SplearnTestConfiguration
import cham.splearn.domain.DuplicateEmailException
import cham.splearn.domain.MemberRegisterRequest
import cham.splearn.domain.MemberStatus.PENDING
import cham.splearn.domain.createMemberRegisterRequest
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.extensions.spring.SpringExtension
import jakarta.transaction.Transactional
import jakarta.validation.ConstraintViolationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
@Transactional
@Import(SplearnTestConfiguration::class)
class MemberRegisterTest : FunSpec() {

    override fun extensions() = listOf(SpringExtension)

    @Autowired 
    lateinit var memberRegister: MemberRegister

    init {
        test("register") {
            val member = memberRegister.register(createMemberRegisterRequest())

            member.id shouldNotBe null
            member.status shouldBe PENDING
        }

        test("duplicateEmailFail") {
            val member = memberRegister.register(createMemberRegisterRequest())

            shouldThrow<DuplicateEmailException> {
                memberRegister.register(createMemberRegisterRequest())
            }
        }

        test("memberRegisterFail") {
            assertMemberRegisterValidation(MemberRegisterRequest("cham@splearn.com", "sh", "secret1234"))
            assertMemberRegisterValidation(MemberRegisterRequest("cham@splearn.com", "sh1231232131", "short"))
            assertMemberRegisterValidation(MemberRegisterRequest("cham-splearn.com", "sh123123213", "secret1234"))
        }
    }

    private fun assertMemberRegisterValidation(invalid: MemberRegisterRequest) {
        shouldThrow<ConstraintViolationException> {
            memberRegister.register(invalid)
        }
    }
}