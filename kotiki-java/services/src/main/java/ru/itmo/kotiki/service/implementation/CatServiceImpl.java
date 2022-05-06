package ru.itmo.kotiki.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dao.CatDao;
import ru.itmo.kotiki.dao.UserDao;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Role;
import ru.itmo.kotiki.models.User;
import ru.itmo.kotiki.service.interfaces.CatService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CatServiceImpl implements CatService {
    private final CatDao catDao;
    private final UserDao userDao;

    @Autowired
    public CatServiceImpl(CatDao catDao, UserDao userDao) {
        this.catDao = catDao;
        this.userDao = userDao;
    }

    public Cat findCat(int id) {
        Cat cat = catDao.getById(id);
        User owner_user = userDao.getById(cat.getOwner().getId());
        var user = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.equals(userDao.findUserByUsername(user.getName()).getRole(), Role.ROLE_ADMIN) ||
                Objects.equals(owner_user.getUsername(), user.getName())) {
            return cat;
        }

        return null;
    }

    public Cat saveCat(Cat cat) {
        var user = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.equals(userDao.findUserByUsername(user.getName()).getRole(), Role.ROLE_ADMIN)) {
            return catDao.save(cat);
        }

        return null;
    }

    public void deleteCat(Cat cat) {
        var user = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.equals(userDao.findUserByUsername(user.getName()).getRole(), Role.ROLE_ADMIN)) {
            catDao.delete(cat);
        }
    }

    public List<Cat> findAllCats() {
        List<Cat> cats = new ArrayList<>();
        for (Cat cat : catDao.findAll()) {
            User owner_user = userDao.getById(cat.getOwner().getUserId());
            var user = SecurityContextHolder.getContext().getAuthentication();

            if (Objects.equals(userDao.findUserByUsername(user.getName()).getRole(), Role.ROLE_ADMIN) ||
                    Objects.equals(owner_user.getUsername(), user.getName())) {
                cats.add(cat);
            }
        }

        return cats;
    }
}

