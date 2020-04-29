package cz.muni.fi.ib053.taskMaster.data.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents user entity.
 *
 * @author Adam Baňanka
 */
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull
  @Column(nullable = false)
  @Size(min = 3, max = 32)
  private String login;

  @NotNull
  @Column(nullable = false)
  @Size(min = 6, max = 256)
  private String password;

  @NotNull
  @Column(nullable = false)
  @Size(min = 3, max = 64)
  private String name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof User)) {
      return false;
    }

    User user = (User) obj;
    return Objects.equals(login, user.getLogin());
  }

  @Override
  public int hashCode() {
    return Objects.hash(login);
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", login='" + login + '\'' +
        ", password='" + password + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
