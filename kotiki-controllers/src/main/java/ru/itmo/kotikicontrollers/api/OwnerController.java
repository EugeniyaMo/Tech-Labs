package ru.itmo.kotikicontrollers.api;

import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/owner")
@RequiredArgsConstructor
public class OwnerController {
    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    @PostMapping("/create")
    public ResponseEntity<String> createOwner(@RequestParam String name, @RequestParam String birthday) {
        Map<String, String> data = new HashMap<String, String>() {{
            put("name", name);
            put("birthday", birthday);
        }};
        var message = new GsonBuilder().create().toJson(data, HashMap.class);
        kafkaTemplate.send("owner.create", message);
        return ResponseEntity.ok().body("Owner is created");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteOwner(@RequestParam int ownerId) {
        Map<String, String> data = new HashMap<String, String>() {{
            put("ownerId", String.valueOf(ownerId));
        }};
        var message = new GsonBuilder().create().toJson(data, HashMap.class);
        kafkaTemplate.send("owner.delete", message);
        return ResponseEntity.ok().body("Owner is deleted");
    }
}

