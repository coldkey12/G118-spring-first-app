package kz.bitlab.g118.G118springfirstapp.db;

import kz.bitlab.g118.G118springfirstapp.model.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
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

    public static void updateUser(User user) {
        for (User user1 : users){
            if(user1.getId() == user.getId()){
                user1.setEmail(user.getEmail());
                user1.setFullName(user.getFullName());
                user1.setPassword(user.getPassword());
            }
        }
    }

    public static void deleteUserById(Long id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
            }
        }
    }
}
