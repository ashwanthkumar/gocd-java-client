package in.ashwanthkumar.gocd.client.types.templates;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Artifact
{

  @SerializedName("type")
  private String type;

  @SerializedName("source")
  private String source;

  @SerializedName("destination")
  private String destination;

  @SerializedName("id")
  private String id;

  @SerializedName("store_id")
  private String storeId;

  @SerializedName("configuration")
  private List<Configuration> configuration;

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getSource()
  {
    return source;
  }

  public void setSource(String source)
  {
    this.source = source;
  }

  public String getDestination()
  {
    return destination;
  }

  public void setDestination(String destination)
  {
    this.destination = destination;
  }

  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public String getStoreId()
  {
    return storeId;
  }

  public void setStoreId(String storeId)
  {
    this.storeId = storeId;
  }

  public List<Configuration> getConfiguration()
  {
    return configuration;
  }

  public void setConfiguration(List<Configuration> configuration)
  {
    this.configuration = configuration;
  }

}
