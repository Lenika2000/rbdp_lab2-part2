package group.moveon.logic;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.Scanner;

public class EquationFinderServer implements EquationFinder{

    @Override
    public String findEquation(int eqNumb) throws RemoteException {

        final String fileName = "C:\\Users\\Mikhail\\IdeaProjects\\RefactoringLW2Server\\src\\eqRegistry.txt";

        try {
            Path path = Paths.get(fileName);
            Scanner scanner = new Scanner(path);
            scanner.useDelimiter(System.getProperty("line.separator"));

            for (int i = 0; i < eqNumb; i++) {
                scanner.next();
            }
            String returnValue = scanner.next();
            String[] arguments = returnValue.split(" ");
            String res;
            if (Integer.parseInt(arguments[0]) == 1) {
                res = arguments[1] + " * x + " + arguments[2] + " = 0; root: " + arguments[3];
            } else {
                res = arguments[1] + " * x^2 + " + arguments[2] + " * x + " + arguments[3]
                        + " = 0; roots: " + arguments[4] + ", " + arguments[5];
            }
            scanner.close();
            return res;
        } catch (IOException ex) {
            return "An error while reading file occurred!";
        }

        //return "Equation found!";
    }

}
