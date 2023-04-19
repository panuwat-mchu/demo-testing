package com.example.demoprofile;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
@Log
public class ProfileController {

    @GetMapping("/{id}")
    public ResponseEntity<Profile> findById(
            @RequestHeader(name = "sessionRef") String sessionRef,
            @PathVariable String id
    ){
        log.info("sessionRef: "+sessionRef);
        log.info("id: "+id);

        Profile profile = new Profile();
        profile.setId("fooId");

        return ResponseEntity.ok(profile);
    }
}
