package com.groupeisi.dashboardservice.controller;

import com.groupeisi.dashboardservice.dto.AppUserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("dashboard/users")
public class DashboardController {

    private RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<List<AppUserDto>> getApUsers(){

        ResponseEntity<AppUserDto[]> appUsersDto = restTemplate.getForEntity(
                "http://localhost:8082/users",
                AppUserDto[].class
        );

        AppUserDto[] appUserDtos = appUsersDto.getBody();
        return new ResponseEntity<>(Arrays.asList(appUserDtos), HttpStatus.OK);


    }

    @GetMapping("{id}")
    public ResponseEntity<AppUserDto> getUserById(@PathVariable Long id){
        ResponseEntity<AppUserDto> responseEntity = restTemplate.getForEntity(
                "http://localhost:8082/users/" + id,
                AppUserDto.class
        );
        AppUserDto appUserDto = responseEntity.getBody();

        return new ResponseEntity<>(appUserDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AppUserDto> saveUser(@RequestBody AppUserDto appUserDto){
        ResponseEntity<AppUserDto> responseEntity = restTemplate.postForEntity(
                "http://localhost:8082/users",
                appUserDto,
                AppUserDto.class
        );

        AppUserDto savedUser = responseEntity.getBody();
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }
}
