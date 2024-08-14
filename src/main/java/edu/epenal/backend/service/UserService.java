package edu.epenal.backend.service;

import edu.epenal.backend.model.dto.UserDTO;
import edu.epenal.backend.model.entity.User;
import edu.epenal.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream().map(user -> new UserDTO(user.getId(), user.getName())).toList();
    }

    public UserDTO upsertUser(UserDTO user){
        User userToSave = new User();
        userToSave.setId(user.getId());
        userToSave.setName(user.getName());

        User userSaved = userRepository.saveAndFlush(userToSave);

        return new UserDTO(userSaved.getId(), userSaved.getName());
    }
}
