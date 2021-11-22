package group.moveon.logic;

import group.moveon.dao.EquationResultDao;
import group.moveon.dao.UserDao;
import group.moveon.equations.LinearEquation;
import group.moveon.equations.SquareEquation;
import group.moveon.models.EquationResult;
import group.moveon.models.User;
import logic.EquationSolver;

import java.rmi.RemoteException;

public class EquationSolvingServer implements EquationSolver {

    private final EquationResultDao equationResultDao = new EquationResultDao();
    private final UserDao userDao = new UserDao();
    
    @Override
    public String solveEquation(int equationType, int[] params, String username) throws RemoteException {
        User user = userDao.getUserByName(username);
        if (equationType == 1) {

            LinearEquation le = new LinearEquation(params[0], params[1]);
            le.setResult(-le.getB()/le.getK());
            int[] res = new int[1];
            res[0] = le.getResult();

            String equation = params[0] + "*x+(" + params[1] + ")=0";
            String solution = "x=" + res[0];
            equationResultDao.addEquationResult(new EquationResult(0, equation, solution, user));

            return solution;
        } else if (equationType == 2) {
            SquareEquation se = new SquareEquation(params[0], params[1], params[2]);
            se.setResult(solveSquare(se));
            int[] res = new int[2];
            res[0] = se.getResult()[0];
            res[1] = se.getResult()[1];

            String equation = params[0] + "*x^2+(" + params[1] + "*x)+(" + params[2] + ")=0";
            String solution = "x=" + res[0] + "," + res[1];
            equationResultDao.addEquationResult(new EquationResult(0, equation, solution, user));

            return solution;
        } else {
            return "";
        }
    }


    private int[] solveSquare(SquareEquation square) {
        int discriminant = (int) (Math.pow(square.getB(), 2) - 4 * square.getA() * square.getC());
        int[] squareRes = new int[2];
        squareRes[0] = (int) ((- square.getB() - Math.sqrt(discriminant)) / (2 * square.getA()));
        squareRes[1] = (int) ((- square.getB() + Math.sqrt(discriminant)) / (2 * square.getA()));
        return squareRes;
    }
}
