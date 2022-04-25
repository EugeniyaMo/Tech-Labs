package ru.itmo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.models.Friendship;
import ru.itmo.serviceImpl.FriendshipServiceImpl;
import ru.itmo.wrapper.FriendshipWrapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/friendship")
@RequiredArgsConstructor
public class FriendshipController {
    private final FriendshipServiceImpl friendshipService;

    @PostMapping("/create")
    public ResponseEntity<FriendshipWrapper> createFriendship(@RequestParam int idFirstCat, @RequestParam int idSecondCat) {
        return ResponseEntity.ok().body(friendshipService.createFriendship(idFirstCat, idSecondCat).getWrapper());
    }

    @GetMapping("/getById")
    public ResponseEntity<FriendshipWrapper> getFriendship(@RequestParam int friendshipId) {
        return ResponseEntity.ok().body(friendshipService.getFriendshipById(friendshipId).getWrapper());
    }

    @GetMapping("/all")
    public ResponseEntity<List<FriendshipWrapper>> getFriendships() {
        return new ResponseEntity<>(
                friendshipService.getFriendships().stream().map((Friendship::getWrapper)).
                        collect(Collectors.toList()), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<FriendshipWrapper> deleteFriendship(@RequestParam int friendshipId) {
        Friendship friendship = friendshipService.getFriendshipById(friendshipId);
        return ResponseEntity.ok().body(friendshipService.deleteFriendship(friendship).getWrapper());
    }
}
