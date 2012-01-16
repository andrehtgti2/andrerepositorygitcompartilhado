package listeners;

import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


	

public class ListenerBotaoResize implements ActionListener {

	//public ReplacerAHT tela;
	public Panel pGrande;
	public TextArea areaFrom;
	public TextArea areaTo;
	public TextArea areaAlvo;
	public TextArea areaDestino;
	public static final int larguraP = 200;
	public static final int alturaP = 100;
	public static final int larguraG = 300;
	public static final int alturaG = 200;

	
	//ESSE RESIZE NAO FUNCIONA POIS NAO ORGANIZA OS PAINES NAS POSIÇÕES CORRETAS
	
	
	//public ListenerBotaoResize(ReplacerAHT tela, Panel pGrande, TextArea areaFrom, TextArea areaTo, TextArea areaAlvo, TextArea areaDestino) {
	public ListenerBotaoResize( Panel pGrande, TextArea areaFrom, TextArea areaTo, TextArea areaAlvo, TextArea areaDestino) {
		//this.tela = tela;
		this.pGrande = pGrande;
		this.areaFrom = areaFrom;
		this.areaTo = areaTo;
		this.areaAlvo = areaAlvo;
		this.areaDestino = areaDestino;
	}

	public void actionPerformed(ActionEvent e) {
//		if(tela.getSize().height == 700){
//			tela.resize(900,800);
//			pGrande.resize(900,800);
//			areaFrom.resize(alturaG,larguraG);
//			areaTo.resize(alturaG,larguraG);
//			areaAlvo.resize(alturaG,larguraG);
//			areaDestino.resize(alturaG,larguraG);
//
//		}else{
//			tela.resize(800,700);
//			pGrande.resize(800,700);
//			areaFrom.resize(alturaP,larguraP);
//			areaTo.resize(alturaP,larguraP);
//			areaAlvo.resize(alturaP,larguraP);
//			areaDestino.resize(alturaP,larguraP);
//
//		}
//		
//
	}

}
