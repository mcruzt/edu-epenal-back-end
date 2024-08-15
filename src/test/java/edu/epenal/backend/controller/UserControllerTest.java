package edu.epenal.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.epenal.backend.model.dto.UserDTO;
import edu.epenal.backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService mockUserService;

    @Test
    void testGetEndpoint() throws Exception {
        // Mock the service layer response
        List<UserDTO> usersList = new ArrayList<>();
        usersList.add(new UserDTO(1L, "Usuario"));
        when(mockUserService.getAllUsers()).thenReturn(usersList);

        // Perform GET request and verify the response
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Usuario")));
    }

    @Test
    void testPutEndpoint() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        // Mock the service layer response
        UserDTO user = new UserDTO(1L,"Usuario");
        when(mockUserService.upsertUser(any())).thenReturn(user);

        // Perform PUT request and verify the response
        mockMvc.perform(put("/user").contentType(APPLICATION_JSON).content(mapper.writeValueAsString(new UserDTO(null,"Usuario"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Usuario")));
    }
}
