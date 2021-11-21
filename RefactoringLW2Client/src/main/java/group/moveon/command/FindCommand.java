package group.moveon.command;

import logic.EquationFinder;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FindCommand implements Command {
    private Scanner scanner = new Scanner(System.in);
    private EquationFinder finder;
    private String userName;

    public FindCommand(String userName) throws RemoteException, NotBoundException {
        String BINDING_NAME_FINDER = "server.finder";
        Registry registry = LocateRegistry.getRegistry(15672);
        this.finder = (EquationFinder) registry.lookup(BINDING_NAME_FINDER);
        this.userName = userName;
    }

    public int execute() throws IOException {
        try {
            System.out.println(finder.getAvailableEquationIds(userName));
            System.out.println("Enter the index of the equation:");
            int equationNumber = scanner.nextInt();
            System.out.println(finder.findEquation(equationNumber, userName));
            scanner.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("Wrong argument!");
        }
        return 0;
    }
}
