package ru.itmo.kotiki.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private final String name;
    @Column(name = "birthday")
    private final Date birthday;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Cat> cats;

    public Owner(){
        this.name = null;
        this.birthday = null;
        this.cats = null;
    }

    public Owner(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
        this.cats = new ArrayList<>();
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

    public List<Cat> getCats() {
        return cats;
    }

    public void addCat(Cat cat){
        cat.setOwner(this);
        cats.add(cat);
    }

    public void deleteCat(Cat cat){
        cats.remove(cat);
    }
}
