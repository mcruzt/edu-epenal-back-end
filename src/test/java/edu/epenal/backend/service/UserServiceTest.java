package edu.epenal.backend.service;

import edu.epenal.backend.model.dto.UserDTO;
import edu.epenal.backend.model.entity.User;
import edu.epenal.backend.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetAllUsers() {
        when(mockUserRepository.findAll()).thenReturn(new ArrayList<>());

       List<UserDTO> userDTOList = userService.getAllUsers();

        Assertions.assertNotNull(userDTOList);
    }

    @Test
    void testUpsertUser() {
        when(mockUserRepository.saveAndFlush(any())).thenReturn(new User());

        UserDTO userDTO = userService.upsertUser(new UserDTO(null, "Usuario"));

        Assertions.assertNotNull(userDTO);
    }
}
