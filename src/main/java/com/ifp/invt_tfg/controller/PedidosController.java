package com.ifp.invt_tfg.controller;

import com.ifp.invt_tfg.model.*;
import com.ifp.invt_tfg.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/pedidos")
public class PedidosController {

    @Autowired
    IPedidosService servicePedidos;

    @Autowired
    IProductosService serviceProd;

    @Autowired
    IUsuariosService usuariosRepo;

    @Autowired
    IVentasService serviceVents;

    @Autowired
    IClientesService serviceClient;

    @Autowired
    IPedidosService servicePedido;


    @PostMapping(value="/quitar/{indice}")
    public String EliminarProducto(@PathVariable int indice, HttpServletRequest request){
        ArrayList<ProductoVendido> carrito = this.obtenerCarrito(request);
        if(carrito != null && carrito.size() >0 && carrito.get(indice) !=null){
            carrito.remove(indice);
            this.guardarCarrito(carrito, request);
        }
        return "redirect:/pedidos/index";
    }

    private void limpiarCarrito (HttpServletRequest request){
        this.guardarCarrito(new ArrayList<>(), request);
    }

    @GetMapping(value = "/limpiar")
    public String cancelarVenta(HttpServletRequest request, RedirectAttributes attributes){
        this.limpiarCarrito(request);
        attributes
                .addFlashAttribute("mensaje","Pedido Cancelado")
                .addFlashAttribute("clase","info");
        return "redirect:/pedidos/index";
    }

    @PostMapping(value="/terminar")
    public String terminarPedido(HttpServletRequest request, @AuthenticationPrincipal UserDetails currentUser, RedirectAttributes attributes)  {

     ArrayList<ProductoVendido> carrito = this.obtenerCarrito(request);

     if(carrito==null || carrito.size() <=0){
         return "redirect:/pedidos/index";
     }
        String NumRefr = request.getParameter("NumRefClient");
        if(serviceClient.BuscarPorNumRefr(NumRefr)!=null) {
            Cliente client=serviceClient.BuscarPorNumRefr(NumRefr);
            client.plusTotalPed();

            System.out.println(NumRefr);

            Ventas v = serviceVents.guardar(new Ventas(serviceClient.BuscarPorNumRefr(NumRefr)));


            for (ProductoVendido prodcVend : carrito) {
                Productos p = serviceProd.buscarPorId(prodcVend.getId());
                if (p == null) continue;

                p.restarUnidades(prodcVend.getCantidad());

                serviceProd.guardar(p);

                Pedidos Ped = new Pedidos(prodcVend.getCantidad(), prodcVend.getCodigo(), prodcVend.getNombre(), prodcVend.getPrecio(), usuariosRepo.buscarPorUsername(currentUser.getUsername()), v);
                servicePedidos.guardar(Ped);
            }

            this.limpiarCarrito(request);
            attributes
                    .addFlashAttribute("mensaje", "El pedido se ha realizado correctamente")
                    .addFlashAttribute("clase", "success");
            return "redirect:/pedidos/index";
        }else{
            attributes
                    .addFlashAttribute("mensaje", "El Cliente no es correcto")
                    .addFlashAttribute("clase", "info");
            return "redirect:/pedidos/index";

        }


    }


    //Muestra la lista de productos del pedido
    @GetMapping(value="/index")
    public String MostrarIndex(Model model, HttpServletRequest request, @Param("filtroProd") String filtroProd
    , @Param("filtroCli") String filtroCli){
        model.addAttribute("productos",new Productos());
        float total =0;
        ArrayList<ProductoVendido> carrito = this.obtenerCarrito(request);
        for(ProductoVendido p : carrito) total += p.getTotal();
        model.addAttribute("total",total);

        List<Productos> listaProd = servicePedidos.buscarTodosProd(filtroProd);
        List<Cliente> listaCli = servicePedidos.buscarTodosCli(filtroCli);
        model.addAttribute("prod", listaProd);
        model.addAttribute("cli", listaCli);
        model.addAttribute("filtroProd", filtroProd);
        model.addAttribute("filtroCli", filtroCli);
        return "pedidos/listPedidos";

    }


    private ArrayList<ProductoVendido> obtenerCarrito(HttpServletRequest request){
        ArrayList<ProductoVendido> carrito = (ArrayList<ProductoVendido>) request.getSession().getAttribute("carrito");
        if(carrito == null){
            carrito = new ArrayList<>();
        }
        return carrito;
    }

    private void guardarCarrito(ArrayList<ProductoVendido> carrito, HttpServletRequest request){
        request.getSession().setAttribute("carrito", carrito);

    }


    @GetMapping(value="/sumar/{code}/{index}")
    public String suamarProductos(@PathVariable("code") String CodePedidos,@PathVariable("index") int indexPed , HttpServletRequest request, RedirectAttributes attributes){
        ArrayList<ProductoVendido> carrito = this.obtenerCarrito(request);

        Productos productoCode = serviceProd.buscarPorCodigo(CodePedidos);

        if(productoCode.sinUnidades()) {
            attributes
                    .addFlashAttribute("mensaje", "El producto está agotado")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/pedidos/index";
        }


        boolean encontrado = false;
        int Unid =  productoCode.getUnidades();
        float ProdUnid= Unid;
        for(ProductoVendido PVActual : carrito){
            if(carrito.get(indexPed).getCodigo().equals(productoCode.getCodigo()) && ProdUnid>PVActual.getCantidad()){
                System.out.println("AQUIIII "+indexPed+"  AAAA"+carrito.size());
                PVActual.aumentarCantidad();
                encontrado=true;
                break;
            }
           else {
                System.out.println(ProdUnid +" yyyy "+PVActual.getCantidad() );
                attributes
                        .addFlashAttribute("mensaje", "No hay suficientes existencias del producto")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/pedidos/index";
            }


        }
        if(!encontrado){
            carrito.add(new ProductoVendido(productoCode.getNombre(),productoCode.getCodigo(),productoCode.getPrecio(),productoCode.getUnidades(),productoCode.getId(),1f));
        }
        this.guardarCarrito(carrito,request);
        return "redirect:/pedidos/index";
    }

    @GetMapping(value="/restar/{code}")
    public String restarProductos(@PathVariable("code") String CodePedidos, HttpServletRequest request, RedirectAttributes attributes){
        ArrayList<ProductoVendido> carrito = this.obtenerCarrito(request);
        Productos productoCode = serviceProd.buscarPorCodigo(CodePedidos);

        if(productoCode.sinUnidades()) {
            attributes
                    .addFlashAttribute("mensaje", "El producto está agotado")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/pedidos/index";
        }


        boolean encontrado = false;
        int Unid =  productoCode.getUnidades();
        float ProdUnid= Unid;
        for(ProductoVendido PVActual : carrito){
            if(PVActual.getCodigo().equals(productoCode.getCodigo()) && PVActual.getCantidad()>1){
                PVActual.restarCantidad();
                encontrado=true;
                break;
            }
            else {
                System.out.println(ProdUnid +" yyyy "+PVActual.getCantidad() );
                attributes
                        .addFlashAttribute("mensaje", "No hay existencias del producto")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/pedidos/index";
            }


        }
        if(!encontrado){
            carrito.add(new ProductoVendido(productoCode.getNombre(),productoCode.getCodigo(),productoCode.getPrecio(),productoCode.getUnidades(),productoCode.getId(),1f));
        }
        this.guardarCarrito(carrito,request);
        return "redirect:/pedidos/index";
    }


    @PostMapping(value="/agregar")
    public String agregarProductos(@ModelAttribute Productos productos, HttpServletRequest request, RedirectAttributes attributes){
        float unidades;
        ArrayList<ProductoVendido> carrito = this.obtenerCarrito(request);
        Productos productoCode = serviceProd.buscarPorCodigo(productos.getCodigo());
        if(request.getParameter("Unidades").equals(null)){
            unidades=1;
        }else{  
        unidades= Float.parseFloat(request.getParameter("Unidades"));}

        if(productoCode == null){
            attributes
                    .addFlashAttribute("mensaje","El producto con el código "+ productos.getCodigo() +" no existe")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/pedidos/index";
        }
        if(productoCode.sinUnidades()) {
            attributes
                    .addFlashAttribute("mensaje", "El producto está agotado")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/pedidos/index";
        }if(productoCode.getUnidades()<unidades||unidades<0){
            attributes
                    .addFlashAttribute("mensaje", "No hay suficientes existencias del producto")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/pedidos/index";
        }
        boolean encontrado = false;
        for(ProductoVendido PVActual : carrito){

            if(PVActual.getCodigo().equals(productoCode.getCodigo())){

                    PVActual.InputCantidad(unidades);
                    //PVActual.aumentarCantidad();
                    encontrado=true;
                    break;

            }

        }
        if(!encontrado){
            carrito.add(new ProductoVendido(productoCode.getNombre(),productoCode.getCodigo(),productoCode.getPrecio(),productoCode.getUnidades(),productoCode.getId(),unidades));
        }
        this.guardarCarrito(carrito,request);
        return "redirect:/pedidos/index";
    }
}

