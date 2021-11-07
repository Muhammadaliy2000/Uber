package models;

public class User extends BaseModel implements Comparable<User>{

    private String phoneNumber;

    public User(String name, String username, String password, Boolean isActive, Type type, String phoneNumber) {
        super(name, username, password, isActive, type);
        this.phoneNumber = phoneNumber;
    }

    public User() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int compareTo(User o) {
        return super.getPassword().compareTo(o.getPassword());
    }
}
