package cham.splearn.application.provided

import cham.splearn.domain.Member
import cham.splearn.domain.MemberRegisterRequest
import jakarta.validation.Valid

/**
 * Member registration functionality.
 */
interface MemberRegister {
    fun register(@Valid registerRequest: MemberRegisterRequest): Member
}