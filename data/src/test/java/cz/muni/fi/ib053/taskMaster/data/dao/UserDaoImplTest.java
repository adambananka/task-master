package cz.muni.fi.ib053.taskMaster.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cz.muni.fi.ib053.taskMaster.data.PersistenceApplicationContext;
import cz.muni.fi.ib053.taskMaster.data.entity.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

/**
 * Tests for {@link UserDaoImpl}.
 *
 * @author Adam Baňanka
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@Transactional
public class UserDaoImplTest {

  @Autowired
  private UserDao userDao;

  private User user1;
  private User user2;

  @Before
  public void setUp() {
    user1 = new User();
    user1.setLogin("test1");
    user1.setPassword("pwTest1");
    user1.setName("test user 1");

    user2 = new User();
    user2.setLogin("test2");
    user2.setPassword("pwTest2");
    user2.setName("test user 2");
  }

  @Test
  public void testCreate() {
    assertEquals(0,user1.getId());
    userDao.create(user1);
    assertTrue(user1.getId() > 0);
  }

  @Test(expected = DataAccessException.class)
  public void testCreate_null() {
    userDao.create(null);
  }

  @Test(expected = ValidationException.class)
  public void testCreate_invalidLogin() {
    user1.setLogin("in");
    userDao.create(user1);
  }

  @Test(expected = ValidationException.class)
  public void testCreate_nullLogin() {
    user1.setLogin(null);
    userDao.create(user1);
  }

  @Test(expected = ValidationException.class)
  public void testCreate_invalidPassword() {
    user1.setPassword("12345");
    userDao.create(user1);
  }

  @Test(expected = ValidationException.class)
  public void testCreate_nullPassword() {
    user1.setPassword(null);
    userDao.create(user1);
  }

  @Test(expected = ValidationException.class)
  public void testCreate_invalidName() {
    user1.setName("in");
    userDao.create(user1);
  }

  @Test(expected = ValidationException.class)
  public void testCreate_nullName() {
    user1.setName("in");
    userDao.create(user1);
  }

  @Test
  public void testDelete() {
    userDao.create(user1);
    userDao.create(user2);
    assertEquals(2, userDao.findAll().size());
    userDao.delete(user1);
    assertEquals(1, userDao.findAll().size());
    assertEquals(user2, userDao.findAll().get(0));
  }

  @Test(expected = DataAccessException.class)
  public void testDelete_null() {
    userDao.create(null);
  }

  @Test
  public void testFindById() {
    userDao.create(user1);
    userDao.create(user2);
    assertEquals(Optional.of(user1), userDao.findById(user1.getId()));
  }

  @Test(expected = DataAccessException.class)
  public void testFindById_invalid() {
    userDao.findById(0);
  }

  @Test
  public void testFindById_notExisting() {
    assertFalse(userDao.findById(7).isPresent());
  }

  @Test
  public void testFindByLogin() {
    userDao.create(user1);
    userDao.create(user2);
    assertEquals(user1, userDao.findByLogin(user1.getLogin()));
  }

  @Test(expected = DataAccessException.class)
  public void testFindByLogin_null() {
    userDao.findByLogin(null);
  }

  @Test(expected = DataAccessException.class)
  public void testFindByLogin_empty() {
    userDao.findByLogin("");
  }

  @Test(expected = DataAccessException.class)
  public void testFindByLogin_notExisting() {
    userDao.findByLogin("invalid");
  }

  @Test
  public void testFindAll() {
    userDao.create(user1);
    assertEquals(1, userDao.findAll().size());
    userDao.create(user2);
    assertEquals(2, userDao.findAll().size());
  }
}
