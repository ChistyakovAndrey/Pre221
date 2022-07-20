package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = context.getBean(Car.class);
        car1.setModel("Tahoe");
        car1.setSeries(4);
        User user1 = context.getBean(User.class);
        user1.setCar(car1);
        user1.setFirstName("Andrey");
        user1.setLastName("Chistyakov");
        user1.setEmail("mail@ya.ru");

        Car car2 = context.getBean(Car.class);
        car2.setModel("RAV4");
        car2.setSeries(3);
        User user2 = context.getBean(User.class);
        user2.setCar(car2);
        user2.setFirstName("Sergio");
        user2.setLastName("LastName");
        user2.setEmail("pochta@ya.ru");

        Car car3 = context.getBean(Car.class);
        car3.setModel("Amarok");
        car3.setSeries(6);
        User user3 = context.getBean(User.class);
        user3.setCar(car3);
        user3.setFirstName("Kate");
        user3.setLastName("Bruster");
        user3.setEmail("pochta@ya.ru");


        userService.add(user1);
        userService.add(user2);
        userService.add(user3);


        List<User> users = userService.listUsers();
        for (User u : users) {
            System.out.println("Id = " + u.getId());
            System.out.println("First Name = " + u.getFirstName());
            System.out.println("Last Name = " + u.getLastName());
            System.out.println("Email = " + u.getEmail());
            System.out.println("Car = " + u.getCar());
            System.out.println();
        }

        String modelWanted = "Amarok";
        int seriesWanted = 6;

        List<User> usersByCarParams = userService.getUserByCarParams(modelWanted, seriesWanted);

        System.out.println("\nUsers who have " + modelWanted + " " + seriesWanted + " series");
        for (User user : usersByCarParams) {
            System.out.println(user);
        }


        context.close();
    }
}
