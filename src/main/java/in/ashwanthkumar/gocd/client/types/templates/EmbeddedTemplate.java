package in.ashwanthkumar.gocd.client.types.templates;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import in.ashwanthkumar.gocd.client.types.Pipeline;
import in.ashwanthkumar.gocd.client.types.Stage;

public class EmbeddedTemplate
{

  @SerializedName("name")
  private String name;

  @SerializedName("can_edit")
  private boolean canEdit;

  @SerializedName("can_administer")
  private boolean canAdminister;

  @SerializedName("stages")
  private List<TemplateStage> stages;

  @SerializedName("_links")
  private EmbeddedLinks links;
  
  @SerializedName("_embedded")
  private EmbeddedPipeline embedded;

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

  public EmbeddedLinks getLinks()
  {
    return links;
  }

  public void setLinks(EmbeddedLinks links)
  {
    this.links = links;
  }

  public EmbeddedPipeline getEmbedded()
  {
    return embedded;
  }

  public void setEmbedded(EmbeddedPipeline embedded)
  {
    this.embedded = embedded;
  }

}
