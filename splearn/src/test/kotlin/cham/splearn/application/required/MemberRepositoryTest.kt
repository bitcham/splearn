package cham.splearn.application.required

import cham.splearn.domain.Member
import cham.splearn.domain.createPasswordEncoder
import cham.splearn.domain.createMemberRegisterRequest
import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    lateinit var memberRepository: MemberRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun createMember(){
        val member = memberRepository.save(
            Member.register(createMemberRegisterRequest(), createPasswordEncoder()))

        assertThat(member.id).isNotNull()

        entityManager.flush()
    }

}