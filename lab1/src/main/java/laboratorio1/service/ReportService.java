package laboratorio1.service;

import laboratorio1.dao.ClientRepository;
import laboratorio1.model.Client;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    @Autowired
    private ClientRepository repoclient;
    private static String username= System.getProperty("user.name");
    
    public String generarReporteClientes(){
        

        String direccion = "//home//" + username + "//Desktop//var";
        File carpeta = new File(direccion);
        try{
            if (!carpeta.exists()) {
                carpeta.mkdir();
            }
        }catch(Exception e){
            
        }
        direccion = "//home//" + username + "//Desktop//var//www";
        carpeta = new File(direccion);
        try{
            if (!carpeta.exists()) {
                carpeta.mkdir();
            }
        }catch(Exception e){
            
        }
        
        carpeta = new File(direccion);
        int cantidad = carpeta.list().length;
        String archivo = direccion + "//Clientes_"+(cantidad+1)+".html";
        
        try{
            carpeta = new File(archivo);
            if(!carpeta.exists()){
                carpeta.createNewFile();
            }
            
            FileWriter fw = new FileWriter(carpeta);
            BufferedWriter bw = new BufferedWriter(fw);
            
            List<Client> clientes= (List<Client>) this.repoclient.findAll();
            bw.write("<html>");
            bw.newLine();
            bw.write("<body><h1>REPORTE DE CLIENTES</h1><HR/><br><br>");
            bw.write("First Name      Last Name       NIT       Phone       Address<br>");
            bw.newLine();
            
            for(Client e : clientes){
                bw.write(e.getFirstName());
                bw.write("    ");
                bw.write(e.getLastName());
                bw.write("    ");
                bw.write(e.getNit());
                bw.write("    ");
                bw.write(e.getPhone());
                bw.write("    ");
                bw.write(e.getAddress()+"<br>");
                bw.newLine();
            }
            bw.write("</body>");
            bw.newLine();
            bw.write("</html>");
            bw.close();
            return "el reporte "+archivo+" ha sido generado";
        }catch(Exception e){
            e.printStackTrace();
            return "No se pudo generar el reporte";
        }
        
        
    }            
    
}
