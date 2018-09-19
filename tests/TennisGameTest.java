import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	@Test
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);
	}

    @Test
    public void testTennisGame_Player1ScoredPoint() throws TennisGameException {
        //Setup
        TennisGame game = new TennisGame();
        game.player1Scored();
        //Act
        String score = game.getScore();
        //Assert
        assertEquals("Player 1 score did not go up.", "15 - love", score);
    }

	@Test
	public void testTennisGame_Player2ScoredPoint() throws TennisGameException {
		//Setup
		TennisGame game = new TennisGame();
		game.player2Scored();
		//Act
		String score = game.getScore();
		//Assert
		assertEquals("Player 2 score did not go up.", "love - 15", score);
	}

	@Test
	public void testTennisGame_30Points() throws TennisGameException {
		//Setup
		TennisGame game = new TennisGame();
		game.player1Scored();
		game.player1Scored();
		//Act
		String score = game.getScore();
		//Assert
		assertEquals("Player 1 score is not 30.", "30 - love", score);
	}

	@Test
	public void testTennisGame_40Points() throws TennisGameException {
		//Setup
		TennisGame game = new TennisGame();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		String score = game.getScore();
		//Assert
		assertEquals("Player 1 score is not 40.", "40 - love", score);
	}

	@Test
	public void testTennisGame_Player1Wins() throws TennisGameException {
		//Setup
		TennisGame game = new TennisGame();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		String score = game.getScore();
		//Assert
		assertEquals("Player 1 did not win.", "player1 wins", score);
	}

	@Test
	public void testTennisGame_Player2Wins() throws TennisGameException {
		//Setup
		TennisGame game = new TennisGame();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		//Act
		String score = game.getScore();
		//Assert
		assertEquals("Player 2 did not win.", "player2 wins", score);
	}

	@Test
	public void testTennisGame_ScoreTied() throws TennisGameException {
		//Setup
		TennisGame game = new TennisGame();
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore();
		//Assert
		assertEquals("The game was not tied.", "15 - 15", score);
	}

	@Test
    public void testTennisGame_Player1Advantage() throws TennisGameException {
	    //Setup
        TennisGame game = new TennisGame();

        game.player1Scored();
        game.player1Scored();
        game.player1Scored();

        game.player2Scored();
        game.player2Scored();
        game.player2Scored();

        game.player1Scored();
        game.player2Scored();
        game.player1Scored();

        //Act
        String score = game.getScore();
        //Assert
        assertEquals("Player 1 did not have advantage.", "player1 has advantage", score);
    }

    @Test
    public void testTennisGame_Player2Advantage() throws TennisGameException {
        //Setup
        TennisGame game = new TennisGame();

        game.player1Scored();
        game.player1Scored();
        game.player1Scored();

        game.player2Scored();
        game.player2Scored();
        game.player2Scored();

        game.player1Scored();
        game.player2Scored();
        game.player2Scored();

        //Act
        String score = game.getScore();
        //Assert
        assertEquals("Player 2 did not have advantage.", "player2 has advantage", score);
    }

	@Test
	public void testTennisGame_EachPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();
	}

}
