package sk.upjs.ics.GameBase.Entity;

import java.util.Objects;

public class Tag {

    private long ID;
    private long CategoryID;
    private String TagName;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(long CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getTagName() {
        return TagName;
    }

    public void setTagName(String TagName) {
        this.TagName = TagName;
    }

    @Override
    public String toString() {
        return getTagName();
    }
//pokusne metody
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tag)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        return getTagName().equals(((Tag) obj).getTagName());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.getTagName());
        return hash;
    }
}
