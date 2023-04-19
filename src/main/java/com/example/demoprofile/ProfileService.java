package com.example.demoprofile;

import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    public Profile findById(String profileId){
        Profile profile = new Profile();
        profile.setId("barId");
        return profile;
    }
}
