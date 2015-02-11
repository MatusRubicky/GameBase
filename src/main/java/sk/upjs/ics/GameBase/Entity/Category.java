package sk.upjs.ics.GameBase.Entity;

public class Category {

    private long ID;
    private String CategoryName;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    @Override
    public String toString() {
        return getCategoryName();
    }
}
