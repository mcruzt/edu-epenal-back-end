package edu.epenal.backend.controller;

import edu.epenal.backend.dto.MessageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {


    @GetMapping
    public ResponseEntity<MessageDTO> getMessage(){
        return ResponseEntity.ok(new MessageDTO("Welcome to epenal!!!"));
    }
}
