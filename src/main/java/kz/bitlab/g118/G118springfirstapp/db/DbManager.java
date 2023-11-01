package kz.bitlab.g118.G118springfirstapp.db;

import kz.bitlab.g118.G118springfirstapp.model.City;
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

    @Getter
    private static List<City> cities = new ArrayList<>();

    static {
        cities.add(new City(1L, "Almaty", "ALM"));
        cities.add(new City(2L, "Astana", "AST"));
        cities.add(new City(3L, "Karaganda", "KRG"));
        cities.add(new City(4L, "New-York", "NYC"));
        cities.add(new City(5L, "Taraz", "TRZ"));
        users.add(new User(1L, "marat@gmail.com", "qwe", "Марат", "Java", getCityById(1L)));
        users.add(new User(2L, "saniya@gmail.com", "qwe", "Сания", "C#", getCityById(2L)));
        users.add(new User(3L, "vlad@gmail.com", "qwe", "Влад", "GoLang", getCityById(3L)));
        users.add(new User(4L, "assanali@gmail.com", "qwe", "Асанали", "Java", getCityById(4L)));
        users.add(new User(5L, "alizhan@gmail.com", "qwe", "Алижан", null, null));
        users.add(new User(6L, "elnur@gmail.com", "qwe", "Ельнур", null, null));

    }

    public static City getCityById(Long id) {
        return cities.stream()
                .filter(city -> city.getId().equals(id))
                .findFirst()
                .orElse(null);
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

    public static void editUser(Long id, String email, String fullName, Long cityId) {
        User user = getUserById(id);
        if (user == null) {
            return;
        }
        user.setCity(getCityById(cityId));
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
