package in.ashwanthkumar.gocd.client.types.templates;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class EmbeddedTemplates
{

  @SerializedName("templates")
  private List<EmbeddedTemplate> templates;

  public List<EmbeddedTemplate> getTemplates()
  {
    return templates;
  }

  public void setTemplates(List<EmbeddedTemplate> templates)
  {
    this.templates = templates;
  }

}
