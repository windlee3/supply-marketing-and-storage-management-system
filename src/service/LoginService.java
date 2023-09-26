package service;

import dao.UserDao;
import model.User;

public class LoginService {

        public static UserDao userDao;

        static {
            userDao = new UserDao();
        }
        public static boolean login(String username, String password) {
            User user = userDao.getUserByName(username);
            if(user != null && password.equals(user.getPassword())) {
                return true;
            }
            return false;
        }

        public static void register(User user) {
            userDao.addUser(user);
        }
    }