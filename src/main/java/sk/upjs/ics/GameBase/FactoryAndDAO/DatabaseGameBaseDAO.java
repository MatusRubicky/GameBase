package sk.upjs.ics.GameBase.FactoryAndDAO;

import sk.upjs.ics.GameBase.Entity.Game;
import sk.upjs.ics.GameBase.Entity.Tag;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.GameBase.Entity.Category;

public class DatabaseGameBaseDAO implements GameBaseDAO {

    private final JdbcTemplate jdbcTemplate;
    private final BeanPropertyRowMapper<Game> gameMapper = new BeanPropertyRowMapper<>(Game.class);
    private final BeanPropertyRowMapper<Tag> tagMapper = new BeanPropertyRowMapper<>(Tag.class);
    private final BeanPropertyRowMapper<Category> categoryMapper = new BeanPropertyRowMapper<>(Category.class);

    public DatabaseGameBaseDAO() {
        
        
        SQLServerDataSource dataSource = new SQLServerDataSource();
        
        
       // localhost run
       
        dataSource.setURL("jdbc:sqlserver://localhost\\K55V\\SQLEXPRESSJP:50883;databaseName=GameBase");
        dataSource.setUser("paz1csp");
        dataSource.setPassword("a");
        
        
        
        
        //normal run
        /*dataSource.setURL("jdbc:sqlserver://SQL5004.Smarterasp.net;Initial Catalog=DB_9BABA4_gamebase");
        dataSource.setUser("DB_9BABA4_gamebase_admin");
        dataSource.setPassword("chlebarohlik");*/
        
        
        //test run
        /*
        dataSource.setURL("jdbc:sqlserver://SQL5004.Smarterasp.net;Initial Catalog=DB_9BABAA_gamebasetest");
        dataSource.setUser("DB_9BABAA_gamebasetest_admin");
        dataSource.setPassword("chlebarohlik");
        */
        
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Game> listAllGames() {
        return jdbcTemplate.query("SELECT * FROM Game", gameMapper);
    }

    @Override
    public List<Game> listAllGamesТitled(String title) {
        title = title.trim();
        title = "%" + title + "%";
        return jdbcTemplate.query("SELECT * FROM Game where Title LIKE ?", gameMapper, title);
    }

    //Databaza obsahuje vlastnu funkciu MostSimilarGames
    @Override
    public List<Game> listMostSimilarGames(long gameID) {
        return jdbcTemplate.query("SELECT * FROM MostSimilarGames(?) order by matchCount desc", gameMapper, gameID);
    }

    @Override
    public List<Tag> getTagsForGame(long gameID) {
        return jdbcTemplate.query("select ID,CategoryID,TagName from Tag join RelayTable on Tag.ID=RelayTable.TagID where GameID=?", tagMapper, gameID);
    }

    @Override
    public List<Tag> getTagsForGameInCategory(Category category, long gameID) {
        return jdbcTemplate.query("select ID,CategoryID,TagName from Tag join RelayTable on Tag.ID=RelayTable.TagID where GameID=? AND Tag.CategoryID=?", tagMapper, gameID, category.getID());
    }

    @Override
    public List<Tag> getAllTagsInCategory(Category category) {
        return jdbcTemplate.query("SELECT * FROM Tag where CategoryID = ?", tagMapper, category.getID());
    }

    @Override
    public List<Category> getCategories() {
        return jdbcTemplate.query("SELECT * FROM Category", categoryMapper);
    }

    @Override
    public Game getGame(long ID) {
        List<Game> game = jdbcTemplate.query("SELECT * FROM Game where ID = ?", gameMapper, ID);
        return game.get(0);
    }

    //Pri ukladani hry ide bud o uz existujucu hru alebo novu, nova hra nema pridelene ID a defaultne je -1
    @Override
    public void saveGame(Game game) {
        if (game.getID() == -1) {
            jdbcTemplate.update("INSERT INTO Game VALUES(?,?,?,?)", game.getTitle(), game.getGameDesc(), game.getStudio(), game.getReleaseDate());
        } else {
            jdbcTemplate.update("UPDATE Game SET Title=?,GameDesc=?,Studio=?,ReleaseDate=? WHERE ID=? ", game.getTitle(), game.getGameDesc(), game.getStudio(), game.getReleaseDate(), game.getID());
        }
    }

    @Override
    public void saveTag(Tag tag) {
        jdbcTemplate.update("INSERT INTO Tag VALUES(?,?)", tag.getCategoryID(), tag.getTagName());
    }

    @Override
    public void saveAllTags(List<Tag> tags, long gameID) {
        for (Tag tag : tags) {
            jdbcTemplate.update("insert into RelayTable values(?,?);", gameID, tag.getID());
        }
    }

    @Override
    public void deleteTags(long ID) {
        jdbcTemplate.update("DELETE FROM RelayTable WHERE GameID=?", ID);
    }

    //Vrati najvyssie ID v databaze, pouziva sa pri zistovani ake ID dostala prave ulozena hra
    @Override
    public long getLastIDFromGames() {
        return jdbcTemplate.queryForObject("select top 1 ID from Game order by ID desc", Long.class);
    }

    @Override
    public List<Game> listAllGamesTagged(List<Tag> selectedTags) {
        List<Game> foundGames = new ArrayList<>();
        List<Tag> gameTags;

        for (Game game : listAllGames()) {
            //zo zaciatku je nastavene ze hru pridavame. pokial sa zisti ze hra nevyhovuje,nastavi sa na false
            boolean pridavame = true;

            //vsetky tagy hry game dame do list<tag> gameTags
            gameTags = new ArrayList<>();

            gameTags.addAll(getTagsForGame(game.getID()));

            //kazdy tag z vybranych tagov(teda kazdy tag ktory ma splnat) porovname s kazdym tagom hry game
            for (Tag tg : selectedTags) {
                //match je nastavena na false lebo zhoda sa este nenasla
                boolean matchFound = false;
                for (Tag tg2 : gameTags) {
                    //nasla sa zhoda cez zostavajuce tagy uz dalej nema zmysel prechadzat
                    if (tg.equals(tg2)) {
                        matchFound = true;
                        break;
                    }
                }
                //pokial pozadovany tag dana hra neobsahuje,teda zhoda sa nenasla, tak tu hru do vysledku nepridavame
                // a nema zmysel pokracovat v prechadzani dalsich tagov preto break
                if (matchFound == false) {
                    pridavame = false;
                    break;
                }

            }
            if (pridavame) {
                foundGames.add(game);
            }

        }

        return foundGames;
    }



}
