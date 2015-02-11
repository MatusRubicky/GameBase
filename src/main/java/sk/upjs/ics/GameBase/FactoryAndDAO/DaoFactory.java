package sk.upjs.ics.GameBase.FactoryAndDAO;

import java.util.List;
import sk.upjs.ics.GameBase.Entity.Category;

public enum DaoFactory {

    INSTANCE;

    GameBaseDAO gameBaseDAO;
    StudioDAO studioDAO;
    List<Category> categories;

    public GameBaseDAO getGameBaseDAO() {
        if (gameBaseDAO == null) {
            gameBaseDAO = new DatabaseGameBaseDAO();
        }

        return gameBaseDAO;
    }
    
    public StudioDAO getStudioDAO(){
        if (studioDAO == null){
            studioDAO = new DatabaseStudioDAO();
        }
        
        return studioDAO;
    }

    public List<Category> getCategories() {
        if (categories == null) {
            categories = getGameBaseDAO().getCategories();
        }

        return categories;
    }
}
