package com.junior.library.services;

import com.junior.library.dto.UserRequestDTO;
import com.junior.library.entities.User;
import com.junior.library.exceptions.ResourceNotFoundException;
import com.junior.library.exceptions.UserLinkedToLoanException;
import com.junior.library.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    }

    public User save(UserRequestDTO userRequestDTO) {
        User user = new User(userRequestDTO.getName(), userRequestDTO.getEmail());
        return userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        User user = findById(id);

        if (user.getActiveLoansQuantity() != 0) {
            throw new UserLinkedToLoanException("Cannot delete a user with active loans");
        }

        userRepository.delete(user);
    }

    @Transactional
    public User update(Long id, UserRequestDTO userRequestDTO) {
        User user = findById(id);

        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());

        return user;
    }
}
