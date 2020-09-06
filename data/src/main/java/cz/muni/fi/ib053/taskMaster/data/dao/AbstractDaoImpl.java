package cz.muni.fi.ib053.taskMaster.data.dao;

import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementation of {@link AbstractDao} interface.
 *
 * @param <T> entity represented by the DAO
 *
 * @author Adam Baňanka
 */
abstract class AbstractDaoImpl<T> implements AbstractDao<T> {

  private final Class<T> entityClass;
  protected final Logger LOG;

  @PersistenceContext
  protected EntityManager em;

  protected AbstractDaoImpl(Class<T> entityClass, Logger logger) {
    this.entityClass = entityClass;
    this.LOG = logger;
  }

  @Override
  public void create(T item) {
    if (item == null) {
      throw new IllegalArgumentException(
          entityClass.getName() + " cannot be null");
    }
    LOG.info("persist {} {}", entityClass.getName(), item);
    em.persist(item);
  }

  @Override
  public void delete(T item) {
    if (item == null) {
      throw new IllegalArgumentException(
          entityClass.getName() + " cannot be null");
    }
    LOG.info("delete {} {}", entityClass.getName(), item);
    em.remove(item);
  }

  @Override
  public Optional<T> findById(int id) {
    if (id <= 0) {
      throw new IllegalArgumentException("ID must be higher than zero");
    }
    LOG.info("find {} with id {}", entityClass.getName(), id);
    return Optional.ofNullable(em.find(entityClass, id));
  }

  @Override
  public List<T> findAll() {
    LOG.info("find all {}s", entityClass.getName());
    return em.createQuery(
        "SELECT e FROM " + entityClass.getName() + " e", entityClass)
        .getResultList();
  }
}
