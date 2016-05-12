package modelo;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import com.db4o.activation.ActivationPurpose;
import com.db4o.activation.Activator;
import com.db4o.ta.Activatable;

import dto.CanjeDTO;
import dto.CiudadanoDTO;
import dto.ReclamoDTO;

public class Ciudadano implements Activatable {

	private String id = null;
	private String nombre;
	private String apellido;
	private int dni;
	private String email;
	private int puntos;
	private List<Canje> canjes;
	private List<Reclamo> reclamos;
	private transient Activator _activator;

	public Ciudadano(String nombre, String apellido, int dni, String email) {
		super();
		this.id = UUID.randomUUID().toString();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.puntos = 0;
		this.canjes = new ArrayList();
		this.reclamos = new ArrayList();
	}

	public Ciudadano(String id, String nombre, String apellido, int dni, String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.puntos = 0;
		this.canjes = new ArrayList();
		this.reclamos = new ArrayList();
	}

	public Ciudadano(CiudadanoDTO ciudadanoDto) {
		this.id = UUID.randomUUID().toString();
		this.nombre = ciudadanoDto.getNombre();
		this.apellido = ciudadanoDto.getApellido();
		this.dni = ciudadanoDto.getDni();
		this.email = ciudadanoDto.getEmail();
		this.puntos = ciudadanoDto.getPuntos();
		this.canjes = new ArrayList();
		this.reclamos = new ArrayList();
	}

	public List<CanjeDTO> listCanjesDTO() {
		List<CanjeDTO> lcanjes = new ArrayList<CanjeDTO>();
		for (Canje canjes : canjes) {
			lcanjes.add(canjes.toDTO());
		}

		return lcanjes;
	}

	public String getId() {
		activate(ActivationPurpose.READ);
		return id;
	}

	public void setId(String id) {
		activate(ActivationPurpose.WRITE);
		this.id = id;
	}

	public String getNombre() {
		activate(ActivationPurpose.READ);
		return nombre;
	}

	public void setNombre(String nombre) {
		activate(ActivationPurpose.WRITE);
		this.nombre = nombre;
	}

	public String getApellido() {
		activate(ActivationPurpose.READ);
		return apellido;
	}

	public void setApellido(String apellido) {
		activate(ActivationPurpose.WRITE);
		this.apellido = apellido;
	}

	public int getDni() {
		activate(ActivationPurpose.READ);
		return dni;
	}

	public void setDni(int dni) {
		activate(ActivationPurpose.WRITE);
		this.dni = dni;
	}

	public String getEmail() {
		activate(ActivationPurpose.READ);
		return email;
	}

	public void setEmail(String email) {
		activate(ActivationPurpose.WRITE);
		this.email = email;
	}

	public int getPuntos() {
		activate(ActivationPurpose.READ);
		return puntos;
	}

	public void setPuntos(int puntos) {
		activate(ActivationPurpose.WRITE);
		this.puntos = puntos;
	}

	public List getCanjes() {
		activate(ActivationPurpose.READ);
		return canjes;
	}

	public void setCanjes(List canjes) {
		activate(ActivationPurpose.WRITE);
		this.canjes = canjes;
	}

	public List<Reclamo> getReclamos() {
		activate(ActivationPurpose.READ);
		return reclamos;
	}

	public void setReclamos(List<Reclamo> reclamos) {
		activate(ActivationPurpose.WRITE);
		this.reclamos = reclamos;
	}

	public void realizarReclamo(Reclamo reclamo) {
		Integer pts = 0;

		LocalDateTime dia = LocalDateTime.parse("2016-08-27T10:11:30");

		if (esPrimavera(dia) && esFinde(dia)) {
			pts = reclamo.getCategoria().getPuntos() * 2;// Duplico
			// los
			// puntos
		} else {

			pts = reclamo.getCategoria().getPuntos();
		}
		this.setPuntos(this.getPuntos() + pts);

		this.reclamos.add(reclamo);

		System.out.println(
				"Se realizo el Reclamo " + reclamo.getDescripcion() + " correctamente por: " + pts + " puntos");

	}// Obtengo
		// la
		// categoria
		// del
		// Reclamo
		// y
		// actualizo
		// los
		// puntos
		// del
		// Ciudadano

	public void canjearPuntos(Producto pro) {
		Integer pts = null;
		Integer pt = null;
		Calendar cal = null;
		if (this.getPuntos() < pro.getPuntosrequeridos()) {

			System.out.println("El Ciudadano " + this.getNombre() + " " + this.getApellido()
					+ ", no tiene los Puntos requeridos para realizar el canje ");

		} else {
			LocalDateTime dia = LocalDateTime.parse("2016-08-22T10:11:30");

			if (esPrimavera(dia) && this.puntos > 10) { // Periodo
				// de
				// Primavera
				System.out.println("Entro en primavera");
				pt = pro.getPuntosrequeridos() / 2;
				pts = this.getPuntos() - (pt);

			} else {
				pt = pro.getPuntosrequeridos();
				pts = this.getPuntos() - (pt);
			}
			this.setPuntos(pts);
			Canje can = new Canje();
			can.setProducto(pro);

			this.canjes.add(can);

			System.out.println("El Ciudadano " + this.getNombre() + " " + this.getApellido()
					+ ", realizo el canje correctamente por: " + pt + " puntos");

		}
	}

	public boolean esPrimavera(LocalDateTime fecha) {
		LocalDateTime primaveraini = LocalDateTime.parse("2016-08-21T10:11:30");
		LocalDateTime primaverafin = LocalDateTime.parse("2016-12-21T10:11:30");

		if (fecha.isBefore(primaverafin) && fecha.isAfter(primaveraini)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean esFinde(LocalDateTime fecha) {

		DayOfWeek dayOfWeek = fecha.getDayOfWeek();

		if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) { // Finde

			System.out.println("ES FINDEEE");
			return true;
		} else {
			return false;
		}
	}

	public void activate(ActivationPurpose purpose) {
		if (_activator != null) {
			_activator.activate(purpose);
		}
	}

	public void bind(Activator activator) {
		if (_activator == activator) {
			return;
		}
		if (activator != null && _activator != null) {
			throw new IllegalStateException();
		}
		_activator = activator;
	}

	public CiudadanoDTO toDTO() {
		CiudadanoDTO ciudadanoDto = new CiudadanoDTO();
		ciudadanoDto.setId(this.id);
		ciudadanoDto.setNombre(this.nombre);
		ciudadanoDto.setApellido(this.apellido);
		ciudadanoDto.setDni(this.dni);
		ciudadanoDto.setEmail(this.email);
		ciudadanoDto.setPuntos(this.puntos);
		List<CanjeDTO> canjesDto = new ArrayList<CanjeDTO>();
		if (!this.canjes.isEmpty()) {
			for (Canje canje : canjes) {
				canjesDto.add(canje.toDTO());
			}
		}
		ciudadanoDto.setCanjes(canjesDto);
		List<ReclamoDTO> reclamosDto = new ArrayList<ReclamoDTO>();
		if (!reclamos.isEmpty()) {
			for (Reclamo reclamo : reclamos) {
				reclamosDto.add(reclamo.toDTO());
			}
		}
		ciudadanoDto.setProductos(reclamosDto);
		return ciudadanoDto;
	}
}
