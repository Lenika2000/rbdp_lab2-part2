package group.moveon;

import group.moveon.command.*;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, NotBoundException {
        int exit = 0;
        AuthCommand authCommand = new AuthCommand();
        authCommand.execute();
        HashMap<String, Command> commandHashMap = createCommands(authCommand.getUserName());
        printWelcoming();

        while (true) {
            System.out.println("____");
            System.out.println("Enter the command:");
            String userCommand = scanner.nextLine();
            Command command = commandHashMap.get(userCommand);
            if (command != null) {
                exit = command.execute();
            } else  {
                System.out.println("Unknown command. Please, retry.");
            }
            if (exit == 1) break;
        }
    }

    private static HashMap<String, Command> createCommands(String userName) throws RemoteException, NotBoundException {
        FindCommand findCommand = new FindCommand(userName);
        SolveCommand solveCommand = new SolveCommand(userName);
        HashMap<String, Command> commands = new HashMap<String, Command>();
        commands.put("solve", solveCommand);
        commands.put("find", findCommand);
        commands.put("quit",  new QuitCommand());
        return commands;
    }


    private static void printWelcoming() {
        System.out.println("Usage");
        System.out.println("Use one of the following commands:");
        System.out.println("\t\t\"solve\" to select the type of an equation and solve it;");
        System.out.println("\t\t\"find\" to get an existing solution from memory;");
        System.out.println("\t\t\"quit\" to leave the program.");
    }

}
