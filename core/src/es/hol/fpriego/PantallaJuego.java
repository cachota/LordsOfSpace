package es.hol.fpriego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PantallaJuego extends Pantalla {
	
	private Stage escenario;
	private ActorNave nave;
	private ActorFondo fondo1,fondo2,fondo3;
	private ActorEnemigo enemy1;
	private ActorAsteroide asteroide;
	private Touchpad pad;
	private TextButton botonDisparo;
	private JuegoMain game;
	private ActorTexto ready,score,textoPuntos;
	private Skin skin;
	private int estado,tiempo,puntuacion;
	private boolean pause,gameover;
	private Dialog menuPausa;
	private Button botonSalir,botonResumir;
	private ActorBarraVida barraVida;
	
	public PantallaJuego(JuegoMain game) {
		
		super(game);
		this.game = game;
	}
	
	@Override
	public void show() {
		
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setCatchMenuKey(true);
		
		this.skin = game.graficos.getSkin();
		
		estado = 0;
		tiempo = 0;
		setPuntuacion(0);
		pause = false;
		gameover = false;
		
		cargarInicioJuego();
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		escenario.draw();
		escenario.act();
		
		switch(estado){
		case 0:
			inicioJuego();
			break;
		case 1:
			actualizarJuego();
			break;
		}
	}
	
	private void inicioJuego() {
			
			game.batch.begin();
				if(tiempo>100){
					escenario.getActors().removeValue(ready, true);
					asteroide.setPaused(false);
					asteroide.setMover(true);
					enemy1.setPaused(false);
					enemy1.setMover(true);
					botonDisparo.setDisabled(false);
					estado=1;
				}
			game.batch.end();
			tiempo++;
		
	}

	public void cargarInicioJuego(){
		
		barraVida = new ActorBarraVida(game.graficos.getBarraVidaTex(), game.graficos.getVidaTex(), game.graficos.getMiniPlayerSprite());
		
		pad = new Touchpad(1,skin);
		botonDisparo = new TextButton("Fire!",skin);
		botonDisparo.setSize(100, 70);
		botonDisparo.setDisabled(true);
		
		menuPausa = new Dialog("Pausa",skin);
		botonSalir = new TextButton("Salir",skin);
		botonResumir = new TextButton("Volver",skin);
		menuPausa.setPosition(50, 300);
		menuPausa.setSize(300, 100);
		menuPausa.add(botonSalir).width(100).left().expand();
		menuPausa.add(botonResumir).width(100).left().expand();
		
		escenario = new Stage(new FitViewport(400f,600f));
		fondo1 = new ActorFondo(game.graficos.getFondoSprite());
		fondo2 = new ActorFondo(game.graficos.getFondoSprite());
		fondo3 = new ActorFondo(game.graficos.getFondoSprite());
		ready = new ActorTexto(game.graficos.getSkin(),"Preparate!",2f,130,320);
		score = new ActorTexto(game.graficos.getSkin(), "score: ", 2f, 20, 550);
		textoPuntos = new ActorTexto(game.graficos.getSkin(),"0",2f,110,550);
		
		asteroide = new ActorAsteroide(game.graficos.getAsteroideSprite(),game.graficos.getAnimRompe(),true,false);
		enemy1 = new ActorEnemigo(game.graficos.getEnemSprites(),game.graficos.getAnimExplota(),0,true,false);
		
		nave = new ActorNave(game.graficos.getNaveSprite(),game.graficos.getAnimExplota());
		nave.setPosition(escenario.getWidth()/2, 100);
		
		Gdx.input.setInputProcessor(escenario);
		
		botonDisparo.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				nave.getShots().add(new ActorDisparo(game.graficos.getDisparoPlayerSprite(),nave.getX()+nave.getWidth()/2-4
						,nave.getY()+nave.getHeight()));
			}
			
		});
		
		botonSalir.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(game.menu);
			}
			
		});
		
		botonResumir.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				escenario.getActors().removeValue(menuPausa, true);
				pause = false;
			}
			
		});
		
		fondo1.setPosition(0, 0);
		fondo2.setPosition(0, 512);
		fondo3.setPosition(0, 1024);
		
		escenario.addActor(fondo1);
		escenario.addActor(fondo2);
		escenario.addActor(fondo3);
		escenario.addActor(nave);
		escenario.addActor(enemy1);
		escenario.addActor(asteroide);
		escenario.addActor(ready);
		escenario.addActor(score);
		escenario.addActor(textoPuntos);
		escenario.addActor(barraVida);
		
		botonDisparo.setPosition(280, 20);
		escenario.addActor(botonDisparo);
		
		pad.setBounds(20, 20, 75, 75);
		escenario.addActor(pad);
	}
	
	public void actualizarJuego(){
		
		if(!gameover && !pause){
			
			if(botonDisparo.isDisabled()){
				botonDisparo.setDisabled(false);
			}
			
			nave.mover(pad);
				
			for(Actor actor:escenario.getActors()){
				
				if(actor instanceof ActoresEnemigos){
						
					((ActoresEnemigos) actor).setMover(true);
						
					if(((ActoresEnemigos) actor).colisionNave(nave.getRectNave()) && !nave.esInmune()){
						nave.setVida(nave.getVida()-1);
						barraVida.quitarVida();
						nave.setInmune(true);
						if(nave.getVida()==0){
							((ActoresEnemigos) actor).setDibujar(false);
							
						}
					}
										
					if(nave.getShots().size>0){
					
						for(int i=0;i<nave.getShots().size;i++){
						
							if(((ActoresEnemigos)actor).colisionDisparo(nave.getShots().get(i).getRectDisparo())){
								
								if(((ActoresEnemigos) actor).getVida()==0){
									this.setPuntuacion(this.getPuntuacion()+((ActoresEnemigos)actor).getPuntos());
								}
								nave.getShots().removeIndex(i);
							}
							else if(nave.getShots().get(i).getY()>escenario.getHeight()-nave.getShots().get(i).getHeight()){
								nave.getShots().removeIndex(i);
							}
						}
				
					}
				}
						
			}
			
			textoPuntos.setTexto(Integer.toString(this.getPuntuacion()));
				
			if(nave.esInmune()){
				nave.setContadorIn(nave.getContadorIn()+1);
				if(nave.getContadorIn()>100){
					nave.setContadorIn(0);
					nave.setInmune(false);
				}
			}
			
			if(barraVida.getVida()==0){
				
				nave.setInmune(false);
				barraVida.setDibujarVida(false);
				
				for(Actor actor:escenario.getActors()){
					if(actor instanceof ActorEnemigo){
						((ActorEnemigo) actor).setMover(false);
					}
				}
				this.gameover = true;
			}
			
			if(fondo1.getY()+512<0){
				fondo1.setY(fondo3.getY()+512);
			}
			if(fondo2.getY()+512<0){
				fondo2.setY(fondo1.getY()+512);
			}
			if(fondo3.getY()+512<0){
				fondo3.setY(fondo2.getY()+512);
			}
			
			fondo1.setY(fondo1.getY()-1);
			fondo2.setY(fondo2.getY()-1);
			fondo3.setY(fondo3.getY()-1);
			
			if(nave.getX()>escenario.getWidth()-nave.getWidth()){
				nave.setX(escenario.getWidth()-nave.getWidth());
			}
			
			if(nave.getY()>escenario.getHeight()-nave.getHeight()){
				nave.setY(escenario.getHeight()-nave.getHeight());
				
			}
			
			if(nave.getX()<0){
				nave.setX(0);
			}
			
			if(nave.getY()<0){
				nave.setY(0);
			}
			
			if(Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.ESCAPE) || Gdx.input.isKeyPressed(Keys.MENU)){
				pause = true;
			}
		}
		
		else if(pause){
			
			botonDisparo.setDisabled(true);
			
			for(Actor actor:escenario.getActors()){
				if(actor instanceof ActoresEnemigos){
					((ActoresEnemigos) actor).setMover(false);
				}
			}
			
			escenario.addActor(menuPausa);
			
		}
		
		else if(gameover){
			
			botonDisparo.setDisabled(true);
			
			for(Actor actor:escenario.getActors()){
				if(actor instanceof ActoresEnemigos){
					((ActoresEnemigos) actor).setMover(false);
				}
			}
			
			escenario.addActor(menuPausa);
		}
		
	}
	
	@Override
	public void resize(int width, int height) {
		escenario.getViewport().update(width, height);
		ready.setSize(width, height);
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	public ActorBarraVida getBarraVida() {
		return barraVida;
	}

	public void setBarraVida(ActorBarraVida barraVida) {
		this.barraVida = barraVida;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public ActorTexto getScore() {
		return score;
	}

	public void setScore(ActorTexto score) {
		this.score = score;
	}

	public ActorTexto getTextoPuntos() {
		return textoPuntos;
	}

	public void setTextoPuntos(ActorTexto textoPuntos) {
		this.textoPuntos = textoPuntos;
	}

	@Override
	public void dispose() {
		escenario.dispose();
		super.dispose();
	}
	
}
