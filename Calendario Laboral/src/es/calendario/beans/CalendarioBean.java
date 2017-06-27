package es.calendario.beans;

public class CalendarioBean {

	private MesBean enero;
	private MesBean febrero;
	private MesBean marzo;
	private MesBean abril;
	private MesBean mayo;
	private MesBean junio;
	private MesBean julio;
	private MesBean agosto;
	private MesBean septiembre;
	private MesBean octubre;
	private MesBean noviembre;
	private MesBean diciembre;
	
	public MesBean getEnero() {
		return enero;
	}
	public void setEnero(MesBean enero) {
		this.enero = enero;
	}
	public MesBean getFebrero() {
		return febrero;
	}
	public void setFebrero(MesBean febrero) {
		this.febrero = febrero;
	}
	public MesBean getMarzo() {
		return marzo;
	}
	public void setMarzo(MesBean marzo) {
		this.marzo = marzo;
	}
	public MesBean getAbril() {
		return abril;
	}
	public void setAbril(MesBean abril) {
		this.abril = abril;
	}
	public MesBean getMayo() {
		return mayo;
	}
	public void setMayo(MesBean mayo) {
		this.mayo = mayo;
	}
	public MesBean getJunio() {
		return junio;
	}
	public void setJunio(MesBean junio) {
		this.junio = junio;
	}
	public MesBean getJulio() {
		return julio;
	}
	public void setJulio(MesBean julio) {
		this.julio = julio;
	}
	public MesBean getAgosto() {
		return agosto;
	}
	public void setAgosto(MesBean agosto) {
		this.agosto = agosto;
	}
	public MesBean getSeptiembre() {
		return septiembre;
	}
	public void setSeptiembre(MesBean septiembre) {
		this.septiembre = septiembre;
	}
	public MesBean getOctubre() {
		return octubre;
	}
	public void setOctubre(MesBean octubre) {
		this.octubre = octubre;
	}
	public MesBean getNoviembre() {
		return noviembre;
	}
	public void setNoviembre(MesBean noviembre) {
		this.noviembre = noviembre;
	}
	public MesBean getDiciembre() {
		return diciembre;
	}
	public void setDiciembre(MesBean diciembre) {
		this.diciembre = diciembre;
	}
	public void setMes (MesBean mes, String nombreMes) {
		switch (nombreMes) {
			case "enero":
				this.enero = mes;
				break;
			case "febrero":
				this.febrero = mes;
				break;
			case "marzo":
				this.marzo = mes;
				break;
			case "abril":
				this.abril = mes;
				break;
			case "mayo":
				this.mayo = mes;
				break;
			case "junio":
				this.junio = mes;
				break;
			case "julio":
				this.julio = mes;
				break;
			case "agosto":
				this.agosto = mes;
				break;
			case "septiembre":
				this.septiembre = mes;
				break;
			case "octubre":
				this.octubre = mes;
				break;
			case "noviembre":
				this.noviembre = mes;
				break;
			case "diciembre":
				this.diciembre = mes;
				break;							
		}
	}
	
	
}
