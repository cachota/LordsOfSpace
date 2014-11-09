package es.hol.fpriego;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorDisparo extends Actor {
	
	private Sprite img;
	private Rectangle rectDisparo;
	private float velocidad;
	public ActorDisparo(Sprite img,float x,float y) {
		
		this.setImg(img);
		this.setVelocidad(6);
		this.setSize(img.getWidth(), img.getHeight());
		this.setPosition(x, y);
		this.setRectDisparo(new Rectangle(getX(),getY(),img.getWidth(),img.getHeight()));
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		Color col = getColor();
		batch.setColor(col.r, col.g, col.b, col.a*parentAlpha);
		
		batch.draw(img, getX(), getY());
	}
	
	public void moverDispNave(){
		this.setY(this.getY()+this.getVelocidad());
		this.setPosRectDisparo(this.getX(), this.getY());
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

	public Rectangle getRectDisparo() {
		return rectDisparo;
	}

	public void setRectDisparo(Rectangle rectDisparo) {
		this.rectDisparo = rectDisparo;
	}
	
	public void setPosRectDisparo(float x,float y){
		this.rectDisparo.setX(x);
		this.rectDisparo.setY(y);
	}

}
