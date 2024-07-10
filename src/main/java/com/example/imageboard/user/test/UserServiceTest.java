package com.example.imageboard.user.test;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean // Mock the repository for unit testing
    private UserRepository userRepository;

    @MockBean
    private UserValidator userValidator;

    @Test
    void testCreateUser_ValidInput() {
        UserDto userDto = UserDto.builder()
                .username("newuser")
                .email("newuser@example.com")
                .password("password")
                .build();
        // Mocking:  when(userRepository.save(any(User.class))).thenReturn(...);
        // ... (set up mocks and expectations)

        User createdUser = userService.createUser(userDto, new BeanPropertyBindingResult(userDto, "userDto"));

        // ... (Assertions to verify successful user creation and password hashing)
    }
}

