package ru.itmo.kotikiservices.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class OwnerWrapper {
    private int id;
    private String name;
    private LocalDate birthday;
    private List<Integer> cats;
}
