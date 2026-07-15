package com.example.springapi.user.service;

import com.example.springapi.user.dto.UserRequestDTO;
import com.example.springapi.user.model.User;
import com.example.springapi.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id){
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("Usuário não encontrado!"));
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("Usuário não encontrado."));
    }

    public User createUser(UserRequestDTO dto){
        if(userRepository.existsByEmail(dto.email())){
            throw new RuntimeException("Email já cadastrado.");
        }

        User newUser = new User();

        newUser.setName(dto.name());
        newUser.setEmail(dto.email());

        String hashedPassword = passwordEncoder.encode(dto.password());
        newUser.setPassword(hashedPassword);

        return userRepository.save(newUser);
    }

    public User updateUser(UUID id, UserRequestDTO dto){
        User existingUser = getUserById(id);

        Optional<User> userWithSameEmail = userRepository.findByEmail(dto.email());

        if(userWithSameEmail.isPresent() && !userWithSameEmail.get().getId().equals(id)) {
            throw new RuntimeException("Email já cadastrado por outro usuário.");
        }

        // Preciso adicionar criptografia a senha armazenada no banco de dados.
        // salt + hash
        existingUser.setName(dto.name());
        existingUser.setEmail(dto.email());

        String hashedPassword = passwordEncoder.encode(dto.password());
        existingUser.setPassword(hashedPassword);

        return userRepository.save(existingUser);
    }

    public void removeUser(UUID id){
        User existingUser = getUserById(id);
        userRepository.delete(existingUser);
    }

}
