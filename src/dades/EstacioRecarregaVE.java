package dades;

/**
 * Inicialitzem la classe:
 */
public class EstacioRecarregaVE {
	private String promotor;
	private String[] tipusVelocitat;
	private int numTipusVelocitat;
	private float latitud;
	private float longitud;
	private String provincia;
	private String municipi;
	private int numPlaces;


	/**
	 * Creem el constructor:
	 * @param promotor
	 * @param tipusVelocitat
	 * @param latitud
	 * @param longitud
	 * @param provincia
	 * @param municipi
	 * @param nplaces
	 */
	public EstacioRecarregaVE(String promotor, String tipusVelocitat, float latitud, float longitud, String provincia,
			String municipi, int nplaces) {
		this.promotor = promotor;
		this.tipusVelocitat = new String[3]; // reservem espai per guardar tres tipus de velocitat diferents de
												// l'estaci�
		this.tipusVelocitat[0] = tipusVelocitat;
		this.numTipusVelocitat = 1; // en el constructor rebem nom�s un tipus
		this.latitud = latitud;
		this.longitud = longitud;
		this.provincia = provincia;
		this.municipi = municipi;
		this.numPlaces = nplaces;
	}

	/**
	 * 
	 * @param tipusVelocitat
	 */
	public void afegirTipusVelocitat(String tipusVelocitat) {
		if (numTipusVelocitat >= this.tipusVelocitat.length) {
			// si la taula est� plena reservem m�s espai
			String[] nouAmpliar = new String[numTipusVelocitat + 2];
			for (int i = 0; i < numTipusVelocitat; i++)
				nouAmpliar[i] = this.tipusVelocitat[i];
			this.tipusVelocitat = nouAmpliar;
		}
		// ja podem assegurar que hi ha espai per afegir el nou tipus
		this.tipusVelocitat[numTipusVelocitat] = tipusVelocitat;
		numTipusVelocitat++;
	}

	/**
	 * Getter pel numPlaces
	 * 
	 * @return numPlaces
	 */
	public int getNumPlaces() {
		return numPlaces;
	}

	/**
	 * Funcio que diu si es troba en aquest municipi
	 * 
	 * @param municipi
	 * @return (municipi.equalsIgnoreCase(this.municipi))
	 */
	public boolean esTrobaEnAquestMunicipi(String municipi) {
		return (municipi.equalsIgnoreCase(this.municipi));
	}

	/**
	 * Funcio que diu si es troba en aquest provincia
	 * 
	 * @param provincia
	 * @return (provincia.equalsIgnoreCase(this.provincia))
	 */
	public boolean esTrobaEnAquestaProvincia(String provincia) {
		return (provincia.equalsIgnoreCase(this.provincia));
	}

	/**
	 * Funcio que troba si te el tipus de recarga en questio
	 * 
	 * @param tipusRecarrega
	 * @return
	 */
	public boolean teAquestTipusRecarrega(String tipusRecarrega) {
		boolean trobat = false;
		int pos = 0;

		while ((pos < numTipusVelocitat) && (!trobat)) {
			if (tipusVelocitat[pos].equalsIgnoreCase(tipusRecarrega))
				trobat = true;
			else
				pos++;
		}

		return trobat;
	}

	@Override
	/**
	 * Funcio toString
	 * 
	 * @return aux
	 */
	public String toString() {
		String aux = "EstacioRecarregaVE =>";
		aux = aux + "\n\tpromotor= " + promotor + ", tipusVelocitat= [ ";
		for (int i = 0; i < numTipusVelocitat; i++)
			aux = aux + tipusVelocitat[i] + ", ";
		aux = aux + "], ";
		aux = aux + "\n\tlatitud= " + latitud + ", longitud= " + longitud;
		aux = aux + "\n\tprovincia= " + provincia + ", municipi= " + municipi;
		aux = aux + "\n\tnumPlaces= " + numPlaces + "\n\n";
		return aux;
	}

	/**
	 * Funcio que copia la estacio de recarrega
	 * 
	 * @return est
	 */
	public EstacioRecarregaVE copia() {
		EstacioRecarregaVE est = new EstacioRecarregaVE(promotor, tipusVelocitat[0], latitud, longitud, provincia,
				municipi, numPlaces);
		for (int i = 1; i < numTipusVelocitat; i++) {
			est.afegirTipusVelocitat(tipusVelocitat[i]);
		}
		return est;
	}

	/**
	 * 
	 * Funcio que calcula la distancia a una ubicacio
	 * 
	 * @param latitud
	 * @param longitud
	 * @return dist
	 */
	public double distanciaA(float latitud, float longitud) {
		double dist = 0;
		double lat1, lat2, lon1, lon2, incLat, incLon, a, r;

		lat1 = latitud * Math.PI / 180;
		lat2 = this.latitud * Math.PI / 180;
		lon1 = longitud * Math.PI / 180;
		lon2 = this.longitud * Math.PI / 180;

		incLat = lat2 - lat1;
		incLon = lon2 - lon1;

		a = Math.sin(incLat / 2) * Math.sin(incLat / 2)
				+ Math.cos(lat1) * Math.cos(lat2) * Math.sin(incLon / 2) * Math.sin(incLon / 2);

		r = 6378.137;
		dist = r * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return (dist);
	}

	/**
	 * Getter de TipusVelocitat
	 * 
	 * @return tipusVelocitat
	 */
	public String[] getTipusVelocitat() {
		return tipusVelocitat;
	}

	/**
	 * Setter de TipusVelocitat
	 * 
	 * @param tipusVelocitat
	 */
	public void setTipusVelocitat(String[] tipusVelocitat) {
		this.tipusVelocitat = tipusVelocitat;
	}

	/**
	 * Getter de provincia
	 * 
	 * @return provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * Setter de provincia
	 * 
	 * @param provincia
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * Getter de municipi
	 * 
	 * @return municipi
	 */
	public String getMunicipi() {
		return municipi;
	}

	/**
	 * Setter municipi
	 * 
	 * @param municipi
	 */
	public void setMunicipi(String municipi) {
		this.municipi = municipi;
	}

	/**
	 * Setter de numPlaces
	 * 
	 * @param numPlaces
	 */
	public void setNumPlaces(int numPlaces) {
		this.numPlaces = numPlaces;
	}

}
