package cham.splearn.domain

import cham.splearn.domain.MemberStatus.*
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
@ConsistentCopyVisibility
data class Member private constructor(

    @Id @GeneratedValue
    val id: Long = 0,

    @Embedded
    val email: Email,

    var nickname: String,

    var passwordHash: String,

    @Enumerated(EnumType.STRING)
    var status: MemberStatus = PENDING
){

    protected constructor() : this(
        email = Email.of("default@example.com"),
        nickname = "",
        passwordHash = ""
    )

    companion object {
        fun register(createRequest: MemberRegisterRequest, passwordEncoder: PasswordEncoder): Member {
            require(createRequest.nickname.isNotBlank()) { "Nickname cannot be blank" }
            require(createRequest.password.isNotBlank()) { "Password cannot be blank" }

            return Member(
                email = createRequest.email,
                nickname = createRequest.nickname,
                passwordHash = passwordEncoder.encode(createRequest.password)
            )
        }
    }

    fun activate() {
        check(status == PENDING){ "Only members with PENDING status can be activated." }

        status = ACTIVE
    }

    fun isActive() = status == ACTIVE

    fun deactivate() {
        check(isActive()){ "Only members with ACTIVE status can be deactivated." }

        status = DEACTIVATED
    }

    fun verifyPassword(password: String, passwordEncoder: PasswordEncoder): Boolean {
        return passwordEncoder.matches(password, this.passwordHash)
    }

    fun changeNickname(nickname: String) {
        require(nickname.isNotBlank()) { "Nickname cannot be blank" }
        this.nickname = nickname
    }

    fun changePassword(password: String, passwordEncoder: PasswordEncoder) {
        require(password.isNotBlank()) { "Password cannot be blank" }
        this.passwordHash = passwordEncoder.encode(password)
    }


}