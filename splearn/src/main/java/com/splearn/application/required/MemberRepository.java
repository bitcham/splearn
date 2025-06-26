package com.splearn.application.required;

import com.splearn.domain.Member;
import org.springframework.data.repository.Repository;


/**
 * This interface represents the contract for member repository functionality.
 */
public interface MemberRepository extends Repository<Member, Long> {
    Member save(Member member);
}
