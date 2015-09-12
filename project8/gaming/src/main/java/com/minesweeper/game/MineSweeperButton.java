package com.minesweeper.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MineSweeperButton 
    extends JButton {
    
    private static final ImageIcon EMPTY_ICON = new ImageIcon( getImage("icons/empty.jpg"));
    private static final ImageIcon MINE_ICON = new ImageIcon( getImage("icons/mine.jpg"));    
    private static final ImageIcon HIT_MINE_ICON = new ImageIcon( getImage("icons/hitMine.jpg"));
    private static final ImageIcon WRONG_MINE_ICON = new ImageIcon( getImage("icons/wrongMine.jpg"));
    private static final ImageIcon FLAG_ICON = new ImageIcon( getImage("icons/flag.jpg"));

    private static final ImageIcon[] NUMBER_ICONS = new ImageIcon[9];
    static {
	NUMBER_ICONS[0] = new ImageIcon( getImage("icons/zero.jpg"));
	NUMBER_ICONS[1] = new ImageIcon( getImage("icons/one.jpg"));
	NUMBER_ICONS[2] = new ImageIcon( getImage("icons/two.jpg"));
	NUMBER_ICONS[3] = new ImageIcon( getImage("icons/three.jpg"));
	NUMBER_ICONS[4] = new ImageIcon( getImage("icons/four.jpg"));
	NUMBER_ICONS[5] = new ImageIcon( getImage("icons/five.jpg"));
	NUMBER_ICONS[6] = new ImageIcon( getImage("icons/six.jpg"));
	NUMBER_ICONS[7] = new ImageIcon( getImage("icons/seven.jpg"));
	NUMBER_ICONS[8] = new ImageIcon( getImage("icons/eight.jpg"));
    }

    private ImageIcon curIcon;
    private int myRow;
    private int myCol;

    public MineSweeperButton(int row, int col) {
	super(EMPTY_ICON);
	
	myRow = row;
	myCol = col;

	setBorder(BorderFactory.createRaisedBevelBorder());
	setBackground(new Color(155,153,156));
    }

    public int getRow() {
	return myRow;
    }

    public int getCol() {
	return myCol;
    }

    public void setPlayingIcon(int iconNumber) {
	switch (iconNumber) {
	case MineSweeperBoard.COVERED_CELL:   curIcon = EMPTY_ICON; break;
	case MineSweeperBoard.MINE:           curIcon = EMPTY_ICON; break;
	case MineSweeperBoard.FLAG:           curIcon = FLAG_ICON; break;
	case MineSweeperBoard.FLAGGED_MINE:   curIcon = FLAG_ICON; break;
	case MineSweeperBoard.UNCOVERED_MINE: curIcon = MINE_ICON; break;
	default:                              curIcon = NUMBER_ICONS[iconNumber];
	}  

	if (curIcon != EMPTY_ICON && curIcon != FLAG_ICON) {
	    setBorder(BorderFactory.createLoweredBevelBorder());
	}

	setIcon(curIcon);
	repaint();
    }

    public void setRevealIcon(int iconNumber) {
	switch (iconNumber) {
	case MineSweeperBoard.COVERED_CELL:   curIcon = EMPTY_ICON; break;
	case MineSweeperBoard.MINE:           curIcon = MINE_ICON; break;
	case MineSweeperBoard.FLAG:           curIcon = WRONG_MINE_ICON; break;
	case MineSweeperBoard.FLAGGED_MINE:   curIcon = FLAG_ICON; break;
	case MineSweeperBoard.UNCOVERED_MINE: curIcon = HIT_MINE_ICON; break;
	default:                              curIcon = NUMBER_ICONS[iconNumber];
	}   
	
	setBorder(BorderFactory.createLoweredBevelBorder());
	setIcon(curIcon);
	repaint();
    }

public static Image getImage(final String pathAndFileName) {
    final java.net.URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
    return Toolkit.getDefaultToolkit().getImage(url);
}

}
