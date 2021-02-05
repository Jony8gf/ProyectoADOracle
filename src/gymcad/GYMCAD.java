/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymcad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author Jonathan González Fraga
 */
public class GYMCAD {
    
    Connection conexion;

    public GYMCAD() throws ExcepcionGYM {
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
        } catch (ClassNotFoundException ex) {
            ExcepcionGYM e = new ExcepcionGYM();
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            
            throw e; 
        }    
    }

    private void conectar() throws ExcepcionGYM{
        
        try {
            conexion = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.69:1521:test", "GYM", "kk");
        }  catch (SQLException ex) {
            ExcepcionGYM e = new ExcepcionGYM();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
            
            
            throw e;
        }  
    }
    

 
    
    
    /**
     * Inserta un registro de la tabla Gimnasio
     * @param gimnasio Objeto que contiene todos los datos e insertar en la tabla Gimnasio
     * @return cantidad de registros insertados
     * @throws gymcad.ExcepcionGYM Cuando se produzca un error con la base de datos
     */
    public int insertarGimnasio(Gimnasio gimnasio) throws ExcepcionGYM {
        
        GYMCAD gym = new GYMCAD();
        conectar();
        
        String dml = "insert into GIMNASIO (GYM_ID, NOMBRE_GIMNASIO, LOCALIDAD, CODIGO_POSTAL, DIRECCION_CALLE, EMAIL_GIMNASIO, TELEFONO ) values (SEQUENCE_GYM_ID.nextval, ? , ? , ? , ? , ? , ? )" ; 
        int registrosAfectados;
         
        try {
            
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
        
            sentenciaPreparada.setString(1, gimnasio.getNombreGimnasio());
            sentenciaPreparada.setString(2, gimnasio.getLocalidad());
            sentenciaPreparada.setObject(3, gimnasio.getCodigoPostal(),Types.INTEGER);
            sentenciaPreparada.setString(4, gimnasio.getDireccionCalle());
            sentenciaPreparada.setString(5, gimnasio.getEmailGimnasio());
            sentenciaPreparada.setObject(6, gimnasio.getTelefono(),Types.INTEGER);
            registrosAfectados = sentenciaPreparada.executeUpdate();
          
            sentenciaPreparada.close();
            conexion.close();
            
            
            
        } catch (SQLException ex) {
            ExcepcionGYM e = new ExcepcionGYM();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setMensajeErrorUsuario("Error al insertar. Compruebe los datos introducidos.");
            e.setSentenciaSQL(dml);
            
            switch(ex.getErrorCode()){
                
                case 1400:e.setMensajeErrorUsuario("Comprueba que los campos Nombre, Localidad, Código postal, Direccion de Calle,"
                        + " Email y Telefono están debidamente rellenados.");
                    break;
                
                case 1: e.setMensajeErrorUsuario("El Email y Telefono ya existe por otro Gimnasio.");
                    break;
                    
                case 2290: e.setMensajeErrorUsuario("El Telefono debe empezar por 9, 6 o 7. Y Recuerda que en el Email se pon @");
                    break;
                    
                case 20005: e.setMensajeErrorUsuario("El correo introducido ya exite por un cliente.");
                    break;
                
                default: e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
            }
            
            throw e;
        }
        
        return registrosAfectados;
    }
    
    /**
    * Este método permite eliminar un registro de la tabla Gimnasio
    * @param gymId indica el valor del campo Gym_id
    * @return Cantidad de registros eliminados
    * @throws gymcad.ExcepcionGYM cuando se produzca cualquier error en la
    * conexión de la base de datos.
    */
    public int eliminarGimnasio(Integer gymId) throws ExcepcionGYM{
        
        GYMCAD gym = new GYMCAD();
        conectar();
        String dml = "delete from GIMNASIO where GYM_ID = " + gymId;
        int registrosAfectados;
        
        try {
            Statement sentencia = conexion.createStatement();

            registrosAfectados = sentencia.executeUpdate(dml);
            
            sentencia.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionGYM e = new ExcepcionGYM();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setMensajeErrorUsuario("Error al borrar. Compruebe los datos introducidos.");
            e.setSentenciaSQL(dml);
            
            switch(ex.getErrorCode()){
                
                case 2292: e.setMensajeErrorUsuario("No se puede borrar un gimnasio que tiene asignado clientes.");
                break;
                
                default: e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
            }
            
            throw e;
        }
        
        return registrosAfectados;
    }
    
    
    /**
     * Modifica un registro de la tabla Gimnasio
     * @param gimnasio Objeto que contien los datos nuevos a modificar en el registro, no se puede
     * modificar el campo gymId
     * @param gymId Identificador de Gimnasio a modificar
     * @return 0 . No devuelve la cantidad de registros modificados
     * @throws ExcepcionGYM 
     */ 
    public int modificarGimnasio(Gimnasio gimnasio, Integer gymId) throws ExcepcionGYM{
            
        GYMCAD gym = new GYMCAD();
        conectar();
        String llamada = "call modificar_gimnasio(?,?,?,?,?,?,?)";
        CallableStatement sentenciaLlamable;
        
        try {
            
            sentenciaLlamable = conexion.prepareCall(llamada);
            sentenciaLlamable.setObject(1, gymId,Types.INTEGER);
            sentenciaLlamable.setString(2, gimnasio.getNombreGimnasio());
            sentenciaLlamable.setString(3, gimnasio.getLocalidad());
            sentenciaLlamable.setObject(4, gimnasio.getCodigoPostal(),Types.INTEGER);
            sentenciaLlamable.setString(5, gimnasio.getDireccionCalle());
            sentenciaLlamable.setString(6, gimnasio.getEmailGimnasio());
            sentenciaLlamable.setObject(7, gimnasio.getTelefono(),Types.INTEGER);

            sentenciaLlamable.executeUpdate();


            sentenciaLlamable.close();
            conexion.close();
            
        
        
        } catch (SQLException ex) {
            ExcepcionGYM e = new ExcepcionGYM();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setSentenciaSQL(llamada);
            
            switch(ex.getErrorCode()){
                
                case 1: e.setMensajeErrorUsuario("El Email y Telefono ya existe y no se puede repetir.");
                    break;
                
                case 1407:e.setMensajeErrorUsuario("Comprueba que los campos Nombre, Localidad, Código postal, Direccion de Calle,"
                        + " Email y Telefono están debidamente rellenados.");
                    break;
                    
                case 20005: e.setMensajeErrorUsuario("El correo introducido ya exite por un cliente.");
                    break;
                    
                case 2290: e.setMensajeErrorUsuario("El Telefono debe empezar por 9, 6 o 7. Y Recuerda que en el Email se pon @");
                    break;
                
                default: e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
            }
            
            throw e;
        }
        
        return 0;
    }
    
    public Gimnasio mostrarGimnasio(Integer gymId) throws ExcepcionGYM{
        
            conectar();
            Gimnasio g = new Gimnasio();
            String dql = "select * from gimnasio g where g.gym_id = "+gymId;
            
            
        try {

            Statement sentencia = conexion.createStatement();
            ResultSet res = sentencia.executeQuery(dql);
            
            while (res.next()) {
                
                g.setGymId(res.getInt("gym_id"));
                g.setNombreGimnasio(res.getString("nombre_gimnasio"));  
                g.setLocalidad(res.getString("localidad"));
                g.setCodigoPostal(res.getInt("codigo_postal"));
                g.setDireccionCalle(res.getString("direccion_calle"));
                g.setEmailGimnasio(res.getString("email_gimnasio"));
                g.setTelefono(res.getInt("telefono"));
                
            }
            
            if(g.gymId != null){
                
                String toString = g.toString();
                System.out.println(toString);
            }else{
                System.out.println("El gimnasio no existe");
            }
            
            res.close();
            sentencia.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionGYM e = new ExcepcionGYM();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
            throw e;
        }
        
        return null;
    }
    
    
    public ArrayList<Gimnasio> mostrarGimnasios() throws ExcepcionGYM{
        
            conectar();
            ArrayList<Gimnasio> listaGimnasios = new  ArrayList();
            String dql = "select * from gimnasio";
            
        try {

            Statement sentencia = conexion.createStatement();
            ResultSet res = sentencia.executeQuery(dql);
            
            while (res.next()) {
                Gimnasio g = new Gimnasio();
                g.setGymId(res.getInt("gym_id"));
                g.setNombreGimnasio(res.getString("nombre_gimnasio"));
                g.setLocalidad(res.getString("localidad"));
                g.setCodigoPostal(res.getInt("codigo_postal"));
                g.setDireccionCalle(res.getString("direccion_calle"));
                g.setEmailGimnasio(res.getString("email_gimnasio"));
                g.setTelefono(res.getInt("telefono"));
                listaGimnasios.add(g);
            }
            
            String toString = listaGimnasios.toString();
            System.out.println(toString);
            
            res.close();
            sentencia.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionGYM e = new ExcepcionGYM();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
            throw e;
        }
        return listaGimnasios;
    }
    
    
    
    
    
    public int insertarCliente(Cliente cliente) throws ExcepcionGYM{
        GYMCAD gym = new GYMCAD();
        conectar();
        String llamada = "call insertar_cliente(?, ?, ?, ?, ?, ?)";
        CallableStatement sentenciaLlamable;
        
        
        try {
            
            sentenciaLlamable = conexion.prepareCall(llamada);
            sentenciaLlamable.setString(1, cliente.getNombreCliente());
            sentenciaLlamable.setString(2, cliente.getApellido1());
            sentenciaLlamable.setString(3, cliente.getApellido2());
            sentenciaLlamable.setString(4, cliente.getDni());
            sentenciaLlamable.setString(5, cliente.getEmailCliente());
            sentenciaLlamable.setObject(6, cliente.getGimnasio().getGymId(), Types.INTEGER);

            sentenciaLlamable.executeUpdate();


            sentenciaLlamable.close();
            conexion.close();
            
        
        
        } catch (SQLException ex) {
            ExcepcionGYM e = new ExcepcionGYM();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setSentenciaSQL(llamada);
            
            switch(ex.getErrorCode()){
                
                case 1400:e.setMensajeErrorUsuario("Comprueba que los campos Nombre, Apelllidos, Dni,  Email están debidamente rellenados.");
                    break;
                    
                case 2291 : e.setMensajeErrorUsuario("Comprueba que el identificador del gimnasio es correcto.");
                
                case 20006: e.setMensajeErrorUsuario("El correo introducido ya exite por un gimnasio.");
                    break;
                    
                case 2290: e.setMensajeErrorUsuario("Recuerda que en el Email se pone @");
                    break;
                
                default: e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
            }
            
            throw e;
        }
        return 0;
    }
    
    public int eliminarCliente(Integer clienteId) throws ExcepcionGYM{
        
        GYMCAD gym = new GYMCAD();
        conectar();
        String dml = "delete from CLIENTE where CLIENTE_ID = ? " ;
        int registrosAfectados = 0;
        
        try {
            
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setObject(1, clienteId, Types.INTEGER);
            registrosAfectados = sentenciaPreparada.executeUpdate();
            
            sentenciaPreparada.close();
            conexion.close();
            
        } catch (SQLException ex) {
            
            ExcepcionGYM e = new ExcepcionGYM();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setMensajeErrorUsuario("Error al borrar. Compruebe los datos introducidos.");
            e.setSentenciaSQL(dml);
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
            
            throw e;
        }      
        
        return registrosAfectados;       
    }
    
     public int modificarCliente(Cliente cliente, Integer clienteId) throws ExcepcionGYM{
         
        GYMCAD gym = new GYMCAD();
        conectar();
        
        String dml = "update cliente set nombre_cliente = ? , apellido1 = ? , apellido2 = ?, dni = ?, email_cliente = ?, gym_id = ? where cliente_id = "+clienteId ; 
        int registrosAfectados;
        
        
        try {
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setString(1, cliente.getNombreCliente());
            sentenciaPreparada.setString(2, cliente.getApellido1());
            sentenciaPreparada.setString(3, cliente.getApellido2());
            sentenciaPreparada.setString(4, cliente.getDni()); 
            sentenciaPreparada.setString(5, cliente.getEmailCliente());
            sentenciaPreparada.setObject(6, cliente.getGimnasio().getGymId(),Types.INTEGER);
            
            registrosAfectados = sentenciaPreparada.executeUpdate();
          
            sentenciaPreparada.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionGYM e = new ExcepcionGYM();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setMensajeErrorUsuario("Error al insertar. Compruebe los datos introducidos.");
            e.setSentenciaSQL(dml);
            
            switch(ex.getErrorCode()){
                
                case 1407:e.setMensajeErrorUsuario("Comprueba que los campos Nombre, Apellidos, Dni, y email están debidamente rellenados.");
                    break;
                case 2291:e.setMensajeErrorUsuario("El gimnasio introducido no existe.");
                    break;   
                case 2290: e.setMensajeErrorUsuario("Recuerda que en el Email se pone @");
                    break;
                case 20006: e.setMensajeErrorUsuario("El email introducido ya esta registra por otro usuario.");
                    break;
                
                default: e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
            }
            
            throw e;
        }
        
        return registrosAfectados;
    }
     
     
    public Cliente mostrarCliente(Integer clienteId) throws ExcepcionGYM{
        
            conectar();
            Cliente c = new Cliente();
            String dql = "select * from gimnasio g, cliente c where g.gym_id = c.gym_id and c.cliente_id = "+clienteId;
            
            
        try {

            Statement sentencia = conexion.createStatement();
            ResultSet res = sentencia.executeQuery(dql);
            
            while (res.next()) {
                Gimnasio g = new Gimnasio();
            
                c.setClienteId(res.getInt("cliente_id"));
                c.setNombreCliente(res.getString("nombre_cliente"));
                c.setApellido1(res.getString("apellido1"));
                c.setApellido2(res.getString("apellido2"));
                c.setDni(res.getString("dni"));
                c.setEmailCliente(res.getString("email_cliente"));
                
                
                g.setGymId(res.getInt("gym_id"));
                g.setNombreGimnasio(res.getString("nombre_gimnasio"));  
                g.setLocalidad(res.getString("localidad"));
                g.setCodigoPostal(res.getInt("codigo_postal"));
                g.setDireccionCalle(res.getString("direccion_calle"));
                g.setEmailGimnasio(res.getString("email_gimnasio"));
                g.setTelefono(res.getInt("telefono"));
                
                c.setGimnasio(g);
            }
            
            if(c.clienteId != null){
                
                String toString = c.toString();
                System.out.println(toString);
                
            }else{
                System.out.println("El cliente no existe");
            }
            
            res.close();
            sentencia.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionGYM e = new ExcepcionGYM();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
            throw e;
        }
        
        return null;
    }
    
    
    public ArrayList<Cliente> mostrarClientes() throws ExcepcionGYM{
        
        
            conectar();
            ArrayList<Cliente> listaClientes = new  ArrayList();
            String dql = "select * from gimnasio g, cliente c where g.gym_id = c.gym_id";
            Cliente c = new Cliente();
            Gimnasio g = new Gimnasio();
            
            
        try {

            Statement sentencia = conexion.createStatement();
            ResultSet res = sentencia.executeQuery(dql);
            
            while (res.next()) {
                
                c.setClienteId(res.getInt("cliente_id"));
                c.setNombreCliente(res.getString("nombre_cliente"));
                c.setApellido1(res.getString("apellido1"));
                c.setApellido2(res.getString("apellido2"));
                c.setDni(res.getString("dni"));
                c.setEmailCliente(res.getString("email_cliente"));
                
                
                g.setGymId(res.getInt("gym_id"));
                g.setNombreGimnasio(res.getString("nombre_gimnasio"));  
                g.setLocalidad(res.getString("localidad"));
                g.setCodigoPostal(res.getInt("codigo_postal"));
                g.setDireccionCalle(res.getString("direccion_calle"));
                g.setEmailGimnasio(res.getString("email_gimnasio"));
                g.setTelefono(res.getInt("telefono"));
                
                c.setGimnasio(g);
                listaClientes.add(c);
            }
            
            
            res.close();
            sentencia.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionGYM e = new ExcepcionGYM();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
            throw e;
        }
        
        return listaClientes;
    }
    
    
    
}
