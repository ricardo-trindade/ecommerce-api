package com.projects.ecommerce.service;

import com.projects.ecommerce.dto.request.UserRequestDTO;
import com.projects.ecommerce.dto.response.UserResponseDTO;
import com.projects.ecommerce.entity.User;
import com.projects.ecommerce.enums.Roles;
import com.projects.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(
            user.getName(),
            user.getEmail(),
            user.getPhone(),
            user.getRole()
        );
    }

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = new User();

        // Adicionar "E-mail já utilizado"
        // Adicionar "Telefone já utilizado"

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPhone(dto.phone());
        user.setPassword(dto.password());
        user.setRole(Roles.USER);

        repository.save(user);

        return toResponse(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO findByEmail(User user) {
        if (repository.existsByEmail(user)) {
            return toResponse(user);
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    public UserResponseDTO updateUser(UUID id, UserRequestDTO dto) {

        // Adicionar "E-mail já utilizado"
        // Adicionar "Telefone já utilizado"
        
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPhone(dto.phone());
        user.setPassword(dto.password());

        repository.save(user);

        return toResponse(user);
    }

    public void deleteUser(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        repository.deleteById(id);
    }

}
