package edu.epenal.backend.controller;

import edu.epenal.backend.model.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class DemoController {

    @Value("${epenal.message}")
    private String message;

    @GetMapping
    public ResponseEntity<MessageDTO> getMessage() {

        try {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            return ResponseEntity.ok(new MessageDTO("Welcome to %s!!!".formatted(addr.getHostName())));
        } catch (UnknownHostException ex) {
            return ResponseEntity.ok(new MessageDTO("Welcome to %s!!!".formatted(message)));
        }


    }
}
