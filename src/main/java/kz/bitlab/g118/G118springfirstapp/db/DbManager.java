package kz.bitlab.g118.G118springfirstapp.db;

import kz.bitlab.g118.G118springfirstapp.model.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class DbManager {

    private static Long id = 4L;

    public static void addUser(User user) {
        log.info("add user method is started");
        user.setId(id);
        users.add(user);
        id++;
        log.info("user added successfully");
        log.info("user info: {}", user);
    }

    @Getter
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1L, "marat@gmail.com", "qwe", "Марат"));
        users.add(new User(2L, "saniya@gmail.com", "qwe", "Сания"));
        users.add(new User(3L, "vlad@gmail.com", "qwe", "Влад"));
    }

    public static User getUserById(Long id) {
        return users.stream().filter(user -> Objects.equals(user.getId(), id)).findFirst().orElse(null);
    }

//    public static void updateUser(User user) {
//        for (User user1 : users){
//            if(user1.getId() == user.getId()){
//                user1.setEmail(user.getEmail());
//                user1.setFullName(user.getFullName());
//                user1.setPassword(user.getPassword());
//            }
//        }
//    }

    public static void deleteUserById(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    public static void editUser(Long id, String email, String fullName) {
        User user = getUserById(id);
        if (user == null) {
            return;
        }
        user.setEmail(email);
        user.setFullName(fullName);
    }

    public static List<User> findUsers(String search) {
        return users.stream().filter(user -> user.getEmail().toLowerCase().contains(search.toLowerCase())
                        || user.getFullName().toLowerCase().contains(search.toLowerCase()))
                .sorted(Comparator.comparing(User::getFullName).
                        thenComparing(User::getId).reversed())
                .collect(Collectors.toList());
    }

    public static String findUsersAlt(String search) {
        List<User> filteredUsers = users.stream()
                .filter(user -> user.getEmail().toLowerCase().contains(search.toLowerCase())
                        || user.getFullName().toLowerCase().contains(search.toLowerCase()))
                .sorted(Comparator.comparing(User::getFullName)
                        .thenComparing(User::getId).reversed())
                .collect(Collectors.toList());

        String fullNames = filteredUsers.stream()
                .map(User::getFullName)
                .collect(Collectors.joining(" - "));

        return fullNames;
    }
}
