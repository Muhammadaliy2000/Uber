package models;

public class User extends BaseModel implements Comparable<User>{
    public User() {
    }

    public User(String name, String username, String password, String phoneNumber, Boolean isActive, Type type, double locX, double locY) {
        super(name, username, password, phoneNumber, isActive, type, locX, locY);
    }

    @Override
    public int compareTo(User o) {
        return super.getPassword().compareTo(o.getPassword());
    }
}
