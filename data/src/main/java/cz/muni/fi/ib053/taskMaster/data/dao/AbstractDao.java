package cz.muni.fi.ib053.taskMaster.data.dao;

import java.util.List;
import java.util.Optional;

/**
 * Represents general Data Access Object for further specified entity.
 *
 * @param <T> entity represented by the DAO
 *
 * @author Adam Baňanka
 */
interface AbstractDao<T> {

  /**
   * Persists a new item into database.
   *
   * @param item entity to be persisted
   */
  void create(T item);

  /**
   * Deletes given item from database.
   *
   * @param item entity to be deleted
   */
  void delete(T item);

  /**
   * Retrieves item with given id from database.
   *
   * @param id id of requested entity
   * @return {@link Optional} object containing either entity with given id
   * retrieved from database or {@code null}, never returns {@code null}
   */
  Optional<T> findById(int id);

  /**
   * Retrieves all items from database.
   *
   * @return list of all entities retrieved from database
   */
  List<T> findAll();
}
