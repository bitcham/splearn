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
    init {
        require(email.isNotBlank()) { "Email cannot be blank" }
        require(nickname.isNotBlank()) { "Nickname cannot be blank" }
        require(passwordHash.isNotBlank()) { "Password hash cannot be blank" }
    }

    companion object create{

        operator fun invoke(email: String, nickname: String, password: String, passwordEncoder: PasswordEncoder): Member {
            val passwordHash = passwordEncoder.encode(password)
            return Member(email, nickname, passwordHash, passwordEncoder)
        }

    }

    fun activate() {
        check(status == PENDING){ "Only members with PENDING status can be activated." }
        status = ACTIVE
    }

    fun deactivate() {
        check(status == ACTIVE){ "Only members with ACTIVE status can be deactivated." }

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