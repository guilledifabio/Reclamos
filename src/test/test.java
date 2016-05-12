package test;

import api.Api;
import dto.CatalogoDTO;
import dto.CategoriaDTO;
import dto.CiudadanoDTO;
import dto.ProductoDTO;
import modelo.ProveedorDelTiempo;

public class test {

	private static Api api = new Api();

	public static void main(String[] args) {
		creacionDATOS();

//		api.conectarDB();
		/*
		// La vista crea un nuevo ciudadano, api devuelve un ciudadanoDTO con id
		// correspodniente
		CiudadanoDTO ciudadanoDto1 = new CiudadanoDTO("Javier", "Valsecchi", 35591732, "javier@javier.com");
		ciudadanoDto1 = api.crearCiudadano(ciudadanoDto1);

		// El usuario crea un reclamo, se recuperan las categorias para el
		// reclamo
		List<CategoriaDTO> lcategoria = api.listarCategorias();
		ReclamoDTO reclamoDto = new ReclamoDTO("Poso en la calle", "Rotonda 1", lcategoria.get(2));
		api.crearReclamo(ciudadanoDto1, reclamoDto);

		// Se agrega un nuevo evento al reclamo realizando por el usuario
		EventoDTO eventoDto = new EventoDTO("Reclamo procesado, pendiente solucion");
		List<ReclamoDTO> lreclamosDto = api.listarReclamoCiudadano(ciudadanoDto1);
		api.agregarEventoReclamo(lreclamosDto.get(0), eventoDto);
		List<EventoDTO> leventoDto = api.listarEventosReclamo(lreclamosDto.get(0));

		// Se actualiza un evento del reclamo
		EventoDTO eventoDto1 = leventoDto.get(1);
		eventoDto1.setDescripcion("Reclamo procesado, pendiente solucion + ALGO");
		api.modificarEventoReclamo(eventoDto1);
		api.listarEventosReclamo(lreclamosDto.get(0));

		// Eliminar Evento de un reclamo
		api.eliminarEventoDeReclamo(lreclamosDto.get(0), eventoDto1);
		api.listarEventosReclamo(lreclamosDto.get(0));
	*/
		CiudadanoDTO ciudadanoDto2 = new CiudadanoDTO(null, "Guillermo", "Difabio", 36849832, "guille@gmail.com", 100,
				null, null, new ProveedorDelTiempo("2016-08-22T10:11:30"));
		CiudadanoDTO ciudadanoDto3 = new CiudadanoDTO(null, "Roberto", "Perez", 38567432, "robertoperez@gmail.com", 10,
				null, null);

		ciudadanoDto2 = api.crearCiudadano(ciudadanoDto2);
		ciudadanoDto3 = api.crearCiudadano(ciudadanoDto3);

		// PRODUCTOS
		ProductoDTO productoDto = new ProductoDTO("Notebook", 30);
		ProductoDTO productoDto2 = new ProductoDTO("Tv Samsung  29' ", 30);
		ProductoDTO productoDto3 = new ProductoDTO("Tablet Noganet' ", 12);
		ProductoDTO productoDto4 = new ProductoDTO("Smartphone LG G3 Beat' ", 16);
		productoDto = api.crearProducto(productoDto);
		productoDto2 = api.crearProducto(productoDto2);
		productoDto3 = api.crearProducto(productoDto3);
		productoDto4 = api.crearProducto(productoDto4);
		// api.eliminarProducto(productoDto);
		productoDto = api.buscarProducto(productoDto);
		productoDto.setNombre("Tv Samsung  30' ");
		api.modificarProducto(productoDto);

		// CATALOGOS
		CatalogoDTO catalogoDto = new CatalogoDTO("Catalogo 1");
		CatalogoDTO catalogoDto2 = new CatalogoDTO("Catalogo Nuevo");
		catalogoDto = api.crearCatalogo(catalogoDto);
		catalogoDto2 = api.crearCatalogo(catalogoDto2);
		api.agregarProductoaCatalogo(catalogoDto, productoDto2);
		api.agregarProductoaCatalogo(catalogoDto, productoDto3);
		// catalogoDto2= api.buscarCatalogo(catalogoDto2);
		catalogoDto2.setNombre("Catalogo 3");
		api.modificarCatalogo(catalogoDto2);
		api.listarCatalogos();

		// CANJES
		api.realizarCanje(ciudadanoDto2, productoDto3);
		api.realizarCanje(ciudadanoDto2, productoDto2);
		api.realizarCanje(ciudadanoDto3, productoDto4);// No tiene los puntos
														// Necesario 15
		api.listarCanjes();
		api.listarCiudadanos();

//		api.desconectarDB();

		System.out.println("Fin del Test");
		
//		try {
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		} finally {
//			
//		}
	}

	public static void creacionDATOS() {
//		api.conectarDB();

		CategoriaDTO categoriaDto = new CategoriaDTO("Derrumbe", "Todo se cae", 10);
		CategoriaDTO categoriaDto1 = new CategoriaDTO("Arboles", "Cortar Arboles", 2);
		CategoriaDTO categoriaDto2 = new CategoriaDTO("Pavimento", "Pozos en la calle", 6);
		CategoriaDTO categoriaDto3 = new CategoriaDTO("Postes", "Postes en mal estado o en peligro", 7);
		CategoriaDTO categoriaDto4 = new CategoriaDTO("Servicios", "problema con algun serivio publico", 9);
		categoriaDto = api.crearCategoria(categoriaDto);
		categoriaDto1 = api.crearCategoria(categoriaDto1);
		categoriaDto2 = api.crearCategoria(categoriaDto2);
		categoriaDto3 = api.crearCategoria(categoriaDto3);
		categoriaDto4 = api.crearCategoria(categoriaDto4);

//		api.desconectarDB();
	}

}