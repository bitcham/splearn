package cham.splearn.application.provided

import cham.splearn.domain.Member
import cham.splearn.domain.MemberRegisterRequest

/**
 * Member registration functionality.
 */
interface MemberRegister {
    fun register(registerRequest: MemberRegisterRequest): Member
}