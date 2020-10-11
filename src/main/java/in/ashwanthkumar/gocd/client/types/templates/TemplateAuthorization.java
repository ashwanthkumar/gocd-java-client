package in.ashwanthkumar.gocd.client.types.templates;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import in.ashwanthkumar.gocd.client.types.users.User;
import in.ashwanthkumar.gocd.client.types.users.UserRole;

public class TemplateAuthorization
{

  @SerializedName("users")
  private List<User> users;

  @SerializedName("roles")
  private List<UserRole> roles;

  public List<User> getUsers()
  {
    return users;
  }

  public void setUsers(List<User> users)
  {
    this.users = users;
  }

  public List<UserRole> getRoles()
  {
    return roles;
  }

  public void setRoles(List<UserRole> roles)
  {
    this.roles = roles;
  }


}
