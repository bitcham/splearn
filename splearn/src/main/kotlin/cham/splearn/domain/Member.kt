package cham.splearn.domain

import cham.splearn.domain.MemberStatus.*


class Member(
    val email: String,
    var nickname: String,
    var passwordHash: String,
    var status: MemberStatus = PENDING
){

}