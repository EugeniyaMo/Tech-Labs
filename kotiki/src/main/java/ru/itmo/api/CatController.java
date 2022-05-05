package ru.itmo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.models.Cat;
import ru.itmo.models.CustomUser;
import ru.itmo.models.Owner;
import ru.itmo.serviceImpl.CatServiceImpl;
import ru.itmo.serviceImpl.OwnerServiceImpl;
import ru.itmo.serviceImpl.UserServiceImpl;
import ru.itmo.wrapper.CatWrapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cat")
@RequiredArgsConstructor
public class CatController {
    private final CatServiceImpl catService;
    private final OwnerServiceImpl ownerService;
    private final UserServiceImpl userService;

    @PostMapping("/create")
    public ResponseEntity<?> createCat(@RequestParam String name, @RequestParam String birthday,
                                                @RequestParam String breed, @RequestParam int colorId,
                                                @RequestParam int ownerId, Authentication authentication) {
        Owner owner = ownerService.getOwnerById(ownerId);
        CustomUser user = userService.getUserByUsername(authentication.getName());
        if (user.getOwner().getId() == ownerId) {
            return ResponseEntity.ok().body(catService.createCat(name, birthday, breed, colorId, owner).getWrapper());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("You can not create cat that is owned by another user.");
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getCat(@RequestParam int catId, Authentication authentication) {
        int ownerId = userService.getUserByUsername(authentication.getName()).getOwner().getId();
        Cat cat = catService.getCatById(catId);
        if (ownerId == cat.getOwnerId()) {
            return ResponseEntity.ok().body(cat.getWrapper());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("You can not get info about stranger cat.");
    }

    @GetMapping("/all")
    public ResponseEntity<List<CatWrapper>> getCats(Authentication authentication) {
        int ownerId = userService.getUserByUsername(authentication.getName()).getOwner().getId();
        return new ResponseEntity<>(
                catService.getCats()
                        .stream()
                        .filter(cat -> cat.getOwnerId() == ownerId)
                        .map((Cat::getWrapper)).
                        collect(Collectors.toList()),
                HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCat(@RequestParam int catId, Authentication authentication) {
        CustomUser user = userService.getUserByUsername(authentication.getName());
        Cat cat = catService.getCatById(catId);
        if (user.getOwner().getId() == cat.getOwnerId()) {
            return ResponseEntity.ok().body(catService.deleteCat(cat).getWrapper());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("You can not delete stranger cat.");
    }
}