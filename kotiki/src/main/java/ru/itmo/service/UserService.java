package ru.itmo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itmo.models.CustomUser;
import ru.itmo.models.Owner;
import ru.itmo.models.Role;

@Service
public interface UserService extends UserDetailsService {
    CustomUser createUser(String username, String password, Role role, Owner owner) throws Exception;
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    CustomUser saveUser(CustomUser user);
    CustomUser getUserById(int userId);
    CustomUser getUserByUsername(String username);
}
