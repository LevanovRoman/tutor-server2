package com.testing.questions_history.service;

import com.testing.questions_history.dto.UsersDto;
import com.testing.questions_history.model.Users;

import java.util.List;

public interface UsersService {

    void createUser(UsersDto usersDto);

    Users getUserById(Long id);

    List<Users> getAllUsers();

    void updateUser(Long id, UsersDto usersDto);

    void deleteUser(Long id);
}
