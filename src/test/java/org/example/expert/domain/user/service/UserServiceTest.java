package org.example.expert.domain.user.service;

import org.example.expert.config.PasswordEncoder;
import org.example.expert.data.UserFixture;
import org.example.expert.domain.user.dto.request.UserChangePasswordRequest;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User testUser;
    private long userId;

    @BeforeEach
    void setup() {
        userId = 1L;
        testUser = UserFixture.createUserAdminRole();
    }

    @Test
    @DisplayName("사용자ID로 사용자 조회 성공")
    void getUser_success() {
        // given
        ReflectionTestUtils.setField(testUser, "id", userId);

        given(userRepository.findUserById(anyLong())).willReturn(testUser);

        // when
        UserResponse result = userService.getUser(userId);

        // then
        assertThat(result.getId()).isEqualTo(userId);
        assertThat(result.getEmail()).isEqualTo(testUser.getEmail());
    }

    @Test
    @DisplayName("비밀번호 변경 성공")
    void changePassword_success() {
        // given
        ReflectionTestUtils.setField(testUser, "id", userId);

        given(userRepository.findUserById(anyLong())).willReturn(testUser);

        String curPassword = "curPassword";
        String newPassword = "newPassword1";

        given(passwordEncoder.matches(newPassword, testUser.getPassword())).willReturn(false);
        given(passwordEncoder.matches(curPassword, testUser.getPassword())).willReturn(true);
        given(passwordEncoder.encode(newPassword)).willReturn(newPassword);

        UserChangePasswordRequest request = new UserChangePasswordRequest(curPassword, newPassword);

        // when
        userService.changePassword(userId, request);

        // then
        assertThat(testUser.getPassword()).isEqualTo(newPassword);
    }
}