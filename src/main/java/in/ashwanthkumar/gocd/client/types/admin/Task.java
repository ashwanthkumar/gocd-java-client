package in.ashwanthkumar.gocd.client.types.admin;

import com.google.gson.annotations.SerializedName;

public class Task
{

  @SerializedName("type")
  private String type;

  @SerializedName("attributes")
  private TaskAttribute attributes;

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public TaskAttribute getAttributes()
  {
    return attributes;
  }

  public void setAttributes(TaskAttribute attributes)
  {
    this.attributes = attributes;
  }

}
