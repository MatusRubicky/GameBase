package sk.upjs.ics.GameBase.Other;

import sk.upjs.ics.GameBase.FactoryAndDAO.GameBaseDAO;
import sk.upjs.ics.GameBase.FactoryAndDAO.DaoFactory;
import sk.upjs.ics.GameBase.Entity.Game;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jakub
 */
public class GameTableModel extends AbstractTableModel {
    
    private final GameBaseDAO gamebaseDao = DaoFactory.INSTANCE.getGameBaseDAO();
    private List<Game> games = new LinkedList<>();
    private static final Class[] COLUMN_TYPE={Long.class,String.class,String.class,String.class};
    private static final String[] COLUMN_NAME = {"ID", "Názov", "Štúdio","Dátum"};

    @Override
    public int getRowCount() {
        return games.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAME.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Game selectedGame = games.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return selectedGame.getID();
            case 1:
                return selectedGame.getTitle();
            case 2:
                return selectedGame.getStudio();
            case 3:
                return selectedGame.getReleaseDate();
            default:
                return "???";

        }
    }
    
    @Override
    public String getColumnName(int column) {
        return COLUMN_NAME[column];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return COLUMN_TYPE[columnIndex];
    }
    
    public void refresh() {
        games = gamebaseDao.listAllGames();
        fireTableDataChanged();
    }
    
    public void refresh(String filter) {
        games = gamebaseDao.listAllGamesТitled(filter);
        fireTableDataChanged();
    }
    
}
