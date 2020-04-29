package cz.muni.fi.ib053.taskMaster.data.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

/**
 * Represents task entity.
 *
 * @author Vojtěch Kovářík
 */
@Table(name = "tasks")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  private User user;

  @Min(0)
  private int numInQueue;

  @Min(0)
  private int solveTime;

  @ManyToMany
  @JoinTable(name = "prerequisites", joinColumns = @JoinColumn(name="taskId", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "prerequisiteId", referencedColumnName = "id") )
  private List<Task> prerequisites;

  @ManyToMany(mappedBy = "prerequisites")
  private List<Task> prerequisiteOf;

  public int getId() { return id; }

  public void setId(int id) { this.id = id; }

  public User getUser() { return user; }

  public void setUser(User user) { this.user = user; }

  public int getNumInQueue() { return numInQueue; }

  public void setNumInQueue(int numInQueue) { this.numInQueue = numInQueue; }

  public int getSolveTime() { return solveTime; }

  public void setSolveTime(int solveTime) { this.solveTime = solveTime; }

  public List<Task> getPrerequisites() { return prerequisites; }

  public void setPrerequisites(List<Task> prerequisites) { this.prerequisites = prerequisites; }

  public List<Task> getPrerequisiteOf() { return prerequisiteOf; }

  public void setPrerequisiteOf(List<Task> prerequisiteOf) { this.prerequisiteOf = prerequisiteOf; }

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
    if (!Objects.equals(user, task.getUser())) return false;
    if (numInQueue != task.getNumInQueue()) return false;
    if (solveTime != task.getSolveTime()) return false;
    if (!Objects.equals(prerequisites, task.getPrerequisites())) return false;
    return Objects.equals(prerequisiteOf, task.getPrerequisiteOf());
  }

  @Override
  public int hashCode() {
    int prime = 37;
    int result = 1;

    result = prime * result + Objects.hashCode(user);
    result = prime * result + numInQueue;
    result = prime * result + solveTime;
    result = prime * result + Objects.hashCode(prerequisites);
    result = prime * result + Objects.hashCode(prerequisiteOf);
    return result;
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
