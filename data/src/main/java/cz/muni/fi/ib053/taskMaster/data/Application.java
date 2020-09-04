package cz.muni.fi.ib053.taskMaster.data;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Application starting point, contains main method.
 *
 * @author Adam Baňanka
 */
public class Application {

  public static void main(String[] args) {
    new AnnotationConfigApplicationContext(PersistenceApplicationContext.class);
  }
}
