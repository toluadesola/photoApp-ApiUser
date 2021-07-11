package com.tolulope.photoappapiuser.service;


import com.tolulope.photoappapiuser.data.UserEntity;
import com.tolulope.photoappapiuser.data.UserRepository;
import com.tolulope.photoappapiuser.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService{

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

     @Override
     public UserDto createUser(UserDto userDto){

         userDto.setUserId(UUID.randomUUID().toString());
         userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

         ModelMapper modelMapper = new ModelMapper();
         modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

         UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);

         userRepository.save(userEntity);

         return userDto;
     }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null) throw new UsernameNotFoundException(email);

        return new ModelMapper().map(userEntity, UserDto.class);
    }
}
