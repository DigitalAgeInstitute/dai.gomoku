package dai.gomoku.game.core;

public class GameRunner implements Runnable {
	private GomokuGame game;
	private boolean stopped;
	
	public GameRunner ( GomokuGame game ) {
		this.game = game;
		stopped = false;
	}
	
	public void run ( ) {
		while ( !stopped ) {
			// TODO: Confirm this is even necessary
			// TODO: If yes, implement the necessary steps
			
			// End the game
			if ( game.isGameOver() ) {
				stopped = true;
			}
		}
	}
}
