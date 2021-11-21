package group.moveon.command;

import logic.Auth;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AuthCommand implements Command {
    private String userName;

    public AuthCommand() {
    }

    public int execute() throws NotBoundException, RemoteException {
        String BINDING_NAME_AUTH = "server.auth";
        Registry registry = LocateRegistry.getRegistry(15672);
        Auth auth = (Auth) registry.lookup(BINDING_NAME_AUTH);
        this.userName = signInOrRegister();
        auth.register(this.userName);
        return 0;
    }

    private String signInOrRegister() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter your name:");
            String name = scanner.nextLine();
            if (name.length() != 0) return name;
        }
    }

    public String getUserName() {
        return userName;
    }
}
