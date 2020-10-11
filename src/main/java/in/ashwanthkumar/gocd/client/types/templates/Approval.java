package in.ashwanthkumar.gocd.client.types.templates;


import com.google.gson.annotations.SerializedName;

public class Approval
{
  @SerializedName("type")
  private String type;

  @SerializedName("allow_only_on_success")
  private boolean allowOnlyOnSuccess;

  @SerializedName("authorization")
  private TemplateAuthorization authorization;

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

  public TemplateAuthorization getAuthorization()
  {
    return authorization;
  }

  public void setAuthorization(TemplateAuthorization authorization)
  {
    this.authorization = authorization;
  }

}
