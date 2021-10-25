package group.moveon.logic;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EquationFinder extends Remote {

    String findEquation(int eqNumb) throws RemoteException;

}
