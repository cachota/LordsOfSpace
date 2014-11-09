package es.hol.fpriego;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class JuegoMain extends Game {
	
	SpriteBatch batch;
	PantallaSplash splash;
	PantallaJuego juego;
	PantallaMenu menu;
	Graficos graficos;
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		splash = new PantallaSplash(this);
		juego = new PantallaJuego(this);
		menu = new PantallaMenu(this);
		graficos = new Graficos();
		
		this.setScreen(splash);
	}

	@Override
	public void dispose() {
		graficos.dispose();
		batch.dispose();
		super.dispose();
	}

}
