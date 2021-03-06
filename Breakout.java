/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;

import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		
		setup();
		play();
		
		
	}
	
	public void setup(){
		createBricks();
		createPaddle();
	}
	
	public void createBricks(){
		int bHeight = BRICK_Y_OFFSET;
		for(int i =1; i<=NBRICK_ROWS; i++){
			
			for(int j=0;j<NBRICKS_PER_ROW; j++){
				rect = new GRect(0+(j*(BRICK_WIDTH + BRICK_SEP)), bHeight , BRICK_WIDTH, BRICK_HEIGHT);
				rect.setFilled(true);
				if(i <= NBRICK_ROWS/5){ rect.setFillColor(Color.RED);}
				else if(i > NBRICK_ROWS/5 && i <= 2*(NBRICK_ROWS/5)){rect.setFillColor(Color.ORANGE);}
				else if(i > 2*(NBRICK_ROWS/5) && i <= 3*(NBRICK_ROWS/5)){rect.setFillColor(Color.YELLOW);}
				else if(i > 3*(NBRICK_ROWS/5) && i <= 4*(NBRICK_ROWS/5)){rect.setFillColor(Color.GREEN);}
				else { rect.setFillColor(Color.CYAN); }
				add(rect);
				
			}
			bHeight += BRICK_HEIGHT;
		}
		
	}
	
	public void createPaddle(){
		paddle = new GRect((getWidth()- PADDLE_WIDTH)/2, getHeight()-PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setFillColor(Color.BLACK);
		add(paddle);
	}
	
	public void mouseMoved(MouseEvent e){
		double x = e.getX();
		double a = paddle.getX();
		if (x > getWidth()- PADDLE_WIDTH){ x = getWidth()- PADDLE_WIDTH;}
		paddle.move(x - a, 0);
		a = e.getX();
	}
	
	public void play(){
		
		createBall();
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) {vx = -vx;}
		vy = 3;
		while(true){	
		moveBall();
		if(m < 10) pause(20);
		if(m >= 10 && m < 30) pause(15);
		if(m >= 30 && m < 40) pause(10);
		if(m >= 40) pause(5);
		if(m == 100) break;
		
		addMouseListeners();
		checkForCollison();}
		winner();
	}
	
	private void createBall(){
		ball = new GOval ((getWidth()- BALL_RADIUS)/2, (getHeight()-BALL_RADIUS)/2, BALL_RADIUS, BALL_RADIUS);
		ball.setFilled(true);
		ball.setFillColor(Color.BLACK);
		add(ball);
	}
	
	private void moveBall(){
		
		
		if(ball.getY() >getHeight() - BALL_RADIUS){ endgame(); System.out.println();}
		if(ball.getX() >getWidth()- BALL_RADIUS){ vx = -vx;}
		if(ball.getX() <= 0){ vx = -vx;}
		if(ball.getY()<=0){ vy = -vy;}
		ball.move(vx, vy);
	}
	
	public void checkForCollison(){
		GObject collider = getCollidingObject();
		if (collider == paddle){
			vy = -vy;
			
		}
		if(collider != paddle && collider != null){
			vy = -vy;
			remove(collider);
			m = m+1;
		}
	}
	
	public GObject getCollidingObject(){
		GObject obj1;
		while(true){
		obj1 = getElementAt(ball.getX(), ball.getY());
		if(obj1 != null){  break;}
		obj1 = getElementAt (ball.getX(), ball.getY()+ 2*BALL_RADIUS);
		if(obj1 != null){break;}
		obj1 = getElementAt (ball.getX()+ 2*BALL_RADIUS, ball.getY());
		if(obj1 != null){break;}
		obj1 = getElementAt (ball.getX()+ 2*BALL_RADIUS, ball.getY()+ 2*BALL_RADIUS);
		if(obj1 != null){break;}
		obj1 = null;
		break;
		}
		return obj1;
	}
	public void endgame(){
		n += 1;
		remove(ball);
		while (n<4){
			label = new GLabel("OOPS !! The ball is crashed   Play again",getWidth()/4,getHeight()/2);
			add(label);
			if(n==2){
				GLabel label1 = new GLabel("You have two more attempts",getWidth()/4,0.75*getHeight());
				add(label1);
				pause(800);
				remove(label1);
			}
			if(n==3){
				GLabel label2 = new GLabel("You have one more attempt",getWidth()/4,0.75*getHeight());
				add(label2);
				pause(800);
				remove(label2);
			}
			pause(500);
			remove(label);
			
			System.out.println("n" +n);
			play();
			
		}
		
		GLabel label3 = new GLabel("GAME OVER",getWidth()/4,getHeight()/2);
		add(label3);
		GLabel label4 = new GLabel(" ",getWidth()/4,0.75*getHeight());
		label4.setLabel("YOUR  SCORE IS " +m);
		add(label4);
		
	}
	
	public void winner(){
		remove(ball);
		GLabel label5 = new GLabel("", getWidth()/2, getHeight()/2);
		label5.setLabel("WELL DONE!!!! YOU WON THE GAME");
		add(label5);
	}
    //Instance Variables
	public GRect rect;
	public GRect paddle;
	public double vx, vy;
	public GOval ball;
	public int n=1;
	public int m;
	public GLabel label;
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
