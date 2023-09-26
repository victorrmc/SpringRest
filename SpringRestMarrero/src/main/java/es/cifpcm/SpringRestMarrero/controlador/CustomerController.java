package es.cifpcm.SpringRestMarrero.controlador;

import es.cifpcm.SpringRestMarrero.dao.CustomerRepository;
import es.cifpcm.SpringRestMarrero.modelo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository CustomerRepository;

    public CustomerController(CustomerRepository CustomerRepository) {
        this.CustomerRepository = CustomerRepository;
    }

    @GetMapping("/customer")
    List<Customer> all(){
        return CustomerRepository.findAll();
    }

    @PostMapping("/customer")
    Customer nuevoCustomer(@RequestBody Customer cus){
        return CustomerRepository.save(cus);
    }

    @GetMapping("/customer/{id}")
    Optional<Customer> recibirCustomer(@PathVariable Integer id){
        return CustomerRepository.findById(id);
    }

    @PostMapping("/customer/{id}")
    String eliminarCustomer(@PathVariable Integer id){
       Customer cus = CustomerRepository.findById(id).orElse(null);
       if (cus.equals(null)){
           return "no se encontro";
       }else{
           CustomerRepository.delete(cus);
           return "eliminado correctamente";
       }
    }

    @PostMapping("/customer/edit/{id}")
    String editarCustomer(@PathVariable Integer id, @RequestBody Customer cus){
        Customer cus2 = CustomerRepository.findById(id).orElse(null);
        cus2.setEmail(cus.getEmail());
        cus2.setFechadenacimiento(cus.getFechadenacimiento());
        cus2.setFirstname(cus.getFirstname());
        cus2.setLastname(cus.getLastname());
        cus2.setTelefono(cus.getTelefono());
        if (cus2.equals(null)){
            return "no se encontro";
        }else{
            CustomerRepository.save(cus2);
            return "Editado correctamente";
        }
    }
 //   @GetMapping("/Customer/{id}")
   // void editarCustomer(@PathVariable Integer id, @RequestBody Customer cus){
     //   Optional<Customer> cus = CustomerRepository.findById(id);
       // return CustomerRepository.delete(cus);
   // }

}
