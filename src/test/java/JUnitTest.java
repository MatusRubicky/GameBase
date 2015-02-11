/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Locale.Category;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sk.upjs.ics.GameBase.Entity.Game;
import sk.upjs.ics.GameBase.Entity.Tag;
import sk.upjs.ics.GameBase.FactoryAndDAO.DaoFactory;
import sk.upjs.ics.GameBase.FactoryAndDAO.GameBaseDAO;

/**
 *
 * @author ew
 */
// pred junit testom Treba v GameBaseDAO zmenit normal run na test run

public class JUnitTest {

    private final GameBaseDAO gamebaseDao = DaoFactory.INSTANCE.getGameBaseDAO();
    List<Game> listOfGames = new ArrayList<Game>();
    Game g1;
    Game g2;
    Game g3;
    Game g4;
    Game g5;

    public JUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        g1 = new Game();
        g1.setTitle("Doom");
        g1.setID(-1);
        g1.setGameDesc("Akcna hra doom.");
        g1.setReleaseDate("11.10.1999");
        g1.setStudio("Studio 1");

        g2 = new Game();
        g2.setTitle("Tetris");
        g2.setID(-1);
        g2.setGameDesc("Skladacka tetrs.");
        g2.setReleaseDate("1.2.1990");
        g2.setStudio("Studio 2");

        g3 = new Game();
        g3.setTitle("Mario");
        g3.setID(-1);
        g3.setGameDesc("Skakacka Mario.");
        g3.setReleaseDate("22.12.1995");
        g3.setStudio("Studio 3");

        g4 = new Game();
        g4.setTitle("Warcraft 3");
        g4.setID(-1);
        g4.setGameDesc("Strategia Warcraft 3.");
        g4.setReleaseDate("5.7.2003");
        g4.setStudio("Blizzard");

        g5 = new Game();
        g5.setTitle("Age Of Empires");
        g5.setID(-1);
        g5.setGameDesc("Strategia AOE.");
        g5.setReleaseDate("17.5.2002");
        g5.setStudio("Microsoft");

        g1.setID(1);
        g2.setID(2);
        g3.setID(3);
        g4.setID(4);
        g5.setID(5);
        listOfGames.add(g1);
        listOfGames.add(g2);
        listOfGames.add(g3);
        listOfGames.add(g4);
        listOfGames.add(g5);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void listAllGamesTest() {
        assertEquals(listOfGames.toString(), gamebaseDao.listAllGames().toString());
    }

    @Test
    public void listAllGamesTitled() {
        assertEquals("[" + g2.toString() + "]", gamebaseDao.listAllGamesТitled("Tetris").toString());
    }

    @Test
    public void getGameTest() {
        assertEquals(g3.toString(), gamebaseDao.getGame(3).toString());
    }

    @Test
    public void listSimilarTest() {
        listOfGames.clear();
        listOfGames.add(g5);
        assertEquals(listOfGames.toString(), gamebaseDao.listMostSimilarGames(4).toString());
    }

    @Test
    public void getTagsForGameTest() {
        assertEquals("[Strategy, PC]", Arrays.toString(gamebaseDao.getTagsForGame(5).toArray()));
    }

    @Test
    public void getTagsForGameInCategoryTest() {
        for (sk.upjs.ics.GameBase.Entity.Category category : DaoFactory.INSTANCE.getCategories()) {
            if (category.getID() == 1) {
                assertEquals("[Strategy]", gamebaseDao.getTagsForGameInCategory(category, 5).toString());
            }
        }

    }
    
    @Test
    public void getAllTagsInCategoryTest(){
        for (sk.upjs.ics.GameBase.Entity.Category category : DaoFactory.INSTANCE.getCategories()) {
            if (category.getID() == 1) {
                assertEquals("[1st person, 3rd person, Strategy, Racing, Moba, Turn based]", gamebaseDao.getAllTagsInCategory(category).toString());
            }
        }

    }
    
    @Test
    public void getCategoriesTest(){
        assertEquals("[Gameplay, Multiplayer, Network, Platform, Setting]", gamebaseDao.getCategories().toString());
    }
    
    @Test
    public void deleteTag(){
        List<Tag> tagList = new ArrayList<>();
        Tag t2 = new Tag();
        t2.setCategoryID(4);
        t2.setID(15);
        tagList.add(t2);
        gamebaseDao.deleteTags(1);
        assertEquals("[]", Arrays.toString(gamebaseDao.getTagsForGame(1).toArray()));
        gamebaseDao.saveAllTags(tagList, 1);
    }
    
    @Test
    public void getLastIdFromGamesTest(){
        assertEquals(5L, gamebaseDao.getLastIDFromGames());
    }

}
