package sk.upjs.ics.GameBase.FactoryAndDAO;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import sk.upjs.ics.GameBase.Entity.Game;
import sk.upjs.ics.GameBase.Entity.Studio;

class DatabaseStudioDAO implements StudioDAO {

    private final JdbcTemplate jdbcTemplate;
    private final BeanPropertyRowMapper<Studio> studioMapper = new BeanPropertyRowMapper<>(Studio.class);
    private final BeanPropertyRowMapper<Game> gameMapper = new BeanPropertyRowMapper<>(Game.class);

    public DatabaseStudioDAO() {
        SQLServerDataSource dataSource = new SQLServerDataSource();

        dataSource.setURL("jdbc:sqlserver://localhost\\K55V\\SQLEXPRESSJP:50883;databaseName=GameBase");
        dataSource.setUser("paz1csp");
        dataSource.setPassword("a");

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * vráti Object Studio podla ID
     * @param ID
     * @return 
     */
    @Override
    public Studio getStudio(long ID) {
        List<Studio> list = jdbcTemplate.query("SELECT * FROM Studio WHERE ID=?", studioMapper, ID);

        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }
    
    /**
     * vráti Object Studio podla mena
     * @param name
     * @return 
     */
    @Override
    public Studio getStudio(String name) {
        List<Studio> list = jdbcTemplate.query("SELECT * FROM Studio WHERE name=?", studioMapper, name);

        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }
    
    @Override
    public int getNumberOfStudios(String studio){
        return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM Studio WHERE name=?", studio);
    }

    @Override
    public List<Game> getListOfGames(long StudioID) {
        return jdbcTemplate.query("SELECT * FROM StudiosGames S left JOIN Game G ON (S.GameID=G.ID) WHERE S.StudioID=?", gameMapper, StudioID);
    }

    @Override
    public void addStudio(Studio studio) {
        int num = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM Studio WHERE name=?", studio.getName());
        if (studio.getID() == -1 && num == 0) {
            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
            Map<String, Object> studioValues = new HashMap<>();
            studioValues.put("name", studio.getName());
            
            insert.setGeneratedKeyName("ID");
            insert.setTableName("Studio");
            Number id = insert.executeAndReturnKey(studioValues);
            studio.setID(id.longValue());
            
            if (!studio.getListOfGames().isEmpty()){
                for (Game  game : studio.getListOfGames()) {
                    jdbcTemplate.update("INSERT INTO StudiosGames(StudioID, GameID) VALUES(?, ?)",studio.getID(), game.getID());
                }
            }
        }
    }

    @Override
    public void addGameToStudio(long studioID, long gameID) {
        jdbcTemplate.update("INSERT INTO StudiosGames(StudioID, GameID) VALUES(?, ?)", studioID, gameID);
    }

    @Override
    public boolean checkGame(long studioID, long gameID) {
        return (jdbcTemplate.queryForInt("SELECT COUNT(*) FROM StudiosGames WHERE StudioID=? AND GameID=?", studioID, gameID) != 0);
    }

    @Override
    public List<Studio> getAllStudios() {
        return jdbcTemplate.query("SELECT * FROM Studio", studioMapper);
    }

    @Override
    public void deleteGameFromStudio(long studioID, long gameID) {
        jdbcTemplate.update("DELETE FROM StudiosGames WHERE StudioID=? AND GameID=?", studioID, gameID);
    }

}
