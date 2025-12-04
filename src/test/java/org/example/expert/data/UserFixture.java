package org.example.expert.data;

import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.enums.UserRole;

public class UserFixture {
    public static final String DEFAULT_EMAIL = "test@test.com";
    public static final String DEFAULT_PASSWORD = "1234";

    public static User createUserAdminRole() {
        return new User(DEFAULT_EMAIL, DEFAULT_PASSWORD, UserRole.ADMIN);
    }

    public static User createUserUserRole() {
        return new User(DEFAULT_EMAIL, DEFAULT_PASSWORD, UserRole.USER);
    }

    public static AuthUser createAuthUserAdminRole() {
        return new AuthUser(1L, DEFAULT_EMAIL, UserRole.ADMIN);
    }

    public static AuthUser createAuthUserUserRole() {
        return new AuthUser(1L, DEFAULT_EMAIL, UserRole.USER);
    }
}
