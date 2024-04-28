package cz.uhk.sk_web.forms;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ChangePassForm {
    @NotNull
    @Size(min=1, max = 2048)
    private String passwordOld;
    @NotNull
    @Size(min=1, max = 2048)
    private String password;

    @NotNull
    @Size(min=1, max = 2048)
    private String passwordRep;

    public String getPasswordOld() {
        return passwordOld;
    }

    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRep() {
        return passwordRep;
    }

    public void setPasswordRep(String passwordRep) {
        this.passwordRep = passwordRep;
    }
}
