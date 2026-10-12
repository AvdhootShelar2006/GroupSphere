package com.avdhoot.GroupSphereAPI.service;

import com.avdhoot.GroupSphereAPI.dto.userDto.CreateUserDetailResponse;
import com.avdhoot.GroupSphereAPI.dto.userDto.CreateUserRequestDto;
import com.avdhoot.GroupSphereAPI.entity.Roles;
import com.avdhoot.GroupSphereAPI.entity.User;
import com.avdhoot.GroupSphereAPI.exception.DuplicateResourceException;
import com.avdhoot.GroupSphereAPI.exception.ResourceNotFoundException;
import com.avdhoot.GroupSphereAPI.mapper.MemberMapper;
import com.avdhoot.GroupSphereAPI.repository.RoleRepository;
import com.avdhoot.GroupSphereAPI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MemberMapper memberMapper;
    private final RoleRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;


    public CreateUserDetailResponse registerUser(CreateUserRequestDto requestDto) {
        if(userRepository.existsByEmail(requestDto.email())){
            throw new DuplicateResourceException("User with email " + requestDto.email()+" already exits");
        }
        User user = new User();
        Roles roles = rolesRepository.findByName("ROLE_USER").orElseThrow(()-> new ResourceNotFoundException("USER not Found!!"));
        String encodedPassword = passwordEncoder.encode(requestDto.password());

        user.setName(requestDto.name());
        user.setUsername(requestDto.username());
        user.setPassword(encodedPassword);
        user.setEmail(requestDto.email());
        user.getRoles().add(roles);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return memberMapper.toMemberResponseDto(user);
    }

}
