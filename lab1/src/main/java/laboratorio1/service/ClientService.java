package laboratorio1.service;

import laboratorio1.dao.ClientRepository;
import laboratorio1.model.Client;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repoclient;
    
    public List<Client> buscarTodos(){
        return (List<Client>) this.repoclient.findAll();
    }
    
    public Client buscarPorNit(String nit){
        List<Client> clientes = (List<Client>) this.repoclient.findAll();
        Client cliente = new Client();
        for(Client x:clientes)
        {
            if(x.getNit().equals(nit)){
                cliente.setId(x.getId());
                cliente.setNit(x.getNit());
                cliente.setFirstName(x.getFirstName());
                cliente.setLastName(x.getLastName());
                cliente.setAddress(x.getAddress());
                cliente.setPhone(x.getPhone());
            }
        }
        return cliente;
    }
    
    public List<Client> buscarPorNombreApellido(String query){
        List<Client> clientes = (List<Client>) this.repoclient.findAll();
        Client cliente = new Client();
        List<Client> encontrados = new ArrayList<Client>();
        if(!query.contains("*"))
        {
            for(Client x:clientes)
            {
                if(x.getFirstName().equals(query))
                {
                    encontrados.add(x);
                }
                else if(x.getLastName().equals(query))
                {
                    encontrados.add(x);
                }
                else if((x.getFirstName()+" "+x.getLastName()).equals(query))
                {
                    encontrados.add(cliente);                    
                }
                else{
                    
                }
            }            
        }
        else{
            int posicion = query.indexOf("*");
            
            if(posicion == 0){
                for(Client x:clientes)
                {
                    String nombreApellido = x.getFirstName()+" "+x.getLastName();
                    if(nombreApellido.contains(query.substring(posicion+1)))
                    {
                        encontrados.add(x);
                    }
                }
            }
            else if(posicion == (query.length()-1))
            {
                for(Client x:clientes)
                {
                    String nombreApellido = x.getFirstName()+" "+x.getLastName();
                    if(nombreApellido.contains(query.substring(0, posicion)))
                    {
                        encontrados.add(x);
                    }
                }
            }
            else
            {
                for(Client x:clientes)
                {
                    String nombreApellido = x.getFirstName()+" "+x.getLastName();
                    if(nombreApellido.contains(query.substring(0, posicion)) && nombreApellido.contains(query.substring(posicion+1)))
                    {
                        encontrados.add(x);
                    }
                }
            }
        }
        return encontrados;
    }
    
    public Client crearCliente(Client cliente) throws Exception{
        String nombre = cliente.getFirstName();
        String apellido = cliente.getLastName();
        String nit = cliente.getNit();
        
        nombre = nombre.substring(0,1).toUpperCase() + nombre.substring(1).toLowerCase();
        cliente.setFirstName(nombre);
        
        apellido = apellido.substring(0, 1).toUpperCase() + apellido.substring(1).toLowerCase();
        cliente.setLastName(apellido);
        
        if(nit.length()<=10 && nit.matches("[0-9]+"))
        {
            return this.repoclient.save(cliente);
        } else{
            throw new Exception("NIT invalido");
        }                
    }
    
    public Client editarCliente(Client cliente){
        return this.repoclient.save(cliente);
    }
    
    public Client editarClienteNit(Long id, String nit) throws Exception{
        Client cliente = this.repoclient.findById(id).get();
        if(nit.length()<=10 && nit.matches("[0-9]+"))
        {
        cliente.setNit(nit);
        return this.repoclient.save(cliente);
        } else{
            throw new Exception("NIT invalido");
        }        
    }
    
    
    public Client editarClienteNombres(Long id, String nombre, String apellido){
        Client cliente = this.repoclient.findById(id).get();
        nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
        cliente.setFirstName(nombre);
        
        apellido = apellido.substring(0, 1).toUpperCase() + apellido.substring(1).toLowerCase();
        cliente.setLastName(apellido);
        return this.repoclient.save(cliente);
    }
    
    
    
    
}
