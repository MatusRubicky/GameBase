package sk.upjs.ics.GameBase.Entity;

import java.util.LinkedList;
import java.util.List;

public class Studio {
    
    private long ID = -1;
    private String name;
    private List<Game> listOfGames = new LinkedList<>();

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Game> getListOfGames() {
        return listOfGames;
    }
    
    public void addGame(Game game){
        this.listOfGames.add(game);
    }

    @Override
    public String toString() {
        return new StringBuilder().append(name).toString();
    }
    
    
}
