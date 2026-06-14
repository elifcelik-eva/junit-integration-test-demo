package com.elifcelik.testlab.unitTestCases.midDemoTest;

import com.elifcelik.testlab.unitTestCases.midDemo.User;
import com.elifcelik.testlab.unitTestCases.midDemo.UserNotFoundException;
import com.elifcelik.testlab.unitTestCases.midDemo.UserRepository;
import com.elifcelik.testlab.unitTestCases.midDemo.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private User user;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp(){
        user = new User(1L, "ela");
    }

    @Test
    void getUserById_whenUserExists_shouldReturnUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        userService.getUserById(1L);
        verify(userRepository).findById(1L);
    }

    @Test
    void getUserById_whenUserNotFound_shouldThrowException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, ()->userService.getUserById(1L));
    }

    @Test
    void deleteUser_whenUserExists_shouldCallDeleteById() {
        when(userRepository.existsById(1L)).thenReturn(true);
        userService.deletedUser(1L);
        verify(userRepository).deletedById(1L);
    }
    @Test
    void deleteUser_whenUserNotFound_shouldThrowException() {
        when(userRepository.existsById(1L)).thenReturn(false);
        assertThrows(UserNotFoundException.class, () -> userService.deletedUser(1L));
        verify(userRepository, never()).deletedById(1L);
    }

}
