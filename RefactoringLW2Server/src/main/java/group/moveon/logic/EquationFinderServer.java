package group.moveon.logic;

import group.moveon.dao.EquationResultDao;
import group.moveon.dao.UserDao;
import group.moveon.models.EquationResult;
import logic.EquationFinder;

import java.rmi.RemoteException;
import java.util.List;
import java.util.ListIterator;


public class EquationFinderServer implements EquationFinder {

    private final EquationResultDao equationResultDao = new EquationResultDao();
    private final UserDao userDao = new UserDao();

    @Override
    public String findEquation(int eqNumb, String username) throws RemoteException {

        EquationResult result = equationResultDao.getEquationResultById(eqNumb);
        if (result == null) {
            return "No equation with id = " + eqNumb;
        }
        if (result.getUser().getLogin().equals(username)) {
            return result.toString();
        } else {
            return "No access to solution with id = " + eqNumb;
        }
    }

    @Override
    public String getAvailableEquationIds(String username) throws RemoteException {
        List result = equationResultDao.getEquationResultByUserName(userDao.getUserByName(username));
        if (result.isEmpty()) {
            return "You haven't added any equations yet";
        } else {
            String ids = "";
            ListIterator<Integer> listIterator = result.listIterator();
            while (listIterator.hasNext()) {
                Integer element = listIterator.next();
                ids += element + " ";
            }
            return "You can get solutions to equations with ids = " + ids;
        }
    }

}
