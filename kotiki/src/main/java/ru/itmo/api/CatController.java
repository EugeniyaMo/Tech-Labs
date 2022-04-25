package ru.itmo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.models.Cat;
import ru.itmo.models.Owner;
import ru.itmo.serviceImpl.CatServiceImpl;
import ru.itmo.serviceImpl.OwnerServiceImpl;
import ru.itmo.wrapper.CatWrapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cat")
@RequiredArgsConstructor
public class CatController {
    private final CatServiceImpl catService;
    private final OwnerServiceImpl ownerService;

    @PostMapping("/create")
    public ResponseEntity<CatWrapper> createCat(@RequestParam String name, @RequestParam String birthday,
                                                  @RequestParam String breed, @RequestParam int colorId,
                                                @RequestParam int ownerId) {
        Owner owner = ownerService.getOwnerById(ownerId);
        return ResponseEntity.ok().body(catService.createCat(name, birthday, breed, colorId, owner).getWrapper());
    }

    @GetMapping("/getById")
    public ResponseEntity<CatWrapper> getCat(@RequestParam int catId) {
        return ResponseEntity.ok().body(catService.getCatById(catId).getWrapper());
    }

    @GetMapping("/all")
    public ResponseEntity<List<CatWrapper>> getCats() {
        return new ResponseEntity<>(
                catService.getCats().stream().map((Cat::getWrapper)).
                        collect(Collectors.toList()), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CatWrapper> deleteCat(@RequestParam int catId) {
        Cat cat = catService.getCatById(catId);
        return ResponseEntity.ok().body(catService.deleteCat(cat).getWrapper());
    }
}