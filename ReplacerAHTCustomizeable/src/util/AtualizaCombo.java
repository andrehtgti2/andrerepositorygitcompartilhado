package util;

import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JComboBox;

public abstract class AtualizaCombo {


	
	public static ArrayList converteElementosDoPropertieEmArrayList(Properties propComboList){
		int qtd = Integer.parseInt(propComboList.getProperty("NumeroDoProximoElemento"));
		String item;
		ArrayList lista = new ArrayList();
		for (int i = 0; i < qtd; i++) {
			if(propComboList.getProperty(String.valueOf(i))!=null){
				item = i + "-" + propComboList.getProperty(String.valueOf(i));
				lista.add(item);
			}			
		}
		return lista;
	}
		
}
