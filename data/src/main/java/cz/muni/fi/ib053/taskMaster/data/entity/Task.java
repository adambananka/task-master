package cz.muni.fi.ib053.taskMaster.data.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private User user;

    @NotNull
    private int numInQueue;

    @NotNull
    private int solveTime;

    @ManyToMany
    @JoinTable(name="tbl_prerequisites",
            joinColumns=@JoinColumn(name="taskId"),
            inverseJoinColumns=@JoinColumn(name="prerequisiteId")
    )
    private List<User> prerequisites;

    @ManyToMany
    @JoinTable(name="tbl_prerequisites",
            joinColumns=@JoinColumn(name="prerequisiteId"),
            inverseJoinColumns=@JoinColumn(name="taskId")
    )
    private List<User> prerequisiteOf;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public int getNumInQueue() { return numInQueue; }

    public void setNumInQueue(int numInQueue) { this.numInQueue = numInQueue; }

    public int getSolveTime() { return solveTime; }

    public void setSolveTime(int solveTime) { this.solveTime = solveTime; }

    public List<User> getPrerequisites() { return prerequisites; }

    public void setPrerequisites(List<User> prerequisites) { this.prerequisites = prerequisites; }

    public List<User> getPrerequisiteOf() { return prerequisiteOf; }

    public void setPrerequisiteOf(List<User> prerequisiteOf) { this.prerequisiteOf = prerequisiteOf; }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Task)) {
            return false;
        }

        Task task = (Task) o;
        return Objects.equals(id, task.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", user=" + user +
                ", numInQueue=" + numInQueue +
                ", solveTime=" + solveTime +
                ", prerequisites=" + prerequisites +
                ", prerequisiteOf=" + prerequisiteOf +
                '}';
    }
}
