package sk.upjs.ics.GameBase.FactoryAndDAO;

import java.util.List;
import sk.upjs.ics.GameBase.Entity.Game;
import sk.upjs.ics.GameBase.Entity.Studio;

public interface StudioDAO {
    
    public Studio getStudio(long ID);
    
    public Studio getStudio(String name);
    
    public int getNumberOfStudios(String studio);
    
    public List<Game> getListOfGames(long StudioID);
    
    public void addStudio(Studio studio);
    
    public void addGameToStudio(long studioID, long gameID);

    public boolean checkGame(long studioID, long gameID);

    public List<Studio> getAllStudios();

    public void deleteGameFromStudio(long studioID, long gameID);
}
