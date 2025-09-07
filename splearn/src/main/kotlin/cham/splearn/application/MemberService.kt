package cham.splearn.application

import cham.splearn.application.provided.MemberRegister
import cham.splearn.application.required.EmailSender
import cham.splearn.application.required.MemberRepository
import cham.splearn.domain.DuplicateEmailException
import cham.splearn.domain.Email
import cham.splearn.domain.Member
import cham.splearn.domain.MemberRegisterRequest
import cham.splearn.domain.PasswordEncoder
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Service
@Transactional
@Validated
class MemberService(
    private val memberRepository: MemberRepository,
    private val emailSender: EmailSender,
    private val passwordEncoder: PasswordEncoder
) : MemberRegister {


    override fun register(@Valid registerRequest: MemberRegisterRequest): Member {
        checkDuplicateEmail(registerRequest)

        val member = Member.register(registerRequest, passwordEncoder)

        memberRepository.save(member)

        sendWelcomeEmail(member)

        return member
    }

    private fun sendWelcomeEmail(member: Member) {
        emailSender.send(
            member.email,
            "Please complete your registration",
            "Please complete your registration by clicking the below link"
        )
    }

    private fun checkDuplicateEmail(registerRequest: MemberRegisterRequest) {
        memberRepository.findByEmail(Email.of(registerRequest.email))?.let {
            throw DuplicateEmailException("Email ${registerRequest.email} is already registered.")
        }
    }
}