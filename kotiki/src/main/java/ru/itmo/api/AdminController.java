package ru.itmo.api;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.models.Owner;
import ru.itmo.models.Role;
import ru.itmo.service.OwnerService;
import ru.itmo.service.RoleService;
import ru.itmo.service.UserService;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private final UserService userService;
    private final OwnerService ownerService;
    private final RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestParam String username, @RequestParam String password,
                                                 @RequestParam int roleId, @RequestParam int ownerId) {
        Role role = roleService.getRoleById(roleId);
        Owner owner = ownerService.getOwnerById(ownerId);
        try {
            return ResponseEntity.ok().body(userService.createUser(username, password, role, owner).getWrapper());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
