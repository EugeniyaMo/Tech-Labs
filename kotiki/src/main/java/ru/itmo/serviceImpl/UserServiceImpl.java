package ru.itmo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmo.models.Owner;
import ru.itmo.models.Role;
import ru.itmo.models.CustomUser;
import ru.itmo.repository.UserRepository;
import ru.itmo.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public CustomUser createUser(String username, String password, Role role, Owner owner) throws Exception {
        List<String> users = userRepository.findAll().stream()
                .map(CustomUser::getUsername).collect(Collectors.toList());
        if (!users.contains(username)) {
            return userRepository.save(new CustomUser(username, bCryptPasswordEncoder.encode(password), role, owner));
        }
        throw new Exception("User with the same name already exists.");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Unknown user: " + username);
        }
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().getName())
                .build();
    }

    public CustomUser getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public CustomUser getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public CustomUser saveUser(CustomUser user) {
        CustomUser userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return null;
        }

        user.setRole(user.getRole());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }
}
