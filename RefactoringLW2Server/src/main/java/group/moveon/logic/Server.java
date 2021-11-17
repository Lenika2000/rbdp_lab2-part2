package group.moveon.logic;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static final String BINDING_NAME_SOLVER = "server.solver";
    public static final String BINDING_NAME_FINDER = "server.finder";
    public static final String BINDING_NAME_AUTH = "server.auth";

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {
        final group.moveon.logic.EquationSolvingServer solvingServer = new group.moveon.logic.EquationSolvingServer();
        final group.moveon.logic.EquationFinderServer findingServer = new group.moveon.logic.EquationFinderServer();
        final group.moveon.logic.AuthServer authServer = new group.moveon.logic.AuthServer();
        final Registry registry = LocateRegistry.createRegistry(15672);

        Remote stub = UnicastRemoteObject.exportObject(solvingServer, 0);
        Remote stubFind = UnicastRemoteObject.exportObject(findingServer, 1);
        Remote stubAuth = UnicastRemoteObject.exportObject(authServer, 2);

        registry.bind(BINDING_NAME_SOLVER, stub);
        registry.bind(BINDING_NAME_FINDER, stubFind);
        registry.bind(BINDING_NAME_AUTH, stubAuth);

        System.out.println("Server has been started!");

        Thread.sleep(Integer.MAX_VALUE);
    }

}
