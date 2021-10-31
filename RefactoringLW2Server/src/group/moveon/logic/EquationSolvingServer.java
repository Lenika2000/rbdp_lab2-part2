package group.moveon.logic;

import group.moveon.equations.LinearEquation;
import group.moveon.equations.SquareEquation;

import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;

public class EquationSolvingServer implements EquationSolver {

    final String fileAddress = "C:\\Users\\Mikhail\\IdeaProjects\\RefactoringLW2Server\\src\\eqRegistry.txt";

    @Override
    public int[] solveEquation(int equationType, int[] params) throws RemoteException {
        if (equationType == 1) {

            LinearEquation le = new LinearEquation(params[0], params[1]);
            le.setResult(-le.getB()/le.getK());
            int[] res = new int[1];
            res[0] = le.getResult();


            try (FileWriter writer = new FileWriter(fileAddress, false)) {
                String line = equationType + " " + params[0] + " " + params[1] + " " + res[0];
                writer.write(line);
                writer.append('\n');
                writer.flush();
            } catch (IOException ex) {
                System.out.println("An error while reading file occurred!");
            }


            return res;
        } else if (equationType == 2) {
            SquareEquation se = new SquareEquation(params[0], params[1], params[2]);
            se.setResult(solveSquare(se));
            int[] res = new int[2];
            res[0] = se.getResult()[0];
            res[1] = se.getResult()[1];


            try (FileWriter writer = new FileWriter(fileAddress, false)) {
                String line = equationType + " " + params[0] + " " + params[1] + " " + params[2] + " "
                        + res[0] + " " + res[1];
                writer.write(line);
                writer.append('\n');
                writer.flush();
            } catch (IOException ex) {
                System.out.println("An error while reading file occurred!");
            }


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
