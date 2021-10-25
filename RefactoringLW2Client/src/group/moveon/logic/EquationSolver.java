package group.moveon.logic;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EquationSolver extends Remote {

    int[] solveEquation(int equationType, int[] params) throws RemoteException;

}
