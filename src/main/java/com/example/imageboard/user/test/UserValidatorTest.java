package com.example.imageboard.user.test;

@ExtendWith(MockitoExtension.class)
public class UserValidatorTest {

    @Mock
    private UserRepository userRepository;

    private UserValidator userValidator;

    @BeforeEach
    public void setup() {
        userValidator = new UserValidator(userRepository);
    }

    @Test
    void testValidate_ValidUserDto() {
        // ... (create a valid UserDto)

        Errors errors = new BeanPropertyBindingResult(userDto, "userDto");
        userValidator.validate(userDto, errors);

        assertThat(errors.hasErrors()).isFalse();
    }
}
