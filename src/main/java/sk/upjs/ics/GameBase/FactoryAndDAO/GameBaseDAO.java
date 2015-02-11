package sk.upjs.ics.GameBase.FactoryAndDAO;

import sk.upjs.ics.GameBase.Entity.Game;
import sk.upjs.ics.GameBase.Entity.Tag;
import sk.upjs.ics.GameBase.Entity.Category;
import java.util.List;

public interface GameBaseDAO {

    List<Game> listAllGames();

    List<Game> listAllGamesТitled(String title);

    List<Game> listAllGamesTagged(List<Tag> selectedTags);

    List<Game> listMostSimilarGames(long ID);

    List<Tag> getTagsForGame(long gameID);

    List<Tag> getTagsForGameInCategory(Category category, long gameID);

    List<Tag> getAllTagsInCategory(Category category);

    List<Category> getCategories();

    Game getGame(long ID);
    
    void saveGame(Game game);
    
    void saveTag(Tag tag);

    void saveAllTags(List<Tag> tags, long gameID);

    void deleteTags(long ID);

    long getLastIDFromGames();

}
