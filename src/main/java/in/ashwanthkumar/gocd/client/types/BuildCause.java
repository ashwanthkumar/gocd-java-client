package in.ashwanthkumar.gocd.client.types;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BuildCause {
    @SerializedName("approver")
    private String approver;

    @SerializedName("trigger_forced")
    private boolean triggerForced;

    @SerializedName("trigger_message")
    private String triggerMessage;

    @SerializedName("material_revisions")
    private List<MaterialRevision> materialRevisions;

    public BuildCause(String approver, boolean triggerForced, String triggerMessage, List<MaterialRevision> materialRevisions) {
        this.approver = approver;
        this.triggerForced = triggerForced;
        this.triggerMessage = triggerMessage;
        this.materialRevisions = materialRevisions;
    }

    public BuildCause() {
    }

    public String getApprover() {
        return approver;
    }

    public boolean isTriggerForced() {
        return triggerForced;
    }

    public String getTriggerMessage() {
        return triggerMessage;
    }

    public List<MaterialRevision> getMaterialRevisions() {
        return materialRevisions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BuildCause that = (BuildCause) o;

        if (triggerForced != that.triggerForced) return false;
        if (approver != null ? !approver.equals(that.approver) : that.approver != null) return false;
        if (triggerMessage != null ? !triggerMessage.equals(that.triggerMessage) : that.triggerMessage != null)
            return false;
        return !(materialRevisions != null ? !materialRevisions.equals(that.materialRevisions) : that.materialRevisions != null);

    }

    @Override
    public int hashCode() {
        int result = approver != null ? approver.hashCode() : 0;
        result = 31 * result + (triggerForced ? 1 : 0);
        result = 31 * result + (triggerMessage != null ? triggerMessage.hashCode() : 0);
        result = 31 * result + (materialRevisions != null ? materialRevisions.hashCode() : 0);
        return result;
    }
}
