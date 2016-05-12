package modelo;

import java.time.LocalDateTime;

public class ProveedorDelTiempo {
	
	private LocalDateTime dia;
	
	public ProveedorDelTiempo() {
		super();
		dia = LocalDateTime.now();
	}
	
	public ProveedorDelTiempo(String fecha) {
		super();
		dia = LocalDateTime.parse(fecha);
	}
	
	public LocalDateTime getDia() {
		return dia;
	}

}
