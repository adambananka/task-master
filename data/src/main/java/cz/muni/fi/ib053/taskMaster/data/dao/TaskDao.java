package cz.muni.fi.ib053.taskMaster.data.dao;

import cz.muni.fi.ib053.taskMaster.data.entity.Task;
import cz.muni.fi.ib053.taskMaster.data.entity.User;

import java.util.List;

/**
 * Represents Data Access Object for {@link Task} entity.
 * Extends {@link AbstractDao} by specifying the entity and adding methods
 * specific to {@link Task} entity.
 *
 * @author Vojtěch Kovářík
 */
public interface TaskDao extends AbstractDao<Task> {
  /**
   * Updates given task in database.
   *
   * @param task entity to be updated
   */
  void update(Task task);

  /**
   * Retrieves user with given login from database.
   *
   * @param user User of requested user
   * @return Task entity with given User retrieved from database
   */
  List<Task> findByUser(User user);
}
