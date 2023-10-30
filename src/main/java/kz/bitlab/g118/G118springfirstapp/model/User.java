package kz.bitlab.g118.G118springfirstapp.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private Long id;
    private String email;
    private String password;
    private String fullName;
}
