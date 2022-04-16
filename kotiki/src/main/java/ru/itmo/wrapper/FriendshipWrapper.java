package ru.itmo.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FriendshipWrapper {
    private int id;
    private int idFirstCat;
    private int idSecondCat;
}
