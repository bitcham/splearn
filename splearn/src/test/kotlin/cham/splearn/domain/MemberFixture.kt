package cham.splearn.domain

fun createMemberRegisterRequest(email: String): MemberRegisterRequest = MemberRegisterRequest(Email.of(email), "cham", "secret")

fun createMemberRegisterRequest(): MemberRegisterRequest = MemberRegisterRequest(Email.of("cham@splearn.com"), "cham", "secret")


fun createPasswordEncoder(): PasswordEncoder = object : PasswordEncoder {
    override fun encode(password: String): String {
        return password.reversed()
    }

    override fun matches(password: String, passwordHash: String): Boolean {
        return encode(password) == passwordHash
    }
}