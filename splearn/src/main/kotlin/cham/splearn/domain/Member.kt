package cham.splearn.domain

import cham.splearn.domain.MemberStatus.*


data class Member(
    val email: String,
    var nickname: String,
    var passwordHash: String,
    var status: MemberStatus = PENDING
){
    init {
        require(email.isNotBlank()) { "Email cannot be blank" }
        require(nickname.isNotBlank()) { "Nickname cannot be blank" }
        require(passwordHash.isNotBlank()) { "Password hash cannot be blank" }
    }

    fun activate() {
        check(status == PENDING){ "Only members with PENDING status can be activated." }

        this.status = ACTIVE
    }

    fun deactivate() {
        check(status == ACTIVE){ "Only members with ACTIVE status can be deactivated." }

        this.status = MemberStatus.DEACTIVATED
    }
}