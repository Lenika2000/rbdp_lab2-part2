package group.moveon.models;

import javax.persistence.*;

@Entity
@Table(name = "equation")
public class EquationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String equation;
    private String solution;
    @ManyToOne
    @JoinColumn(name="username")
    private User user;

    public EquationResult(int id, String equation, String solution, User user) {
        this.id = id;
        this.equation = equation;
        this.solution = solution;
        this.user = user;
    }

    public EquationResult() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "id=" + id +
                " " + equation +
                " " + solution;
    }
}

