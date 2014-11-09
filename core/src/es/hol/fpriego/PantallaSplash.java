package es.hol.fpriego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PantallaSplash extends Pantalla {
	
	private TextureRegion barra,cuadrado;
	JuegoMain game;
	int contador;
	private ActorBarra actorBarra;
	private ActorCuadro actorCuadro;
	private Stage escenario;
	
	public PantallaSplash(JuegoMain game) {
		super(game);
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		escenario.act();
		escenario.draw();
		
		actorCuadro.addAction(Actions.sizeTo(30+contador, actorCuadro.getHeight()));
		contador+=2;
		
		if(contador==200){
			game.setScreen(game.menu);
		}
	}

	@Override
	public void resize(int width, int height) {
		escenario.getViewport().update(width, height);
		super.resize(width, height);
	}

	@Override
	public void show() {
		escenario = new Stage(new FitViewport(400f,600f));
		barra = new TextureRegion(new Texture("black_bar.png"));
		cuadrado = new TextureRegion(new Texture("progressBarGreen.png"));
		setActorBarra(new ActorBarra(barra));
		setActorCuadro(new ActorCuadro(cuadrado));
		contador = 0;
		
		actorBarra.setPosition(76, 300);
		actorCuadro.setPosition(79, 304);
		
		escenario.addActor(actorBarra);
		escenario.addActor(actorCuadro);
	}

	private class ActorBarra extends Actor{
		
		TextureRegion barra;
		
		public ActorBarra(TextureRegion barra){
			this.barra = barra;
			this.setWidth(barra.getRegionWidth());
			this.setHeight(barra.getRegionHeight());
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			Color col = getColor();
			batch.setColor(col.r, col.g, col.b, col.a*parentAlpha);
			
			batch.draw(barra, getX(), getY());
		}
	}
	
	private class ActorCuadro extends Actor{
		
		TextureRegion cuadro;
		
		public ActorCuadro(TextureRegion cuadro){
			this.cuadro = cuadro;
			this.setWidth(cuadro.getRegionWidth());
			this.setHeight(cuadro.getRegionHeight());
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			Color col = getColor();
			batch.setColor(col.r, col.g, col.b, col.a*parentAlpha);
			
			batch.draw(cuadro, getX(), getY(),getWidth(),getHeight());
		}
		
	}
	
	@Override
	public void hide() {
		super.hide();
		this.dispose();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose() {
		barra.getTexture().dispose();
		cuadrado.getTexture().dispose();
		escenario.dispose();
		super.dispose();
	}

	public ActorBarra getActorBarra() {
		return actorBarra;
	}

	public void setActorBarra(ActorBarra actorBarra) {
		this.actorBarra = actorBarra;
	}

	public ActorCuadro getActorCuadro() {
		return actorCuadro;
	}

	public void setActorCuadro(ActorCuadro actorCuadro) {
		this.actorCuadro = actorCuadro;
	}

}
