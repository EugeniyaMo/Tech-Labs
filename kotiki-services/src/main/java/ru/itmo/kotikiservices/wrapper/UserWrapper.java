package ru.itmo.kotikiservices.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserWrapper {
    private int id;
    private String username;
    private String password;
    private int roleId;
    private int ownerId;
}
