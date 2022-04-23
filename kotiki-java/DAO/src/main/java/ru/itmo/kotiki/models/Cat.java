package ru.itmo.kotiki.models;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "Cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Birthday")
    private Date birthday;
    @Column(name = "Breed")
    @Enumerated(EnumType.STRING)
    private CatBreed breed;
    @Column(name = "Color")
    @Enumerated(EnumType.STRING)
    private CatColor color;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Owner_id")
    private Owner owner;

    public Cat() {
    }

    public Cat(String name, Date birthday, CatBreed breed, CatColor color) {
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.color = color;
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

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
