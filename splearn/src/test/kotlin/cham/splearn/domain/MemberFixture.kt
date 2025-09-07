package cham.splearn.domain

fun createMemberRegisterRequest(email: String): MemberRegisterRequest = MemberRegisterRequest(email, "cham123", "secret1234")

fun createMemberRegisterRequest(): MemberRegisterRequest = MemberRegisterRequest("cham@splearn.com", "cham123", "secret1234")


fun createPasswordEncoder(): PasswordEncoder = object : PasswordEncoder {
    override fun encode(password: String): String {
        return password.reversed()
    }

    override fun matches(password: String, passwordHash: String): Boolean {
        return encode(password) == passwordHash
    }
}