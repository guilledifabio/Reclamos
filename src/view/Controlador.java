package view;

import api.Reclamos;
import modelo.Ciudadano;

public class Controlador {
	
	Ciudadano ciudadano;
	Reclamos api;
	
	public Controlador() {
		this.api = new Reclamos(); 
	}
	
	public void ConectarAPI () {
		 api.conectarDB();
	}
	
	public void DesconectarAPI () {
		api.desconectarDB();
	}
	
	public Boolean Ingresar (int dni) {
		ciudadano = api.buscarCiudadano(dni);
		if (ciudadano != null) {
			return true;
		}
		
		return false;
	}
	
	public void Registrar() {
		
	}

}
