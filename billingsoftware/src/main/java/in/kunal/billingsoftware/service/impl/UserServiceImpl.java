package in.kunal.billingsoftware.service.impl;

import in.kunal.billingsoftware.entity.UserEntity;
import in.kunal.billingsoftware.io.UserRequest;
import in.kunal.billingsoftware.io.UserResponce;
import in.kunal.billingsoftware.repository.UserRepository;
import in.kunal.billingsoftware.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponce createUser(UserRequest request) {
        UserEntity newUser = convertToEntity(request);
        newUser = userRepository.save(newUser);
        return convertToResponce(newUser);
    }

    private UserResponce convertToResponce(UserEntity newUser) {
        return UserResponce.builder()
                .name(newUser.getName())
                .email(newUser.getEmail())
                .userId(newUser.getUserId())
                .createdAt(newUser.getCreatedAt())
                .updatedAt(newUser.getUpdatedAt())
                .role(newUser.getRole())
                .build();
    }

    private UserEntity convertToEntity(UserRequest request) {

        return UserEntity.builder()
                .userId(UUID.randomUUID().toString())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole().toUpperCase())
                .name(request.getName())
                .build();
    }

    @Override
    public String getUserRole(String email) {
        UserEntity existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found for the email: "+ email));
        return existingUser.getRole();
    }

    @Override
    public List<UserResponce> readUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> convertToResponce(user))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(String id) {

        UserEntity existingUser = userRepository.findByUserId(id)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        userRepository.delete(existingUser);

    }
}
