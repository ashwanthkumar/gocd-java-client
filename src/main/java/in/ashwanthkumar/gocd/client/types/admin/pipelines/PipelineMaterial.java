package in.ashwanthkumar.gocd.client.types.admin.pipelines;

import com.google.gson.annotations.SerializedName;

public class PipelineMaterial
{

  @SerializedName("materials")
  private String type;

  @SerializedName("attributes")
  private PipelinAttributes attributes;

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public PipelinAttributes getAttributes()
  {
    return attributes;
  }

  public void setAttributes(PipelinAttributes attributes)
  {
    this.attributes = attributes;
  }
  
}
