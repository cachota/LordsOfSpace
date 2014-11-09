package es.hol.fpriego;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class ActorAsteroide extends Actor{
	
	private Sprite img;
	private float velocidad,x;
	private boolean esNuevo,paused;
	private Rectangle rectAsteroide;
	
	public ActorAsteroide(Sprite img,boolean paused) {
		 
		this.setImg(img);
		this.setSize(img.getWidth(), img.getHeight());
		this.setVelocidad(1.5f);
		this.esNuevo = true;
		this.paused = paused;
		this.setOrigin(this.getWidth()/2, this.getHeight()/2);
		this.setRectAsteroide(new Rectangle(getX(),getY(),img.getWidth(),img.getHeight()));
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color col = getColor();
		batch.setColor(col.r, col.g, col.b, col.a * parentAlpha);
		if(!paused){
			
			batch.draw(img, getX(), getY(), getOriginX(), getOriginY(),
					getWidth(), getHeight(), getScaleX(), getScaleY(),
					getRotation());
		}
	}

	public void mover(){
		
		this.addAction(Actions.rotateBy(2));
		if(esNuevo){
			x = (float) (MathUtils.random(0, 37))*10;
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
	
	private void setPosRect(float x, float y) {
		this.rectAsteroide.x = x;
		this.rectAsteroide.y = y;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
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

	public Rectangle getRectAsteroide() {
		return rectAsteroide;
	}

	public void setRectAsteroide(Rectangle rectAsteroide) {
		this.rectAsteroide = rectAsteroide;
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

}
