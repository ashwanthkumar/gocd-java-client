package in.ashwanthkumar.gocd.client.types.admin;

import com.google.gson.annotations.SerializedName;

public class EmbeddedLink
{

  @SerializedName("href")
  private String href;

  public String getHref()
  {
    return href;
  }

  public void setHref(String href)
  {
    this.href = href;
  }

  
}
