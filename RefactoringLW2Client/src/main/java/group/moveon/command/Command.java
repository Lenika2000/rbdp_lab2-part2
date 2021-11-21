package group.moveon.command;

import java.io.IOException;
import java.rmi.NotBoundException;

public interface Command {
    int execute() throws IOException, NotBoundException;
}
