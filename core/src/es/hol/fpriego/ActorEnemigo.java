package es.hol.fpriego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class ActorEnemigo extends Actor {
	
	private Sprite img,imgAnim;
	private Animation animExplota;
	private float velocidad,estado;
	private int vida,tipo,puntos;
	private Rectangle rectEnemigo;
	private boolean esNuevo,paused;
	private SequenceAction accionParpadeo;
	
	public ActorEnemigo(Sprite[] sprites,Animation anim,int tipo,boolean paused) {
		
		this.esNuevo = true;
		this.setPaused(paused);
		this.tipo = tipo;
		this.setEstado(0);
		this.animExplota = anim;
		this.accionParpadeo = new SequenceAction(Actions.color(Color.RED, 0.1f),Actions.color(getColor(),0.1f),
				Actions.color(Color.RED, 0.1f),Actions.color(getColor(),0.1f));
		
		if(tipo==0){
			this.setImg(sprites[this.tipo]);
			this.setVelocidad(2);
			this.setVida(2);
			this.puntos = 10;
		}
		else if(tipo==1){
			this.setImg(sprites[this.tipo]);
			this.setVelocidad(3);
			this.setVida(3);
			this.puntos = 20;
		}
		else if(tipo==2){
			this.setImg(sprites[this.tipo]);
			this.setVelocidad(4);
			this.setVida(3);
			this.puntos = 30;
		}
		
		this.setSize(img.getWidth(), img.getHeight());
		this.setRectEnemigo(new Rectangle(getX(), getY(), img.getWidth(), img.getHeight()));
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color col = getColor();
		batch.setColor(col.r, col.g, col.b, col.a * parentAlpha);
		if(!paused){
			if(getVida()==0){
				estado+=Gdx.graphics.getDeltaTime();
				batch.draw(animExplota.getKeyFrame(estado, false), getX(), getY());
				if(animExplota.isAnimationFinished(estado)){
					this.reset();
				}
			}
			else{
				batch.draw(img, getX(), getY());
			}
		}
	}

	public void mover(int tipo){
		if(esNuevo){
			this.setX((float) (MathUtils.random(0, 37))*10);
			this.setY(600);
			this.esNuevo = false;
			setPosRect(getX(), getY());
		}
		else {
			if(tipo==0){
				this.setY(this.getY()-velocidad);
				setPosRect(getX(), getY());
				if(this.getY()+this.getHeight()<0){
					this.esNuevo = true;
				}
			}
			else if(tipo==1){
				this.setY(this.getY()-velocidad);
				setPosRect(getX(), getY());
				if(this.getY()+this.getHeight()<0){
					this.esNuevo = true;
				}
			}
			else if(tipo==2){
				this.setY(this.getY()-velocidad);
				setPosRect(getX(), getY());
				if(this.getY()+this.getHeight()<0){
					this.esNuevo = true;
				}
			}
		}
	}
	
	public boolean colisionDisparo(Rectangle rect){
		if(getVida()>0){
			if(rect.overlaps(getRectEnemigo())){
				this.setVida(getVida()-1);
				return true;
			}
		}
		return false;
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
	
	private void setPosRect(float x, float y) {
		this.rectEnemigo.x = x;
		this.rectEnemigo.y = y;
	}

	public Sprite getImg() {
		return img;
	}

	public void setImg(Sprite img) {
		this.img = img;
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

	public Rectangle getRectEnemigo() {
		return rectEnemigo;
	}

	public void setRectEnemigo(Rectangle rectEnemigo) {
		this.rectEnemigo = rectEnemigo;
	}

	public boolean isEsNuevo() {
		return esNuevo;
	}

	public void setEsNuevo(boolean esNuevo) {
		this.esNuevo = esNuevo;
	}
	
	public void reset(){
		
		this.setEsNuevo(true);
		this.setEstado(0);
		
		switch(this.tipo){
		case 0:
			this.vida = 2;
			break;
		case 1:
			this.vida = 3;
			break;
		case 2:
			this.vida = 3;
			break;
		}
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public float getEstado() {
		return estado;
	}

	public void setEstado(float estado) {
		this.estado = estado;
	}

	public Sprite getImgAnim() {
		return imgAnim;
	}

	public void setImgAnim(Sprite imgAnim) {
		this.imgAnim = imgAnim;
	}

}
