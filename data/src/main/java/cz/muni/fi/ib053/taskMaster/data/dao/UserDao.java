package cz.muni.fi.ib053.taskMaster.data.dao;

import cz.muni.fi.ib053.taskMaster.data.entity.User;

/**
 * Represents Data Access Object for {@link User} entity.
 * Extends {@link AbstractDao} by specifying the entity and adding methods
 * specific to {@link User} entity.
 *
 * @author Adam Baňanka
 */
public interface UserDao extends AbstractDao<User> {

  /**
   * Retrieves user with given login from database.
   *
   * @param login login of requested user
   * @return user entity with given login retrieved from database
   */
  User findByLogin(String login);
}
