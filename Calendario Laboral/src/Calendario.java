

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import es.calendario.beans.CalendarioBean;
import es.calendario.beans.DatosCalendario;


public class Calendario {

	public static void main(String[] args) {
		try {			
			ArrayList<CalendarioBean> datos = DatosCalendario.getDatosCalendario();
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(datos);
													
			Calendar c = Calendar.getInstance();			
			int anyo = Integer.parseInt(args[0]);	
			c.set(Calendar.YEAR, anyo);
			c.set(Calendar.MONTH, 0);			
			HashMap tablaParams = new HashMap();
			//tablaParams.put("URL_IMAGEN","Fondo Calendario Jaime.jpg");
			tablaParams.put("ANYO", anyo + "");
			InputStream img = Calendario.class.getResourceAsStream(args[1]);
			tablaParams.put("IMG", img);
			InputStream res = Calendario.class.getResourceAsStream("/Calendario Laboral.jrxml");
			JasperReport reporte = JasperCompileManager.compileReport(res);
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, tablaParams, beanColDataSource);						  	
	        JRExporter exporter = new JRPdfExporter(); 
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("/tmp/Calendario Laboral "+ anyo + ".pdf")); 
	        exporter.exportReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println ("YA!!");	
	}

}
