package cham.splearn.domain

import cham.splearn.domain.MemberStatus.PENDING
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test


class MemberTest {

    @Test
    fun createMember(){
        var member = Member("cham@splearn.app", "cham", "secret")

        assertThat(member.status).isEqualTo(PENDING)
    }
}