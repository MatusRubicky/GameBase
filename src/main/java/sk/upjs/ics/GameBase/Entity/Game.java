package sk.upjs.ics.GameBase.Entity;

public class Game {

    private long ID;
    private String Title;
    private String GameDesc;
    private String Studio;
    private String ReleaseDate;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getGameDesc() {
        return GameDesc;
    }

    public void setGameDesc(String GameDesc) {
        this.GameDesc = GameDesc;
    }

    public String getStudio() {
        return Studio;
    }

    public void setStudio(String Studio) {
        this.Studio = Studio;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String ReleaseDate) {
        this.ReleaseDate = ReleaseDate;
    }

    @Override
    public String toString() {
        return "(" + getID() + ") Nadpis:" + getTitle() + " Štúdio:" + getStudio() + " Dátum vydania:" + getReleaseDate();
    }
}
