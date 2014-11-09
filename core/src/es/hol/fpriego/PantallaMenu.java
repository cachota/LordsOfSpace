package es.hol.fpriego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PantallaMenu extends Pantalla {
	
	JuegoMain game;
	private Stage escenario;
	private ActorFondo fondo1,fondo2;
	private TextureRegion titulo;
	private Dialog menuPausa;
	private TextButton botonSalir;
	private TextButton botonResumir;
	
	public PantallaMenu(JuegoMain game) {
		super(game);
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		escenario.act();
		escenario.draw();
		
		if(Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.ESCAPE) || Gdx.input.isKeyPressed(Keys.MENU)){
			escenario.addActor(menuPausa);
		}
	}

	@Override
	public void resize(int width, int height) {
		escenario.getViewport().update(width, height);
	}

	@Override
	public void show() {
		
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setCatchMenuKey(true);
		
		Skin skin = game.graficos.getSkin();
		skin.getFont("default-font").setScale(2f);
		
		final Button botonIniciar,botonPuntua;
		
		escenario = new Stage(new FitViewport(400f,600f));
		botonIniciar = new TextButton("Iniciar Juego",skin);
		botonPuntua = new TextButton("Puntuaciones",skin);
		Table tabla = new Table();
		
		fondo1 = new ActorFondo(game.graficos.getFondoSprite());
		fondo2 = new ActorFondo(game.graficos.getFondoSprite());
		fondo1.setPosition(0, 0);
		fondo2.setPosition(0, 512);
		setTitulo(new TextureRegion(new Texture("titulo.png")));
		final ActorTitulo actTitulo = new ActorTitulo(titulo);
		
		tabla.add(botonIniciar).width(250).height(80).space(70).row();
		tabla.add(botonPuntua).width(250).height(80).space(70).row();
		
		menuPausa = new Dialog("Salir del juego?",skin);
		botonSalir = new TextButton("Salir",skin);
		botonResumir = new TextButton("Volver",skin);
		menuPausa.setPosition(50, 300);
		menuPausa.setSize(300, 100);
		menuPausa.add(botonSalir).width(100).left().expand();
		menuPausa.add(botonResumir).width(100).left().expand();
		
		Gdx.input.setInputProcessor(escenario);
		
		botonIniciar.addListener(new ChangeListener(){
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				final int desplY = Gdx.graphics.getHeight();
				final float duracion = 0.7f;
				final float espera = 0.1f;
								
				actTitulo.addAction(Actions.sequence(Actions.delay(1*espera),
						Actions.parallel(Actions.moveBy(0, desplY, duracion),Actions.fadeOut(duracion))));
				botonIniciar.addAction(Actions.sequence(Actions.delay(3*espera),
						Actions.parallel(Actions.moveBy(0, desplY, duracion),Actions.fadeOut(duracion))));
				botonPuntua.addAction(Actions.sequence(Actions.delay(5*espera),
						Actions.parallel(Actions.moveBy(0, desplY, duracion),Actions.fadeOut(duracion)),new Action(){

							@Override
							public boolean act(float delta) {
								game.setScreen(game.juego);
								return true;
							}
					
				}));
				
				
			}
			
		});
		
		botonSalir.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit();
			}
		});
		
		botonResumir.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				escenario.getActors().removeValue(menuPausa, true);
			}
			
		});
		
		tabla.setPosition(200, 200);
		//tabla.setFillParent(true);
		
		escenario.addActor(fondo1);
		escenario.addActor(fondo2);
		escenario.addActor(actTitulo);
		escenario.addActor(tabla);
		
	}
	
	public class ActorTitulo extends Actor{
		
		TextureRegion img;
		
		public ActorTitulo(TextureRegion img){
			this.img = img;
			this.setX(72);
			this.setY(300);
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			Color col = getColor();
			batch.setColor(col.r, col.g, col.b, col.a * parentAlpha);
			
			batch.draw(img, getX(), getY());
		}
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void dispose() {
		escenario.dispose();
		super.dispose();
	}

	public Stage getEscenario() {
		return escenario;
	}

	public void setEscenario(Stage escenario) {
		this.escenario = escenario;
	}

	public ActorFondo getFondo1() {
		return fondo1;
	}

	public void setFondo1(ActorFondo fondo1) {
		this.fondo1 = fondo1;
	}

	public ActorFondo getFondo2() {
		return fondo2;
	}

	public void setFondo2(ActorFondo fondo2) {
		this.fondo2 = fondo2;
	}

	public TextureRegion getTitulo() {
		return titulo;
	}

	public void setTitulo(TextureRegion titulo) {
		this.titulo = titulo;
	}

}
