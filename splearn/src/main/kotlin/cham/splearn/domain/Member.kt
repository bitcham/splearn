package cham.splearn.domain

import cham.splearn.domain.MemberStatus.*

@ConsistentCopyVisibility
data class Member private constructor(
    val email: String,
    var nickname: String,
    var passwordHash: String,
    var passwordEncoder: PasswordEncoder,
    var status: MemberStatus = PENDING
){

    companion object {

        fun create(createRequest: MemberCreateRequest, passwordEncoder: PasswordEncoder): Member {
            require(createRequest.email.isNotBlank()) { "Email cannot be blank" }
            require(createRequest.nickname.isNotBlank()) { "Nickname cannot be blank" }
            require(createRequest.password.isNotBlank()) { "Password cannot be blank" }

            return Member(
                email = createRequest.email,
                nickname = createRequest.nickname,
                passwordHash = passwordEncoder.encode(createRequest.password),
                passwordEncoder = passwordEncoder
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