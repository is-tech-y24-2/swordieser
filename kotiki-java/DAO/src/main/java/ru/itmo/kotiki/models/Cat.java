package ru.itmo.kotiki.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private final String name;
    @Column(name = "birthday")
    private final Date birthday;
    @Column(name = "breed")
    @Enumerated(EnumType.STRING)
    private final CatBreed breed;
    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private final CatColor color;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;
    @OneToMany(targetEntity = Cat.class)
    private final List<Cat> friends;

    public Cat() {
        this.name = null;
        this.birthday = null;
        this.breed = null;
        this.color = null;
        this.friends = null;
    }

    public Cat(String name, Date birthday, CatBreed breed, CatColor color) {
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.color = color;
        this.friends = new ArrayList<>();
    }


    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public CatBreed getBreed() {
        return breed;
    }

    public CatColor getColor() {
        return color;
    }

    public Owner getOwner() {
        return owner;
    }

    public List<Cat> getFriends() {
        return friends;
    }

    public void addFriend(Cat cat){
        friends.add(cat);
    }

    public void deleteFriend(Cat cat){
        friends.remove(cat);
    }

    public void setOwner(Owner owner){
        this.owner = owner;
    }
}
