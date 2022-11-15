package ru.itmo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.wrapper.CatWrapper;
import ru.itmo.wrapper.OwnerWrapper;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public void addCat(Cat cat) {
        cat.setOwner(this);
        cats.add(cat);
    }

    public Owner(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
        this.cats = new ArrayList<>() {
        };
    }

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

    public List<Cat> getCats() {
        return cats;
    }

    public List<Integer> getCatsId() {
        List<Integer> catsId = new ArrayList<>();
        for (Cat cat: cats) {
            catsId.add(cat.getId());
        }
        return catsId;
    }

    public OwnerWrapper getWrapper() {
        return new OwnerWrapper(id, name, birthday, this.getCatsId());
    }
}
