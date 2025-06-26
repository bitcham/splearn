package com.splearn.application.required;

import com.splearn.domain.Member;
import com.splearn.domain.MemberFixture;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.splearn.domain.MemberFixture.createMemberRegisterRequest;
import static com.splearn.domain.MemberFixture.createPasswordEncoder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void createMember(){
        var member = Member.register(createMemberRegisterRequest(), createPasswordEncoder());

        memberRepository.save(member);

        entityManager.flush();
    }


}