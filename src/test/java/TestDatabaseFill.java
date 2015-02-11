
import java.util.ArrayList;
import java.util.List;
import sk.upjs.ics.GameBase.Entity.Game;
import sk.upjs.ics.GameBase.Entity.Tag;
import sk.upjs.ics.GameBase.FactoryAndDAO.DaoFactory;
import sk.upjs.ics.GameBase.FactoryAndDAO.GameBaseDAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ew
 */

//
//Treba v GameBaseDAO zmenit normal run na test run
//
public class TestDatabaseFill {

    
    private static final GameBaseDAO gamebaseDao = DaoFactory.INSTANCE.getGameBaseDAO();
    private static List<Game> listOfGames = new ArrayList<Game>();
    private static Game g1;
    private static Game g2;
    private static Game g3;
    private static Game g4;
    private static Game g5;
    
    static void fillupTable() {
        
        //STARE , NESPUSTAT , NEMENIT DATA V TESTOVACEJ DATABAZE PROSIM
        
        /*
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

        gamebaseDao.saveGame(g1);
        gamebaseDao.saveGame(g2);
        gamebaseDao.saveGame(g3);
        gamebaseDao.saveGame(g4);
        gamebaseDao.saveGame(g5);

        Tag t1 = new Tag();
        t1.setCategoryID(1);
        t1.setID(3);
        Tag t2 = new Tag();
        t2.setCategoryID(4);
        t2.setID(15);
        
        List<Tag> tagList = new ArrayList<>();
        
        tagList.add(t1);
        tagList.add(t2);
        
        gamebaseDao.saveAllTags(tagList, 4);
        gamebaseDao.saveAllTags(tagList, 5);
        tagList.clear();
        tagList.add(t2);
        gamebaseDao.saveAllTags(tagList, 1);
        
        */
        
    }
    
    public static void main(String[] args){
        
     fillupTable();
    
    }
}
