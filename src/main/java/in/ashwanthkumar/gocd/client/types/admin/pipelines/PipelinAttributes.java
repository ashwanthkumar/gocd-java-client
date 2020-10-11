package in.ashwanthkumar.gocd.client.types.admin.pipelines;

import com.google.gson.annotations.SerializedName;

public class PipelinAttributes
{

  @SerializedName("url")
  private String url;

  @SerializedName("destination")
  private String destination;

  @SerializedName("filter")
  private Object filter;

  @SerializedName("invert_filter")
  private boolean invertFilter;

  @SerializedName("name")
  private String name;

  @SerializedName("auto_update")
  private boolean autoUpdate;

  @SerializedName("branch")
  private String branch;

  @SerializedName("submodule_folder")
  private String submoduleFolder;

  @SerializedName("shallow_clone")
  private boolean shallowClone;

  public String getUrl()
  {
    return url;
  }

  public void setUrl(String url)
  {
    this.url = url;
  }

  public String getDestination()
  {
    return destination;
  }

  public void setDestination(String destination)
  {
    this.destination = destination;
  }

  public Object getFilter()
  {
    return filter;
  }

  public void setFilter(Object filter)
  {
    this.filter = filter;
  }

  public boolean isInvertFilter()
  {
    return invertFilter;
  }

  public void setInvertFilter(boolean invertFilter)
  {
    this.invertFilter = invertFilter;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public boolean isAutoUpdate()
  {
    return autoUpdate;
  }

  public void setAutoUpdate(boolean autoUpdate)
  {
    this.autoUpdate = autoUpdate;
  }

  public String getBranch()
  {
    return branch;
  }

  public void setBranch(String branch)
  {
    this.branch = branch;
  }

  public String getSubmoduleFolder()
  {
    return submoduleFolder;
  }

  public void setSubmoduleFolder(String submoduleFolder)
  {
    this.submoduleFolder = submoduleFolder;
  }

  public boolean isShallowClone()
  {
    return shallowClone;
  }

  public void setShallowClone(boolean shallowClone)
  {
    this.shallowClone = shallowClone;
  }

}
