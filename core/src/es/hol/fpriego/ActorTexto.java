package es.hol.fpriego;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ActorTexto extends Actor{
	
	private Skin skin;
	private Label texto;
	private float x,y;
	
	public ActorTexto(Skin skin,String texto,float scale,float x,float y) {
		this.skin = skin;
		this.texto = new Label(texto,skin);
		this.setX(x);
		this.setY(y);
		this.texto.setPosition(x, y);
		this.setTextoScale(scale);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		Color col = getColor();
		batch.setColor(col.r, col.g, col.b, col.a*parentAlpha);
		
		texto.setPosition(getX(), getY());
		texto.draw(batch, parentAlpha);
	}

	public Skin getSkin() {
		return skin;
	}

	public void setSkin(Skin skin) {
		this.skin = skin;
	}

	public Label getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto.setText(texto);
	}
	
	public void setTextoScale(float scale){
		skin.getFont("default-font").setScale(scale);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
		texto.setX(x);
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
		texto.setY(y);
	}
	
}
