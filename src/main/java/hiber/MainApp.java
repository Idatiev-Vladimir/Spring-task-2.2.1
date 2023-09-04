package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Интернет герой", "-_-", "xexe@mail.ru"));
      userService.add(new User("Vladimir", "Idatiev", "veitadi@gmail.com"));
      userService.add(new User("Rich", "Human", "chelNaCamry@list.blog", new Car("Toyota Camry", 2020)));
      userService.add(new User("R2", "D2", "cosmos@@@cosmos", new Car("Lada Priora", 2018)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+ user.getId());
         System.out.println("First Name = " +user.getFirstName());
         System.out.println("Last Name = " +user.getLastName());
         System.out.println("Email = " +user.getEmail());
         System.out.println("Car = " + user.getUserCar());
         System.out.println("---------");
      }

      User carOwner = userService.carOwners("Toyota Camry", 2020);
      System.out.println("Пользователь с данным авто - " + carOwner);

      context.close();
   }
}
