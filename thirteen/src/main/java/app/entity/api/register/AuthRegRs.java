package app.entity.api.register;

import lombok.Data;

@Data
public class AuthRegRs {


    private int code;
    private String message;


    public AuthRegRs(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
