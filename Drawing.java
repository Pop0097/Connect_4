import java.awt.*;
import java.util.*;
import javax.swing.JFrame;


public class Drawing extends Canvas {

	static int w = 7;
	static int h = 7;
	static int bottomRow = 6;
	static char[][] b = new char[w][h];
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
		g.drawLine(100, 100, 100, 800);
		g.drawLine(200, 100, 200, 800);
		g.drawLine(300, 100, 300, 800);
		g.drawLine(400, 100, 400, 800);
		g.drawLine(500, 100, 500, 800);
		g.drawLine(600, 100, 600, 800);
		 
		//vertical lines
		g.drawLine(0, 100, 700, 100);
		g.drawLine(0, 200, 700, 200);
		g.drawLine(0, 300, 700, 300);
		g.drawLine(0, 400, 700, 400);
		g.drawLine(0, 500, 700, 500);
		g.drawLine(0, 600, 700, 600);
		g.drawLine(0, 700, 700, 700);
		  
		//draw border
		g.drawLine(0, 100, 000, 800);
		g.drawLine(700, 100, 700, 800);
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
		
		//title
		g.setColor(Color.red);
		g.setFont(new Font("TimesRoman", 1, 40));
		g.drawString("CONNECT4PALOOZA!", 125, 50);
		
		//print all the pieces
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
		
		//if the game ends, the code prints the results depending on the variable winner. 
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
