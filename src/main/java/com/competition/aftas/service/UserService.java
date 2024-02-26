package com.competition.aftas.service;

import com.competition.aftas.DTO.response.UserDto;
import com.competition.aftas.utils.Response;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService{

    Response<UserDto> me();
}
