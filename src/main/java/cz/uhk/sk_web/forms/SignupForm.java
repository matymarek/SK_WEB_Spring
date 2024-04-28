package cz.uhk.sk_web.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SignupForm {
    @NotEmpty
    @Size(min = 5, max = 30)
    private String login = "";

    @NotEmpty
    @Size(min = 7, max = 30)
    private String password = "";

    @NotEmpty
    @Size(min = 7, max = 30)
    private String passwordCheck = "";


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

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }
}
