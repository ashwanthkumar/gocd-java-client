package in.ashwanthkumar.gocd.client.types;

import com.google.gson.annotations.SerializedName;
import in.ashwanthkumar.gocd.client.GoCD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaterialRevision {
    static private final Pattern PIPELINE_REVISION_PATTERN =
            Pattern.compile("^([^/]+)/(\\d+)/.*");
    static private final Pattern GITHUB_MATERIAL_PATTERN =
            Pattern.compile("^URL: git@github\\.com:(.+)\\.git,.*");

    private Logger LOG = LoggerFactory.getLogger(MaterialRevision.class);

    @SerializedName("changed")
    private boolean changed;

    @SerializedName("material")
    private Material material;

    @SerializedName("modifications")
    private List<Modification> modifications;

    public MaterialRevision(boolean changed, Material material, List<Modification> modifications) {
        this.changed = changed;
        this.material = material;
        this.modifications = modifications;
    }

    public MaterialRevision() {
    }

    public boolean isChanged() {
        return changed;
    }

    public Material getMaterial() {
        return material;
    }

    public List<Modification> getModifications() {
        return modifications;
    }

    /**
     * Is this revision a pipeline, or something else (generally a commit
     * to a version control system)?
     */
    public boolean isPipeline() {
        return material.isPipeline();
    }

    /**
     * Return a URL pointing to more information about one of our
     * modifications, if we can figure out how to generate one.  It's an
     * error to call us with a modification that isn't part of this
     * MaterialRevision.  (This is implemented this way because
     * Modification objects are deserialized without any back-pointers to
     * the containing MaterialRevision.)
     */
    public String modificationUrl(Modification modification) {
        if (!material.getType().equals("Git") || material.getDescription() == null
                || modification.getRevision() == null) {
            LOG.info(String.format("Can't build URL for modification (%s)/(%s)/(%s)",
                    material.getType(), material.getDescription(),
                    modification.getRevision()));
            return null;
        }

        // Parse descriptions like:
        // "URL: git@github.com:faradayio/marius.git, Branch: master"
        Matcher matcher = GITHUB_MATERIAL_PATTERN.matcher(material.getDescription());
        if (!matcher.matches()) {
            LOG.info("Can't build URL for non-GitHub repo: " + material.getDescription());
            return null;
        }
        String org_and_repo = matcher.group(1);

        // Shorten our commit ID.
        String commit = modification.getRevision();
        if (commit.length() > 6)
            commit = commit.substring(0, 6);

        return "https://github.com/" + org_and_repo + "/commit/" + commit;
    }


    /**
     * Collect all changed MaterialRevision objects, walking changed
     * "Pipeline" objects recursively instead of including them directly.
     */
    void addChangesRecursively(GoCD client, List<MaterialRevision> outChanges)
            throws MalformedURLException, IOException {
        // Give up now if this material hasn't changed.
        if (!changed) {
            return;
        }

        if (!isPipeline()) {
            // Add this change if somebody hasn't added it already (which
            // can happen in complex pipelines).
            if (!outChanges.contains(this))
                outChanges.add(this);
        } else {
            // Recursively walk pipeline.  We're not entirely sure what it
            // would mean to have multiple associated modifications with
            // isPipeline is true, so we walk all of them just to be on the
            // safe side.
            for (Modification m : modifications) {
                // Parse out the pipeline info.
                Matcher matcher = PIPELINE_REVISION_PATTERN.matcher(m.getRevision());
                if (matcher.matches()) {
                    String pipelineName = matcher.group(1);
                    int pipelineCounter = Integer.parseInt(matcher.group(2));

                    // Fetch the pipeline and walk it recursively.
                    Pipeline pipeline = client.pipelineInstance(pipelineName, pipelineCounter);
                    pipeline.addChangesRecursively(client, outChanges);
                } else {
                    LOG.error("Error matching pipeline revision: " + m.getRevision());
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaterialRevision that = (MaterialRevision) o;

        if (changed != that.changed) return false;
        if (LOG != null ? !LOG.equals(that.LOG) : that.LOG != null) return false;
        if (material != null ? !material.equals(that.material) : that.material != null) return false;
        return !(modifications != null ? !modifications.equals(that.modifications) : that.modifications != null);

    }

    @Override
    public int hashCode() {
        int result = LOG != null ? LOG.hashCode() : 0;
        result = 31 * result + (changed ? 1 : 0);
        result = 31 * result + (material != null ? material.hashCode() : 0);
        result = 31 * result + (modifications != null ? modifications.hashCode() : 0);
        return result;
    }
}
