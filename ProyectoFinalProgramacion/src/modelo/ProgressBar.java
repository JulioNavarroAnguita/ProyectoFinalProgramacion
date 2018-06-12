package modelo;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class ProgressBar {

	static JFrame ventana;
	static JButton boton;
	static JProgressBar barra;
	
	public ProgressBar() {
		ventana = new JFrame("JProgressBar");
		boton = new JButton("Comenzar");
		barra = new JProgressBar(0, 100);
		
		boton.addActionListener(new Escucha());
		ventana.setLayout(new FlowLayout());
		ventana.add(barra);
		ventana.add(boton);
		ventana.pack();
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		
		new ProgressBar();
	}
	
	public static class Escucha implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new Thread(new Hilo()).start();
			
		}
		
	}
	
	public static class Hilo implements Runnable {

		@Override
		public void run() {
			for(int i = 0; i < 100; i++) {
				barra.setValue(i);
				barra.repaint();
				
				try {
					Thread.sleep(50);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
		}
		
	}
}
