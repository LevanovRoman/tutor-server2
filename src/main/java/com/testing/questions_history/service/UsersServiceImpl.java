package com.testing.questions_history.service;

import com.testing.questions_history.dto.UsersDto;
import com.testing.questions_history.exception.UserNotFoundException;
import com.testing.questions_history.model.Users;
import com.testing.questions_history.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService{

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UsersDto usersDto) {
        Users user = new Users();
        saveUser(user, usersDto);
    }

    @Override
    public Users getUserById(Long id) {
        return usersRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public void updateUser(Long id, UsersDto usersDto) {
        Users user = getUserById(id);
        saveUser(user, usersDto);
    }

    public void saveUser(Users user, UsersDto usersDto){
        user.setFirstName(usersDto.firstName());
        user.setLastName(usersDto.lastName());
        user.setEmail(usersDto.email());
        user.setPassword(passwordEncoder.encode(usersDto.password()));
        user.setPasswordOrigin(usersDto.password());
        user.setRole(usersDto.role());
        usersRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }
}
