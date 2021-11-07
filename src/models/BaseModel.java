package models;

public abstract class BaseModel {

    private String name;
    private String username;
    private String password;
    private Boolean isActive;
    private Type type;

    public BaseModel(String name, String username, String password, Boolean isActive, Type type) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public BaseModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
