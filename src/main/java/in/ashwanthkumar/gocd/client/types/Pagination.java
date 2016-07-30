package in.ashwanthkumar.gocd.client.types;

import com.google.gson.annotations.SerializedName;

public class Pagination {
    @SerializedName("offset")
    private int offset;

    @SerializedName("total")
    private int total;

    @SerializedName("page_size")
    private int pageSize;

    public Pagination(int offset, int total, int pageSize) {
        this.offset = offset;
        this.total = total;
        this.pageSize = pageSize;
    }

    public Pagination() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pagination that = (Pagination) o;

        if (offset != that.offset) return false;
        if (total != that.total) return false;
        return pageSize == that.pageSize;
    }

    @Override
    public int hashCode() {
        int result = offset;
        result = 31 * result + total;
        result = 31 * result + pageSize;
        return result;
    }
}
