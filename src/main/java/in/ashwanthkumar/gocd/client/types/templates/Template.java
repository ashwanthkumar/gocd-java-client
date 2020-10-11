package in.ashwanthkumar.gocd.client.types.templates;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import in.ashwanthkumar.gocd.client.types.Pipeline;
import in.ashwanthkumar.gocd.client.types.Stage;

public class Template
{

  @SerializedName("name")
  private String name;

  @SerializedName("can_edit")
  private boolean canEdit;

  @SerializedName("can_administer")
  private boolean canAdminister;

  @SerializedName("stages")
  private List<TemplateStage> stages;

  @SerializedName("pipelines")
  private List<Pipeline> pipelines;

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public boolean isCanEdit()
  {
    return canEdit;
  }

  public void setCanEdit(boolean canEdit)
  {
    this.canEdit = canEdit;
  }

  public boolean isCanAdminister()
  {
    return canAdminister;
  }

  public void setCanAdminister(boolean canAdminister)
  {
    this.canAdminister = canAdminister;
  }

  public List<TemplateStage> getStages()
  {
    return stages;
  }

  public void setStages(List<TemplateStage> stages)
  {
    this.stages = stages;
  }

  public List<Pipeline> getPipelines()
  {
    return pipelines;
  }

  public void setPipelines(List<Pipeline> pipelines)
  {
    this.pipelines = pipelines;
  }

}
