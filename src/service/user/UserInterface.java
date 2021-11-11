package service.user;

import models.driver.Driver;

public interface UserInterface {
    boolean checkUser(String userName);
    boolean login(String userName, String password);
}
