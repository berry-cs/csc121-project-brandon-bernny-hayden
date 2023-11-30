import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import processing.core.*;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LeaderBoard {
	String score;
	boolean beenThrough = false;
	boolean restart = false;


	public LeaderBoard() {

	}

	public PApplet draw(PApplet c) {
		
			c.background(0);
			c.textAlign(PConstants.CENTER, PConstants.CENTER);// Set the text alignment to center
			c.textSize(30);
			c.fill(252);
			c.fill(255);
			c.text("GAME OVER", 200, 50);


			String[] scoreList = getScores();


			int startY = 100; // Adjust the starting Y-coordinate for the scores
			int spacing = 30; // Adjust the vertical spacing between names and scores

			// Display names and scores
			for (int i = 0; i < scoreList.length; i++) {

				c.text(scoreList[i], 200, startY + i * spacing);
			}
			c.text("Press r to restart", 200, 300);
/*
		} else {
			c.background(0);
			c.textAlign(PConstants.CENTER, PConstants.CENTER);// Set the text alignment to center
			c.textSize(30);
			c.fill(252);
			c.fill(255);
			c.text("GAME OVER", 200, 50);


			String[] scoreList = getScores();


			int startY = 100; // Adjust the starting Y-coordinate for the scores
			int spacing = 30; // Adjust the vertical spacing between names and scores

			// Display names and scores
			for (int i = 0; i < scoreList.length; i++) {

				c.text(scoreList[i], 200, startY + i * spacing);
			}
			c.text("Press r to restart", 200, 300);

		}
*/


		return c;

	}

	
	/*
	 * register a new game over score on the leaderboard
	 */
	public void registerGame(String score) {
		// Prompt the user to enter their name
		String playerName = JOptionPane.showInputDialog("Enter your name:");
		if (playerName == null || (playerName != null && ("".equals(playerName.trim())))) {
			playerName = "Anonymous";
		}

		addScore(playerName, score);
	}
	
	
	public String[] getScores() {
		ArrayList<String> scoreList = new ArrayList<String>();

		try {
			Scanner sc = new Scanner(new File("leaderboard.txt"));


			while(sc.hasNext() && scoreList.size() < 5) {
				String curScore = sc.nextLine();

				scoreList.add(curScore);
			}
			sc.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Convert the ArrayList to an array
		return scoreList.toArray(new String[0]);

	}

	public void addScore(String name, String score) {
		String[] scores = getScores();
		String[] newScores;

		if (scores.length >= 5) {
			// If the scores array is full, consider whether to add the new score or not

			boolean added = false;

			newScores = new String[scores.length];
			int j = 0;

			for (int i = 0; i < scores.length; i++) {
				String[] cur = scores[i].split(" ");

				if (Integer.parseInt(cur[1]) < Integer.parseInt(score) && !added) {
					// Insert the new score
					newScores[i] = name + " " + score;
					added = true;
				} else {
					// Shift the existing scores down
					newScores[i] = scores[j];
					j++;
				}
			}

			if (!added && j < newScores.length) {
				// Add the new score at the end if it hasn't been added yet
				newScores[j] = name + " " + score;
			}
		} else {
			// If the scores array is not full, add the new score directly
			newScores = new String[scores.length + 1];

			int j = 0;
			boolean added = false;

			for (int i = 0; i < scores.length; i++) {
				String[] cur = scores[i].split(" ");

				if (Integer.parseInt(cur[1]) < Integer.parseInt(score) && !added) {
					// Insert the new score
					newScores[i] = name + " " + score;
					added = true;
				} else {
					// Shift the existing scores down
					newScores[i] = scores[j];
					j++;
				}
			}

			if (!added && j < newScores.length) {
				// Add the new score at the end if it hasn't been added yet
				newScores[j] = name + " " + score;
			}
		}

		// Update the scores array with the new sorted array
		setScores(newScores);
	}


	// Mock method to simulate updating scores in a file or other storage
	public void setScores(String[] newScores) {
		// Replace this with your actual implementation to update scores
		File file = new File("leaderboard.txt");

		try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
			// Write each score to the file
			for (String score : newScores) {
				writer.println(score);
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setBeenThrough() {
		beenThrough = true;
	}

	public boolean getBeenThrough() {
		return beenThrough;
	}

	public void setScore(String gwSCore) {
		score = gwSCore;
	}



	/**
	 * Update the moving direction of the player based on the key event
	 */
	public void keyPress(KeyEvent key) {

		if (key.getKeyCode() == 82) {
			restart = true;
		}
	}

	public boolean getRestart() {
		return restart;
	}

	public void resetRestart() {
		restart = false;
	}

	/**
	 * Reset the moving direction of the player once the appropriate key is released
	 */


}








