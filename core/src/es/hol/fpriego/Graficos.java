package es.hol.fpriego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

public class Graficos implements Disposable{
	
	private TextureAtlas atlas;
	private TextureRegion regionNaves,regionDisparos,regionAsteroide,regionPowerups,regionFondo,regionDisparosEnem,regionMini;
	private TextureRegion[] explotaFrames,rompeFrames;
	private Sprite fondoSprite,naveSprite,enemy1Sprite,enemy2Sprite,enemy3Sprite,powDisparoSprite,powPuntosSprite,
		powVidaSprite,disparoPlayerSprite,disparoEnemySprite,asteroideSprite,miniAsteroideSprite,miniPlayerSprite;
	private Sprite[] enemSprites;
	private Skin skin;
	private Animation animExplota,animRompe;
	private static final int EXPLOTA_COLS = 4;
	private static final int EXPLOTA_ROWS = 4;
	private static final int ROMPE_COLS = 4;
	private static final int ROMPE_ROWS = 2;
	private Texture expTexture,romTexture,barraVidaTex,vidaTex;
	
	public Graficos() {
		
		atlas = new TextureAtlas("LOS_02.atlas");
		setSkin(new Skin(Gdx.files.internal("skin/uiskin.json")));
		expTexture = new Texture("explosion.png");
		romTexture = new Texture("romper.png");
		setBarraVidaTex(new Texture("black_bar_small2.png"));
		setVidaTex(new Texture("vidaBar.png"));
		
		regionMini = atlas.findRegion("miniPlayer");
		regionNaves = atlas.findRegion("naves");
		regionDisparos = atlas.findRegion("disparosPlayer");
		regionDisparosEnem = atlas.findRegion("disparosEnem");
		regionAsteroide = atlas.findRegion("asteroides");
		regionPowerups = atlas.findRegion("powerups");
		regionFondo = atlas.findRegion("fondo");
		
		TextureRegion[][] exTemp = TextureRegion.split(expTexture, expTexture.getWidth()/EXPLOTA_COLS, 
				expTexture.getHeight()/EXPLOTA_ROWS);
		explotaFrames = new TextureRegion[EXPLOTA_COLS * EXPLOTA_ROWS];
		int index = 0;
		for(int i=0;i<EXPLOTA_ROWS;i++){
			for(int j=0;j<EXPLOTA_COLS;j++){
				explotaFrames[index++] = exTemp[i][j];
			}
		}
		animExplota = new Animation(0.025f, explotaFrames);
		
		TextureRegion[][] romTemp = TextureRegion.split(romTexture, romTexture.getWidth()/ROMPE_COLS, 
				romTexture.getHeight()/ROMPE_ROWS);
		rompeFrames = new TextureRegion[ROMPE_COLS * ROMPE_ROWS];
		index = 0;
		for(int i=0;i<ROMPE_ROWS;i++){
			for(int j=0;j<ROMPE_COLS;j++){
				rompeFrames[index++] = romTemp[i][j];
			}
		}
		animRompe = new Animation(0.040f, rompeFrames);
		
		fondoSprite = new Sprite(regionFondo,0,0,regionFondo.getRegionWidth(),regionFondo.getRegionHeight());
		naveSprite = new Sprite(regionNaves,0,0,40,32);
		enemy1Sprite = new Sprite(regionNaves,40,0,36,32);
		enemy2Sprite = new Sprite(regionNaves,38,32,32,32);
		enemy3Sprite = new Sprite(regionNaves,0,32,38,32);
		powDisparoSprite = new Sprite(regionPowerups,0,16,16,16);
		powPuntosSprite = new Sprite(regionPowerups,0,0,16,16);
		powVidaSprite = new Sprite(regionPowerups,16,0,16,16);
		disparoPlayerSprite = new Sprite(regionDisparos,32,0,8,16);
		disparoEnemySprite = new Sprite(regionDisparosEnem,32,0,8,16);
		asteroideSprite = new Sprite(regionAsteroide,0,0,32,32);
		miniAsteroideSprite = new Sprite(regionAsteroide,0,32,16,16);
		setMiniPlayerSprite(new Sprite(regionMini));
		
		setEnemSprites(new Sprite[]{enemy1Sprite,enemy2Sprite,enemy3Sprite});
		
	}

	public Skin getSkin() {
		return skin;
	}

	public void setSkin(Skin skin) {
		this.skin = skin;
	}

	public Sprite getFondoSprite() {
		return fondoSprite;
	}


	public void setFondoSprite(Sprite fondoSprite) {
		this.fondoSprite = fondoSprite;
	}

	public Sprite getNaveSprite() {
		return naveSprite;
	}


	public void setNaveSprite(Sprite naveSprite) {
		this.naveSprite = naveSprite;
	}


	public Sprite getEnemy1Sprite() {
		return enemy1Sprite;
	}


	public void setEnemy1Sprite(Sprite enemy1Sprite) {
		this.enemy1Sprite = enemy1Sprite;
	}


	public Sprite getEnemy2Sprite() {
		return enemy2Sprite;
	}


	public void setEnemy2Sprite(Sprite enemy2Sprite) {
		this.enemy2Sprite = enemy2Sprite;
	}


	public Sprite getEnemy3Sprite() {
		return enemy3Sprite;
	}


	public void setEnemy3Sprite(Sprite enemy3Sprite) {
		this.enemy3Sprite = enemy3Sprite;
	}


	public Sprite getPowDisparoSprite() {
		return powDisparoSprite;
	}


	public void setPowDisparoSprite(Sprite powDisparoSprite) {
		this.powDisparoSprite = powDisparoSprite;
	}


	public Sprite getPowPuntosSprite() {
		return powPuntosSprite;
	}


	public void setPowPuntosSprite(Sprite powPuntosSprite) {
		this.powPuntosSprite = powPuntosSprite;
	}


	public Sprite getPowVidaSprite() {
		return powVidaSprite;
	}


	public void setPowVidaSprite(Sprite powVidaSprite) {
		this.powVidaSprite = powVidaSprite;
	}


	public Sprite getDisparoPlayerSprite() {
		return disparoPlayerSprite;
	}


	public void setDisparoPlayerSprite(Sprite disparoPlayerSprite) {
		this.disparoPlayerSprite = disparoPlayerSprite;
	}


	public Sprite getDisparoEnemySprite() {
		return disparoEnemySprite;
	}


	public void setDisparoEnemySprite(Sprite disparoEnemySprite) {
		this.disparoEnemySprite = disparoEnemySprite;
	}


	public Sprite getAsteroideSprite() {
		return asteroideSprite;
	}


	public void setAsteroideSprite(Sprite asteroideSprite) {
		this.asteroideSprite = asteroideSprite;
	}


	public Sprite getMiniAsteroideSprite() {
		return miniAsteroideSprite;
	}


	public void setMiniAsteroideSprite(Sprite miniAsteroideSprite) {
		this.miniAsteroideSprite = miniAsteroideSprite;
	}


	public Animation getAnimExplota() {
		return animExplota;
	}

	public void setAnimExplota(Animation animExplota) {
		this.animExplota = animExplota;
	}

	public Animation getAnimRompe() {
		return animRompe;
	}

	public void setAnimRompe(Animation animRompe) {
		this.animRompe = animRompe;
	}

	public Sprite[] getEnemSprites() {
		return enemSprites;
	}

	public void setEnemSprites(Sprite[] enemSprites) {
		this.enemSprites = enemSprites;
	}

	public Texture getBarraVidaTex() {
		return barraVidaTex;
	}

	public void setBarraVidaTex(Texture barraVidaTex) {
		this.barraVidaTex = barraVidaTex;
	}

	public Texture getVidaTex() {
		return vidaTex;
	}

	public void setVidaTex(Texture vidaTex) {
		this.vidaTex = vidaTex;
	}

	public Sprite getMiniPlayerSprite() {
		return miniPlayerSprite;
	}

	public void setMiniPlayerSprite(Sprite miniPlayerSprite) {
		this.miniPlayerSprite = miniPlayerSprite;
	}

	@Override
	public void dispose() {
		vidaTex.dispose();
		barraVidaTex.dispose();
		romTexture.dispose();
		expTexture.dispose();
		atlas.dispose();
	}

}
