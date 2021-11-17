package group.moveon.logic;

import group.moveon.dao.UserDao;
import group.moveon.models.User;
import logic.Auth;

import java.rmi.RemoteException;

public class AuthServer implements Auth {
    private UserDao userDao = new UserDao();
    @Override
    public String register(String username) throws RemoteException {
        userDao.addUser(new User(username));
        return "";
    }
}
