import net.apryx.ftec.engine.FTec;
import net.apryx.ftec.engine.Game;

public class App {
	
	public static void main(String[] args) throws InterruptedException {
		Game game = new TestGame();
		
		FTec.create(game);
	}
}
