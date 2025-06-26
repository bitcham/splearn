package com.splearn.application.provided;

import com.splearn.domain.Member;
import com.splearn.domain.MemberRegisterRequest;

/**
 * This interface represents the contract for member registration functionality.
 */
public interface MemberRegister {
    Member register(MemberRegisterRequest registerRequest);
}
