package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();



        userService.createUsersTable();

        userService.saveUser("Andrei", "Vasiliev", (byte) 20);
        userService.saveUser("Oleg", "Abramov", (byte) 25);
        userService.saveUser("Dinara", "Safina", (byte) 31);
        userService.saveUser("Vlad", "Mukhin", (byte) 38);

        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();




    }
}
