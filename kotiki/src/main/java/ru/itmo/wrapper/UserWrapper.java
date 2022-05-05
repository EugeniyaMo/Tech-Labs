package ru.itmo.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.itmo.models.Owner;
import ru.itmo.models.Role;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserWrapper {
    private int id;
    private String username;
    private String password;
    private int roleId;
    private int ownerId;
}
