package cham.splearn.domain

import cham.splearn.domain.MemberStatus.PENDING
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.assertions.throwables.shouldThrow


class MemberTest : FunSpec({

    lateinit var member: Member
    lateinit var passwordEncoder: PasswordEncoder

    beforeEach {
        passwordEncoder = createPasswordEncoder()
        member = Member.register(createMemberRegisterRequest(), passwordEncoder)
    }

    test("createMember") {
        member.status shouldBe PENDING
    }

    test("constructorBlankCheck") {
        shouldThrow<IllegalArgumentException> {
            Member.register(MemberRegisterRequest("   ", "cham123", "secret1234"), passwordEncoder)
        }

        shouldThrow<IllegalArgumentException> {
            Member.register(MemberRegisterRequest("cham@splearn.com", "   ", "secret1234"), passwordEncoder)
        }

        shouldThrow<IllegalArgumentException> {
            Member.register(MemberRegisterRequest("cham@splearn.com", "cham123", "   "), passwordEncoder)
        }
    }

    test("activate") {
        member.activate()

        member.status shouldBe MemberStatus.ACTIVE
    }

    test("activateFail") {
        member.activate()

        shouldThrow<IllegalStateException> {
            member.activate()
        }
    }

    test("deactivate") {
        member.activate()

        member.deactivate()

        member.status shouldBe MemberStatus.DEACTIVATED
    }

    test("deactivateFail") {
        shouldThrow<IllegalStateException> {
            member.deactivate()
        }
    }

    test("verifyPassword") {
        member.verifyPassword("secret1234", passwordEncoder) shouldBe true
        member.verifyPassword("wrongpassword", passwordEncoder) shouldBe false
    }

    test("changeNickname") {
        member.nickname shouldBe "cham123"

        member.changeNickname("newCham123")

        member.nickname shouldBe "newCham123"
    }

    test("changePassword") {
        member.changePassword("verysecret", passwordEncoder)

        member.verifyPassword("verysecret", passwordEncoder) shouldBe true
    }

    test("shouldBeActive") {
        member.isActive() shouldBe false

        member.activate()

        member.isActive() shouldBe true

        member.deactivate()

        member.isActive() shouldBe false
    }

    test("invalidEmail") {
        shouldThrow<IllegalArgumentException> {
            Member.register(createMemberRegisterRequest("invalidemail"), passwordEncoder)
        }

        Member.register(createMemberRegisterRequest("valid@gmail.com"), passwordEncoder)
    }

})