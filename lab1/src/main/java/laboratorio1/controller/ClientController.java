package laboratorio1.controller;

import laboratorio1.model.Client;
import laboratorio1.service.ClientService;
import laboratorio1.service.ReportService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    @Autowired
    private ReportService reportService;
    
    @Autowired 
    private ClientService clientService;
    
    @GetMapping("/buscarTodos")
    public List<Client> buscarTodos(){
        return clientService.buscarTodos();
    }
      
    @GetMapping("/buscarPorNit")
    public Client buscarPorNit(@RequestParam String nit){
        return this.clientService.buscarPorNit(nit);
    }
    
    @GetMapping("/buscarPorNombreApellido")
    public List<Client> buscarPorNombreApellido(@RequestParam String query){
        return this.clientService.buscarPorNombreApellido(query);
    }
    
    @PostMapping("/crearCliente")
    public Client crearCliente(@RequestBody(required = true) Client client) throws Exception{
        return this.clientService.crearCliente(client);
    }
    
    @PutMapping("/editarCliente")
    public Client editarCliente(@RequestBody(required = true) Client client){
        return this.clientService.editarCliente(client);
    }
    
    @PutMapping("/editarCliente/{id}/{nit}")
    public Client editarClienteNit(@PathVariable(required = true) Long id, @PathVariable(required = true) String nit) throws Exception{
        return this.clientService.editarClienteNit(id, nit);
    }
    
    @PutMapping("/editarCliente/{id}/{firstName}/{lastName}")
    public Client editarClienteNombres(@PathVariable(required = true) Long id, @PathVariable(required = true) String firstName, @PathVariable(required = true) String lastName){
        return this.clientService.editarClienteNombres(id, firstName, lastName);
    }
    
    
    @GetMapping("/generarReporteClientes")
    public String generarReporteClientes(){
        return this.reportService.generarReporteClientes();
    }
    
    
    
}
