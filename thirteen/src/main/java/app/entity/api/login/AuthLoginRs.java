package app.entity.api.login;

import app.entity.api.register.AuthRegRs;
import lombok.Data;

@Data
public class AuthLoginRs {


    private int code;
    private String token;


    public AuthLoginRs(int code, String token) {
        this.code = code;
        this.token = token;
    }

    public static AuthRegRs Ok() {
        return new AuthRegRs(0, "successful");
    }
}
