package models.user;

import models.BaseModel;
import models.visual.GameScreen;

import java.util.UUID;

public class User extends BaseModel implements Comparable<User>{
    public User() {
        super.id = UUID.randomUUID();
    }

    public User(String name, String username, String password, String phoneNumber, int locX, int locY) {
        super(name, username, password, phoneNumber, locX, locY, 'C');
    }

    public void addLocation(GameScreen screen, User user){
//        int x = (int) (Math.random() * (screen.getScreenWidth() - 1));
//        int y = (int) (Math.random() * (screen.getScreenHeight() - 1));
        screen.setObjectOnLocation(user, user.getLocX(), user.getLocY());
    }

    @Override
    public int compareTo(User o) {
        return super.getPassword().compareTo(o.getPassword());
    }
}
