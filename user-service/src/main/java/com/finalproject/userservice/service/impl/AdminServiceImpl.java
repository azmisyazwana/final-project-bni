package com.finalproject.userservice.service.impl;

import com.finalproject.userservice.dto.input.IdRequest;
import com.finalproject.userservice.dto.output.AccountUserOutput;
import com.finalproject.userservice.model.User;
import com.finalproject.userservice.repository.UserRepository;
import com.finalproject.userservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AccountUserOutput> getAllUser() {
        Iterable<User> users = userRepository.findAll();
        List<User> userList = IterableUtils.toList(users);

        List<AccountUserOutput> accountUserOutputs = new ArrayList<>();
        for(User user : userList){
            accountUserOutputs.add(modelMapper.map(user, AccountUserOutput.class));
        }
        return accountUserOutputs;
    }

    @Override
    public AccountUserOutput getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User temp = user.get();
            return AccountUserOutput.builder()
                    .id(temp.getId())
                    .username(temp.getUsername())
                    .email(temp.getEmail())
                    .gender(temp.getGender())
                    .enabled(temp.isEnabled())
                    .build();
        }else{
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public void activateAccountById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User temp = user.get();
            temp.setEnabled(true);
            userRepository.save(temp);
        }
    }

    @Override
    public void activateMultipleAccount(List<IdRequest> userActivate){
        for (IdRequest user: userActivate) {
           Optional<User> userById = userRepository.findById(user.getId());
           if(userById.isPresent()){
               User temp = userById.get();
               temp.setEnabled(true);
               userRepository.save(temp);
           }else{
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id not found");
           }
        }
    }

}
