import java.util.*;
import javax.swing.JFrame;
import java.awt.*;

public class Main {

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		int width = 7;
		int height = 7;
		int bottomR = 6;
		int turns = 0;
		int lastPlay = 10;
		int player = 0;

		//initializes the frame for the graphics.
		JFrame frame = new JFrame("CONNECT4PALOOZA - Dhruv Rawat");
	    Canvas canvas = new Drawing();

		System.out.println("Welcome to CONNECT4PALOOZA! The best game of Connect 4 on the internet!");
		System.out.println();

		//array in which all information is stored
		char[][] board = new char[width][height];
		//initializes all indices in array
		for(int i = 0; width > i; i++) {
			for(int j = 0; height > j; j++) {
				board[i][j] = '_';
			}
		}

		//prints the board
		print(board,frame, canvas, lastPlay, width, height);

		boolean continueGame = true;
		while(continueGame) {
			//calls method so Player 1 can place a piece
			player = 1;
			place(board, width, height, bottomR, player); //this method gets the input from the player and places a piece down in the appropriate spot.
			print(board, frame, canvas, lastPlay, width, height);
			turns++;

			if(!playerCheck(board, width, height, player)) { //if Player1 has won, then game is over
				lastPlay = 1; //sets this so program knows Player 1 has won
				continueGame = false;
				break;

			}
			if(turns == 49) { //if all turns are done, then game terminates

				continueGame = false;
				break;

			}

			//calls method so Player 2 can place a piece
			player = 2;
			place(board, width, height, bottomR, player);
			print(board, frame, canvas, lastPlay, width, height);
			turns++;


			if(!playerCheck(board, width, height, player) || turns == 49) {//if Player 2 has won, then game is over

				lastPlay = 2; //sets this so program knows Player 2 has won
				continueGame = false;
				break;

			}
			if(turns == 49) {//if all turns are done, then game terminates

				continueGame = false;
				break;

			}
		}

		//determines winner based on who played the last play
		if(lastPlay == 10) {
			lastPlay = 0;
			print(board, frame, canvas, lastPlay, width, height);
			System.out.println("It is a draw!");
		}
		else if(lastPlay == 1) {
			print(board, frame, canvas, lastPlay, width, height);
			System.out.println("Player 1 wins!");
		}
		else {
			print(board, frame, canvas, lastPlay, width, height);
			System.out.println("Player 2 wins!");
		}
	}

	public static boolean inputCheck(String playerInput) { //checks if input is valid
		boolean valid = false;
		int ACSII = 0;
		if(playerInput.length() == 1) { //checks if the length is only one character
			ACSII = (int) playerInput.charAt(0);
			if(ACSII >= 49 && ACSII <= 55) { //checks if ACSII value of character matches accepted values of 1 to 5
				valid = true; //states condition as true so loop will terminate
			}
		}
		return valid;
	}

	//parameters w and h will only be used if game is text-based. Board, frame and canvas will only be used if graphics are used
	 public static void print(char[][] board, JFrame frame, Canvas canvas, int lastPlay, int w, int h) {
		 /*Part 1: this code is used for the text-based version of the game.*/
		 for (int i = 0; w > i; i += 1) {
			 for (int j = 0; h > j; j += 1) {
				 System.out.print(board[i][j] + " ");
			 }
			 System.out.println();
		 }

		 System.out.println("1 2 3 4 5 6 7");
		 System.out.println();

		 /*Part 2: this code is used for the graphics version of the game*/
		 ((Drawing) canvas).addArray(board, lastPlay);//sends array to the Drawing class file
		 canvas.setSize(702, 860);	//sizes the screen
		 frame.add(canvas);
		 frame.pack();
		 frame.setVisible(true); //sets screen as visible
	 }

	 public static char[][] place(char[][] board, int w, int h, int bottomR, int p){
		 int counter = 1;
		 int inputCounter = 0;
		 int trial = 0;

		 if(p == 1) {//distinguishes between the player that is playing
		 	 System.out.println("Player 1 turn. Input a number between 1 and 7.");
 		 }
 		 else {
		 	 System.out.println("Player 2 turn. Input a number between 1 and 7.");
		 }

		 boolean inputValid = false;
		 String userInput = "";
		 boolean turnNotDone = true;

		 while(turnNotDone) {
			 if(trial == 0 || trial > 1 && counter == 0) {//ensures that inputs is only taken when necessary
				 trial++; //after every trial for a player, the value increases
				 inputValid = false;
				 inputCounter = 0;

				 while(inputValid == false) {//while the input is not valid
				 	if(inputCounter > 0) { //if input was incorrect
						 System.out.println("Invalid input. Please input an integer between 1 and 7.");
				 	 }
					 userInput = input.next();
					 inputValid = inputCheck(userInput); //checks if input is valid
					 inputCounter++;
				 }
			 }

			 int c = Integer.valueOf(userInput) - 1; //converts string input into an integer

			 //finds appropriate spot on grid and places player's counter
			 if(counter < h) {
			 	 if(board[bottomR][c] == '_') {//if the bottom row is empty
					 if(p == 1) { //checks which player is playing
					     board[bottomR][c] = 'X';
					     turnNotDone = false;
					 }
					 else {
						 board[bottomR][c] = 'O';
						 turnNotDone = false;
					 }
				 }
				 else if(board[bottomR][c] == 'X' || board[bottomR][c] == 'O'){ //if the bottom row is full
					 if(board[bottomR - counter][c] == '_'){ //increments up one row until an empty spot is found
					 	if(p == 1) {
					 		  board[bottomR - counter][c] = 'X';
							  turnNotDone = false;
						 }
						 else {
							  board[bottomR - counter][c] = 'O';
							  turnNotDone = false;
						 }
	                 }
	             }
			 }
			 counter++; 	//with every failed attempt to find an empty spot in the row, the counter increases
			 if(counter == w) { //if column is full, the player is prompted to input another value
				 System.out.println("Please select a different column. The one you selected is full");
				 counter = 0;
				 trial++;
			 }
		 }
		 return board;
	 }

	 public static boolean playerCheck(char[][] b, int w, int h, int p) {
		 boolean result = true;
		 //checks all possible winning conditions and see ig they are present
		 if(!verticalCheck(b,w,h, p) || !horizontalCheck(b,w,h, p) || !forwardDiagonalCheck(b,w,h, p) || !backwardDiagonalCheck(b,w,h, p)) {
			 result = false;
		 }
		 return result;
	 }

	 public static boolean verticalCheck(char[][] board, int w, int h, int p) {
		 boolean result = true;
		 int counter = 0;

		 //goes through the board vertically
		 for(int i = 0; w > i; i += 1){
			 for(int j = 0; h > j; j += 1){
				 if(p == 1) {//distinguishes between the player playing
					 if(board[j][i] == 'X'){
						 counter++;
						 if(counter == 4){
							 result = false;
						 }
					 }
					 else {
						 counter = 0;
					 }
				 }
				 else {
					 if(board[j][i] == 'O'){
						 counter++;
						 if(counter == 4) {
							 result = false;
						 }
					 }
					 else {
						 counter = 0;
					 }
				 }
			 }
		 }
		 return result;
	 }

	 public static boolean horizontalCheck(char[][] board, int w, int h, int p) {
		 boolean result = true;
		 int counter = 0;

		 //goes through board horizontally
		 for(int i = 0; w > i; i += 1){
			 for(int j = 0; h > j; j += 1){
				 if(p == 1) {//distinguishes between player playing
					 if(board[i][j] == 'X'){ //if it finds an X, add 1 to counter
						 counter++;
						 if(counter == 4){
							 result = false;
						 }
					 }
					 else{
						 counter = 0; // if next piece is not an X, set counter to 0
					 }
				 }
				 else {
					 if(board[i][j] == 'O'){ //if it finds an X, add 1 to counter
						 counter++;
						 if(counter == 4){
							 result = false;
						 }
					 }
					 else{
						 counter = 0; // if next piece is not an X, set counter to 0
					 }
				 }
			 }
		 }
		 return result;
	 }

	 public static boolean forwardDiagonalCheck(char[][] board, int w, int h, int p) {
		 boolean result = true;
		 int counter = 0;
		 int checkColumn = 1;
		 int checkRow = 1;
		 boolean check = false;

		 for(int i = 0; i < 4; i++) {
			 for(int j = 0; j < 4; j++) {
				 if(p == 1) {//distinguishes between player playing
					 if(board[i][j] == 'X') {
						 counter++;
						 check = true;
						 while(check) { //checks to see if there is a possible winning combination
							 if(board[i + checkColumn][j + checkRow] == 'X') { //checks boxes diagonal
								 counter++;
								 if(counter == 4) { //if four consecutive matching pieces found, loop terminates
									 result = false;
									 check = false;
								 }
							 }
							 else { //if diagonal piece does not match, the loop terminates
								 check = false;
							 }
							 //increments these values to ensure that a row diagonal to the current one is searched next
							 checkColumn++;
							 checkRow++;
						 }
					 }
				 }
				 else {
					 if(board[i][j] == 'O') {
						 counter++;
						 check = true;
						 while(check) {
							 if(board[i + checkColumn][j + checkRow] == 'O') {
								 counter++;
								 if(counter == 4) {
									 result = false;
									 check = false;
								 }
							 }
							 else {
								 check = false;
							 }
							 checkColumn++;
							 checkRow++;
						 }
					 }
				 }
				 counter = 0;
				 checkColumn = 1;
				 checkRow = 1;
			 }
		 }
		 return result;
	 }

	 public static boolean backwardDiagonalCheck(char[][] board, int w, int h, int p) {
		 boolean result = true;
		 int counter = 0;
		 int checkColumn = 1;
		 int checkRow = 1;
		 boolean check = false;

		 for(int i = 0; i < 4; i++) {
			 for(int j = h-1; j > 2; j--) {
				 if(p == 1) { //distinguishes between player playing
					 if(board[i][j] == 'X') {
						 counter++;
						 check = true;
						 while(check) {
							 if(board[i + checkColumn][j - checkRow] == 'X') {
								 counter++;
								 if(counter == 4) {
									 result = false;
									 check = false;
								 }
							 }
							 else {
								 check = false;
							 }
							 checkColumn++;
							 checkRow++;
						 }
						 counter = 0;
						 checkColumn = 1;
						 checkRow = 1;
					 }
				 }
				 else {
					 if(board[i][j] == 'O') {
						 counter++;
						 check = true;
						 while(check) {
							 if(board[i + checkColumn][j - checkRow] == 'O') {
								 counter++;
								 if(counter == 4) {
									 result = false;
									 check = false;
								 }
							 }
							 else {
								 check = false;
							 }
							 checkColumn++;
							 checkRow++;

						 }
						 counter = 0;
						 checkColumn = 1;
						 checkRow = 1;
					 }
				 }
			 }
		 }
		 return result; //break is used in case no winning combinations detected
	 }
}
