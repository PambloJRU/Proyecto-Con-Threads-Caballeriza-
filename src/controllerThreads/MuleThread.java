package controllerThreads;

import domain.Mule;
import domain.MuleBehaviour;
import logic.ListLogicThreads;
import logic.Suppliers;
import logic.TableWriter;
import view.StableGUI;

public class MuleThread extends Thread{

	private Mule mule;
	private StableGUI gui;
	private MuleBehaviour muleBehav;
	private int randomSet=0;
	private boolean pause;
	private ListLogicThreads lgTh;

	private int actualTime;
	private int initTime = 0; 
    private int passedTime = 0;
    
    private int tempPauseTime = 0;
    private int tempResumeTime = 0;
    private int tempPauseLength =0;
	
	public MuleThread(Mule mule, StableGUI gui,ListLogicThreads lgTh) {
		
			this.mule=mule;
			this.gui=gui;
			this.lgTh=lgTh;
			muleBehav = new MuleBehaviour(this.mule,this.gui);
	}

	@Override
	public void run() {
					
		 initTime = (int) System.currentTimeMillis(); 
	     passedTime = 0;
			 while (passedTime < gui.getTime()) {
				 
				 	muleBehav.moveRandom(); 
				 	TableWriter.addMuleMMove();
				 	TableWriter.addMuleFMove();
				 	if(randomSet==0) {
				 		randomFirstRun();
				 	}
				       
				 	
				 	
			        actualTime = (int) System.currentTimeMillis();
			        passedTime = (int) actualTime - initTime;
			        
			        if(gui.getBtnMatrix()[muleBehav.getX()][muleBehav.getY()].getText().equals("MM")) {
				 		try {
							sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				 		versusMule();
				 	}

			        gui.getBtnMatrix()[muleBehav.getX()][muleBehav.getY()].setText(mule.getSpecies());
			        try {
			            sleep(3000); // Espera 3 segundos
			        } catch (InterruptedException e) {
			            e.printStackTrace();
			        }
			        
			        if(lgTh.randomUseSuppliers()==20) {
			        	randomSupplier();
			        }
			        
			        gui.getBtnMatrix()[muleBehav.getX()][muleBehav.getY()].setText("");
			        
			        synchronized(this) {
						if(pause) {
							try {
								tempPauseTime = (int) System.currentTimeMillis();
								gui.getBtnMatrix()[muleBehav.getX()][muleBehav.getY()].setText(mule.getSpecies());
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
	
	public synchronized void resumeThread(){
		this.pause=false;
		notify();
		gui.getBtnMatrix()[muleBehav.getX()][muleBehav.getY()].setText("");
	}
	
	public void randomFirstRun() {
		int tempx = muleBehav.randomMatrixPoint();
 		int tempy = muleBehav.randomMatrixPoint();
 		gui.getBtnMatrix()[tempx][tempy].setText(mule.getSpecies());
 		muleBehav.setX(tempx);
 		muleBehav.setY(tempy);
 		randomSet++;
 		gui.getBtnMatrix()[tempx][tempy].setText("");
	}
	
	public synchronized void versusMule() {
		if(mule.getGender().equals("MM")) {
			passedTime = 1000000;
			lgTh.getMuleArray().remove(mule);
			lgTh.getMuleThArray().remove(this);
			System.out.println("Hubo una pelea y murieron Mulos");
			TableWriter.addMuleMFights();
		}
		
	}
	
	public synchronized void randomSupplier() {
		TableWriter.addMuleMSup();
		switch(Suppliers.randomOptionSupply()) {
		case 0:if(gui.getBtnHay().getText().equals("...")) {
			pauseThread();
			gui.getBtnMatrix()[muleBehav.getX()][muleBehav.getY()].setText("");
			gui.getBtnHay().setText(mule.getGender());
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
				gui.getBtnMatrix()[muleBehav.getX()][muleBehav.getY()].setText("");
				gui.getBtnFood().setText(mule.getGender());
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
				gui.getBtnMatrix()[muleBehav.getX()][muleBehav.getY()].setText("");
				gui.getBtnVitamins().setText(mule.getGender());
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
				gui.getBtnMatrix()[muleBehav.getX()][muleBehav.getY()].setText("");
				gui.getBtnWater().setText(mule.getGender());
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
