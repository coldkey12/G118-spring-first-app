package kz.bitlab.g118.G118springfirstapp.db;

import kz.bitlab.g118.G118springfirstapp.model.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DbManager {

    private static Long id = 4L;

    public static void addUser(User user) {
        user.setId(id);
        users.add(user);
        id++;
    }

    @Getter
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1L,"marat@gmail.com","qwe","Marat"));
        users.add(new User(2L,"saniya@gmail.com","qwe","Saniya"));
        users.add(new User(3L,"vlad@gmail.com","qwe","Vlad"));
    }

    public static User getUserById(Long id) {
        return users.stream().filter(user -> Objects.equals(user.getId(),id)).findFirst().orElse(null);
    }
}
