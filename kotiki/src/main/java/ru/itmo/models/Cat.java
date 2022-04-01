package ru.itmo.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "breed")
    private String breed;

    @Column (name = "color_id")
    private int colorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "id_first_cat"),
            inverseJoinColumns = @JoinColumn(name = "id_second_cat"))
    private List<Cat> friends;

    public Cat() {
    }

    public Cat(String name, LocalDate birthday, Color color, String breed) {
        this.name = name;
        this.birthday = birthday;
        this.colorId = color.ordinal();
        this.breed = breed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getBreed() { return breed; }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

//    public Color getColor() {
//        return new Color()
//    }
    /*
    @Override
    public String toString() {
        return color + " " + model;
    }*/
}
