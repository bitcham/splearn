package cham.splearn.application.required

import cham.splearn.domain.Email
import cham.splearn.domain.Member
import org.springframework.data.repository.Repository

/**
 * Member repository functionality.
 */
interface MemberRepository: Repository<Member, Long> {
    fun save(member: Member): Member
    fun findByEmail(email: Email): Member?
}