package in.ashwanthkumar.gocd.client.types.admin.templates;


import com.google.gson.annotations.SerializedName;

import in.ashwanthkumar.gocd.client.types.admin.Authorization;

public class Approval
{
  @SerializedName("type")
  private String type;

  @SerializedName("allow_only_on_success")
  private boolean allowOnlyOnSuccess;

  @SerializedName("authorization")
  private Authorization authorization;

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public boolean isAllowOnlyOnSuccess()
  {
    return allowOnlyOnSuccess;
  }

  public void setAllowOnlyOnSuccess(boolean allowOnlyOnSuccess)
  {
    this.allowOnlyOnSuccess = allowOnlyOnSuccess;
  }

  public Authorization getAuthorization()
  {
    return authorization;
  }

  public void setAuthorization(Authorization authorization)
  {
    this.authorization = authorization;
  }

}
