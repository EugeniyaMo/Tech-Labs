package ru.itmo.kotikicontrollers.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cat")
@RequiredArgsConstructor
public class CatController {
    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    @PostMapping("/create")
    public ResponseEntity<String> createCat(@RequestParam String name, @RequestParam String birthday,
                                    @RequestParam String breed, @RequestParam int colorId,
                                    @RequestParam int ownerId) {
        Map<String, String> data = new HashMap<String, String>() {{
            put("name", name);
            put("birthday", birthday);
            put("breed", breed);
            put("colorId", String.valueOf(colorId));
            put("ownerId", String.valueOf(ownerId));
        }};
        var message = new GsonBuilder().create().toJson(data, HashMap.class);
        kafkaTemplate.send("cat.create", message);
        return ResponseEntity.ok().body("Cat is created");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCat(@RequestParam int catId) {
        Map<String, String> data = new HashMap<String, String>() {{
            put("catId", String.valueOf(catId));
        }};
        var message = new GsonBuilder().create().toJson(data, HashMap.class);
        kafkaTemplate.send("cat.delete", message);
        return ResponseEntity.ok().body("Cat is deleted");
    }
}