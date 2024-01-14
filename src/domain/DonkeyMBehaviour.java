package domain;

import java.util.Random;

import view.StableGUI;

public class DonkeyMBehaviour {

	DonkeyM dkMale;
	int x;
	int y;
	StableGUI gui;
	Random r;
	
	public DonkeyMBehaviour(DonkeyM dkMale,StableGUI gui) {
		this.dkMale=dkMale;
		x=dkMale.getX();
		y=dkMale.getY();
		this.gui=gui;
		r=new Random();
	}
	
    public void moveRandom() {
    	int movePossibles = r.nextInt(4);

        switch (movePossibles) {
            case 0: // Mover  arriba
            	
            	if(x-1 >= 0 && x < 20) {
            		x--;
            	}else {
            		x=19;
            	}
                break;
            case 1: // Mover  abajo
            	
            	if(x >= 0 && x+1 < 20) {
            		x++;
            	}else {
            		x=0;
            	}
                break;
            case 2: // Mover  izquierda
            	
            	if( y - 1 >= 0 && y < 20) {
            		y--;
            	}else {
            		y=19;
            	}
                break;
            case 3: // Mover derecha
            	
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
