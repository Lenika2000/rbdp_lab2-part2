package logic;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EquationFinder extends Remote {

    String findEquation(int eqNumb, String username) throws RemoteException;
    String getAvailableEquationIds(String username) throws RemoteException;
}
