package service.user;

import models.driver.Driver;
import models.responce.Responce;

public interface UserInterface {
    boolean checkUser(String userName);
    Responce login(String userName, String password);
}
