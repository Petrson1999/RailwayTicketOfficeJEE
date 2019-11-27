package com.railvayticketiffice.services;

import com.railvayticketiffice.dao.jdbcdao.interfaces.CrudGenericDao;
import com.railvayticketiffice.factory.DaoFactory;
import com.railvayticketiffice.entity.User;
import com.railvayticketiffice.enums.DaoType;
import com.railvayticketiffice.exeptions.PersistException;
import com.railvayticketiffice.web.form.RegistrationForm;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserService {
    private static final Logger LOG = Logger.getLogger(UserService.class);

    private CrudGenericDao<User, Integer> userDao;

    public UserService() {
        this.userDao = DaoFactory.getEntityDao(DaoType.USER);
    }

    public User validateUser(String login, String password) {

        List<User> users = getAll();
        if (users == null) {
            return null;
        }
        User user = null;
        Optional<User> optionalUser = users.stream().filter(u -> u.getLogin().equals(login) && u.getPassword().equals(password)).findFirst();
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        return user;
    }

    public boolean isExist(String login) {
        List<User> users = getAll();

        if (users != null) {
            return users.stream()
                    .anyMatch(u -> u.getLogin().equals(login));
        } else return false;
    }

    private List<User> getAll() {
        List<User> users = null;
        try {
            users = userDao.getAll();
        } catch (PersistException e) {
            LOG.error(e);
        }
        return users;
    }

    public User registerUser(RegistrationForm registrationForm) {
        User user = new User();
        user.setLogin(registrationForm.getLogin());
        user.setName(registrationForm.getFirstName());
        user.setSurname(registrationForm.getLastName());
        user.setPassword(registrationForm.getPassword());
        user.setRole(registrationForm.getRole());
        try {
            userDao.persist(user);
        } catch (PersistException e) {
            LOG.error(e);
        }
        Optional<User> optionalUser = getAll().stream().filter(x -> x.getLogin().equals(registrationForm.getLogin())).findFirst();
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            return user;
        } else return null;
    }

}
