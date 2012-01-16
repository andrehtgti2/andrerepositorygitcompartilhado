package crono;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class Tela extends JFrame {
	
	Button start;
	Button resume;	
	TextField textField;	
	Label label;	
	static Tela tela;
			
	//psvm
	public static void main(String[] args) {
		tela = new Tela();	
		tela.constroiTela();
		tela.associaListeners();

	}

	public Tela() {
		start = new Button("Iniciar");
		resume = new Button("Continuar");
		textField = new TextField();
		label = new Label("AndreHT - 16/01/2012");
		label.setAlignment(Label.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public void constroiTela(){
		//GridLayout n = new GridLayout(4, 1);		
		tela.getContentPane().setLayout(new GridLayout(4,1));		
		tela.setBounds(100, 100, 200, 200);		
		tela.setVisible(true);		
		tela.getContentPane().add(textField);
		tela.getContentPane().add(start);
		tela.getContentPane().add(resume);
		tela.getContentPane().add(label);
	}
	
	private void associaListeners(){
		
		resume.addActionListener(
			new ActionListener() {//RESUME
				public void actionPerformed(ActionEvent e) {
					ThreadConta thread = new ThreadConta(tela.textField, tela, true);
					thread.start();
					start.setLabel("Pausar");
					resume.setEnabled(false);
				}
			}
		);
		
		
		start.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ThreadConta thread = new ThreadConta(tela.textField, tela, true);
					try{
						//INICIA ou REINICIA
						if(start.getLabel().equals("Reiniciar") || start.getLabel().equals("Iniciar")){
							start.setLabel("Pausar");
							thread.ZerarContadores();
							thread.start();			
							resume.setEnabled(false);
							
						}else{//PAUSAR
							resume.setEnabled(true);
							start.setLabel("Reiniciar");
							thread.stop();
							thread.stopThreadAHT();
							
							
						}
					}catch(Throwable e2){
						e2.printStackTrace();
					}
					repaint();
				}
			}
		);
	}


	

}
