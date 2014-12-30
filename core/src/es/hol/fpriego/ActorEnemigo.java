package es.hol.fpriego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Array;

public class ActorEnemigo extends ActoresEnemigos{
	
	private Sprite img,imgAnim,imgDisparo;
	private Animation animExplota;
	private float velocidadY,velocidadX,estado;
	private int vida,tipo,puntos,contadorDisparo;
	private Rectangle rect;
	private boolean esNuevo,paused,dibujar,mover;
	private SequenceAction accionParpadeo;
	private Color colorOriginal;
	private Array<ActorDisparo> disparos;
	
	public ActorEnemigo(){}
	
	public ActorEnemigo(Sprite[] sprites,Animation anim,int tipo,boolean paused,boolean mover,Sprite imgDisparo) {
		
		this.setEsNuevo(true);
		this.setDibujar(true);
		this.setPaused(paused);
		this.setMover(mover);
		this.setTipo(tipo);
		this.setEstado(0);
		this.setImgDisparo(imgDisparo);
		this.disparos = new Array<ActorDisparo>();
		this.animExplota = anim;
		this.accionParpadeo = new SequenceAction(Actions.color(Color.RED, 0.1f),Actions.color(getColor(),0.1f),
				Actions.color(Color.RED, 0.1f),Actions.color(getColor(),0.1f));
		
		
		if(tipo==0){
			this.setImg(sprites[this.tipo]);
			this.setVelocidadY(2);
			this.setVida(2);
			this.puntos = 10;
		}
		else if(tipo==1){
			this.setImg(sprites[this.tipo]);
			this.setVelocidadY(1.5f);
			this.setVelocidadX(2);
			this.setVida(3);
			this.setContadorDisparo(0);
			this.puntos = 20;
		}
		else if(tipo==2){
			this.setImg(sprites[this.tipo]);
			this.setVelocidadY(2);
			this.setVida(3);
			this.setContadorDisparo(0);
			this.puntos = 30;
		}
		
		this.colorOriginal = new Color(getColor());
		this.setSize(img.getWidth(), img.getHeight());
		this.setRect(new Rectangle(getX(), getY(), img.getWidth(), img.getHeight()));
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
			else if(dibujar){
				batch.draw(img, getX(), getY());
			}
			
			for(ActorDisparo disparo:disparos){
				disparo.draw(batch, parentAlpha);
			}
		}
		
	}
	
	@Override
	public void act(float delta) {
		if(mover){
			mover(getTipo());
		}
		super.act(delta);
	}

	public void mover(int tipo){
		
		if(esNuevo){
			
			this.addAction(Actions.color(colorOriginal));
			
			this.setX((float) (MathUtils.random(0, 36))*10);
			this.setY(600);
			setPosRect(getX(), getY());
			this.esNuevo = false;
			
			if(getTipo()==1){
				setVelocidadX(2);
				setVelocidadY(1.5f);
			}
			
			if(getTipo()==2){
				this.addAction(Actions.moveTo(MathUtils.random(0, 36)*10, 300, getVelocidadY()));
			}
		}
		else {
			if(tipo==0){
				this.setY(this.getY()-velocidadY);
				if(this.getY()+this.getHeight()<0){
					this.esNuevo = true;
				}
			}
			else if(tipo==1){
				
				if(getX()<0){
					setVelocidadX(2);
				}
				
				if(getX()+getWidth()>400 && getVelocidadX()>0){
					setVelocidadX(-getVelocidadX());
				}
				setX(getX()+getVelocidadX());
				this.setY(this.getY()-velocidadY);
				if(this.getY()+this.getHeight()<0){
					this.esNuevo = true;
				}
				
				disparar();
				
				for(ActorDisparo disparo:disparos){
					disparo.moverDispNave();
				}
			}
			else if(tipo==2){
				
				if(getY()==300){
					this.addAction(Actions.moveTo(MathUtils.random(0, 36)*10, 550, getVelocidadY()));
				}
				else if(getY()==550){
					this.addAction(Actions.moveTo(MathUtils.random(0, 36)*10, 300, getVelocidadY()));
				}
				
			}
			setPosRect(getX(), getY());
		}
	}
	
	private void disparar() {
		
		if(contadorDisparo == 80){
			ActorDisparo tempDisparo = new ActorDisparo(imgDisparo, getX(), getY());
			tempDisparo.setVelocidad(-6);
			disparos.add(tempDisparo);
			contadorDisparo = 0;
		}
		
		contadorDisparo++;
		
	}

	public boolean colisionDisparo(Rectangle rect){
		
		if(getRect().overlaps(rect)){
			
			if(getVida()!=0){
				
				this.setVida(getVida()-1);

				if(getVida()==0){
					setVelocidadY(0);
					setVelocidadX(0);
					if(getTipo()==2){
						if(this.getActions().size>0){
							for(Action accion:this.getActions()){
								this.removeAction(accion);
							}
						}
					}
				}
				else{
					parpadeo();
				}
				return true;
			}
		}
		return false;
		
	}
	
	public boolean colisionNave(Rectangle rect){
		
		if(getRect().overlaps(rect) && getVida()!=0){
				setVida(0);
				setVelocidadY(0);
				setVelocidadX(0);
				return true;
		}
		return false;
		
	}
	
	public void parpadeo(){
		
		this.addAction(Actions.sequence(Actions.color(Color.RED, 0.1f),Actions.color(colorOriginal,0.1f),
				Actions.color(Color.RED, 0.1f),Actions.color(colorOriginal,0.1f)));
	}
	
	public void setPosRect(float x, float y) {
		this.rect.x = x;
		this.rect.y = y;
	}
	public Sprite getImg() {
		return img;
	}
	public void setImg(Sprite img) {
		this.img = img;
	}
	public Animation getAnim() {
		return animExplota;
	}

	public void setAnim(Animation animExplota) {
		this.animExplota = animExplota;
	}

	public float getVelocidadY() {
		return velocidadY;
	}
	public void setVelocidadY(float velocidad) {
		this.velocidadY = velocidad;
	}
	public float getVelocidadX() {
		return velocidadX;
	}

	public void setVelocidadX(float velocidadX) {
		this.velocidadX = velocidadX;
	}

	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
	public Rectangle getRect() {
		return rect;
	}
	public void setRect(Rectangle rectEnemigo) {
		this.rect = rectEnemigo;
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
			this.setVelocidadY(2);
			break;
		case 1:
			this.vida = 3;
			this.setVelocidadY(1.5f);
			setVelocidadX(2);
			break;
		case 2:
			this.vida = 3;
			this.setVelocidadY(2);
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
	public boolean isDibujar() {
		return dibujar;
	}
	public void setDibujar(boolean dibujarEnemigo) {
		this.dibujar = dibujarEnemigo;
	}

	public boolean isMover() {
		return this.mover;
	}

	public void setMover(boolean mover) {
		this.mover = mover;
	}

	public Array<ActorDisparo> getDisparos() {
		return disparos;
	}

	public void setDisparos(Array<ActorDisparo> disparos) {
		this.disparos = disparos;
	}

	public Sprite getImgDisparo() {
		return imgDisparo;
	}

	public void setImgDisparo(Sprite imgDisparo) {
		this.imgDisparo = imgDisparo;
	}

	public int getContadorDisparo() {
		return contadorDisparo;
	}

	public void setContadorDisparo(int contadorDisparo) {
		this.contadorDisparo = contadorDisparo;
	}


}
