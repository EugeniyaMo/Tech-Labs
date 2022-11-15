package ru.itmo.kotikiservices.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class CatWrapper {
    private int id;
    private String name;
    private LocalDate birthday;
    private String breed;
    private int colorId;
    private int ownerId;
    private List<Integer> friendsId;
}
