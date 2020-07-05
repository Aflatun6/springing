package practice.app.entity;

import lombok.Data;

@Data
public class CurrentDetails {

    public static final String ATTR = "cd";

    private String seat;
    private String name;
    private String surname;
    private String cardno;


}
