import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TennisGameTest {

	private TennisGame game;

	@Before
	public void testTennisGame_Init() {
		game = new TennisGame();
	}

	@Test
	public void testTennisGame_Start() {
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);
	}

    @Test
    public void testTennisGame_Player1ScoredPoint() throws TennisGameException {
        //Setup
        game.player1Scored();
        //Act
        String score = game.getScore();
        //Assert
        assertEquals("Player 1 score did not go up.", "15 - love", score);
    }

	@Test
	public void testTennisGame_Player2ScoredPoint() throws TennisGameException {
		//Setup
		game.player2Scored();
		//Act
		String score = game.getScore();
		//Assert
		assertEquals("Player 2 score did not go up.", "love - 15", score);
	}

	@Test
	public void testTennisGame_30Points() throws TennisGameException {
		//Setup
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
        game.player1Scored();
        game.player1Scored();
        game.player1Scored();

        game.player2Scored();
        game.player2Scored();
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
        game.player1Scored();
        game.player1Scored();
        game.player1Scored();

        game.player2Scored();
        game.player2Scored();
        game.player2Scored();

        game.player2Scored();

        //Act
        String score = game.getScore();
        //Assert
        assertEquals("Player 2 did not have advantage.", "player2 has advantage", score);
    }

    @Test
	public void testTennisGame_40PointsDeuce() throws TennisGameException {
		//Setup
        game.player1Scored();
        game.player1Scored();
        game.player1Scored();

        game.player2Scored();
        game.player2Scored();
        game.player2Scored();
        //Act
        String score = game.getScore();
        //Assert
        assertEquals("The game score was not deuce.", "deuce", score);
	}

	@Test
	public void testTennisGame_EachPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
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
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();
	}

    @Test (expected = TennisGameException.class)
    public void testTennisGame_Player2WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
        //Act
        game.player2Scored();
        game.player2Scored();
        game.player2Scored();
        game.player2Scored();
        //Act
        // This statement should cause an exception
        game.player2Scored();
    }

}
