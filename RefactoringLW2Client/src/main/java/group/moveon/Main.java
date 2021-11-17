package group.moveon;

import logic.Auth;
import logic.EquationFinder;
import logic.EquationSolver;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static final String BINDING_NAME_SOLVER = "server.solver";
    public static final String BINDING_NAME_FINDER = "server.finder";
    public static final String BINDING_NAME_AUTH = "server.auth";

    public static void main(String[] args) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(15672);
        EquationSolver solver = (EquationSolver) registry.lookup(BINDING_NAME_SOLVER);
        EquationFinder finder = (EquationFinder) registry.lookup(BINDING_NAME_FINDER);
        Auth auth = (Auth) registry.lookup(BINDING_NAME_AUTH);
        String userName = signInOrRegister();
        auth.register(userName);

        printWelcoming();

        while (true) {
            System.out.println("____");
            System.out.println("Enter the command:");
            String command = scanner.nextLine();
            if (command.equals("solve")) {
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
                        break;
                    }
                    scanner.nextLine();
                    System.out.println(solver.solveEquation(equationType, eqParams, userName));
                } catch (InputMismatchException ex) {
                    System.out.println("Wrong argument!");
                }
            } else if (command.equals("find")) {
                try {
                    System.out.println(finder.getAvailableEquationIds(userName));
                    System.out.println("Enter the index of the equation:");
                    int equationNumber = scanner.nextInt();
                    System.out.println(finder.findEquation(equationNumber, userName));
                    scanner.nextLine();
                } catch (NoSuchElementException e) {
                    System.out.println("Wrong argument!");
                }
            } else if (command.equals("quit")) {
                break;
            } else if (command.equals("")) {
                continue;
            } else {
                System.out.println("Unknown command. Please, retry.");
            }
        }
    }


    private static void printWelcoming() {
        System.out.println("Usage");
        System.out.println("Use one of the following commands:");
        System.out.println("\t\t\"solve\" to select the type of an equation and solve it;");
        System.out.println("\t\t\"find\" to get an existing solution from memory;");
        System.out.println("\t\t\"quit\" to leave the program.");
    }

    private static String signInOrRegister() {
        while (true) {
            System.out.println("Please enter your name:");
            String name = scanner.nextLine();
            if (name.length() != 0) return name;
        }
    }

}
