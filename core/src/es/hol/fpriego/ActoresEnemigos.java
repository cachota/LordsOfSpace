package es.hol.fpriego;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class ActoresEnemigos extends Actor{
	
	public int puntos,vida;
	public float estado,velocidad;
	public Rectangle rect;
	public Animation anim;
	public Sprite img;
	public boolean esNuevo,paused,dibujar,mover;
	
	public void mover(int tipo) {}
	public void mover(){};
	public void reset(){};
	public void parpadeo(){};
	public boolean colisionDisparo(Rectangle rect){return false;};
	public Animation getAnim() {
		return anim;
	}
	public boolean colisionNave(Rectangle rect){return false;}
	public void setAnim(Animation animExplota) {
		this.anim = animExplota;
	}
	public int getPuntos() {
		return puntos;
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
	public boolean isDibujar() {
		return dibujar;
	}
	public void setDibujar(boolean dibujar) {
		this.dibujar = dibujar;
	}
	public boolean isMover() {
		return mover;
	}
	public void setMover(boolean mover) {
		this.mover = mover;
	}
}
