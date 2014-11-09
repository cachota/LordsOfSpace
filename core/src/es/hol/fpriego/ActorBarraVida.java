package es.hol.fpriego;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorBarraVida extends Actor{
	
	private NinePatch barra,vida;
	private Sprite naveIcon;
	private boolean dibujarVida;
	
	public ActorBarraVida(Texture imgBarra,Texture imgVida, Sprite naveIcon) {
		
		this.barra = new NinePatch(new TextureRegion(imgBarra),4,4,4,4);
		this.vida = new NinePatch(new TextureRegion(imgVida),4,4,4,4);
		this.setNaveIcon(naveIcon);
		naveIcon.setPosition(280, 570);
		this.setPosition(300, 570);
		this.barra.setRightWidth(80);
		this.vida.setRightWidth(105);
		this.dibujarVida = true;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		Color col = getColor();
		batch.setColor(col.r, col.g, col.b, col.a*parentAlpha);
		
		barra.draw(batch, getX(), getY(), barra.getTotalWidth(), barra.getTotalHeight());
		if(dibujarVida){
			vida.draw(batch, getX(), getY(), vida.getTotalWidth(), vida.getTotalHeight());
		}
		batch.draw(naveIcon, naveIcon.getX(), naveIcon.getY());
	}

	public Sprite getNaveIcon() {
		return naveIcon;
	}

	public void setNaveIcon(Sprite naveIcon) {
		this.naveIcon = naveIcon;
	}

	public int getVida() {
		return (int)vida.getRightWidth()/5;
	}

	public void quitarVida() {
		
		vida.setRightWidth(vida.getRightWidth()-21);
	}

	public boolean isDibujarVida() {
		return dibujarVida;
	}

	public void setDibujarVida(boolean dibujarVida) {
		this.dibujarVida = dibujarVida;
	}
	

}
