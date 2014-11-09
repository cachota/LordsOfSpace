package es.hol.fpriego;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.Array;

public class ActorNave extends Actor {
	
	private Sprite img;
	private Array<ActorDisparo> shots;
	private Rectangle rectNave;
	private float velocidad;
	private int vida,contadorIn;
	private Touchpad pad;
	private SequenceAction accionParpadeo;
	private boolean esInmune;
	
	public ActorNave(Sprite img) {
		
		// Se crea la nave con la imagen pasada por parámetro
		this.setImg(img);
		
		this.setVelocidad(4);
		this.setVida(5);
		
		// La nave no es inmune de inicio y se crea un contador para la duracion de dicha inmunidad
		this.setInmune(false);
		this.contadorIn = 0;
		
		// Array para los disparos
		this.shots = new Array<ActorDisparo>();
		
		this.setSize(img.getWidth(), img.getHeight());
		
		// Rectangulo para la deteccion de colisiones
		this.setRectNave(new Rectangle(getX(),getY(),img.getWidth(),img.getHeight()));
		
		// Accion secuencial para cuando se recibe una colision
		this.accionParpadeo = new SequenceAction(Actions.color(Color.RED, 0.1f),Actions.color(getColor(),0.1f),
				Actions.color(Color.RED, 0.1f),Actions.color(getColor(),0.1f));
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		Color col = getColor();
		batch.setColor(col.r, col.g, col.b, col.a*parentAlpha);
		
		this.setPosRectNave(getX(), getY());
		batch.draw(img, getX(), getY());
		
		
		for(int i=0;i<this.getShots().size;i++){
			ActorDisparo tempDisparo = this.getShots().get(i);
			tempDisparo.draw(batch, parentAlpha);
		}
		
	}

	public void mover(Touchpad pad){
		
		this.setPad(pad);
		
		this.setX(this.getX() + pad.getKnobPercentX()*this.getVelocidad());
		this.setY(this.getY() + pad.getKnobPercentY()*this.getVelocidad());
		
		for(int i=0;i<this.getShots().size;i++){
			ActorDisparo tempDisparo = this.getShots().get(i);
			tempDisparo.moverDispNave();
		}
		
	}
	
	public void parpadeo(){
		
		this.addAction(Actions.sequence(accionParpadeo,new Action(){

			@Override
			public boolean act(float delta) {
				this.getActor().removeAction(this);
				return true;
			}
	
		}));
	}

	public Sprite getImg() {
		return img;
	}

	public void setImg(Sprite img) {
		this.img = img;
	}

	public Array<ActorDisparo> getShots() {
		return shots;
	}

	public void setShots(Array<ActorDisparo> shots) {
		this.shots = shots;
	}

	public boolean esInmune() {
		return esInmune;
	}

	public void setInmune(boolean esInmune) {
		this.esInmune = esInmune;
	}

	public float getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(float velocidad) {
		this.velocidad = velocidad;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public Rectangle getRectNave() {
		return rectNave;
	}

	public void setRectNave(Rectangle rectNave) {
		this.rectNave = rectNave;
	}
	
	public void setPosRectNave(float x,float y){
		this.rectNave.setX(x);
		this.rectNave.setY(y);
	}

	public Touchpad getPad() {
		return pad;
	}

	public void setPad(Touchpad pad) {
		this.pad = pad;
	}

	public int getContadorIn() {
		return contadorIn;
	}

	public void setContadorIn(int contadorIn) {
		this.contadorIn = contadorIn;
	}

}
