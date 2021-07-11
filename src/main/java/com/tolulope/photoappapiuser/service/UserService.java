package com.tolulope.photoappapiuser.service;

import com.tolulope.photoappapiuser.shared.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public UserDto createUser(UserDto userDto);
    public UserDto getUserDetailsByEmail(String email);
}
