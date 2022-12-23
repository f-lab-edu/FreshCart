package com.example.freshcart.user.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.fixture.UserFixture;
import com.example.freshcart.user.application.command.LoginCommand;
import com.example.freshcart.user.application.command.SignupCommand;
import com.example.freshcart.user.domain.PasswordEncoder;
import com.example.freshcart.user.domain.User;
import com.example.freshcart.user.domain.UserRepository;
import com.example.freshcart.user.domain.exception.EmailExistsException;
import com.example.freshcart.user.domain.exception.PasswordDoesNotMatchException;
import com.example.freshcart.user.domain.exception.UserNotExistsException;
import com.example.freshcart.user.infrastructure.BCryptEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * passwordEncoder는 BCrypt 라이브러리 사용했기에 mock 사용할 필요가 없다고 판단.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @InjectMocks
  private UserService userService;
  @Mock
  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;
  private SignupCommand signupCommand;
  private User seller;

  @BeforeEach
  void init() {
    passwordEncoder = new BCryptEncoder();
    userService = new UserService(userRepository, passwordEncoder);
  }

  @DisplayName("이메일 중복 아닐 경우 회원가입 성공")
  @Test
  void signupSuccess() {
    //Given
    signupCommand = UserFixture.aSignupCommand().build();
    Mockito.when(userRepository.findByUserEmail("user1@gmail.com")).thenReturn(null);
    ArgumentCaptor<User> valueCapture = ArgumentCaptor.forClass(User.class);

    //when
    userService.register(signupCommand);

    //Then
    verify(userRepository).save(any(User.class));
    verify(userRepository).save(valueCapture.capture());
    User savedUser = valueCapture.getValue();
    assertThat(savedUser.getEmail()).isEqualTo(signupCommand.getEmail());
  }


  @DisplayName("이메일 중복 시 가입에 실패한다.")
  @Test
  void signupFail() {
    signupCommand = UserFixture.aSignupCommand().build();
    //given: 아이디가 중복되는 상황에서
    Mockito.when(userRepository.findByUserEmail("user1@gmail.com"))
        .thenReturn(new User());

    assertThrows(EmailExistsException.class, () -> userService.register(signupCommand));
  }

  /**
   * 사용자는 회원 가입을 한 상황.
   * passwordEncode
   *
   */
  @DisplayName("아이디, 비밀번호 일치 시 로그인에 성공한다")
  @Test
  void loginSuccess() {
    //given
    seller = UserFixture.aSeller().password(passwordEncoder.encrypt("lopp1245")).build();
    userRepository.save(seller);
    Mockito.when(userRepository.findByUserEmail("seller1@gmail.com")).thenReturn(seller);
    LoginCommand loginCommand = UserFixture.aLoginCommand().build();
    //when
    LoginUser user = userService.signIn(loginCommand);
    //Then
    assertThat(user.getEmail()).isEqualTo(seller.getEmail());
  }

  @DisplayName("로그인 시 매치된 유저가 없다면 예외를 반환한다")
  @Test
  void loginUserNotExists(){
    //given
    Mockito.when(userRepository.findByUserEmail("seller1@gmail.com")).thenReturn(null);
    LoginCommand loginCommand = UserFixture.aLoginCommand().build();
    //when, Then
    assertThrows(UserNotExistsException.class, () -> userService.signIn(loginCommand));
  }

  @DisplayName("비밀번호 불일치 시 비밀번호 실패 예외를 반환한다")
  @Test
  void loginPasswordDoesNotMatch() {
    //given
    seller = UserFixture.aSeller().password(passwordEncoder.encrypt("lopp1245")).build();
    userRepository.save(seller);
    Mockito.when(userRepository.findByUserEmail("seller1@gmail.com")).thenReturn(seller);
    LoginCommand invalidLogin = UserFixture.aLoginCommand().password("1111").build();
    //when, Then
    assertThrows(PasswordDoesNotMatchException.class, () -> userService.signIn(invalidLogin));
  }
}