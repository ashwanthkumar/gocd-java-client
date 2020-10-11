package in.ashwanthkumar.gocd.client.types.templates;

import com.google.gson.annotations.SerializedName;

public class Configuration
{

  @SerializedName("key")
  private String key;

  @SerializedName("value")
  private Object value;

  public String getKey()
  {
    return key;
  }

  public void setKey(String key)
  {
    this.key = key;
  }

  public Object getValue()
  {
    return value;
  }

  public void setValue(Object value)
  {
    this.value = value;
  }

  
}
