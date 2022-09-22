package co.edu.unbosque.frontTecno;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import co.edu.unbosque.frontTecno.UsuarioJSON;
import co.edu.unbosque.frontTecno.Usuarios;

@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void validarCedula(String id, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{		
		try {
			ArrayList<Usuarios> listac = UsuarioJSON.getJSON();
			for(Usuarios usuarios: listac) {				
				if(usuarios.getCedula_usuario().equals(id)) {
					request.setAttribute("usuarioSeleccionado", usuarios);					
				}
			}
			} catch (Exception e) {			
				e.printStackTrace();
			}
		}
			
	public Controlador() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//**CODIGO KT**//*******************************************************************************************
	//VARIABLES GENERALES***************************************************************************************
	
	double precio, iva, valor_iva, subtotal, totalapagar, acusubtotal, subtotaliva = 0;
	int cantidad, item = 0;
	long numfac = 0;
	String descripcion, cedulaCliente, codProducto, ciudad, fecha;
	
	Usuarios usuarios = new Usuarios();
	Detalle_Venta detalle_venta = new Detalle_Venta();
	List <Detalle_Venta> listaVentas = new ArrayList<>();
	
	Consolidado consolidado = new Consolidado();
	
	
	//MÉTODO PARA BUSCAR LA CÉDULA DEL CLIENTE*****************************************************************************
	
	
	public void buscarCliente(String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ArrayList<Clientes> listac = ClienteJSON.getJSON();
			for (Clientes clientes: listac) {
				if(clientes.getCedula_cliente().equals(id)) {
					System.out.println(clientes.getNombre_cliente());
					request.setAttribute("clienteSeleccionado", clientes);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//MÉTODO PARA BUSCAR LA CÉDULA DEL CLIENTE*****************************************************************************
	
	
	public void buscarProducto(String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ArrayList<Productos> listap = ProductoJSON.getJSON();
			for (Productos productos: listap) {
				if(productos.getCodigo_producto().equals(id)) {
					
					request.setAttribute("productoSeleccionado", productos);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//************************************************************************************************************************//
	
	//MÉTODO PARA GRABAR LA VENTA*****************************************************************************
	
	public void grabarDetalle_Ventas(Long numFact, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		for (int i=0; i<listaVentas.size();i++) {
			detalle_venta = new Detalle_Venta();
			detalle_venta.setCodigo_venta(numFact);
			detalle_venta.setCodigo_producto(listaVentas.get(i).getCodigo_producto());
			detalle_venta.setValor_venta(listaVentas.get(i).getValor_venta());
			detalle_venta.setValor_total(listaVentas.get(i).getValor_total());
			detalle_venta.setValor_iva(listaVentas.get(i).getValor_iva());
			
			int respuesta=0;
			try {
				respuesta=DetalleVentaJSON.postJSON(detalle_venta);
				PrintWriter write = response.getWriter();
				if(respuesta==200) {
					System.out.println("Registro grabado en Detalle Ventas" + i);
					request.getRequestDispatcher("Controlador?menu=Ventas&accion=default").forward(request, response);
				}else {
					write.println("Error detalle venta "+respuesta);
				}
				write.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}	
		
	}
	//************************************************************************************************************************//
	
	//MÉTODO PARA GENERAR CONSECUTIVO********************************************************************************************//
	
	public Long generarConsecutivo() {
		long aux=0;
		try {
			ArrayList<Ventas> listaventas = VentasJSON.getJSON();
			for(Ventas venta:listaventas) {
				if(venta.getCodigo_venta()>aux) {
					aux=venta.getCodigo_venta();
				}
				
			}
			return aux+1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aux+1;
	}
	
	//************************************************************************************************************************//
	//MÉTODO PARA MOSTRAR CIUDAD FECHA Y QUE NO SE MANTENGAN VISIBLES*********************************************************//
	
	public void mostrarFechaCiudad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ciudad = request.getParameter("ciudad");
		fecha = request.getParameter("fecha");
		
		request.setAttribute("fechaSeleccionada", fecha);
		request.setAttribute("ciudadSeleccionada", ciudad);
		
	}
	//************************************************************************************************************************//	
	//MÉTODO PARA GRABAR CONSOLIDADO******************************************************************************************//
	
		public void grabarConsolidado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int respuesta=0;
			try {
				respuesta=ConsolidadoJSON.postJSON(consolidado);
				PrintWriter write = response.getWriter();
				if(respuesta ==200) {
					System.out.println("Registro grabado en consolidacion");
					request.getRequestDispatcher("Controlador?menu=Ventas&accion=default").forward(request, response);
				}else {
					write.println("Error de consolidacion" + respuesta);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		//************************************************************************************************************************//	
		
		//MÉTODO PARA BUSCAR VENTAS POR CIUDAD******************************************************************************************//
		
			public double mostrarTotalVentasCiudad(String ciudad){
				double acTotalVentas=0;
				
				try {
					ArrayList<Consolidado> listacon = ConsolidadoJSON.getJSON();
					for(Consolidado consolidados:listacon) {
						if(consolidados.getCiudad_venta().equals(ciudad)) {
							acTotalVentas += consolidados.getTotal_ventas();
						}
							
						}
					return acTotalVentas;
					}
					 catch (Exception e) {
					e.printStackTrace();
				}
				
				return acTotalVentas;
			}
			//************************************************************************************************************************//	
		
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
						ServletException, IOException {
		
		String menu=request.getParameter("menu");		
		String accion=request.getParameter("accion");
		
	//**CODIGO KT***************************************************************************************************************//
	//**CEDULA USUARIO ACTIVO PARA VENTAS**//
	
		String cedula_usuario_activo = request.getParameter("UsuarioActivo");
		usuarios.setCedula_usuario(cedula_usuario_activo);
		request.setAttribute("usuarioSeleccionado", usuarios);
	//**************************************************************************************************************************//
		
		switch (menu) {
			case "New_usuarios":
				//if(accion.equals("Agregar")) {
					//String id=request.getParameter("txtcedula");				
					//this.validarCedula(id, request, response);					
					//request.getRequestDispatcher("/New_usuarios.jsp").forward(request, response);
					//System.out.println("Usuario en uso");
					
				//}
				if (accion.equals("Agregar")) {
					Usuarios usuario = new Usuarios();
					usuario.setCedula_usuario(request.getParameter("txtcedula"));
					usuario.setNombre_usuario(request.getParameter("txtnombre"));
					usuario.setEmail_usuario(request.getParameter("txtemail"));
					usuario.setUsuario(request.getParameter("txtusuario"));
					usuario.setPassword(request.getParameter("txtpassword"));
					int respuesta=0;
					
					try {
						respuesta = UsuarioJSON.postJSON(usuario);
						PrintWriter write = response.getWriter();
						
						if (respuesta==200) {							
							request.getRequestDispatcher("/Inicio.jsp").forward(request, response);
						} else {
							write.println("Error: " + respuesta);
						}
						write.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
				
			case "Principal":
				request.getRequestDispatcher("/Home.jsp").forward(request, response);
			break;
			
			case "Usuarios":
				if (accion.equals("Listar")) {
					try {
						ArrayList<Usuarios> lista = UsuarioJSON.getJSON();
						request.setAttribute("listaUsuarios", lista);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if(accion.equals("Agregar")) {
					Usuarios usuario = new Usuarios();
					usuario.setCedula_usuario(request.getParameter("txtcedula"));
					usuario.setNombre_usuario(request.getParameter("txtnombre"));
					usuario.setEmail_usuario(request.getParameter("txtemail"));
					usuario.setUsuario(request.getParameter("txtusuario"));
					usuario.setPassword(request.getParameter("txtpassword"));
					int respuesta=0;
					try {
						respuesta = UsuarioJSON.postJSON(usuario);
						PrintWriter write = response.getWriter();
						if (respuesta==200) {
							request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar")
							.forward(request, response);
						} else {
							write.println("Error: " + respuesta);
						}
						write.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if(accion.equals("Actualizar")) {
					Usuarios usuario = new Usuarios();
					usuario.set_id(request.getParameter("txtid"));
					usuario.setCedula_usuario(request.getParameter("txtcedula"));
					usuario.setNombre_usuario(request.getParameter("txtnombre"));
					usuario.setEmail_usuario(request.getParameter("txtemail"));
					usuario.setUsuario(request.getParameter("txtusuario"));
					usuario.setPassword(request.getParameter("txtpassword"));
					int respuesta=0;
					try {
						respuesta = UsuarioJSON.putJSON(usuario, usuario.get_id());
						PrintWriter write = response.getWriter();
						if (respuesta==200) {
							request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar")
							.forward(request, response);
						} else {
							write.println("Error: " + respuesta);
						}
						write.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if(accion.equals("Cargar")) {
					String id= request.getParameter("id");
					try {
						ArrayList<Usuarios> lista1 = UsuarioJSON.getJSON();
						for (Usuarios usuarios:lista1){
							if (usuarios.getCedula_usuario().equals(id)) {
								request.setAttribute("usuarioSeleccionado", usuarios);
								request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar")
								.forward(request, response);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if(accion.equals("Eliminar")) {
					String id= request.getParameter("id");
					int respuesta=0;
					try {
						respuesta = UsuarioJSON.deleteJSON(id);
						PrintWriter write = response.getWriter();
						if (respuesta==200) {
							request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar")
							.forward(request, response);
						} else {
							write.println("Error: " + respuesta);
						}
					} catch (Exception e) {
							e.printStackTrace();
					}					
				}		
					request.getRequestDispatcher("/Usuarios.jsp").forward(request, response);
				break;
				
				case "Clientes":
					if (accion.equals("Listar")) {
						try {
							ArrayList<Clientes> lista = ClienteJSON.getJSON();
							request.setAttribute("listaClientes", lista);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(accion.equals("Agregar")) {
						Clientes cliente = new Clientes();
						cliente.setCedula_cliente(request.getParameter("txtcedula"));
						cliente.setNombre_cliente(request.getParameter("txtnombre"));
						cliente.setEmail_cliente(request.getParameter("txtemail"));
						cliente.setDireccion_cliente(request.getParameter("txtdireccion"));
						cliente.setTelefono_cliente(request.getParameter("txttelefono"));
						
						int respuesta=0;
						try {
							respuesta = ClienteJSON.postJSON(cliente);
							PrintWriter write = response.getWriter();
							if (respuesta==200) {
								request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar")
								.forward(request, response);
							} else {
								write.println("Error: " + respuesta);
							}
							write.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(accion.equals("Actualizar")) {
						Clientes cliente = new Clientes();
						cliente.set_id(request.getParameter("txtid"));
						cliente.setCedula_cliente(request.getParameter("txtcedula"));
						cliente.setNombre_cliente(request.getParameter("txtnombre"));
						cliente.setEmail_cliente(request.getParameter("txtemail"));
						cliente.setDireccion_cliente(request.getParameter("txtdireccion"));
						cliente.setTelefono_cliente(request.getParameter("txttelefono"));						
						int respuesta=0;
						try {
							respuesta = ClienteJSON.putJSON(cliente, cliente.get_id());
							PrintWriter write = response.getWriter();						
							if (respuesta==200) {
								request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar")
								.forward(request, response);
							} else {
								write.println("Error: " + respuesta);
							}
							write.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(accion.equals("Cargar")) {
						String id= request.getParameter("id");
						try {
							ArrayList<Clientes> lista1 = ClienteJSON.getJSON();
							for (Clientes clientes:lista1){
								if (clientes.getCedula_cliente().equals(id)) {
									request.setAttribute("clienteSeleccionado", clientes);
									request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar")
									.forward(request, response);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(accion.equals("Eliminar")) {
						String id= request.getParameter("id");
						int respuesta=0;
						try {
							respuesta = ClienteJSON.deleteJSON(id);
							PrintWriter write = response.getWriter();
							if (respuesta==200) {
								PrintWriter out = response.getWriter();
									
								request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar")
								.forward(request, response);
							} else {
								write.println("Error: " + respuesta);
							}
						} catch (Exception e) {
								e.printStackTrace();
						}					
					}		
					request.getRequestDispatcher("/Clientes.jsp").forward(request, response);
				break;
				
				case "Proveedores":
					if (accion.equals("Listar")) {
						try {
							ArrayList<Proveedores> lista = ProveedorJSON.getJSON();
							request.setAttribute("listaProveedores", lista);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(accion.equals("Agregar")) {
						Proveedores proveedor = new Proveedores();
						proveedor.setNit_proveedor(request.getParameter("txtnit"));
						proveedor.setNombre_proveedor(request.getParameter("txtnombre"));
						proveedor.setCiudad_proveedor(request.getParameter("txtciudad"));
						proveedor.setDireccion_proveedor(request.getParameter("txtdireccion"));
						proveedor.setTelefono_proveedor(request.getParameter("txttelefono"));
						
						int respuesta=0;
						try {
							respuesta = ProveedorJSON.postJSON(proveedor);
							PrintWriter write = response.getWriter();
							if (respuesta==200) {
								request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar")
								.forward(request, response);
							} else {
								write.println("Error: " + respuesta);
							}
							write.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(accion.equals("Actualizar")) {
						Proveedores proveedor = new Proveedores();
						proveedor.set_id(request.getParameter("txtid"));
						proveedor.setNit_proveedor(request.getParameter("txtnit"));
						proveedor.setNombre_proveedor(request.getParameter("txtnombre"));
						proveedor.setCiudad_proveedor(request.getParameter("txtciudad"));
						proveedor.setDireccion_proveedor(request.getParameter("txtdireccion"));
						proveedor.setTelefono_proveedor(request.getParameter("txttelefono"));						
						int respuesta=0;
						try {
							respuesta = ProveedorJSON.putJSON(proveedor, proveedor.get_id());
							PrintWriter write = response.getWriter();						
							if (respuesta==200) {
								request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar")
								.forward(request, response);
							} else {
								write.println("Error: " + respuesta);
							}
							write.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(accion.equals("Cargar")) {
						String id= request.getParameter("id");
						try {
							ArrayList<Proveedores> lista1 = ProveedorJSON.getJSON();
							for (Proveedores proveedores:lista1){
								if (proveedores.getNit_proveedor().equals(id)) {
									request.setAttribute("proveedorSeleccionado", proveedores);
									request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar")
									.forward(request, response);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(accion.equals("Eliminar")) {
						String id= request.getParameter("id");
						int respuesta=0;
						try {
							respuesta = ProveedorJSON.deleteJSON(id);
							PrintWriter write = response.getWriter();
							if (respuesta==200) {
								PrintWriter out = response.getWriter();
									
								request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar")
								.forward(request, response);
							} else {
								write.println("Error: " + respuesta);
							}
						} catch (Exception e) {
								e.printStackTrace();
						}					
					}	
					request.getRequestDispatcher("/Proveedores.jsp").forward(request, response);
				break;
				
				case "Productos":
					if (accion.equals("Listar")) {
						try {
							ArrayList<Productos> lista = ProductoJSON.getJSON();
							request.setAttribute("listaProductos", lista);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(accion.equals("Agregar")) {
						Productos producto = new Productos();
						producto.setCodigo_producto(request.getParameter("txtcodigo"));
						producto.setNombre_producto(request.getParameter("txtnombre"));
						producto.setNit_proveedor(request.getParameter("txtnit"));
						producto.setPrecio_compra(Double.parseDouble(request.getParameter("txtprecioproducto")));
						producto.setIva_compra(Double.parseDouble(request.getParameter("txtiva")));
						producto.setPrecio_venta(Double.parseDouble(request.getParameter("txtprecioventa")));
						System.out.println(producto.getCodigo_producto());
						System.out.println(producto.getNombre_producto());
						System.out.println(producto.getNit_proveedor());
						System.out.println(producto.getPrecio_compra());
						System.out.println(producto.getIva_compra());
						System.out.println(producto.getPrecio_venta());
						int respuesta=0;
						try {
							respuesta = ProductoJSON.postJSON(producto);
							PrintWriter write = response.getWriter();
							if (respuesta==200) {
								System.out.println("No esta agregando");
								request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar")
								.forward(request, response);
							} else {
								write.println("Error: " + respuesta);
							}
							write.close();
						} catch (Exception e) {
							System.out.println("causa un error");
							e.printStackTrace();
						}
					}else if(accion.equals("Actualizar")) {
						Productos producto = new Productos();
						producto.set_id(request.getParameter("txtid"));
						producto.setCodigo_producto(request.getParameter("txtcodigo"));
						producto.setNombre_producto(request.getParameter("txtnombre"));
						producto.setNit_proveedor(request.getParameter("txtnit"));
						producto.setPrecio_compra(Double.parseDouble(request.getParameter("txtprecioproducto")));
						producto.setIva_compra(Double.parseDouble(request.getParameter("txtiva")));
						producto.setPrecio_venta(Double.parseDouble(request.getParameter("txtprecioventa")));
						int respuesta=0;
						try {
							respuesta = ProductoJSON.putJSON(producto, producto.get_id());
							PrintWriter write = response.getWriter();
							if (respuesta==200) {
								request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar")
								.forward(request, response);
							} else {
								write.println("Error: " + respuesta);
							}
							write.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(accion.equals("Cargar")) {
						String id= request.getParameter("id");
						try {
							ArrayList<Productos> lista1 = ProductoJSON.getJSON();
							for (Productos productos:lista1){
								if (productos.getCodigo_producto().equals(id)) {
									request.setAttribute("productoSeleccionado", productos);
									request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar")
									.forward(request, response);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(accion.equals("Eliminar")) {
						String id= request.getParameter("id");
						int respuesta=0;
						try {
							respuesta = ProductoJSON.deleteJSON(id);
							PrintWriter write = response.getWriter();
							if (respuesta==200) {
								System.out.println("Se elimino");
								request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar")
								.forward(request, response);
							} else {
								write.println("Error: " + respuesta);
							}
						} catch (Exception e) {
								e.printStackTrace();
						}					
					}
						
					request.getRequestDispatcher("/Productos.jsp").forward(request, response);
				break;
				
				case "Ventas":
					//***************enviar al formulario la cc de usuario y numfac****KT*************///
					request.setAttribute("usuarioSeleccionado", usuarios);
					request.setAttribute("numerofactura", numfac);
					//***************e*************************************************KT*************///
					if(accion.equals("BuscarCliente")) {
						String id = request.getParameter("cedulacliente");
						this.buscarCliente(id,request, response);
						this.mostrarFechaCiudad(request, response);
					}else if(accion.equals("BuscarProducto")){
						String id = request.getParameter("cedulacliente");
						this.buscarCliente(id,request, response);
						String cod = request.getParameter("codigoproducto");
						this.buscarProducto(cod, request, response);
						this.mostrarFechaCiudad(request, response);
					}else if(accion.equals("AgregarProducto")){
						String id = request.getParameter("cedulacliente");
						this.buscarCliente(id,request, response);
						this.mostrarFechaCiudad(request, response);
						
						detalle_venta = new Detalle_Venta();
						item++;
						totalapagar=0;
						codProducto=request.getParameter("codigoproducto");
						descripcion=request.getParameter("nombreproducto");
						precio=Double.parseDouble(request.getParameter("precioproducto"));
						cantidad=Integer.parseInt(request.getParameter("cantidadproducto"));
						iva= Double.parseDouble(request.getParameter("ivaproducto"));
						
						subtotal = precio*cantidad;
						valor_iva = (subtotal*iva)/100;
						
						detalle_venta.setCodigo_detalle_venta(item);
						detalle_venta.setCodigo_producto(codProducto);
						detalle_venta.setNombre_producto(descripcion);
						detalle_venta.setCantidad_producto(cantidad);
						detalle_venta.setPrecio_producto(precio);
						detalle_venta.setCodigo_venta(numfac);
						detalle_venta.setValor_iva(valor_iva);
						detalle_venta.setValor_venta(subtotal);
						listaVentas.add(detalle_venta);
						
						for (int i=0; i<listaVentas.size(); i++) {
							acusubtotal+=listaVentas.get(i).getValor_venta();
							subtotaliva+=listaVentas.get(i).getValor_iva();
							
						}
						
						totalapagar=acusubtotal+subtotaliva;
						detalle_venta.setValor_total(totalapagar);
						
						//primer parametro es como lo vamos a llamar desde el jsp / segundo atributo lo que se envia y como lo recibe el formulario 
						request.setAttribute("listaventas", listaVentas);
						request.setAttribute("totalsubtotal", acusubtotal);
						request.setAttribute("totaliva", subtotaliva);
						request.setAttribute("totalapagar", totalapagar);
					
					}else if(accion.equals("GenerarVenta")) {
						cedulaCliente=request.getParameter("cedulacliente");
						String numFact = request.getParameter("numerofactura");
						
						Ventas ventas= new Ventas();
						
						ventas.setCodigo_venta(Long.parseLong(numFact));
						ventas.setCedula_cliente(cedulaCliente);
						ventas.setCedula_usuario(usuarios.getCedula_usuario());
						ventas.setValor_venta(acusubtotal);
						ventas.setIva_venta(subtotaliva);
						ventas.setTotal_venta(totalapagar);
						ventas.setFecha_venta(fecha);
						ventas.setCiudad_venta(ciudad);
						System.out.println(ventas.getCiudad_venta());
						System.out.println(ventas.getFecha_venta());
						
						consolidado.setCiudad_venta(ciudad);
						consolidado.setFecha_venta(fecha);
						consolidado.setTotal_ventas(totalapagar);
						
						int respuesta=0;
						try {
							respuesta = VentasJSON.postJSON(ventas);
							PrintWriter write = response.getWriter();
							if(respuesta==200) {
								System.out.println("Grabación exitosa" + respuesta);
								this.grabarConsolidado(request, response);
								this.grabarDetalle_Ventas(ventas.getCodigo_venta(), request, response);
							}else {
								write.println("Error Ventas:" + respuesta);
							}
							write.close();
							} catch (Exception e) {
							e.printStackTrace();
						}
						listaVentas.clear();
						item=0;
						totalapagar=0;
						acusubtotal=0;
						subtotaliva=0;
					}else {
						numfac=this.generarConsecutivo();
						request.setAttribute("numerofactura", numfac);
					}
						
					request.getRequestDispatcher("/Ventas.jsp").forward(request, response);
				break;
				
				case "Reportes":
					
					int opcion=0;
					if (accion.equals("ReporteClientes")) {
						try {
							ArrayList<Clientes> lista = ClienteJSON.getJSON();
							opcion=1;
							request.setAttribute("listaClientes", lista);
							request.setAttribute("opcion", opcion);
						} catch (Exception e) {
							e.printStackTrace();
							// TODO: handle exception
						}
					}else if(accion.equals("ReporteVentas"))
						try {
							ArrayList<Ventas> lista = VentasJSON.getJSON();
							opcion=2;
							request.setAttribute("listaVentas", lista);
							request.setAttribute("opcion", opcion);
						} catch (Exception e) {
							e.printStackTrace();
						}
							request.getRequestDispatcher("/Reportes.jsp").forward(request, response);
				break;
				case "Consolidado":
					double totalventascali, totalventasbogota, totalventasmedellin, totalventasgeneral = 0;
					totalventascali = this.mostrarTotalVentasCiudad("Cali");
					totalventasbogota = this.mostrarTotalVentasCiudad("Bogota");
					totalventasmedellin = this.mostrarTotalVentasCiudad("Medellin");
					totalventasgeneral = totalventascali+totalventasbogota+totalventasmedellin;
					request.setAttribute("totalventacali", totalventascali);
					request.setAttribute("totalventabogota", totalventasbogota);
					request.setAttribute("totalventamedellin", totalventasmedellin);
					request.setAttribute("totalventasgeneral", totalventasgeneral);
						
					request.getRequestDispatcher("/ReporteConsolidado.jsp").forward(request, response);
				break;
				

		}
	}
}

