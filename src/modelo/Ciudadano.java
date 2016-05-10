package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.db4o.activation.ActivationPurpose;
import com.db4o.activation.Activator;
import com.db4o.ta.Activatable;

public class Ciudadano implements Activatable {
	String nombre;
	String apellido;
	int dni;
	String email;
	int puntos;
	List<Canje> canjes;
	List<Reclamo> reclamos;
	private transient Activator _activator;

	public Ciudadano(String nombre, String apellido, int dni, String email) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.puntos = 0;
		this.canjes = new ArrayList();
		this.reclamos = new ArrayList();
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
		Integer pts = null;
		Calendar cal = null;

		LocalDateTime primaveraini = LocalDateTime.parse("2016-08-21T10:11:30");
		LocalDateTime primaverafin = LocalDateTime.parse("2016-12-21T10:11:30");
		LocalDateTime dia = LocalDateTime.parse("2016-08-27T10:11:30");
		// LocalDateTime diaa = reclamo.getFecha();
		cal = Calendar.getInstance();

		DayOfWeek dayOfWeek = dia.getDayOfWeek();

		if (dia.isBefore(primaverafin) && dia.isAfter(primaveraini)
				&& (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)) { // Finde
			// en
			// Periodo
			// de
			// Primavera
			System.out.println("Entro en primavera");
			System.out.println("ES FINDEEE");
			pts = reclamo.getCategoria().getPuntos() * 2;// Duplico
															// los
															// puntos

		} else {

			pts = reclamo.getCategoria().getPuntos();
		}
		this.setPuntos(this.getPuntos() + pts);

		this.reclamos.add(reclamo);

		System.out.println("Se realizo el Reclamo " + reclamo.descripcion + " correctamente por: " + pts + " puntos");

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
		Calendar cal = null;
		// Date hoy=new Date();
		try {
			if (this.getPuntos() < pro.getPuntosrequeridos()) {

				System.out.println("No tiene los Puntos requeridos para realizar el canje ");

			} else {
				SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
				Date primavera1 = formateador.parse("21/08/2016");
				Date primavera2 = formateador.parse("21/12/2016");
				Date dia = formateador.parse("22/08/2016");
				// dia=hoy;
				cal = Calendar.getInstance();
				cal.setTime(dia);
				int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
				if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {

					System.out.println("ES FINDEEE");

				}
				if (dia.before(primavera2) && dia.after(primavera1) && this.puntos > 10) { // Periodo
																							// de
																							// Primavera
					System.out.println("Entro en primavera");
					pts = this.getPuntos() - (pro.getPuntosrequeridos() / 2);

				} else {

					pts = this.getPuntos() - (pro.getPuntosrequeridos());
				}
				this.setPuntos(pts);
				Canje can = new Canje("22/08/2016");
				can.setProducto(pro);
				this.canjes.add(can);
				System.out.println("Se realizo el canje correctamente por: " + pts + " puntos");

			}
		} catch (ParseException e) {
			System.out.println("Se Produjo un Error!!!  " + e.getMessage());
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
}
