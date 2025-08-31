package cham.splearn.domain

import cham.splearn.domain.MemberStatus.PENDING
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test


class MemberTest {

    @Test
    fun createMember() {
        var member = Member("cham@splearn.app", "cham", "secret")

        assertThat(member.status).isEqualTo(PENDING)
    }

    @Test
    fun constructorNullCheck() {
        assertThatThrownBy {
            Member("   ", "cham", "secret")
        }.isInstanceOf(IllegalArgumentException::class.java)

        assertThatThrownBy {
            Member("cham@splearn.com", "   ", "secret")
        }.isInstanceOf(IllegalArgumentException::class.java)

        assertThatThrownBy {
            Member("cham@splearn.com", "cham", "   ")
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun activate(){
        val member =  Member("cham@splearn.com", "cham", "secret")

        member.activate()

        assertThat(member.status).isEqualTo(MemberStatus.ACTIVE)
    }

    @Test
    fun activateFail(){
        val member =  Member("cham@splearn.com", "cham", "secret")

        member.activate()

        assertThatThrownBy {
            member.activate()
        }.isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun deactivate() {
        val member = Member("cham@splearn.com", "cham", "secret")

        member.activate()
        member.deactivate()

        assertThat(member.status).isEqualTo(MemberStatus.DEACTIVATED)
    }

    @Test
    fun deactivateFail() {
        val member = Member("cham@splearn.com", "cham", "secret")

        assertThatThrownBy {
            member.deactivate()
        }.isInstanceOf(IllegalStateException::class.java)
    }
}