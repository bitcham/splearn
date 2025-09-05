package cham.splearn.application.required

import cham.splearn.domain.Member
import org.springframework.data.repository.Repository
import java.util.Optional

/**
 * Member repository functionality.
 */
interface MemberRepository: Repository<Member, Long> {
    fun save(member: Member): Member
}