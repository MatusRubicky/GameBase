package sk.upjs.ics.GameBase.FactoryAndDAO;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.GameBase.Entity.Game;
import sk.upjs.ics.GameBase.Entity.Studio;

/**
 *
 * @author Uživateľ
 */
public class DatabaseStudioDAOTest {
    
    private JdbcTemplate jdbcTemplate;
    private StudioDAO studioDAO = DaoFactory.INSTANCE.getStudioDAO();
    
    public DatabaseStudioDAOTest() {
    }
    
    @Before
    public void setUp() {
        SQLServerDataSource dataSource = new SQLServerDataSource();

        dataSource.setURL("jdbc:sqlserver://localhost\\K55V\\SQLEXPRESSJP:50883;databaseName=GameBase");
        dataSource.setUser("paz1csp");
        dataSource.setPassword("a");

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Test of getStudio method, of class DatabaseStudioDAO.
     */
    @Test
    public void testGetStudio_String() {
        System.out.println("getStudio");
       
        String testName = "testName";
        Studio studio = new Studio();
        studio.setName(testName);
        jdbcTemplate.update("INSERT INTO Studio(name) VALUES(?)", testName);
                
        Studio result = studioDAO.getStudio(testName);
        jdbcTemplate.update("delete from Studio where name=?", testName);
        assertNotNull(result);
    }

    /**
     * Test of getNumberOfStudios method, of class DatabaseStudioDAO.
     */
    @Test
    public void testGetNumberOfStudios() {
        System.out.println("getNumberOfStudios");
        
        int expResult = 1;
        int result = studioDAO.getNumberOfStudios("Valve");
        assertEquals(expResult, result);
    }

    /**
     * Test of getListOfGames method, of class DatabaseStudioDAO.
     */
    @Test
    public void testGetListOfGames() {
        System.out.println("getListOfGames");
        DatabaseStudioDAO instance = new DatabaseStudioDAO();
        int expResult = 1;
        int result = studioDAO.getListOfGames(14).size(); //valve
        assertEquals(expResult, result);
    }

    /**
     * Test of checkGame method, of class DatabaseStudioDAO.
     */
    @Test
    public void testCheckGame() {
        System.out.println("checkGame");
        long studioID = 14; //Valve
        long gameID = 0; //testID z predchadzajuceho testu
        DatabaseStudioDAO instance = new DatabaseStudioDAO();
        boolean expResult = false;
        boolean result = instance.checkGame(studioID, gameID);
        
        jdbcTemplate.update("DELETE FROM StudiosGames WHERE GameID=0");
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllStudios method, of class DatabaseStudioDAO.
     */
    @Test
    public void testGetAllStudios() {
        System.out.println("getAllStudios");
        DatabaseStudioDAO instance = new DatabaseStudioDAO();
        int expResult = 6;
        List<Studio> result = studioDAO.getAllStudios();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of deleteGameFromStudio method, of class DatabaseStudioDAO.
     */
    @Test
    public void testDeleteGameFromStudio() {
        System.out.println("deleteGameFromStudio");
        long studioID = 14;
        long gameID = 0;
        studioDAO.deleteGameFromStudio(studioID, gameID);
        assertEquals(0, jdbcTemplate.queryForInt("SELECT COUNT(*) FROM StudiosGames WHERE StudioID=14 AND GameID=0"));
    }
    
}
