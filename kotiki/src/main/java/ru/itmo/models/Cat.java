package ru.itmo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.wrapper.CatWrapper;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "color_id")
    private int colorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "friends", joinColumns = @JoinColumn(name = "id_first_cat"), inverseJoinColumns = @JoinColumn(name = "id_second_cat"))
    private List<Cat> friends;

    public Cat(String name, LocalDate birthday, String breed, int colorId, Owner owner) {
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.colorId = colorId;
        this.owner = owner;
        this.friends = new ArrayList<>();
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

    public String getBreed() {
        return breed;
    }

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

    public int getOwnerId() {
        return owner.getId();
    }

    public List<Integer> getFriendsId() {
        List<Integer> friendsId = new ArrayList<>();
        for (Cat friend: friends) {
            friendsId.add(friend.id);
        }
        return friendsId;
    }

    public CatWrapper getWrapper() {
        return new CatWrapper(id, name, birthday, breed, colorId, this.getOwnerId(), this.getFriendsId());
    }
}
