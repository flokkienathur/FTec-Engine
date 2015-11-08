import net.apryx.ftec.engine.FTec;
import net.apryx.ftec.engine.Game;
import net.apryx.math.Transform;

public class App {
	
	public static void main(String[] args) throws InterruptedException {
		Transform a = new Transform(0,0, 32, 32);
		Transform b = new Transform(33,33,16,16);
		Transform c = new Transform(16,16,32,32);
		Transform d = new Transform(32,32,1,1);

		System.out.println("ab " + Transform.overlaps(a, b));
		System.out.println("ac " + Transform.overlaps(a, c));
		System.out.println("bc " + Transform.overlaps(b, c));
		
		System.out.println("ad " + Transform.overlaps(a, d));
		System.out.println("bd " + Transform.overlaps(b, d));
		
		Game game = new TestGame();
		
		FTec.create(game);
	}
}
