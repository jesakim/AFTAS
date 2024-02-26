package com.competition.aftas.service.impl;

import com.competition.aftas.DTO.response.UserDto;
import com.competition.aftas.domain.Authority;
import com.competition.aftas.domain.User;
import com.competition.aftas.service.UserService;
import com.competition.aftas.utils.Response;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public Response<UserDto> me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()){
            var user = (User) authentication.getPrincipal();
            System.out.println(user);
            var userDto = UserDto.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .role(user.getRole().name())
                    .authorities((Set<Authority>) user.getAuthorities())
                    .build();
            return Response.<UserDto>builder()
                    .result(userDto)
                    .build();
        }
        return Response.<UserDto>builder()
                .error("User not found")
                .build();
    }
}
