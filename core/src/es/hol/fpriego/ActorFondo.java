package es.hol.fpriego;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorFondo extends Actor {
	
	private Sprite img;
	
	public ActorFondo(Sprite img) {
		
		this.setImg(img);
		this.setSize(img.getWidth(), img.getHeight());

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		 Color col = getColor();
		 batch.setColor(col.r, col.g, col.b, col.a * parentAlpha);
		 
		 batch.draw(img, getX(), getY());
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

}
