/**
 * @(#)GamePanel.java
 *
 *
 * @author 
 * @version 1.00 2018/6/15
 */

import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

class GamePanel extends JPanel {
	
	private boolean[] keys;
	private int screenX = 1900;
	private int screenY = 1000;
	private int menuCountR = 0;
	private int menuCountC = 0;
	private String screen = "menu";
	private Image titleText, playText, controlsText, controlsPic, creditsText, AlinaYildirimText, quitText, backSpaceText;
	private Image spacePic, map1, map2, map3, lava, castle, diedText, completeText, gameCompleteText, gameOverText, loadingPic, map1Back, map2Back, map3Back;
	private double lavaX;
	private int time = 0, cooldown = 100, level = 1;
	private Rectangle playerRect, healthRect;
	Player man;
	String playerDirection, playerAction;  //playerDirection keeps the direction of the player  //playerAction keeps the action of the player
	
	Rectangle levelCompleteRect;
	
	int starCoinCount = 0, loadingCount = 0;
	Integer coin, starCoin;
	Image coinBig, coinImage, starCoinImage, starCoinBW, starCoinSmall, cannonPic, bullet;
	Image[] coinImages, starCoinImages;
	ArrayList <Coin> allCoins;
	ArrayList <StarCoin> allStarCoins, starCoinsPicked;
	Rectangle coinRect, starCoinRect, RealRect;
	Rectangle whiteRect = new Rectangle(screenX / 2 - 500, screenY / 2 - 200, 1000, 400);
	int coinCount = 0;
	
	ArrayList<Arrow>arrows = new ArrayList<Arrow>();
	ArrayList<Arrow>eArrows = new ArrayList<Arrow>();
	ArrayList<Block>blocks = new ArrayList<Block>();
	ArrayList<Cannon>cannons = new ArrayList<Cannon>();
	ArrayList<Heart>hearts = new ArrayList<Heart>();
	Rectangle heartRect;
	private Image heart;
	
	ArrayList <Fireball> fireballs;
	Rectangle fireRect;
	Image fireball;
	int fireCount = 0;
	double fireAng = 0;
	
	private Image manImage;
	private Image[] manImages;
	private double manFrame;
	
	private BufferedImage mask = null;
	
	int green, red, bronze, yellow, black, darkRed, white;
	
	public GamePanel(){
		/////////////////////////////////MENU////////////////////////////////////////////////
		titleText = new ImageIcon("menuText/titleText.png").getImage();
		playText = new ImageIcon("menuText/playText.png").getImage();
		playText = playText.getScaledInstance(300, 100, Image.SCALE_SMOOTH);
		controlsText = new ImageIcon("menuText/controlsText.png").getImage();
		controlsText = controlsText.getScaledInstance(300, 100, Image.SCALE_SMOOTH);
		creditsText = new ImageIcon("menuText/creditsText.png").getImage();
		creditsText = creditsText.getScaledInstance(300, 100, Image.SCALE_SMOOTH);
		quitText = new ImageIcon("menuText/quitText.png").getImage();
		quitText = quitText.getScaledInstance(300, 100, Image.SCALE_SMOOTH);
		backSpaceText = new ImageIcon("menuText/backSpaceText.png").getImage();
		/////////////////////////////////////CONTROLS/////////////////////////////////////////
		controlsPic = new ImageIcon("menuText/controlsPic.png").getImage();
		/////////////////////////////////////CREDITS/////////////////////////////////////////
		AlinaYildirimText = new ImageIcon("menuText/AlinaYildirimText.png").getImage();
		//////////////////////////////////////////////////////////////////////////////////////		
		diedText = new ImageIcon("gameOver/diedText.png").getImage();
		completeText = new ImageIcon("gameOver/completeText.png").getImage();
		gameCompleteText = new ImageIcon("gameOver/gameCompleteText.png").getImage();
		gameOverText = new ImageIcon("gameOver/gameOverText.png").getImage();
		
		keys = new boolean [KeyEvent.KEY_LAST + 1];

		spacePic = new ImageIcon("images/spacePic.jpg").getImage();
		spacePic = spacePic.getScaledInstance(1900, 1000,Image.SCALE_SMOOTH);
		loadingPic = new ImageIcon("images/loadingPic.jpeg").getImage();
		loadingPic = loadingPic.getScaledInstance(1900, 1000, Image.SCALE_SMOOTH);
		map1 = new ImageIcon("images/map1.png").getImage();
		map2 = new ImageIcon("images/map2.png").getImage();
		map3 = new ImageIcon("images/map3.png").getImage();
		map1Back = new ImageIcon("images/map1Back.jpg").getImage();
		map1Back = map1Back.getScaledInstance(1900, 1000,Image.SCALE_SMOOTH);
		map2Back = new ImageIcon("images/map2Back.jpg").getImage();
		map2Back = map2Back.getScaledInstance(1900, 1000,Image.SCALE_SMOOTH);
		map3Back = new ImageIcon("images/map3Back.jpg").getImage();
		map3Back = map3Back.getScaledInstance(1900, 1000,Image.SCALE_SMOOTH);
		cannonPic = new ImageIcon("images/cannon.png").getImage();
		cannonPic = cannonPic.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		bullet = new ImageIcon("images/bulletBill.png").getImage();
		bullet = bullet.getScaledInstance(50, 25, Image.SCALE_SMOOTH);
		
		lava = new ImageIcon("images/lava.png").getImage();
		lavaX = -1900;
		
		castle = new ImageIcon("images/castle.png").getImage();
		castle = castle.getScaledInstance((int) (castle.getWidth(null) * 2), (int) (castle.getHeight(null) * 2), Image.SCALE_SMOOTH);
		
		playerDirection = "right";
		playerAction = "fall";
		
		loadAll();
		
		man = new Player(mask);
		
		starCoinSmall = new ImageIcon("coin/starCoin0.png").getImage();
		starCoinBW = new ImageIcon("coin/starCoinBW.png").getImage();
		
		heart = new ImageIcon("images/heart.png").getImage();
		heart = heart.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		
		coinImages = new Image[10];
    	for (int i = 0; i < 10; i++){
    		Integer coinNum = i;
    		String imageName = "coin/coin";
    		coinImage = new ImageIcon(imageName + coinNum.toString() + ".png").getImage();
    		coinImages[i] = coinImage;
    	}
    	coinBig = coinImages[9];
    	coinBig = coinBig.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
    	
    	
    	starCoinImages = new Image[10];
    	for (int i = 0; i < 10; i++){
    		Integer starCoinNum = i;
    		String imageName = "coin/starCoin";
    		starCoinImage = new ImageIcon(imageName + starCoinNum.toString() + ".png").getImage();
    		starCoinImages[i] = starCoinImage;
    	}
    	
    	fireball = new ImageIcon("images/fireball.png").getImage();
    	fireball = fireball.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    	
    	manImages = new Image[41];
    	for (int i = 0; i < 41; i++){
    		Integer manNum = i;
    		String imageName = "char/ness";
    		manImage = new ImageIcon(imageName + manNum.toString() + ".png").getImage();
    		manImage = manImage.getScaledInstance((int) (manImage.getWidth(null) * 2), (int) (manImage.getHeight(null) * 2), Image.SCALE_SMOOTH);
    		manImages[i] = manImage;
    	}
	}
	public void loadAll(){//method loads all objects into the game
		loadImage(level);
		loadHearts();
		loadCoins();
		loadStarCoins();
		loadFireballs();
		loadBlocks();
		loadCannons();
	}
	//////////////////////////////////////////////////////////////////////////////
	public void loadImage(int level){//method loads in map based on level
		try {
			if(level == 1){
				mask = ImageIO.read(new File("images/map1Mask.png"));
			}
			else if(level == 2){
				mask = ImageIO.read(new File("images/map2Mask.png"));	
			}
			else{
				mask = ImageIO.read(new File("images/map3Mask.png"));
			}
		} 
		catch (IOException e) {
		}
		green = getPixelCol(mask, 25, 25);
		red = getPixelCol(mask, 75, 25); //for instant death areas
		bronze = getPixelCol(mask, 125, 25); //regular coins
		yellow = getPixelCol(mask, 175, 25); //star coins
		black = getPixelCol(mask, 225, 25);
		darkRed = getPixelCol(mask, 375, 25); //heart
    }
    
    public int getPixelCol(BufferedImage mask, int xx, int yy){
    	return mask.getRGB(xx , yy);
    }
    /////////////////////////////////COINS////////////////////////////////////////
    public void loadCoins(){
    	allCoins = new ArrayList <Coin>();
    	for (int i = 0; i < 500; i++){
			for (int j = 0; j < 20; j++){
				if (getPixelCol(mask, i * 50, j * 50) == bronze && j != 0){ //cannot be 2 because on the mask that space is the base colour (we dont want a coin being created there)
					allCoins.add(new Coin(i * 50, j * 50)); //wherever there is a bronze space on the mask, create a coin object there
				}
			}
    	}
    }
    
    public void checkCoin(){
    	for (int i = 0; i < allCoins.size(); i++){
    		coinRect = new Rectangle(allCoins.get(i).getX() - man.getX(), allCoins.get(i).getY(), 50, 50); //smoother to check rectangles/squares than a point on the coin
    		if (playerRect.intersects(coinRect)){ //player picks up coin
    			if (!allCoins.get(i).getPicked()){	
    				allCoins.get(i).setPickedTrue();
    				coinCount++;
    				if (coinCount % 100 == 0){ //when 100 coins gave been picked up, give the user another life
    					man.addLife();
    				}
    			}
    		}
    		if (allCoins.get(i).getPicked() == true){ //once coin is picked up, used for animation
    			if (allCoins.get(i).getY() - allCoins.get(i).getNewY() < 50){ // how far the coin will raise
    				allCoins.get(i).addNewY(); //raise the coin
    			}
    			else{
    				allCoins.remove(i); //once the coin gets high enough, get rid of it
    				//ADD TO PLAYER SCORE //add to the player score
    			}	
    		}
    	}
    }
    	
	public void coinFrameIncrease(){
		for (int i = 0; i < allCoins.size(); i++){
			if (allCoins.get(i).getPicked() == true){
				allCoins.get(i).speedFrame(); //increase frame + framerate of specific coin if picked up
			}
			else{
				allCoins.get(i).increaseFrame(); //increase frame of all coins at general rate
			}
		}
	}
	////////////////////////////////////HEARTS/////////////////////////////////////
	public void loadHearts(){
		hearts = new ArrayList <Heart>();
    	for (int i = 0; i < 500; i++){
			for (int j = 0; j < 20; j++){
				if (getPixelCol(mask, i * 50, j * 50) == darkRed && j != 0){
					Heart h = new Heart(i * 50, j * 50);
					hearts.add(h); 
				}
			}
    	}
	}
	
	public void checkHearts(){//method checks hearts for collision
		for (int i = 0; i < hearts.size(); i++){
    		heartRect = new Rectangle(hearts.get(i).getX() - man.getX(), hearts.get(i).getY(), 50, 50); //smoother to check rectangles/squares than a point on the star coin
    		if (playerRect.intersects(heartRect)){ //player picks up heart		
    			hearts.remove(hearts.get(i));//removes heart
    			man.addHealth(50);//adds 50 health
    		}
    	}
	}
	////////////////////////////////////BLOCKS/////////////////////////////////////
	public void loadBlocks(){//method loads in block objects
    	for (int i = 0; i < 500; i++){
			for (int j = 0; j < 20; j++){
				if (getPixelCol(mask, i * 50, j * 50) == green){//creates block object where there is green
					Block block = new Block(i * 50, j * 50, "");
					blocks.add(block); 
				}
			}
    	}
    }
	///////////////////////////////STAR COINS////////////////////////////////////////
    public void loadStarCoins(){
    	allStarCoins = new ArrayList <StarCoin>();
    	starCoinsPicked = new ArrayList <StarCoin>();
    	int n = 0; //keeps track of which coin it is (helps the player know which coins he needs to pick up (or is has skipped one)
    	for (int i = 0; i < 500; i++){
			for (int j = 0; j < 20; j++){
				if (getPixelCol(mask, i * 50, j * 50) == yellow && j != 0){ //cannot be 3 because on the mask that space is the base colour (we dont want a star coin being created there)
					allStarCoins.add(new StarCoin(i * 50 - 25, j * 50 - 25, n)); //wherever there is a yellow space on the mask, create a coin object there
					n++;
				}
			}
    	}
    }
    
    public void checkStarCoin(){
    	for (int i = 0; i < allStarCoins.size(); i++){
    		starCoinRect = new Rectangle(allStarCoins.get(i).getX() - man.getX(), allStarCoins.get(i).getY(), 100, 100); //smoother to check rectangles/squares than a point on the star coin
    		if (playerRect.intersects(starCoinRect)){ //player picks up coin			
    			if (!allStarCoins.get(i).getPicked()){	
    				allStarCoins.get(i).setPickedTrue();
    			}
    		}
    		if (allStarCoins.get(i).getPicked() == true){ //once coin is picked up, used for animation
    			if (allStarCoins.get(i).getY() - allStarCoins.get(i).getNewY() < 100){ // how far the coin will raise
    				allStarCoins.get(i).addNewY(); //raise the coin
    			}
    			else{
    				starCoinsPicked.add(allStarCoins.get(i)); //add to array (used for showing user which coins they have/have not picked up)
    				if(starCoinsPicked.size() == 3){//if all 3 starcoins picked
    					man.addLife();//add 1 to player's life
    				}
    				allStarCoins.remove(i); //once the coin gets high enough, get rid of it
    				starCoinCount ++;
    				//ADD TO PLAYER SCORE
    			}	
    		}
    	}
    }
    	
	public void starCoinFrameIncrease(){
		for (int i = 0; i < allStarCoins.size(); i++){
			if (allStarCoins.get(i).getPicked() == true){
				allStarCoins.get(i).speedFrame(); //increase frame + framerate of specific coin if picked up
			}
			else{
				allStarCoins.get(i).increaseFrame(); //increase frame of all coins at general rate
			}
		}
	}
	//////////////////////////////////////////////////////////////////////////////
		
	public void move(){//player movement
		//////////////////////////////HORIZONTAL MOVEMENT//////////////////////////////
		if (keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]){
			if (playerDirection.equals("left")){
				man.setVx(0); //makes for easier transitions when switching direction (less snappy)
				manFrame = 0; //restart the frame loop
				playerDirection = "right";
			}
			if (!playerAction.equals("run") && !man.getJump()){
				playerAction = "run";
				manFrame = 0;
			}
			man.move(playerDirection);
			if (manFrame < 7){ //7 frames for this movement
				manFrame += 0.1;
			}
			else{
				manFrame = 0;		
			}
		}
		else if (keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]){
			if (playerDirection.equals("right")){
				man.setVx(0);
				manFrame = 0;
				playerDirection = "left";
			}
			if (!playerAction.equals("run") && !man.getJump()){
				playerAction = "run";
				manFrame = 0;
			}
			man.move(playerDirection);
			if (manFrame < 7){ //7 frames for this movement
				manFrame += 0.1;
			}
			else{
				manFrame = 0;		
			}
		}
		else{
			if (man.getVx() != 0){
				man.slowDown(playerDirection);
				if (manFrame < 7){ //7 frames for this movement
					manFrame += 0.08;
				}
				else{
					manFrame = 0;		
				}
			}
			else{
				playerAction = "stand";
				if (manFrame < 4){ //4 frames for this movement
					manFrame += 0.05;
				}
				else{
					manFrame = 0;		
				}
			}
		}
		///////////////////////////////VERTICAL MOVEMENT///////////////////////////////
		if (keys[KeyEvent.VK_SPACE] && !man.getJump() && man.getGround()){ //so the user cannot double jump or jump after falling from a platform
			man.setVy(-20);
			man.setJump(true); //used in player class to call jump
			man.setGround(false);
			if (!playerAction.equals("jump")){
				playerAction = "jump";
				manFrame = 0;
			}
		}
		if (man.getJump()){
			man.jump();
			playerAction = "jump";
			if (manFrame < 1){ //1 frame for this movement
				manFrame += 0.5;
			}
			else{
				manFrame = 0;		
			}
		}
		else{
			man.fall();
			if (!man.getGround()){
				playerAction = "fall";
				manFrame = 0;
				if (manFrame < 1){ //1 frame for this movement
					manFrame += 0.5;
				}
				else{
					manFrame = 0;		
				}
			}
			
		}
		//////////////////////////////////////////////////////////////////////////////
	}
	//////////////////////////////FIREBALLS///////////////////////////////////////
	public void loadFireballs(){//method that loads in fireball objects
		fireballs = new ArrayList<Fireball>();
		for (int i = 0; i < 500; i++){
			for (int j = 0; j < 20; j++){
				if (getPixelCol(mask, i * 50, j * 50) == red && j != 0){ //cannot be 2 because on the mask that space is the base colour (we dont want a coin being created there)
					fireballs.add(new Fireball(i * 50 - 25, 900, 1000 - j * 50));//creates a fireball object where there is red
				}
			}
		}
	}
	
	public void jumpFireballs(){//method that makes fireballs jump
		for (int i = 0; i < fireballs.size(); i++){
			fireballs.get(i).refresh();
			if (!fireballs.get(i).getRest()){//if not at rest
				fireballs.get(i).jump();//call jump method
			}
			if (fireballs.get(i).getY() >= 950){
				fireballs.get(i).reset();
			}
		}
	}
	
	public void checkFireballs(){//checks for collision with fireballs and player
		for(int i = 0; i < fireballs.size(); i++){
			fireRect = new Rectangle(fireballs.get(i).getX() - man.getX(), fireballs.get(i).getY(), 50, 50);
			if(fireRect.intersects(playerRect)){//player takes damage if collision
				man.takeDamage(0.6);
			}
		}
	}
	
	public void drawFireball(Graphics2D g2,int x,int y){//method draws fireball objects
		AffineTransform saveXform = g2.getTransform();
		AffineTransform at = new AffineTransform();
		int w = fireball.getWidth(this);
		int h = fireball.getHeight(this);
		at.rotate(fireAng, x + w / 2, y + h / 2);
		g2.transform(at);
		g2.drawImage(fireball, x, y, this);
		g2.setTransform(saveXform);		
	}
	///////////////////////////////////////////CANNONS///////////////////////////////////
	public void loadCannons(){//method loads in cannon objects
		for(int i = 0; i < 500; i++){
			for(int j = 0; j < 20; j++){
				if(getPixelCol(mask, i * 50, j * 50) == black && j != 0){//creates cannon object where there is black on mask
					cannons.add(new Cannon(i * 50, j * 50, 1000 - j * 50));
				}
			}
		}
	}
	//////////////////////////////////////////////////////////////////////////////
	public void moveLava(){//method moves lava image
		if (lavaX >= 0){
			lavaX = -1900; //move both pictures to the front again (so there is a constant flow of lava)
		}
		else{
			lavaX += 0.4; //speed of lava
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////
	public void setKey(int k, boolean v) {
    	keys[k] = v;
    }
 	
	public void respawn(){//method for respawning player
		man.setYPos(400);
		man.setXPos(100);
		man.setX(0);//set player to beginning (previous 3 lines)
		man.setVx(0);//stops any movement
		man.addHealth(100);//resets player health to 100
		hearts.clear();//clears hearts
		loadHearts();//reloads hearts again
		eArrows.clear();//clears enemy bullets
		screen = "game";//reset screen to game
	}
	
	public void shootCannons(){//method for shooting cannons
		if(time >= cooldown){//if cooldown is reached
			for(int i = 0; i < cannons.size(); i++){//shoot all cannons
				Cannon cannon = cannons.get(i);
				eArrows.add(cannon.shoot(man));
			}
			time = 0;//reset time
		}
		for(int i = 0; i < eArrows.size(); i++){//for all arrows
			Arrow arrow = eArrows.get(i);
			int d = Math.abs(man.getX() - arrow.getX());//distance variable 
			if(d > 4000){//if arrow is at least 4000 pixels away from player delete it	
				eArrows.remove(arrow);
				i++;
			}
			arrow.move();//move arrow
			for(int j = 0; j < blocks.size(); j++){
				Block block = blocks.get(j);
				if(arrow.getRect(man).intersects(block.getRect())){//checks intersection with arrows and blocks
					eArrows.remove(arrow);//remove arrow if collision
					i++;
				}
			}
			if(arrow.getRealRect(man).intersects(RealRect)){//checks collision of arrow with player
				eArrows.remove(arrow);//removes arrow
				man.takeDamage(arrow.getDamage());//damage player
				i++;
			}
		}
		time++;//add to time (cannon shooting timer)
	}
	
	public void resetAll(){//method resets game
		//method clears all ArrayList
		eArrows.clear();
		blocks.clear();
		cannons.clear();
		hearts.clear();
		fireballs.clear();
		allStarCoins.clear();
		allCoins.clear();
		starCoinsPicked.clear();
		//set coin count to 0
		coinCount = 0;
		//set level to 1
		level = 1;
		man = new Player(mask);//create new man passing in mask
		loadAll();//load all objects
	}
	
	public void refresh(){//refresh method
		if (screen.equals("menu")){//if on menu scree
		//menuCountC and menuCountR keep track of the row and colum the player is currently selecting
			if (keys[KeyEvent.VK_W]  || keys[KeyEvent.VK_UP]){
				if (menuCountC == 1){
					menuCountC = 0;
				}
			}
			if (keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]){
				if (menuCountR == 1){
					menuCountR = 0;
				}
			}
			if (keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN]){
				if (menuCountC == 0){
					menuCountC = 1;
				}
			}
			if (keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]){
				if (menuCountR == 0){
					menuCountR = 1;
				}
			}
			if (keys[KeyEvent.VK_ENTER]){//if pressed enter
				if (menuCountR == 0){
					if (menuCountC == 0){
						screen = "loading game";
					}
					else{
						screen = "credits";
					}
				}
				else{
					if (menuCountC == 0){
						screen = "controls";
					}
					else{
						screen = "quit";
					}
				}
				menuCountR = 0;
				menuCountC = 0;
			}
		}
		else if (screen.equals("controls")){
			if (keys[KeyEvent.VK_BACK_SPACE]){
				screen = "menu";
			}
		}
		else if (screen.equals("credits")){
			if (keys[KeyEvent.VK_BACK_SPACE]){
				screen = "menu";
			}
		}
		else if (screen.equals("quit")){
			System.exit(0);
		}
		else if(screen.equals("dead")){ //if dead
			if(keys[KeyEvent.VK_BACK_SPACE]){ //if clicked backspace respawn
				respawn(); //respawn player
			}
		}
		else if (screen.equals("game")){//if on game screen
			playerRect = new Rectangle(man.getXPos(), man.getYPos(), 68, 84);
			healthRect = new Rectangle(650, 30, (int)((double) man.getHealth()/100 * 600), 50);
			RealRect = new Rectangle(man.getX(), man.getYPos(), 50, 100);
			levelCompleteRect = new Rectangle(25000 - screenX/2 - 5 - man.getX(), 0, 10, 1000);
			//call methods to run game
			move();
			moveLava();
			checkHearts();
			jumpFireballs();
			checkFireballs();
			fireAng += 0.1;
			checkCoin();
			checkStarCoin();
			coinFrameIncrease();
			starCoinFrameIncrease();
			shootCannons();
			if(man.getHealth() <= 0){//if player's health is low
				man.subtractLife();//subtract life
				if(man.getLives() <= 0){//if 0 lives go to game over screen
					screen = "game over";
				}
				else{
					screen = "dead";	
				}
			}
			if (playerRect.intersects(levelCompleteRect)){ //once they reach the end of the level
				if (level == 1 || level ==2){
					screen = "level complete";
				}
				else{
					screen = "game complete";
				}
			}
		}
		else if (screen.equals("game over")){
			if(keys[KeyEvent.VK_ESCAPE]){
				resetAll();
				level = 1;
				loadImage(level);
				man.loadImage(mask);
				screen = "loading menu";
			}
		}
		else if(screen.equals("level complete")){
			man.slowDown(playerDirection);
			man.fall();
			if (manFrame < 4){
				manFrame += 0.05;
			}
			else{
				manFrame = 0;		
			}
			if(keys[KeyEvent.VK_ENTER]){ //everything must be cleared, then reloaded for the next level
				level++;
				eArrows.clear();
				blocks.clear();
				cannons.clear();
				hearts.clear();
				fireballs.clear();
				allStarCoins.clear();
				allCoins.clear();
				starCoinsPicked.clear();
				loadAll();
				man.loadImage(mask);
				respawn();
				screen = "loading game";
			}
		}
		else if (screen.equals("loading game")){
			loadingCount++;
			if (loadingCount >= 100){
				loadingCount = 0;
				screen = "game";
			}
		}
		else if (screen.equals("loading menu")){
			loadingCount++;
			if (loadingCount >= 100){
				loadingCount = 0;
				screen = "menu";
			}
		}
		else if(screen.equals("game complete")){
			man.slowDown(playerDirection); //so player just doesn't stop
			man.fall(); //bring the player back to the ground
			if (manFrame < 4){
				manFrame += 0.05;
			}
			else{
				manFrame = 0;		
			}
			if(keys[KeyEvent.VK_ESCAPE]){
				resetAll();
				screen = "loading menu";
			}
		}
	}
		
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		if (screen.equals("menu")){
			g.drawImage(spacePic, 0, 0, this);
			g.setColor(new Color(0, 0, 0));
			g2.setStroke(new BasicStroke(5));
			g.drawImage(titleText, screenX/2 - titleText.getWidth(null)/2, 100, this);
			g.drawImage(playText, 625, 500, this);
			g.drawImage(controlsText, 975, 500, this);
			g.drawImage(creditsText, 625, 650, this);
			g.drawImage(quitText, 975, 650, this);
			g.drawRect(625 + menuCountR * 350, 500 + menuCountC * 150 , 300, 100);
		}
		else if (screen.equals("play")){
			g.drawImage(spacePic, 0, 0, this);
			g.drawImage(backSpaceText, screenX - backSpaceText.getWidth(null) - 10 , 10, this);
		}
		else if (screen.equals("controls")){
			g.drawImage(spacePic, 0, 0, this);
			g.drawImage(controlsPic, screenX / 2 - controlsPic.getWidth(null) / 2, screenY / 2 - controlsPic.getHeight(null) / 2 - 20, this); 
			g.drawImage(backSpaceText, screenX - backSpaceText.getWidth(null) - 10 , 10, this);
		}
		else if (screen.equals("credits")){
			g.drawImage(spacePic, 0, 0, this);
			g.drawImage(AlinaYildirimText, screenX/2 - AlinaYildirimText.getWidth(null)/2, screenY/2 - AlinaYildirimText.getHeight(null)/2, this);
			g.drawImage(backSpaceText, screenX - backSpaceText.getWidth(null) - 10 , 10, this);
		}
		else if (screen.equals("game")){
			if(level == 1){
				g.drawImage(map1Back, 0, 0, this);
			}
			else if(level == 2){
				g.drawImage(map2Back, 0, 0, this);
			}
			else{
				g.drawImage(map3Back, 0, 0, this);
			}
			g.drawImage(castle, 25000 - screenX/2 - castle.getWidth(null)/2 - man.getX(), 135 , this);
			for (int i = 0; i < fireballs.size(); i++){
				drawFireball(g2, fireballs.get(i).getX() - man.getX(), fireballs.get(i).getY());
			}
			for (int i = 0; i < 15; i++){ //blitting lava across the entire map
				g.drawImage(lava, (int) lavaX - man.getX() + 1900 * i, 900, this);
			}
			if(level == 1){ //map based on level
				g.drawImage(map1, -man.getX(), 0, this);	
			}
			else if(level == 2){
				g.drawImage(map2, -man.getX(), 0, this);
			}
			else{
				g.drawImage(map3, -man.getX(), 0, this);
			}
			if (level != 1){
				g.setColor(new Color(255, 255, 255));
			}
			else{
				g.setColor(new Color(0, 0, 0));
			}
			//g2.fill(playerRect
			g.drawImage(coinBig, 1325, 25 , this);
			String c = "";
			if (coinCount >= 0 && coinCount <= 9){ //shows user current coin count
				c = "x00" + coinCount; //add "" so it creates the string Integer.parseInt() and .toString() did not work)
			}
			else if (coinCount >= 10 && coinCount <= 99){
				c = "x0" + coinCount;
			}
			else{
				c = "x" + coinCount;
			}
	    	g.setFont(new Font("Arial", Font.PLAIN, 50));
			g.drawString(c, 1460 - g.getFontMetrics().stringWidth(c) / 2, 73); //drawing an centering text
	
	
			String l = "Lives:" + man.getLives();
			g.drawString(l, 500 - g.getFontMetrics().stringWidth(l) / 2, 73); //shows users lives
			
			String ll = "";
			if (level == 1){
				ll = "Level 1/3";
			}
			else if (level == 2){
				ll = "Level 2/3";
			}
			else{
				ll = "Level 3/3";
			}
			g.drawString(ll, 200 - g.getFontMetrics().stringWidth(ll) / 2, 73); //shows user level
			
			for (int i = 0; i < 3; i++){
				g.drawImage(starCoinBW, 1460 + ((i + 1) * 110), 10 , this); //black and white images of coins (meaning they havent been picked up yet)
			}
			for (int i = 0; i < starCoinsPicked.size(); i++){
				g.drawImage(starCoinSmall, 1460 + ((starCoinsPicked.get(i).getNum() + 1) * 110), 10 , this); //when the coins have been picked up (displays which one you have picked up)
			}
			for (int i = 0; i < allCoins.size(); i++){
				g.drawImage(coinImages[allCoins.get(i).getFrame()], allCoins.get(i).getX() - man.getX(), allCoins.get(i).getNewY(), this); //displays all regular coins
			}
			for (int i = 0; i < allStarCoins.size(); i++){
				g.drawImage(starCoinImages[allStarCoins.get(i).getFrame()], allStarCoins.get(i).getX() - man.getX(), allStarCoins.get(i).getNewY(), this); //displays all star coins (that have not been picked up yet)
			}
			for (int i = 0; i < hearts.size(); i++){
				g.drawImage(heart, hearts.get(i).getX() - man.getX(), hearts.get(i).getY(), this); //displays all star coins (that have not been picked up yet)
			}
			for(int i = 0; i < eArrows.size(); i++){
	    		Arrow arrow = eArrows.get(i);
	    		g.drawImage(bullet, arrow.getX() - man.getX(), arrow.getY() - 20, this); //arrows/rockets
	    	}
	    	for(int i = 0; i < cannons.size(); i++){
	    		Cannon cannon = cannons.get(i);
	    		g.drawImage(cannonPic, cannon.getX() - man.getX(), cannon.getY(), this); //cannons
	    	}
			////////////////////////////////PLAYER SPRITES DEPENDING ON DIRECTION AND ACTION/////////////////////////////////////
	    	if (playerAction.equals("stand") && playerDirection.equals("right")){
	    		g.drawImage(manImages[8 + (int) manFrame], man.getXPos(), man.getYPos() + 2, this);
	    	}
	    	else if (playerAction.equals("stand") && playerDirection.equals("left")){
	    		int w = manImages[8 + (int) manFrame].getWidth(this);
				int h = manImages[8 + (int) manFrame].getHeight(this);
	    		g.drawImage(manImages[8 + (int) manFrame], man.getXPos() + 50, man.getYPos() + 2, - w, h, this);
	    	}
	    	else if (playerAction.equals("run") && playerDirection.equals("right")){
	    		g.drawImage(manImages[(int) manFrame], man.getXPos() - 5, man.getYPos() + 7, this);
	    	}
	    	else if (playerAction.equals("run") && playerDirection.equals("left")){
	    		int w = manImages[(int) manFrame].getWidth(this);
				int h = manImages[(int) manFrame].getHeight(this);
	    		g.drawImage(manImages[(int) manFrame], man.getXPos() + 72, man.getYPos() + 7, - w, h, this);
	    	}
	    	else if ((playerAction.equals("jump") || playerAction.equals("fall")) && playerDirection.equals("right")){
	    		g.drawImage(manImages[34 + (int) manFrame], man.getXPos() + 5, man.getYPos(), this);
	    	}
	    	else if ((playerAction.equals("jump") || playerAction.equals("fall")) && playerDirection.equals("left")){
	    		int w = manImages[34 + (int) manFrame].getWidth(this);
				int h = manImages[34 + (int) manFrame].getHeight(this);
	    		g.drawImage(manImages[34 + (int) manFrame], man.getXPos() + 65, man.getYPos(), - w, h, this);
	    	}
			//////////////////////////////////////HEALTH BAR/////////////////////////////////////
			g.setColor(new Color(255, 255, 255));
			g2.fillRect(645, 25, 610, 60);
			g.setColor(new Color(255, 0, 0));
			if (healthRect != null){
				g2.fill(healthRect); //created based on users health
			}
			g.setColor(new Color (0, 0, 0));
			/////////////////////////////////////HEALTH NUM//////////////////////////////
			String s = "" + man.getHealth(); //add "" so it creates the string Integer.parseInt() and .toString() did not work)
	    	g.setFont(new Font("Arial", Font.PLAIN, 50));
			g.drawString(s, screenX / 2 - g.getFontMetrics().stringWidth(s) / 2, 73); //drawing an center text
		}
		else if (screen.equals("dead")){
			g.setColor(new Color(255, 255, 255));
			g2.fill(whiteRect);
			String k = "";
			if (man.getLives() == 1){
				k = "You have " + man.getLives() + " life left.";
			}
			else{
				k = "You have " + man.getLives() + " lives left.";
			}
			g.setColor(new Color(0, 0, 0));
			g.setFont(new Font("Arial", Font.PLAIN, 50));
			g.drawString(k, screenX / 2 - g.getFontMetrics().stringWidth(k) / 2, 600);
			g.drawImage(diedText, screenX / 2 - diedText.getWidth(null) / 2, 375, this);		
		}
		else if(screen.equals("game over")){
			g.setColor(new Color(255, 255, 255));
			g2.fill(whiteRect);
			g.drawImage(gameOverText, screenX / 2 - gameOverText.getWidth(null) / 2, 350, this);
			g.setColor(new Color(0, 0, 0));
			g.setFont(new Font("Arial", Font.PLAIN, 50));
			String c = "You ran out of lives.";
			String d = "Press ESCAPE to return to the main menu.";
			g.drawString(c, screenX / 2 - g.getFontMetrics().stringWidth(c) / 2, 520);
			g.drawString(d, screenX / 2 - g.getFontMetrics().stringWidth(d) / 2, 600);
		}
		else if (screen.equals("level complete")){	
			g.drawImage(castle, 25000 - screenX/2 - castle.getWidth(null)/2 - man.getX(), 135 , this);
			if(level == 1){
				g.drawImage(map1, -man.getX(), 0, this);	
			}
			else{
				g.drawImage(map2, -man.getX(), 0, this);
			}
			g.setColor(new Color(255, 255, 255));
			g2.fill(whiteRect);
			g.drawImage(manImages[8 + (int) manFrame], man.getXPos(), man.getYPos() + 2, this);
			g.drawImage(completeText, screenX / 2 - completeText.getWidth(null) / 2, 375, this);
			g.setColor(new Color(0, 0, 0));
			g.setFont(new Font("Arial", Font.PLAIN, 50));
			String d = "";
			if (3 - level == 1){
				d = "You have 1 level left.";
			}
			else{
				d = "You have 2 levels left.";
			}
			g.drawString(d, screenX / 2 - g.getFontMetrics().stringWidth(d) / 2, 600);
		}
		else if (screen.equals("game complete")){
			g.drawImage(castle, 25000 - screenX/2 - castle.getWidth(null)/2 - man.getX(), 135 , this);
			g.drawImage(map3, -man.getX(), 0, this);
			g.drawImage(manImages[8 + (int) manFrame], man.getXPos(), man.getYPos() + 2, this);
			g.setColor(new Color(255, 255, 255));
			g2.fill(whiteRect);
			g.drawImage(gameCompleteText, screenX / 2 - gameCompleteText.getWidth(null) / 2, 375, this);
			g.setColor(new Color(0, 0, 0));
			g.setFont(new Font("Arial", Font.PLAIN, 50));
			String d = "Press ESCAPE to return to the main menu.";
			g.drawString(d, screenX / 2 - g.getFontMetrics().stringWidth(d) / 2, 600);
		}
		else if (screen.equals("loading game") || screen.equals("loading menu")){
			g.drawImage(loadingPic, 0, 0, this);
		}
	}
}