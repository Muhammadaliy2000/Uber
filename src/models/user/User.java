package models.user;

import models.BaseModel;
import models.visual.GameScreen;

import java.util.UUID;

public class User extends BaseModel implements Comparable<User>{
    private boolean isAdmin;

    public User() {
        super.id = UUID.randomUUID();
        super.isActive = true;
        this.isAdmin = false;
    }

    public User(String name, String username, String password, String phoneNumber, int locX, int locY, boolean isAdmin) {
        super(name, username, password, phoneNumber, locX, locY, 'C');
        this.isAdmin = isAdmin;
    }

    public void addLocation(GameScreen screen, User user){
//        int x = (int) (Math.random() * (screen.getScreenWidth() - 1));
//        int y = (int) (Math.random() * (screen.getScreenHeight() - 1));
        screen.setObjectOnLocation(user, user.getLocX(), user.getLocY());
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public int compareTo(User o) {
        return super.getPassword().compareTo(o.getPassword());
    }

    @Override
    public String toString() {
        return  " | name='" + name + '\'' +
                " | username='" + username + '\'' +
                " | password='" + password + '\'' +
                " | phoneNumber='" + phoneNumber + '\'' +
                " | isActive=" + isActive +
                " | locX=" + locX +
                " | locY=" + locY +
                " | isAdmin=" + isAdmin;
    }
}
