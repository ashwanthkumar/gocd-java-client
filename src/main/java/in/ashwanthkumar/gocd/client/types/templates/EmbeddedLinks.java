package in.ashwanthkumar.gocd.client.types.templates;

import com.google.gson.annotations.SerializedName;

public class EmbeddedLinks
{

  @SerializedName("self")
  private EmbeddedLink self;

  @SerializedName("doc")
  private EmbeddedLink doc;

  @SerializedName("find")
  private EmbeddedLink find;

  public EmbeddedLink getSelf()
  {
    return self;
  }

  public void setSelf(EmbeddedLink self)
  {
    this.self = self;
  }

  public EmbeddedLink getDoc()
  {
    return doc;
  }

  public void setDoc(EmbeddedLink doc)
  {
    this.doc = doc;
  }

  public EmbeddedLink getFind()
  {
    return find;
  }

  public void setFind(EmbeddedLink find)
  {
    this.find = find;
  }

  
}
