package com.railvayticketiffice.services;

import com.railvayticketiffice.factory.DaoFactory;
import com.railvayticketiffice.entity.User;
import com.railvayticketiffice.enums.DaoType;
import com.railvayticketiffice.exeptions.PersistException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class LoginService {
    private static final Logger LOG = Logger.getLogger(LoginService.class);

    public User validateUser(String login, String password) {

        List<User> users = null;
        try {
            users = DaoFactory.getEntityDao(DaoType.USER).getAll();
        } catch (PersistException e) {
            LOG.error(e);
        }
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

}
