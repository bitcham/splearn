package com.splearn.domain;

public class MemberFixture {
    public static MemberRegisterRequest createMemberRegisterRequest() {
        return new MemberRegisterRequest("cham@app.com", "cham", "secret");
    }

    public static PasswordEncoder createPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(String password) {
                return password.toUpperCase();
            }

            @Override
            public boolean matches(String password, String passwordHash) {
                return encode(password).equals(passwordHash);
            }
        };
    }

    public static MemberRegisterRequest createMemberRegisterRequest(String email) {
        return new MemberRegisterRequest(email, "cham", "secret");
    }
}
