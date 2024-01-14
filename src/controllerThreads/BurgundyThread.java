package controllerThreads;

import domain.Burgundy;
import domain.BurgundyBehaviour;
import logic.ListLogicThreads;
import logic.Suppliers;
import logic.TableWriter;
import view.StableGUI;

public class BurgundyThread extends Thread {

	private Burgundy bur;
	private StableGUI gui;
	private BurgundyBehaviour burBehav;
	private int randomSet=0;
	private boolean pause;
	
	private ListLogicThreads lgTh;

	private int actualTime;
	private int initTime = 0; 
    private int passedTime = 0;
    
    private int tempPauseTime = 0;
    private int tempResumeTime = 0;
    private int tempPauseLength =0;
	
	public BurgundyThread(Burgundy bur, StableGUI gui, ListLogicThreads lgTh) {
		
			this.bur=bur;
			this.gui=gui;
			this.lgTh=lgTh;
			burBehav = new BurgundyBehaviour(this.bur,this.gui);
	}

	@Override
	public void run() {
					
		 initTime = (int) System.currentTimeMillis(); 
	     passedTime = 0;
			 while (passedTime < gui.getTime()) {
				 
				 	burBehav.moveRandom(); 
				 	TableWriter.addBurMMove();
				 	TableWriter.addBurFMove();
				 	if(randomSet==0) {
				 		randomFirstRun();
				 	}
				 		        			     
			        actualTime = (int) System.currentTimeMillis();
			        passedTime = (int) actualTime - initTime;
			        
			        if(gui.getBtnMatrix()[burBehav.getX()][burBehav.getY()].getText().equals("BGM")) {
				 		try {
							sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				 		versusBurgundy();
				 	}
			        
			        gui.getBtnMatrix()[burBehav.getX()][burBehav.getY()].setText(bur.getSpecies());
			        try {
			            sleep(3000); // Espera 3 segundos 
			        } catch (InterruptedException e) {
			            e.printStackTrace();
			        }
			        
			        if(lgTh.randomUseSuppliers()==20) {
			        	randomSupplier();
			        }
			        
			        gui.getBtnMatrix()[burBehav.getX()][burBehav.getY()].setText("");

			        synchronized(this) {
						if(pause) {
							try {
								tempPauseTime = (int) System.currentTimeMillis();
								gui.getBtnMatrix()[burBehav.getX()][burBehav.getY()].setText(bur.getSpecies());
								wait();
								tempResumeTime = (int) System.currentTimeMillis(); // mantiene el tiempo al reanudar
				                tempPauseLength = tempResumeTime - tempPauseTime; // analiza la duración de la pausa
				                initTime += tempPauseLength; 
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
			    }
			 	
	}
	
	public synchronized void pauseThread() {
		this.pause=true;
	}
	
	public synchronized void resumeThread() {
		this.pause=false;
		notify();
		gui.getBtnMatrix()[burBehav.getX()][burBehav.getY()].setText("");
	}
	
	public void randomFirstRun() {
		int tempx = burBehav.randomMatrixPoint();
 		int tempy = burBehav.randomMatrixPoint();
 		gui.getBtnMatrix()[tempx][tempy].setText(bur.getSpecies());
 		burBehav.setX(tempx);
 		burBehav.setY(tempy);
 		randomSet++;
 		gui.getBtnMatrix()[tempx][tempy].setText("");
	}
	
	public synchronized void versusBurgundy() {
		passedTime = 1000000;
		lgTh.getBurArray().remove(bur);
		lgTh.getBurgundyThArray().remove(this);
		System.out.println("Hubo una pelea y murieron Burdeganos");
		TableWriter.addBurMFights();
	}
	
	public synchronized void randomSupplier() {
		TableWriter.addBurMSup();
		switch(Suppliers.randomOptionSupply()) {
		case 0:if(gui.getBtnHay().getText().equals("...")) {
			pauseThread();
			gui.getBtnMatrix()[burBehav.getX()][burBehav.getY()].setText("");
			gui.getBtnHay().setText(bur.getGender());
			Suppliers.consumeHay();
			gui.getHayQuantity().setText("Cantidad: " + Suppliers.getHay());
			
			try {
				sleep(2000);	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			resumeThread();
			gui.getBtnHay().setText("...");
		}
		break;
		case 1:
			if(gui.getBtnFood().getText().equals("...")) {
				pauseThread();
				gui.getBtnMatrix()[burBehav.getX()][burBehav.getY()].setText("");
				gui.getBtnFood().setText(bur.getGender());
				Suppliers.consumeFood();
				gui.getFoodQuantity().setText("Cantidad: " + Suppliers.getFood());
				
				try {
					sleep(3000);	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				resumeThread();
				gui.getBtnFood().setText("...");
			}
			break;
		case 2:
			if(gui.getBtnVitamins().getText().equals("...")) {
				pauseThread();
				gui.getBtnMatrix()[burBehav.getX()][burBehav.getY()].setText("");
				gui.getBtnVitamins().setText(bur.getGender());
				Suppliers.consumeVitamins();
				gui.getVitaminsQuantity().setText("Cantidad: " + Suppliers.getVitamins());
				
				try {
					sleep(2000);	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				resumeThread();
				gui.getBtnVitamins().setText("...");
			}
			break;
		case 3:	
			if(gui.getBtnWater().getText().equals("...")) {
				pauseThread();
				gui.getBtnMatrix()[burBehav.getX()][burBehav.getY()].setText("");
				gui.getBtnWater().setText(bur.getGender());
				Suppliers.consumeWater();
				gui.getWaterQuantity().setText("Cantidad: " + Suppliers.getWater());
				
				try {
					sleep(3000);	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				resumeThread();
				gui.getBtnWater().setText("...");
			}
			break;
			
		}
		
		
	}
	
}
