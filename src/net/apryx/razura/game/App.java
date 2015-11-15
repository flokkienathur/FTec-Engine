package net.apryx.razura.game;

import net.apryx.ftec.engine.FTec;

public class App {

	public static void main(String[] args) throws InterruptedException {
		Razura game = new Razura();

		FTec.create(game);
	}

}
