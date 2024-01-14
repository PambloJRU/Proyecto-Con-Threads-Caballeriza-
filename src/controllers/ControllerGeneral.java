package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;

import controllerThreads.ActivityCheck;
import controllerThreads.HorseThread;
import domain.Horse;
import logic.Hurricane;
import logic.ListLogicThreads;
import logic.TableWriter;
import view.StableGUI;

public class ControllerGeneral implements ActionListener {

	private StableGUI gui;
	private ListLogicThreads listLogicThreads;
	private ActivityCheck actCh;
	private TableWriter tb;
	private Hurricane evnt;

	public ControllerGeneral() {
		gui = new StableGUI();
		initializers();

		listLogicThreads = new ListLogicThreads(gui);
		actCh = new ActivityCheck(listLogicThreads,gui);
		evnt = new Hurricane(gui,listLogicThreads);
		tb = new TableWriter(listLogicThreads,gui);
		
	}

	public void initializers() {
		gui.getBtnRunSim().addActionListener(this);
		gui.getBtnPauseSim().addActionListener(this);
		gui.getBtnResume().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == gui.getBtnRunSim()) {

			listLogicThreads.initializersArray();
			listLogicThreads.threadsObjectsInitializer();
			listLogicThreads.executeThreads();
			
			Thread th = new Thread(actCh);//Hilo que detecta actividad
			th.start();
			Thread hc = new Thread(evnt);
			hc.start();//ACTIVAR DESACTIVAR HURACAN
			Thread tb = new Thread(this.tb);
			tb.start();
			
			gui.getBtnRunSim().setEnabled(false);
			gui.getBtnPauseSim().setEnabled(true);
		}
		if (e.getSource() == gui.getBtnPauseSim()) {
			for (int i = 0; i < 4; i++) {
				listLogicThreads.pauseThreads();
			}
			gui.getBtnPauseSim().setEnabled(false);
			gui.getBtnResume().setEnabled(true);
		}
		if (e.getSource() == gui.getBtnResume()) {

			listLogicThreads.resumeThreads();
			gui.getBtnResume().setEnabled(false);
			gui.getBtnPauseSim().setEnabled(true);
		}
		

	}

}
