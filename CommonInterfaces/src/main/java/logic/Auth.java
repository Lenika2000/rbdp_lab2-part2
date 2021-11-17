package logic;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Auth extends Remote {
    String register(String username) throws RemoteException;
}
