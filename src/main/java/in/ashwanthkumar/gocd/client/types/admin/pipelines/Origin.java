package in.ashwanthkumar.gocd.client.types.admin.pipelines;

import com.google.gson.annotations.SerializedName;

import in.ashwanthkumar.gocd.client.types.admin.EmbeddedLinks;

public class Origin
{

  @SerializedName("_links")
  private EmbeddedLinks links;

  @SerializedName("type")
  private String type;

  public EmbeddedLinks getLinks()
  {
    return links;
  }

  public void setLinks(EmbeddedLinks links)
  {
    this.links = links;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  
}
