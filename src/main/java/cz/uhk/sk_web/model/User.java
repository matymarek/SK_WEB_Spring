package cz.uhk.sk_web.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "login", nullable = false, unique = true, length = 255)
    private String login;

    @Column(name = "pass", nullable = false, length = 255)
    private String password;

    @Column(name = "admin", nullable = false)
    private boolean admin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments= new ArrayList<>();

    public User(String login, String password, boolean admin) {
        this.login = login;
        this.password = password;
        this.admin = admin;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + "hidden" + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin(){
        return admin;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
