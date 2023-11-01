package kz.bitlab.g118.G118springfirstapp.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class City {
    private Long id;
    private String name;
    private String shortName;
}
