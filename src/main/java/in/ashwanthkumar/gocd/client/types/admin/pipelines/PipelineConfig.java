package in.ashwanthkumar.gocd.client.types.admin.pipelines;

import com.google.gson.annotations.SerializedName;
import in.ashwanthkumar.gocd.client.GoCD;
import in.ashwanthkumar.gocd.client.types.Material;
import in.ashwanthkumar.gocd.client.types.admin.EmbeddedLinks;
import in.ashwanthkumar.gocd.client.types.admin.EnvironmentVariable;
import in.ashwanthkumar.gocd.client.types.admin.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class PipelineConfig
{

  @SerializedName("name")
  private String name;

  @SerializedName("_links")
  private EmbeddedLinks links;

  @SerializedName("label_template")
  private String labelTemplate;

  @SerializedName("template")
  private String template;

  @SerializedName("group")
  private String group;

  @SerializedName("origin")
  private Origin origin;

  @SerializedName("parameters")
  private List<Object> parameters;

  @SerializedName("environment_variables")
  private List<EnvironmentVariable> environmentVariables;

  @SerializedName("materials")
  private List<PipelineMaterial> materials;
  
  @SerializedName("stages")
  private List<Stage> stages;
  
  @SerializedName("tracking_tool")
  private Object trackingTool;

  @SerializedName("timer")
  private Object timer;
  
  public PipelineConfig() {
    super();
  }
  
  public PipelineConfig(String name, EmbeddedLinks links, String labelTemplate, String template, String group, Origin origin, List<Object> parameters,
      List<EnvironmentVariable> environmentVariables, List<PipelineMaterial> materials, List<Stage> stages, Object trackingTool, Object timer)
  {
    super();
    this.name = name;
    this.links = links;
    this.labelTemplate = labelTemplate;
    this.template = template;
    this.group = group;
    this.origin = origin;
    this.parameters = parameters;
    this.environmentVariables = environmentVariables;
    this.materials = materials;
    this.stages = stages;
    this.trackingTool = trackingTool;
    this.timer = timer;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public EmbeddedLinks getLinks()
  {
    return links;
  }

  public void setLinks(EmbeddedLinks links)
  {
    this.links = links;
  }

  public String getLabelTemplate()
  {
    return labelTemplate;
  }

  public void setLabelTemplate(String labelTemplate)
  {
    this.labelTemplate = labelTemplate;
  }

  public String getTemplate()
  {
    return template;
  }

  public void setTemplate(String template)
  {
    this.template = template;
  }

  public String getGroup()
  {
    return group;
  }

  public void setGroup(String group)
  {
    this.group = group;
  }

  public Origin getOrigin()
  {
    return origin;
  }

  public void setOrigin(Origin origin)
  {
    this.origin = origin;
  }

  public List<Object> getParameters()
  {
    return parameters;
  }

  public void setParameters(List<Object> parameters)
  {
    this.parameters = parameters;
  }

  public List<EnvironmentVariable> getEnvironmentVariables()
  {
    return environmentVariables;
  }

  public void setEnvironmentVariables(List<EnvironmentVariable> environmentVariables)
  {
    this.environmentVariables = environmentVariables;
  }

  public List<PipelineMaterial> getMaterials()
  {
    return materials;
  }

  public void setMaterials(List<PipelineMaterial> materials)
  {
    this.materials = materials;
  }

  public List<Stage> getStages()
  {
    return stages;
  }

  public void setStages(List<Stage> stages)
  {
    this.stages = stages;
  }

  public Object getTrackingTool()
  {
    return trackingTool;
  }

  public void setTrackingTool(Object trackingTool)
  {
    this.trackingTool = trackingTool;
  }

  public Object getTimer()
  {
    return timer;
  }

  public void setTimer(Object timer)
  {
    this.timer = timer;
  }
  
  

}
