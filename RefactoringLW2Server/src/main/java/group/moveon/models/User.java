package group.moveon.models;

import javax.persistence.*;

@Entity
@Table(name="AppUser")
public class User {
    @Id
    private String login;

    public User(String login) {
        this.login = login;
    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
