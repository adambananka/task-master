package cz.muni.fi.ib053.taskMaster.data.dao;

import cz.muni.fi.ib053.taskMaster.data.PersistenceApplicationContext;
import cz.muni.fi.ib053.taskMaster.data.entity.Task;
import cz.muni.fi.ib053.taskMaster.data.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Tests for {@link TaskDaoImpl}.
 *
 * @author Vojtěch Kovářík
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@Transactional
public class TaskDaoImplTest {

  @Autowired
  private TaskDao taskDao;

  private Task task1;
  private Task task2;

  @Before
  public void setUp() {
    task1 = new Task();
    task1.setNumInQueue(1);
    task1.setSolveTime(45);

    task2 = new Task();
    task2.setNumInQueue(2);
    task2.setSolveTime(30);
  }

  @Test
  public void testCreate() {
    assertEquals(0,task1.getId());
    taskDao.create(task1);
    assertTrue(task1.getId() > 0);
  }

  @Test(expected = DataAccessException.class)
  public void testCreate_null() {
    taskDao.create(null);
  }

  @Test(expected = ValidationException.class)
  public void testCreate_invalidNumInQueue() {
    task1.setNumInQueue(-1);
    taskDao.create(task1);
  }

  @Test(expected = ValidationException.class)
  public void testCreate_NumInQueue() {
    task1.setNumInQueue(3);
    taskDao.create(task1);
    assertEquals(3, taskDao.findAll().get(0).getNumInQueue());
  }

  @Test(expected = ValidationException.class)
  public void testCreate_invalidSolveTime() {
    task1.setSolveTime(-1);
    taskDao.create(task1);
  }

  @Test(expected = ValidationException.class)
  public void testCreate_SolveTime() {
    task1.setSolveTime(35);
    taskDao.create(task1);
    assertEquals(35, taskDao.findAll().get(0).getSolveTime());
  }

  @Test
  public void testDelete() {
    taskDao.create(task1);
    taskDao.create(task2);
    assertEquals(2, taskDao.findAll().size());
    taskDao.delete(task1);
    assertEquals(1, taskDao.findAll().size());
    assertEquals(task2, taskDao.findAll().get(0));
  }

  @Test(expected = DataAccessException.class)
  public void testDelete_null() {
    taskDao.create(null);
  }

  @Test
  public void testFindById() {
    taskDao.create(task1);
    taskDao.create(task2);
    assertEquals(Optional.of(task1), taskDao.findById(task1.getId()));
  }

  @Test(expected = DataAccessException.class)
  public void testFindById_invalid() {
    taskDao.findById(0);
  }

  @Test
  public void testFindById_notExisting() {
    assertFalse(taskDao.findById(7).isPresent());
  }

  @Test
  public void testFindAll() {
    taskDao.create(task1);
    assertEquals(1, taskDao.findAll().size());
    taskDao.create(task2);
    assertEquals(2, taskDao.findAll().size());
  }

  @Test
  public void testFindByUser() {
    User user1 = new User();

    taskDao.create(task1);
    taskDao.create(task2);
    task2.setUser(user1);
    assertEquals(task2, taskDao.findByUser(user1).get(0));
  }
}
