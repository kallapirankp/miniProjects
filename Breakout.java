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
		/* You fill this in, along with any subsidiary methods */
		setup();
		addMouseListeners();
		
	}
	
	public void setup(){
		createBricks();
		createPaddle();
	}
	
	public void createBricks(){
		int bHeight = BRICK_Y_OFFSET;
		for(int i =1; i<=NBRICK_ROWS; i++){
			
			for(int j=0;j<NBRICKS_PER_ROW; j++){
				GRect rect = new GRect(0+(j*(BRICK_WIDTH + BRICK_SEP)), bHeight , BRICK_WIDTH, BRICK_HEIGHT);
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
	public void mousePressed(MouseEvent e){
		double a = paddle.getX();
	}
	public void mouseMoved(MouseEvent e){
		double x = e.getX();
		if (x > WIDTH){ x = WIDTH;}
		System.out.println(+x);
		paddle.move(x,0);
		x = e.getX();
	}
    //Instance Variables
	public GRect paddle;
}
