package app.entity.db;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;


@NoArgsConstructor
@Data
@Entity
public class XUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String roles;
    @Transient
    private final String DELIMETER = ":";

    public XUser(String username, String password,String... roles) {
        this.username = username;
        this.password = password;
        setRoles(roles);
    }

    public String[] getRoles() {
        return roles == null || roles.isEmpty() ? new String[]{}
                : roles.split(DELIMETER);
    }

    public void setRoles(String[] roles) {
        this.roles = String.join(DELIMETER, roles);
    }
}
