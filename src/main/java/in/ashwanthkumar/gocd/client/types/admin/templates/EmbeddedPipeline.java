package in.ashwanthkumar.gocd.client.types.admin.templates;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import in.ashwanthkumar.gocd.client.types.Pipeline;

public class EmbeddedPipeline
{

  @SerializedName("pipelines")
  private List<Pipeline> pipelines;

  public List<Pipeline> getPipelines()
  {
    return pipelines;
  }

  public void setPipelines(List<Pipeline> pipelines)
  {
    this.pipelines = pipelines;
  }
  
}
