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

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {
        final EquationSolvingServer solvingServer = new EquationSolvingServer();
        final EquationFinderServer findingServer = new EquationFinderServer();
        final Registry registry = LocateRegistry.createRegistry(15672);

        Remote stub = UnicastRemoteObject.exportObject(solvingServer, 0);
        Remote stubFind = UnicastRemoteObject.exportObject(findingServer, 1);

        registry.bind(BINDING_NAME_SOLVER, stub);
        registry.bind(BINDING_NAME_FINDER, stubFind);

        System.out.println("Server has been started!");

        Thread.sleep(Integer.MAX_VALUE);
    }

}
