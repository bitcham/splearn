package com.cham.splearn.domain;

import com.splearn.domain.Member;
import com.splearn.domain.MemberStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class MemberTest {
    @Test
    void createMember() {
        var member = new Member("cham@splearn.app", "cham", "secret");

        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void constructorNullCheack(){
        assertThatThrownBy(()->new Member(null,"cham","secret")).isInstanceOf(NullPointerException.class);
    }

    @Test
    void activate(){
        var member = new Member("cham@splearn.app", "cham", "secret");
        member.activate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }

    @Test
    void activateFail(){
        var member = new Member("cham@splearn.app", "cham", "secret");
        member.activate();

        assertThatThrownBy(member::activate).isInstanceOf(IllegalStateException.class)
                .hasMessage("Member is not in PENDING status");

    }

    @Test
    void deactivate() {
        var member = new Member("cham@splearn.app", "cham", "secret");
        member.activate();

        member.deactivate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
    }

    @Test
    void deactivateFail(){
        var member = new Member("cham@splearn.app", "cham", "secret");

        assertThatThrownBy(member::deactivate).isInstanceOf(IllegalStateException.class)
                .hasMessage("Member is not in ACTIVE status");

        member.activate();
        member.deactivate();

        assertThatThrownBy(member::deactivate).isInstanceOf(IllegalStateException.class)
                .hasMessage("Member is not in ACTIVE status");
    }






}
