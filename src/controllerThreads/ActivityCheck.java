package controllerThreads;

import javax.swing.JOptionPane;
import logic.ListLogicThreads;
import view.StableGUI;

public class ActivityCheck implements Runnable {

	ListLogicThreads lgth;
	StableGUI gui;
	
	public ActivityCheck(ListLogicThreads lgth, StableGUI gui) {
		this.lgth=lgth;
		this.gui=gui;
	}
	
	@Override
	public void run() {

		while(lgth.activitychecker()) {
			//Ciclo Infinito
		}
		System.out.println("Equinos finalizados");
		lgth.finalizeAll();
		JOptionPane.showMessageDialog(null, "La simulacion ha finalizado");
	}

}
