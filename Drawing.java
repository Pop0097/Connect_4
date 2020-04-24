import java.awt.*;
import java.util.*;
import javax.swing.JFrame;

public class Drawing extends Canvas {

	static int w = 7;
	static int h = 7;
	static int bottomRow = 6;
	static char[][] b = new char[w][h]; //this array represents the board
	int winner = 10;

	//this function initializes the variables b and winner.
	public void addArray(char[][] board, int lP) {
		b = board;
		winner = lP;
	}

	public void paint(Graphics g) {

		setBackground(Color.black);

		g.setColor(Color.white);
		//horizontal lines
		for(int i = 0; i <= 700; i += 100) {
			g.drawLine(i, 100, i, 800); //horizontal lines
			g.drawLine(0, i, 700, i); //vertical lines
		}
		g.drawLine(0, 800, 700, 800);

		g.setFont(new Font("TimesRoman", 1, 24));
		g.setColor(Color.green);

		//draw text for the columns
		g.drawString("1", 45, 90);
		g.drawString("2", 145, 90);
		g.drawString("3", 245, 90);
		g.drawString("4", 345, 90);
		g.drawString("5", 445, 90);
		g.drawString("6", 545, 90);
		g.drawString("7", 645, 90);

		//Displays the game title
		g.setColor(Color.red);
		g.setFont(new Font("TimesRoman", 1, 40));
		g.drawString("CONNECT4PALOOZA!", 125, 50);

		//print all the pieces on the board
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				if(b[j][i] == 'X') {
					g.setColor(Color.red);
					g.drawOval(1 + i*100, 101 + j*100, 98, 98);
					g.fillOval(1 + i*100, 101 + j*100, 98, 98);
				}
				else if(b[j][i] == 'O') {
					g.setColor(Color.yellow);
					g.drawOval(1 + i*100, 101 + j*100, 98, 98);
					g.fillOval(1 + i*100, 101 + j*100, 98, 98);
				}
			}
		}
		//Code prints game results
		if(winner == 0) {
			g.setColor(Color.white);
			g.drawRect(100, 300, 500, 160);
			g.fillRect(100, 300, 500, 160);
			g.setColor(Color.red);
			g.setFont(new Font("TimesRoman", 1, 40));
			g.drawString("It's a DRAW!", 230, 400);

		}
		else if(winner == 1) {
			g.setColor(Color.white);
			g.drawRect(100, 300, 500, 160);
			g.fillRect(100, 300, 500, 160);
			g.setColor(Color.red);
			g.setFont(new Font("TimesRoman", 1, 40));
			g.drawString("Player 1 Wins!!!", 220, 400);
		}
		else if(winner == 2) {
			g.setColor(Color.white);
			g.drawRect(100, 300, 500, 160);
			g.fillRect(100, 300, 500, 160);
			g.setColor(Color.red);
			g.setFont(new Font("TimesRoman", 1, 35));
			g.drawString("Player 2 Wins!!!", 220, 400);
		}
	}
}
