package es.hol.fpriego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class ActorAsteroide extends ActoresEnemigos{
	
	private Sprite img;
	private Animation animRompe;
	private float velocidad,x,estado;
	private boolean esNuevo,paused,dibujar,mover;
	private Rectangle rect;
	private int puntos,vida;
	
	public ActorAsteroide(){}
	
	public ActorAsteroide(Sprite img,Animation anim,boolean paused,boolean mover) {
		 
		this.setImg(img);
		this.setSize(img.getWidth(), img.getHeight());
		this.setVelocidadY(1.5f);
		this.setEsNuevo(true);
		this.setDibujar(true);
		this.setPaused(paused);
		this.setMover(mover);
		this.setEstado(0);
		this.setVida(3);
		this.setPuntos(10);
		this.setOrigin(this.getWidth()/2, this.getHeight()/2);
		this.setRect(new Rectangle(getX(),getY(),img.getWidth(),img.getHeight()));
		this.setAnimRompe(anim);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		Color col = getColor();
		batch.setColor(col.r, col.g, col.b, col.a * parentAlpha);
		
		if(!paused){
			
			if(getVida()==0){
				estado+=Gdx.graphics.getDeltaTime();
				batch.draw(animRompe.getKeyFrame(estado, false), getX(), getY());
				if(animRompe.isAnimationFinished(estado)){
					this.reset();
				}
			}
			else if(dibujar){
				batch.draw(img, getX(), getY(), getOriginX(), getOriginY(),
					getWidth(), getHeight(), getScaleX(), getScaleY(),
					getRotation());
			}
		}
	}
	
	public void mover(){
		
		if(mover){
			this.addAction(Actions.rotateBy(2));
			if(esNuevo){
				x = (float) (MathUtils.random(0, 36))*10;
				this.setX(x);
				this.setY(600);
				this.esNuevo = false;
				setPosRect(getX(), getY());
			}
			else {
				this.setY(this.getY()-velocidad);
				setPosRect(getX(), getY());
				if(this.getY()+this.getHeight()<0){
					this.esNuevo = true;
				}
			}
		}
		
	}
	public void reset(){
		
		setEsNuevo(true);
		setEstado(0);
		setVida(3);
		setVelocidadY(1.5f);
	}
	
	public boolean colisionDisparo(Rectangle rect){
		
		if(getRect().overlaps(rect)){
			if(getVida()!=0){
				this.setVida(getVida()-1);
				if(getVida()==0){
					setVelocidadY(0);
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
			return true;
		}
		
		
		return false;
		
		
	}
	
	public void setPosRect(float x, float y) {
		this.rect.x = x;
		this.rect.y = y;
	}

	@Override
	public void act(float delta) {
		
		if(!paused){
			mover();
		}
		super.act(delta);
	}
	public float getVelocidad() {
		return velocidad;
	}
	public void setVelocidadY(float velocidad) {
		this.velocidad = velocidad;
	}
	public Rectangle getRect() {
		return rect;
	}
	public void setRect(Rectangle rectAsteroide) {
		this.rect = rectAsteroide;
	}
	public boolean isEsNuevo() {
		return esNuevo;
	}
	public void setEsNuevo(boolean esNuevo) {
		this.esNuevo = esNuevo;
	}
	public boolean isPaused() {
		return paused;
	}
	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public Animation getAnimRompe() {
		return animRompe;
	}

	public void setAnimRompe(Animation animRompe) {
		this.animRompe = animRompe;
	}
	public boolean isDibujar() {
		return dibujar;
	}
	public void setDibujar(boolean dibujarAsteroide) {
		this.dibujar = dibujarAsteroide;
	}
	public int getPuntos() {
		return this.puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public float getEstado() {
		return estado;
	}

	public void setEstado(float estado) {
		this.estado = estado;
	}
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}

	public Sprite getImg() {
		return img;
	}

	public void setImg(Sprite img) {
		this.img = img;
	}

	public boolean isMover() {
		return this.mover;
	}

	public void setMover(boolean mover) {
		this.mover = mover;
	}

}
