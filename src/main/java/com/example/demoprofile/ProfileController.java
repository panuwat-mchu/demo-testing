package com.example.demoprofile;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
@Log
public class ProfileController {

    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_profile:read')")
    public ResponseEntity<Profile> findById(
            @RequestHeader(name = "sessionRef") String sessionRef,
            @PathVariable String id
    ){
        log.info("sessionRef: "+sessionRef);
        log.info("id: "+id);

        Profile profile = this.profileService.findById(id);

        return ResponseEntity.ok(profile);
    }
}
