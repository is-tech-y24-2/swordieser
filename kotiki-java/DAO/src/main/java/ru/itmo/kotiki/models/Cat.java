package ru.itmo.kotiki.models;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "Cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "breed")
    @Enumerated(EnumType.STRING)
    private CatBreed breed;
    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private CatColor color;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Cat() {
    }

    public Cat(String name, Date birthday, CatBreed breed, CatColor color) {
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.color = color;
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

    public void setOwner(Owner owner){
        this.owner = owner;
    }

    public void setName(String name){
        this.name = name;
    }
}
