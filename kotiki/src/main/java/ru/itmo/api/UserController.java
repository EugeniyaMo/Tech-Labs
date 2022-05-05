package ru.itmo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.models.Cat;
import ru.itmo.models.CustomUser;
import ru.itmo.models.Owner;
import ru.itmo.models.Role;
import ru.itmo.repository.UserRepository;
import ru.itmo.service.OwnerService;
import ru.itmo.service.UserService;
import ru.itmo.wrapper.CatWrapper;
import ru.itmo.wrapper.UserWrapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String greeting() {
        return "You have successfully logged in.";
    }

    @GetMapping("/getCats")
    public ResponseEntity<List<CatWrapper>> getCats(int userId) {
        Owner owner = ownerService.getOwnerById(userService.getUserById(userId).getOwner().getId());
        return new ResponseEntity<>(owner.getCats().stream().map((Cat::getWrapper)).
                        collect(Collectors.toList()), HttpStatus.ACCEPTED);
    }

}
