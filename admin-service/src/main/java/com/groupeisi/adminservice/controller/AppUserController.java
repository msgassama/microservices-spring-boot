package com.groupeisi.adminservice.controller;

import com.groupeisi.adminservice.entity.AppUser;
import com.groupeisi.adminservice.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("users")
public class AppUserController {

    private AppUserRepository appUserRepository;

    @PostMapping
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser appUser){
        AppUser savedUser = appUserRepository.save(appUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AppUser>> getUsers(){
        List<AppUser> users = appUserRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id){
        AppUser user = appUserRepository.findById(id).get();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
