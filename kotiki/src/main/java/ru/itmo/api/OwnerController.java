package ru.itmo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.models.Owner;
import ru.itmo.serviceImpl.CatServiceImpl;
import ru.itmo.serviceImpl.OwnerServiceImpl;
import ru.itmo.wrapper.OwnerWrapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/owner")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerServiceImpl ownerService;
    private final CatServiceImpl catService;

    @PostMapping("/create")
    public ResponseEntity<OwnerWrapper> createOwner(@RequestParam String name, @RequestParam String birthday) {
        return ResponseEntity.ok().body(ownerService.createOwner(name, birthday).getWrapper());
    }

    @PostMapping("/addCatToOwner")
    public ResponseEntity<?> addCatToOwner(@RequestParam int catId, @RequestParam int ownerId) {
        ownerService.addCatToOwner(catId, ownerId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getById")
    public ResponseEntity<OwnerWrapper> getOwner(@RequestParam int ownerId) {
        return ResponseEntity.ok().body(ownerService.getOwnerById(ownerId).getWrapper());
    }

    @GetMapping("/all")
    public ResponseEntity<List<OwnerWrapper>> getOwners() {
        return new ResponseEntity<>(
                ownerService.getOwners().stream().map((Owner::getWrapper)).
                        collect(Collectors.toList()), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<OwnerWrapper> deleteOwner(@RequestParam int ownerId) {
        Owner owner = ownerService.getOwnerById(ownerId);
        return ResponseEntity.ok().body(ownerService.deleteOwner(owner).getWrapper());
    }

}

