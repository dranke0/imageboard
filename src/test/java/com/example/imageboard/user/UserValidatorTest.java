package com.example.imageboard.user;

import com.example.imageboard.user.validator.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class UserValidatorTest {

    @Mock
    private UserRepository userRepository;

    private UserValidator userValidator;

    @BeforeEach
    void setup() {
        userValidator = new UserValidator(userRepository);
    }

    @Test
    void testValidate_ValidUserDto() {
        UserDto userDto = UserDto.builder()
                .email("example@mail.com")
                .username("username")
                .password("password")
                .build();

        Errors errors = new BeanPropertyBindingResult(userDto, "userDto");
        userValidator.validate(userDto, errors);

        assertThat(errors.hasErrors()).isFalse(); // Correct assertion for a boolean value
        verifyNoInteractions(userRepository);
    }



    @Test
    void testValidate_InvalidEmail() {
        UserDto userDto = UserDto.builder()
                .username("validuser")
                .email("invalid_email")
                .password("ValidPassword123!")
                .build();

        Errors errors = new BeanPropertyBindingResult(userDto, "userDto");
        userValidator.validate(userDto, errors);

        assertThat(errors.hasErrors()).isTrue();
        assertThat(errors.getFieldError("email")).isNotNull()
                .extracting(FieldError::getCode).isEqualTo("email.invalid");
    }

    // ... Add tests for other validation rules
}


