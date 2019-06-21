package game;

public class GameMain {
	static GameInfo game;
	public static void main(String[] args) {
		game = new GameInfo();
		game.initializeGame();
		while(GameInfo.getStatus()) {
			game.loop();
		}
	}
}
