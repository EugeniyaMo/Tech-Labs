package ru.itmo.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private LocalDate birthday;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cat> cats;

    public Owner() {
    }

    public Owner(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
        this.cats = new ArrayList<>() {
        };
    }

    public void addCat(Cat cat) {
        cat.setOwner(this);
        cats.add(cat);
    }

   // public void removeAuto(Cat cat) {
//        cats.remove(cat);
//    }

    public int getId() {
        return id;
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

//    public List<Cat> getCats() {
//        return cats;
//    }
//
//    public void setAutos(List<Cat> autos) {
//        this.cats = cats;
//    }

    @Override
    public String toString() {
        return "models.User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
