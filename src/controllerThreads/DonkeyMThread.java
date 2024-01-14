package controllerThreads;

import java.util.Random;

import domain.DonkeyF;
import domain.DonkeyM;
import domain.DonkeyMBehaviour;
import domain.Mule;
import logic.ListLogicThreads;
import logic.Suppliers;
import logic.TableWriter;
import view.StableGUI;

public class DonkeyMThread extends Thread{

	private DonkeyM dkMale;
	private StableGUI gui;
	private DonkeyMBehaviour dkMBehav;
	private int randomSet=0;
	private boolean pause;
	ListLogicThreads lgTh;

	private int actualTime;
	private int initTime = 0; 
    private int passedTime = 0;
    
    private int tempPauseTime = 0;
    private int tempResumeTime = 0;
    private int tempPauseLength =0;
	
	public DonkeyMThread(DonkeyM dkMale, StableGUI gui,ListLogicThreads lgTh) {
		
			this.dkMale=dkMale;
			this.gui=gui;
			dkMBehav = new DonkeyMBehaviour(this.dkMale,this.gui);
			this.lgTh=lgTh;
	}

	@Override
	public void run() {
					
		 initTime = (int) System.currentTimeMillis(); 
	     passedTime = 0;
			 while (passedTime < gui.getTime()) {
				 
				 	dkMBehav.moveRandom(); 
				 	TableWriter.addDkMMove();

				 	if(randomSet==0) {
				 		randomFirstRun();
				 	}
				 
				 	if(gui.getBtnMatrix()[dkMBehav.getX()][dkMBehav.getY()].getText().equals("BUH")) {
				 		try {
							sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				 		reproduceDonkey();
				 	}
				 	
				 	if(gui.getBtnMatrix()[dkMBehav.getX()][dkMBehav.getY()].getText().equals("YE")) {
				 		try {
							sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				 		reproduceMule();
				 	}
				 	
				 	actualTime = (int) System.currentTimeMillis();
			        passedTime = actualTime - initTime;
			        
			        if(gui.getBtnMatrix()[dkMBehav.getX()][dkMBehav.getY()].getText().equals("BUM")) {
				 		try {
							sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				 		versusDonkey();
				 	}
				 	
			        gui.getBtnMatrix()[dkMBehav.getX()][dkMBehav.getY()].setText(dkMale.getSpecies());
			        try {
			            sleep(2000); // Espera 2 segundos
			        } catch (InterruptedException e) {
			            e.printStackTrace();
			        }
			        
			        if(lgTh.randomUseSuppliers()==20) {
			        	randomSupplier();
			        }
			        
			        gui.getBtnMatrix()[dkMBehav.getX()][dkMBehav.getY()].setText("");
			           
			        
			        synchronized(this) {
						if(pause) {
							try {
								tempPauseTime = (int) System.currentTimeMillis();
								gui.getBtnMatrix()[dkMBehav.getX()][dkMBehav.getY()].setText(dkMale.getSpecies());
								wait();
								tempResumeTime = (int) System.currentTimeMillis(); 
				                tempPauseLength = tempResumeTime - tempPauseTime; 
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
		gui.getBtnMatrix()[dkMBehav.getX()][dkMBehav.getY()].setText("");
	}
	
	public void randomFirstRun() {
		int tempx = dkMBehav.randomMatrixPoint();
 		int tempy = dkMBehav.randomMatrixPoint();
 		gui.getBtnMatrix()[tempx][tempy].setText(dkMale.getSpecies());
 		dkMBehav.setX(tempx);
 		dkMBehav.setY(tempy);
 		randomSet++;
 		gui.getBtnMatrix()[tempx][tempy].setText("");
	}
	
	public synchronized void reproduceMule() {
		Random r = new Random();
		int ran = r.nextInt(2);
		if(ran==0) {
			TableWriter.addMuleMReproduct();
			System.out.println("Mulo nuevo xd");
			lgTh.getMuleArray().add(new Mule(250000,2000,"MM"));
			lgTh.getMuleArray().get(lgTh.getMuleArray().size()-1).setGender("MM");
			lgTh.getMuleThArray().add(new MuleThread(lgTh.getMuleArray().get(lgTh.getMuleThArray().size()),gui,lgTh));
			lgTh.getMuleThArray().get(lgTh.getMuleThArray().size()-1).start();
			System.out.println("Mulillo  :v");
		}else {
			TableWriter.addMuleFReproduct();
			System.out.println("MULA nueva xd");
			lgTh.getMuleArray().add(new Mule(250000,2000,"MH"));
			lgTh.getMuleArray().get(lgTh.getMuleArray().size()-1).setGender("MH");
			lgTh.getMuleThArray().add(new MuleThread(lgTh.getMuleArray().get(lgTh.getMuleThArray().size()),gui,lgTh));
			lgTh.getMuleThArray().get(lgTh.getMuleThArray().size()-1).start();
			System.out.println("MULA");
		}
	}
	
	public synchronized void reproduceDonkey() {
		Random r = new Random();
		int ran = r.nextInt(2);
		if(ran==0) {
			TableWriter.addDkMReproduct();
			System.out.println("Burro nuevO :D");
			lgTh.getDkMArray().add(new DonkeyM(250000,2000,"BUM"));
			lgTh.getDkMThArray().add(new DonkeyMThread(lgTh.getDkMArray().get(lgTh.getDkMThArray().size()),gui,lgTh));
			lgTh.getDkMThArray().get(lgTh.getDkMThArray().size()-1).start();
		}else {
			TableWriter.addDkfReproduct();
			lgTh.getDkFArray().add(new DonkeyF(250000,2000,"BUH"));
			lgTh.getDkFThArray().add(new DonkeyFThread(lgTh.getDkFArray().get(lgTh.getDkFThArray().size()),gui,lgTh));
			lgTh.getDkFThArray().get(lgTh.getDkFThArray().size()-1).start();
			System.out.println("Burra nuevA :D");
		}
	}
	
	public synchronized void versusDonkey() {
		passedTime = 1000000;
		lgTh.getDkMArray().remove(dkMale);
		lgTh.getDkMThArray().remove(this);
		System.out.println("Hubo una pelea y murieron burros");
		TableWriter.addDkMFights();
	}
	
	public synchronized void randomSupplier() {
		TableWriter.addDkMSup();
		
		switch(Suppliers.randomOptionSupply()) {
		case 0:if(gui.getBtnHay().getText().equals("...")) {
			pauseThread();
			gui.getBtnMatrix()[dkMBehav.getX()][dkMBehav.getY()].setText("");
			gui.getBtnHay().setText("BUM");
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
				gui.getBtnMatrix()[dkMBehav.getX()][dkMBehav.getY()].setText("");
				gui.getBtnFood().setText("BUM");
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
				gui.getBtnMatrix()[dkMBehav.getX()][dkMBehav.getY()].setText("");
				gui.getBtnVitamins().setText("BUM");
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
				gui.getBtnMatrix()[dkMBehav.getX()][dkMBehav.getY()].setText("");
				gui.getBtnWater().setText("BUM");
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
