package es.cifpcm.SpringRestMarrero.controlador;

import es.cifpcm.SpringRestMarrero.dao.ProductoRepository;
import es.cifpcm.SpringRestMarrero.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProduductoController {

    @Autowired
    ProductoRepository productoRepository;

    public ProduductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping("/producto")
    List<Producto> all(){
        return productoRepository.findAll();
    }

    @PostMapping("/producto")
    Producto nuevoProducto(@RequestBody Producto pro){
        return productoRepository.save(pro);
    }

    @GetMapping("/producto/{id}")
    Optional<Producto> recibirProducto(@PathVariable Integer id){
        return productoRepository.findById(id);
    }

    @PostMapping("/producto/{id}")
    String eliminarProducto(@PathVariable Integer id){
       Producto pro = productoRepository.findById(id).orElse(null);
       if (pro.equals(null)){
           return "no se encontro";
       }else{
           productoRepository.delete(pro);
           return "eliminado correctamente";
       }
    }

    @PostMapping("/producto/edit/{id}")
    String editarProducto(@PathVariable Integer id, @RequestBody Producto pro){
        Producto pro2 = productoRepository.findById(id).orElse(null);

        pro2.setImagenProducto(pro.getImagenProducto());
        pro2.setNombreProducto(pro.getNombreProducto());
        pro2.setStockProducto(pro.getStockProducto());
        pro2.setPrecioProducto(pro.getPrecioProducto());
        pro2.setIdMunicipio(pro.getIdMunicipio());
        if (pro2.equals(null)){
            return "no se encontro";
        }else{
            productoRepository.save(pro2);
            return "Editado correctamente";
        }

    }
 //   @GetMapping("/producto/{id}")
   // void editarProducto(@PathVariable Integer id, @RequestBody Producto pro){
     //   Optional<Producto> pro = productoRepository.findById(id);
       // return productoRepository.delete(pro);
   // }

}
