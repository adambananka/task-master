package cz.muni.fi.ib053.taskMaster.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation
    .PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import javax.sql.DataSource;

/**
 * Application context class with database and entity manager setup.
 *
 * @author Adam Baňanka
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
public class PersistenceApplicationContext {

  @Autowired
  private Environment env;

  @Bean
  public PersistenceExceptionTranslationPostProcessor postProcessor() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

  @Bean
  public JpaTransactionManager transactionManager() {
    return new JpaTransactionManager(entityManagerFactory().getObject());
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em =
        new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(getDatasource());
    em.setPackagesToScan("cz.muni.fi.ib053.taskMaster");
    em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    em.setJpaProperties(getAdditionalProperties());
    return em;
  }

  @Bean
  public DataSource getDatasource() {
    final DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("driverClassName"));
    dataSource.setUrl(env.getProperty("url"));
    dataSource.setUsername(env.getProperty("user"));
    dataSource.setPassword(env.getProperty("password"));
    return dataSource;
  }

  private Properties getAdditionalProperties() {
    final Properties hibernateProperties = new Properties();
    hibernateProperties.setProperty("hibernate.hbm2ddl.auto",
        env.getProperty("hibernate.hbm2ddl.auto"));
    hibernateProperties.setProperty("hibernate.dialect",
        env.getProperty("hibernate.dialect"));
    hibernateProperties.setProperty("hibernate.show_sql",
        env.getProperty("hibernate.show_sql"));
    return hibernateProperties;
  }
}
