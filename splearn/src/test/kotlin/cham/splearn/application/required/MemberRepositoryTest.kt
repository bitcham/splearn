package cham.splearn.application.required

import cham.splearn.domain.Member
import cham.splearn.domain.createPasswordEncoder
import cham.splearn.domain.createMemberRegisterRequest
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldNotBe
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.extensions.spring.SpringExtension
import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.DataIntegrityViolationException

@DataJpaTest
class MemberRepositoryTest : FunSpec() {
    
    override fun extensions() = listOf(SpringExtension)
    
    @Autowired
    lateinit var memberRepository: MemberRepository

    @Autowired
    lateinit var entityManager: EntityManager

    init {
        test("createMember") {
            val member = memberRepository.save(Member.register(createMemberRegisterRequest(), createPasswordEncoder()))

            member.id shouldNotBe null

            entityManager.flush()
        }

        test("duplicateEmailFail") {
            val member1 = memberRepository.save(Member.register(createMemberRegisterRequest(), createPasswordEncoder()))

            val member2 = Member.register(createMemberRegisterRequest(), createPasswordEncoder())

            shouldThrow<DataIntegrityViolationException> {
                memberRepository.save(member2)
            }
        }
    }
}