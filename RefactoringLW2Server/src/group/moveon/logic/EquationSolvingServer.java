package group.moveon.logic;

import group.moveon.equations.LinearEquation;
import group.moveon.equations.SquareEquation;

import java.rmi.RemoteException;

public class EquationSolvingServer implements EquationSolver {

    @Override
    public int[] solveEquation(int equationType, int[] params) throws RemoteException {
        if (equationType == 1) {
            LinearEquation le = new LinearEquation(params[0], params[1]);
            le.setResult(-le.getB()/le.getK());
            int[] res = new int[1];
            res[0] = le.getResult();
            return res;
        } else if (equationType == 2) {
            SquareEquation se = new SquareEquation(params[0], params[1], params[2]);
            se.setResult(solveSquare(se));
            int[] res = new int[2];
            res[0] = se.getResult()[0];
            res[1] = se.getResult()[1];
            return res;
        } else {
            return new int[3];
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
