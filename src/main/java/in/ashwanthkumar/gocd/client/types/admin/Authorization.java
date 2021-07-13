package in.ashwanthkumar.gocd.client.types.admin;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import in.ashwanthkumar.gocd.client.types.users.User;
import in.ashwanthkumar.gocd.client.types.users.UserRole;

public class Authorization
{

  @SerializedName("users")
  private List<String> users;

  @SerializedName("roles")
  private List<String> roles;

  public List<String> getUsers()
  {
    return users;
  }

  public void setUsers(List<String> users)
  {
    this.users = users;
  }

  public List<String> getRoles()
  {
    return roles;
  }

  public void setRoles(List<String> roles)
  {
    this.roles = roles;
  }


}
