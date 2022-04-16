package ru.itmo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.wrapper.CatWrapper;
import ru.itmo.wrapper.FriendshipWrapper;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity @Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "friends")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_first_cat")
    private int idFirstCat;

    @Column(name = "id_second_cat")
    private int idSecondCat;

    public Friendship(int idFirstCat, int idSecondCat) {
        this.idFirstCat = idFirstCat;
        this.idSecondCat = idSecondCat;
    }
//@ManyToMany(mappedBy = "friends")
    //private List<Cat> cats;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FriendshipWrapper getWrapper() {
        return new FriendshipWrapper(id, idFirstCat, idSecondCat);
    }

}
