package es.calendario.beans;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DatosCalendario {

	public static String[] diasDeLaSemana = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
	public static String[] mesesDelAnyo = {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
	
	public static ArrayList<CalendarioBean> getDatosCalendario() {
		
		ArrayList<CalendarioBean> calendarios = new ArrayList<CalendarioBean>();
		try {
			//Parseamos el archivo xml
			InputStream fXmlFile = DatosCalendario.class.getResourceAsStream("/DatosCalendario.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();						
			NodeList nodoRaiz = doc.getElementsByTagName("calendario");
			Element eRaiz = (Element)nodoRaiz.item(0);
			String strAnyo = eRaiz.getAttribute("anyo");
			int anyo = Integer.parseInt(strAnyo);

			NodeList nList = doc.getElementsByTagName("mes");
			Hashtable<String, ArrayList<Integer>> tablaMeses = new Hashtable<String, ArrayList<Integer>>();
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);	
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String mes = eElement.getElementsByTagName("nombre").item(0).getTextContent();
					System.out.println("Días: " + eElement.getElementsByTagName("dias").item(0).getTextContent());
					String dias = eElement.getElementsByTagName("dias").item(0).getTextContent();
					String[] arrayDias = dias.split(",");
					ArrayList<Integer> listaDias = new ArrayList<Integer>();
					for (int i=0; i<arrayDias.length; i++) {
						String[] intervaloDias = arrayDias[i].split("-");
						if (intervaloDias.length == 1) {
							listaDias.add(Integer.parseInt(intervaloDias[0]));
						} else {
							int limMin = Integer.parseInt(intervaloDias[0]);
							int limMax = Integer.parseInt(intervaloDias[1]);
							for (int j=limMin; j<=limMax; j++) {
								listaDias.add(j);
							}
						}
					}
					System.out.println("Paro para mirar listaDias");
					tablaMeses.put(mes, listaDias);
				}
			}
			//Construimos el calendario
			Calendar c = Calendar.getInstance();						
			c.set(Calendar.YEAR, anyo);
			c.set(Calendar.MONTH, 0);
			double totalMinutos = 0;			
			CalendarioBean calendario = new CalendarioBean();
			for (int i=0; i<mesesDelAnyo.length; i++) {
				String nombreMes = mesesDelAnyo[i];
				ArrayList<Integer> diasLibres = tablaMeses.get(nombreMes);
				MesBean mes = crearMes (anyo, i, diasLibres);
				calendario.setMes(mes, nombreMes);
				totalMinutos += mes.getMinutosTrabajados();
			}
			calendarios.add(calendario);
			System.out.println("Total horas trabajadas " + (totalMinutos)/60);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		

		return calendarios;

	}
	private static MesBean crearMes (int anyo, int mes, ArrayList<Integer> diasLibres) {

		Calendar c = Calendar.getInstance(new Locale("es_ES"));
		c.set(Calendar.YEAR, anyo);
		c.set(Calendar.MONTH, mes);
		c.set(Calendar.DAY_OF_MONTH, 1);

		int indice = 0;		
		MesBean mesBean = new MesBean();
		ArrayList<SemanaBean> listaSemanas = new ArrayList<SemanaBean>();			
		int limite = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (limite == 0) {
			limite = 7; //Domingo
		}
		SemanaBean semana = new SemanaBean();
		for (int i=1; i<limite; i++) {
			//Celda vacía
			DiaBean diaBlanco = new DiaBean();
			diaBlanco.setDiaDeLaSemana(diasDeLaSemana[i]);
			diaBlanco.setNumDiaMes(0);
			diaBlanco.setClaseDia("diaBlanco");
			switch (i) {
				case 1: 
					semana.setLunes(diaBlanco);
					break;
				case 2: 
					semana.setMartes(diaBlanco);
					break;
				case 3: 
					semana.setMiercoles(diaBlanco);
					break;
				case 4: 
					semana.setJueves(diaBlanco);
					break;
				case 5: 
					semana.setViernes(diaBlanco);
					break;
				case 6: 
					semana.setSabado(diaBlanco);
					break;
				case 7: 
					semana.setDomingo(diaBlanco);
					break;
			}
		}
		double contadorMinutos = 0;
		while (c.get(Calendar.MONTH) == mes) {			
			DiaBean dia = new DiaBean();			
			int diaDeLaSemana = c.get(Calendar.DAY_OF_WEEK) - 1;
			dia.setDiaDeLaSemana(diasDeLaSemana[diaDeLaSemana]);
			dia.setNumDiaMes(c.get(Calendar.DAY_OF_MONTH));
			if (indice < diasLibres.size() && diasLibres.get(indice) == c.get(Calendar.DAY_OF_MONTH)) {
				dia.setClaseDia("Libre");				
				indice++;
			} else {
				dia.setClaseDia("");
				contadorMinutos += 375;
			}
			switch (diaDeLaSemana) {
				case 0: 
					semana.setDomingo(dia);
					break;
				case 1: 
					semana.setLunes(dia);
					break;
				case 2: 
					semana.setMartes(dia);
					break;
				case 3: 
					semana.setMiercoles(dia);
					break;
				case 4: 
					semana.setJueves(dia);
					break;
				case 5: 
					semana.setViernes(dia);
					break;
				case 6: 
					semana.setSabado(dia);
					break;			
			}
			c.add(Calendar.DAY_OF_MONTH, 1);
			if (diaDeLaSemana == 0) {							
				listaSemanas.add(semana);
				semana = new SemanaBean();
			}
		}

		limite = c.get(Calendar.DAY_OF_WEEK) - 1;		
		if (limite == 0) {
			limite = 7; //Domingo
		}
		if (limite > 1) { //Añado celdas en blanco sólo si el último día del mes no fue domingo
			for (int i=limite; i<=7; i++) {
				//Celda vacía
				DiaBean diaBlanco = new DiaBean();
				int diaDeLaSemana = i == 7 ? 0 : i;
				diaBlanco.setDiaDeLaSemana(diasDeLaSemana[diaDeLaSemana]);
				diaBlanco.setNumDiaMes(0);
				diaBlanco.setClaseDia("diaBlanco");
				switch (i) {
					case 1: 
						semana.setLunes(diaBlanco);
						break;
					case 2: 
						semana.setMartes(diaBlanco);
						break;
					case 3: 
						semana.setMiercoles(diaBlanco);
						break;
					case 4: 
						semana.setJueves(diaBlanco);
						break;
					case 5: 
						semana.setViernes(diaBlanco);
						break;
					case 6: 
						semana.setSabado(diaBlanco);
						break;
					case 7: 
						semana.setDomingo(diaBlanco);
						break;
						
				}
			}
			listaSemanas.add(semana);
		}
		mesBean.setListaSemanas(listaSemanas);
		SimpleDateFormat sdf = new SimpleDateFormat("MMMMMMMM");
		//Vuelvo al mes de origen, porque al sumar días he pasado de mes
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
		System.out.println("Horas Totales - " + sdf.format(c.getTime()) +": "+ (contadorMinutos/60));		
		mesBean.setMinutosTrabajados(contadorMinutos);
		return mesBean;
	}

}
