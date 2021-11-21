package group.moveon.command;

import logic.EquationSolver;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SolveCommand implements Command {
    private Scanner scanner = new Scanner(System.in);
    private EquationSolver solver;
    private String userName;

    public SolveCommand(String userName) throws RemoteException, NotBoundException {
        String BINDING_NAME_SOLVER = "server.solver";
        Registry registry = LocateRegistry.getRegistry(15672);
        this.solver = (EquationSolver) registry.lookup(BINDING_NAME_SOLVER);
        this.userName = userName;
    }

    public int execute() throws IOException {
        try {
            System.out.println("Select the type of an equation:");
            System.out.println("1 - k * x + b = 0");
            System.out.println("2 - a * x^2 + b * x + c = 0");
            int equationType = scanner.nextInt();
            int[] eqParams;
            if (equationType == 1) {
                eqParams = new int[2];
                System.out.println("Enter the parameters:");
                System.out.println("Parameter k:");
                eqParams[0] = scanner.nextInt();
                System.out.println("Parameter b:");
                eqParams[1] = scanner.nextInt();
            } else if (equationType == 2) {
                eqParams = new int[3];
                System.out.println("Enter the parameters:");
                System.out.println("Parameter a:");
                eqParams[0] = scanner.nextInt();
                System.out.println("Parameter b:");
                eqParams[1] = scanner.nextInt();
                System.out.println("Parameter c:");
                eqParams[2] = scanner.nextInt();
            } else {
                System.out.println("Wrong type entered!");
                return 0;
            }
            scanner.nextLine();
            System.out.println(solver.solveEquation(equationType, eqParams, userName));
        } catch (InputMismatchException ex) {
            System.out.println("Wrong argument!");
        }
        return 0;
    }
}
