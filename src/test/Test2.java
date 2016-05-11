package test;

import java.time.LocalDateTime;

import api.Api;
import api.Reclamos;
import dto.CanjeDTO;
import dto.CatalogoDTO;
import dto.CiudadanoDTO;
import dto.ProductoDTO;
import modelo.Ciudadano;

public class Test2 {

	public static void main(String[] args) {

		Api api = new Api();
		// CIUDADANO
		api.conectarDB();
		CiudadanoDTO ciudadanoDto = new CiudadanoDTO(null, "Javier", "Valsecchi", 35591732, "javier@javier.com", 0,
				null, null);
		CiudadanoDTO ciudadanoDto2 = new CiudadanoDTO(null, "Guillermo", "Difabio", 36849832, "guille@gmail.com", 100,
				null, null);
		CiudadanoDTO ciudadanoDto3 = new CiudadanoDTO(null, "Roberto", "Perez", 38567432, "robertoperez@gmail.com", 10,
				null, null);

		ciudadanoDto = api.crearCiudadano(ciudadanoDto);
		ciudadanoDto2 = api.crearCiudadano(ciudadanoDto2);
		ciudadanoDto3 = api.crearCiudadano(ciudadanoDto3);
		CiudadanoDTO ciudadanoDto1 = new CiudadanoDTO(null, null, null, 35591732, null, 0, null, null);
		ciudadanoDto1 = api.buscarCiudadano(ciudadanoDto1);
		// api.eliminarCiudadano(ciudadanoDto);
		// ciudadanoDto.setApellido(" ");
		// api.modificarCiudadano(ciudadanoDto);
		api.listarCiudadanos();

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
		api.realizarCanje(ciudadanoDto3, productoDto4);//No tiene los puntos Necesario 15
		api.listarCanjes();
		api.listarCiudadanos();
		api.desconectarDB();
		System.out.println("Fin del Test");
	}

}
