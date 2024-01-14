package domain;

import java.util.Random;

import view.StableGUI;

public class PonyFBehaviour {

	PonyF pF;
	int x;
	int y;
	StableGUI gui;
	Random r;
	
	public PonyFBehaviour(PonyF pF,StableGUI gui) {
		this.pF=pF;
		x=pF.getX();
		y=pF.getY();
		this.gui=gui;
		r = new Random();
	}
	
    public void moveRandom() {
    	 int movePossibles = r.nextInt(4);  

        switch (movePossibles) {
            case 0: 
            	
            	if(x-1 >= 0 && x < 20) {
            		x--;
            	}else {
            		x=19;
            	}
                break;
            case 1: 
            	
            	if(x >= 0 && x+1 < 20) {
            		x++;
            	}else {
            		x=0;
            	}
                break;
            case 2: 
            	
            	if( y - 1 >= 0 && y < 20) {
            		y--;
            	}else {
            		y=19;
            	}
                break;
            case 3: 
            	
            	if(y >= 0 && y + 1 < 20) {
            		y++;
            	}else {
            		y=0;
            	}
                break;
        }
    }

    public int randomMatrixPoint() {
    	Random random =new Random();
    	return random.nextInt(20);
    }


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}
	
}
