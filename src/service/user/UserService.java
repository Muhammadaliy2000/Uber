package service.user;

import models.user.User;
import service.BaseInterface;
import models.responce.Responce;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService implements BaseInterface<User>, UserInterface {
    private static List<User> userList = new ArrayList<>();

    public UserService() {
        userList.add(new User("root", "root", "root", "", 0, 0, true));
    }

    @Override
    public Responce add(User user) {
        boolean ans = true;
        ans = ans && user.getUsername() != null;
        ans = ans && user.getPassword() != null;
        ans = ans && user.getName() != null;
        ans = ans && user.getPhoneNumber() != null;
        if(ans) {
            userList.add(user);
            return new Responce(true, user);
        } else {
            return new Responce(false, user);
        }
    }

    @Override
    public Responce delete(UUID userId) {
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getId() == userId) {
                userList.get(i).setActive(false);
                return new Responce(true, userId);
            }
        }
        return new Responce(false, userId);
    }

    @Override
    public List<User> getList() {
        return userList;
    }

    @Override
    public User getOne(UUID id) {
        for (User user : userList) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean edit(UUID userId) {
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getId() == userId && !userList.get(i).isActive()) {
                userList.get(i).setActive(true);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkUser(String userName) {
        for (User user : userList) {
            if(user.getUsername().equals(userName)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Responce login(String userName, String password) {
        for (User user : userList) {
            if(user.getUsername().equals(userName) && user.getPassword().equals(password)) {
                return new Responce(true, user);
            }
        }
        return new Responce(false, null);
    }


}
