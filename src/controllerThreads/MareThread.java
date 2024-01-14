package controllerThreads;



import domain.Mare;
import domain.MareBehaviour;
import logic.ListLogicThreads;
import logic.Suppliers;
import logic.TableWriter;
import view.StableGUI;

public class MareThread extends Thread {

	private Mare mare;
	private StableGUI gui;
	private MareBehaviour mBehav;
	private int randomSet=0;
	private boolean pause;

	private ListLogicThreads lgTh;
	
	private int actualTime;
	private int initTime = 0; 
    private int passedTime = 0;
    
    private int tempPauseTime = 0;
    private int tempResumeTime = 0;
    private int tempPauseLength =0;
	
	public MareThread(Mare mare, StableGUI gui, ListLogicThreads lgTh) {
		
			this.mare=mare;
			this.gui=gui;
			mBehav = new MareBehaviour(this.mare,this.gui);
			this.lgTh=lgTh;
	}

	@Override
	public void run() {
					
		 initTime = (int) System.currentTimeMillis(); 
	     passedTime = 0;
			 while (passedTime < gui.getTime()) {
				 
				 	mBehav.moveRandom(); 
				 	TableWriter.addMareMove();

				 	if(randomSet==0) {
				 		randomFirstRun();
				 	}
				 
			        gui.getBtnMatrix()[mBehav.getX()][mBehav.getY()].setText(mare.getSpecies());
			        try {
			            sleep(3000); // Espera 3 segundos
			        } catch (InterruptedException e) {
			            e.printStackTrace();
			        }
			        
			        if(lgTh.randomUseSuppliers()==20) {
			        	randomSupplier();
			        }
			     
			        gui.getBtnMatrix()[mBehav.getX()][mBehav.getY()].setText("");
			        
			           
			        actualTime = (int) System.currentTimeMillis();
			        passedTime = (int) actualTime - initTime;

			       
			        synchronized(this) {
						if(pause) {
							try {
								tempPauseTime = (int) System.currentTimeMillis();
								gui.getBtnMatrix()[mBehav.getX()][mBehav.getY()].setText(mare.getSpecies());
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
		gui.getBtnMatrix()[mBehav.getX()][mBehav.getY()].setText("");
	}
	
	public void randomFirstRun() {
		int tempx = mBehav.randomMatrixPoint();
 		int tempy = mBehav.randomMatrixPoint();
 		gui.getBtnMatrix()[tempx][tempy].setText(mare.getSpecies());
 		mBehav.setX(tempx);
 		mBehav.setY(tempy);
 		randomSet++;
 		gui.getBtnMatrix()[tempx][tempy].setText("");
	}

	public synchronized void randomSupplier() {
		TableWriter.addMareSup();
		switch(Suppliers.randomOptionSupply()) {
		case 0:if(gui.getBtnHay().getText().equals("...")) {
			pauseThread();
			gui.getBtnMatrix()[mBehav.getX()][mBehav.getY()].setText("");
			gui.getBtnHay().setText("YE");
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
				gui.getBtnMatrix()[mBehav.getX()][mBehav.getY()].setText("");
				gui.getBtnFood().setText("YE");
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
				gui.getBtnMatrix()[mBehav.getX()][mBehav.getY()].setText("");
				gui.getBtnVitamins().setText("YE");
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
				gui.getBtnMatrix()[mBehav.getX()][mBehav.getY()].setText("");
				gui.getBtnWater().setText("YE");
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
