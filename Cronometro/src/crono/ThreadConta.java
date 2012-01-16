package crono;

import java.awt.TextField;
import java.util.Date;

public class ThreadConta extends Thread {
	
	int sleepTime = (int) 1000;
	TextField textField;
	Tela tela;
	static boolean continuar;
	static int contador = 1;
	static int minuto = 0;
	static int hora = 0;
	
	public ThreadConta(TextField textField, Tela tela, boolean continuar) {
		this.textField = textField;
		this.tela = tela;
		this.continuar = continuar;
	}
	
	public void stopThreadAHT (){
		this.continuar = false;
		//System.out.println("dentro do metodo: continuar = " + continuar);
	}
	
	public void ZerarContadores(){
		this.contador = 1;
		this.minuto = 0;
		this.hora = 0;
	}




	public void run(){
		
		//hora = 0;		
		try {
			while(continuar){
				Thread.sleep(sleepTime);
				Date d = new Date();
				if(contador >= 60){
					minuto ++;
					contador = 0;
				}
				
				if(minuto >= 60){
					hora ++;
					minuto = 0;
				}
				
				if(continuar){
					textField.setText("                   " + preenche2Zeros(hora) +":" + preenche2Zeros(minuto) + ":" + preenche2Zeros(contador));
					contador ++;
				}
				
				
				tela.repaint();
				//System.out.println(contador);				
			}		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	private String preenche2Zeros (int i){
		String s = String.valueOf(i);
		if(s.length()!=2){
			return "0" + s;
		}else{
			return s;
		}
	}
}
