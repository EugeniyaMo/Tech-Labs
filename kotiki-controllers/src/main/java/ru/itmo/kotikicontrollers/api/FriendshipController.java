package ru.itmo.kotikicontrollers.api;

import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/friendship")
@RequiredArgsConstructor
public class FriendshipController {
    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    @PostMapping("/create")
    public ResponseEntity<String> createFriendship(@RequestParam int idFirstCat, @RequestParam int idSecondCat) {
        Map<String, String> data = new HashMap<String, String>() {{
            put("idFirstCat", String.valueOf(idFirstCat));
            put("idSecondCat", String.valueOf(idSecondCat));
        }};
        var message = new GsonBuilder().create().toJson(data, HashMap.class);
        kafkaTemplate.send("friendship.create", message);
        return ResponseEntity.ok().body("Friendship is created");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFriendship(@RequestParam int friendshipId) {
        Map<String, String> data = new HashMap<String, String>() {{
            put("friendshipId", String.valueOf(friendshipId));
        }};
        var message = new GsonBuilder().create().toJson(data, HashMap.class);
        kafkaTemplate.send("friendship.delete", message);
        return ResponseEntity.ok().body("Friendship is deleted");
    }
}
