package logic;

import javax.swing.JOptionPane;

import view.StableGUI;

public class Hurricane extends Thread{

	ListLogicThreads lgTh;
	StableGUI gui;
	private int actualTime;
	private int initTime = 0; 
    private int passedTime = 0;
    private int randomHurricaneSpot;
	
	public Hurricane(StableGUI gui,ListLogicThreads lgTh) {
		this.lgTh=lgTh;
		this.gui=gui;
		randomHurricaneSpot= lgTh.randomHurricaneNumber();
	}
	@Override
	public void run() {
		
		initTime = (int) System.currentTimeMillis(); 
	     passedTime = 0;
		System.out.println("milisegundos: "+ randomHurricaneSpot);
	     while (passedTime < randomHurricaneSpot) {
	    	 actualTime = (int) System.currentTimeMillis();
		     passedTime = (int) actualTime - initTime;
	     }
	     
	     if(lgTh.getMareThArray().get(0).isAlive()){
	    	 System.out.println("milisegundos: "+ randomHurricaneSpot);
	     }else {
	    	 System.out.println("milisegundos: "+ randomHurricaneSpot);
	     }
	     lgTh.finalizeAll();
	     JOptionPane.showMessageDialog(null, "Un huracan se llevó todo :(");
	     
	}
	
}
