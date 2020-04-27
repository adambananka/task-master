package cz.muni.fi.ib053.taskMaster.data.sqlite;

import org.hibernate.MappingException;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;

/**
 * Support for identity columns for SQLite database, which is not supported
 * by Spring by default.
 *
 * @author Adam Baňanka
 */
public class SQLiteIdentityColumnSupport extends IdentityColumnSupportImpl {

  @Override
  public boolean supportsIdentityColumns() {
    return true;
  }

  @Override
  public String getIdentitySelectString(String table, String column, int type)
      throws MappingException {
    return "select last_insert_rowid()";
  }

  @Override
  public String getIdentityColumnString(int type) throws MappingException {
    return "integer";
  }
}
