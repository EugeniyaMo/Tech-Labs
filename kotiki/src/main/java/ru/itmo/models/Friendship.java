package ru.itmo.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "friends")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_first_cat")
    private int idFirstCat;

    @Column(name = "id_second_cat")
    private int idSecondCat;

    //@ManyToMany(mappedBy = "friends")
    //private List<Cat> cats;

    public Friendship() {
    }

    public Friendship(int idFirstCat, int idSecondCat) {
        this.idFirstCat = idFirstCat;
        this.idSecondCat = idSecondCat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
