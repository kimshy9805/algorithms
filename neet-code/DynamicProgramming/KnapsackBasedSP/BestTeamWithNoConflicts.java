package KnapsackBasedSP;

import java.util.Arrays;

public class BestTeamWithNoConflicts {
	
	// bottom up
	
    public int bestTeamScore(int[] scores, int[] ages) {
        
    	
    	Player[] players = new Player[scores.length];
    	
    	for (int i = 0; i < players.length; i++) {
    		players[i] = new Player(ages[i], scores[i]);
    	}
    	
        Arrays.sort(players, (player1,player2)->player1.age==player2.age?player1.score-player2.score:player1.age-player2.age);

    	int[] dp = new int[players.length];
    	
    	int max = dp[0] = players[0].score;
    	
    	for (int i = 1; i < players.length; i++) {
    		dp[i] = players[i].score;
    		
    		for (int j = 0; j < i; j++) {
    			if (players[j].score <= players[i].score) {
    				dp[i] = Math.max(dp[i], dp[j] + players[i].score);
    			}
    		}
    		max = Math.max(max, dp[i]);
    	}
    	
    	return max;
    }
    
    
    class Player {
    	int age;
    	int score;
    	
    	Player(int age, int score) {
    		this.age = age;
    		this.score = score;
    	}
    }
    
}
