package cz.muni.fi.ib053.taskMaster.data.dao;

import cz.muni.fi.ib053.taskMaster.data.entity.User;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Implementation of {@link UserDao} interface.
 *
 * @author Adam Baňanka
 */
@Repository
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {

  public UserDaoImpl() {
    super(User.class, LoggerFactory.getLogger(UserDaoImpl.class));
  }

  @Override
  public User findByLogin(String login) {
    if (login == null || login.isEmpty()) {
      throw new IllegalArgumentException("Invalid login value.");
    }
    LOG.info("find user with login {}", login);
    return em.createQuery("SELECT u FROM User u WHERE u.login = :login",
        User.class).setParameter("login", login).getSingleResult();
  }
}
