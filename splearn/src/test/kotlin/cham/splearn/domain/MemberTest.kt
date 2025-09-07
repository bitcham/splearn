package cham.splearn.domain

import cham.splearn.domain.MemberStatus.PENDING
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class MemberTest {

    private lateinit var member: Member
    private lateinit var passwordEncoder: PasswordEncoder

    @BeforeEach
    fun setup(){
        passwordEncoder = createPasswordEncoder()

        member = Member.register(createMemberRegisterRequest(), passwordEncoder)

    }

    @Test
    fun createMember() {
        assertThat(member.status).isEqualTo(PENDING)
    }

    @Test
    fun constructorBlankCheck() {
        assertThatThrownBy {
            Member.register(MemberRegisterRequest("   ", "cham123", "secret1234"), passwordEncoder)
        }.isInstanceOf(IllegalArgumentException::class.java)

        assertThatThrownBy {
            Member.register(MemberRegisterRequest("cham@splearn.com", "   ", "secret1234"), passwordEncoder)
        }.isInstanceOf(IllegalArgumentException::class.java)

        assertThatThrownBy {
            Member.register(MemberRegisterRequest("cham@splearn.com", "cham123", "   "), passwordEncoder)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun activate(){
        member.activate()

        assertThat(member.status).isEqualTo(MemberStatus.ACTIVE)
    }

    @Test
    fun activateFail(){
        member.activate()

        assertThatThrownBy {
            member.activate()
        }.isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun deactivate() {
        member.activate()

        member.deactivate()

        assertThat(member.status).isEqualTo(MemberStatus.DEACTIVATED)
    }

    @Test
    fun deactivateFail() {
        assertThatThrownBy {
            member.deactivate()
        }.isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun verifyPassword() {
        assertTrue{
            member.verifyPassword("secret1234", passwordEncoder)
        }
        assertFalse{
            member.verifyPassword("wrongpassword", passwordEncoder)
        }
    }

    @Test
    fun changeNickname() {
        assertThat(member.nickname).isEqualTo("cham123")

        member.changeNickname("newCham123")

        assertThat(member.nickname).isEqualTo("newCham123")
    }

    @Test
    fun changePassword(){
        member.changePassword("verysecret", passwordEncoder)

        assertTrue{
            member.verifyPassword("verysecret", passwordEncoder)
        }

    }

    @Test
    fun shouldBeActive(){
        assertFalse(member.isActive())

        member.activate()

        assertTrue(member.isActive())

        member.deactivate()

        assertFalse(member.isActive())
    }

    @Test
    fun invalidEmail(){
        assertThatThrownBy {
            Member.register(createMemberRegisterRequest("invalidemail"), passwordEncoder)
        }.isInstanceOf(IllegalArgumentException::class.java)

        Member.register(createMemberRegisterRequest("valid@gmail.com"), passwordEncoder)
    }


}