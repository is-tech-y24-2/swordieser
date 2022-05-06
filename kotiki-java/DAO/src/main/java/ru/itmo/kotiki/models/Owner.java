package ru.itmo.kotiki.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Birthday")
    private Date birthday;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cat> cats;
    private int userId;

    public Owner() {
    }

    public Owner(String name, Date birthday, int userId) {
        this.name = name;
        this.birthday = birthday;
        this.cats = new ArrayList<>();
        this.userId = userId;
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

    public List<Cat> getCats() {
        return cats;
    }

    public void addCat(Cat cat) {
        cat.setOwner(this);
        cats.add(cat);
    }

    public void deleteCat(Cat cat) {
        cats.remove(cat);
    }

    public int getUserId() {
        return userId;
    }
}
