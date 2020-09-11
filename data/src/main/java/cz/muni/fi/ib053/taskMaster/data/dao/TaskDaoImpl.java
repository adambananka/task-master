package cz.muni.fi.ib053.taskMaster.data.dao;

import cz.muni.fi.ib053.taskMaster.data.entity.Task;
import cz.muni.fi.ib053.taskMaster.data.entity.User;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of {@link TaskDao} interface.
 *
 * @author Vojtěch Kovářík
 */
@Repository
public class TaskDaoImpl extends AbstractDaoImpl<Task> implements TaskDao {

  public TaskDaoImpl() {
    super(Task.class, LoggerFactory.getLogger(TaskDaoImpl.class));
  }

  @Override
  public List<Task> findByUser(User user) {
    if (user == null) {
      throw new IllegalArgumentException("Invalid user value.");
    }
    LOG.info("find task by user {}", user);
    return em.createQuery("SELECT t FROM Task t WHERE t.user = :user",
            Task.class).setParameter("user", user).getResultList();
  }
}
