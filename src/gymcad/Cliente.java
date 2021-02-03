/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymcad;

/**
 *
 * @author Jonathan González Fraga
 */
public class Cliente {
    
    Integer clienteId;
    String nombreCliente;
    String apellido1;
    String apellido2;
    String dni;
    String emailCliente;
    Gimnasio gimnasio;
    
    
    /**
     * Constructor vacio de la clase
     */
    public Cliente() {
    }

    /**
     * Constructor completo parametrizado
     * @param clienteId Almacena el identificador del cliente
     * @param nombre Almacena el nombreCliente del cliente
     * @param apellido1 Almacena el primer apellido del cliente
     * @param apellido2 Almacena el segundo apellido del cliente
     * @param dni Almacena el DNI del cliente
     * @param gimnasio  Almacena el Objeto de la clase Gimansio (Gimnasio que acude el cliente)
     */
    public Cliente(Integer clienteId, String nombre, String apellido1, String apellido2, String dni,String email, Gimnasio gimnasio) {
        this.clienteId = clienteId;
        this.nombreCliente = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.emailCliente = email;
        this.dni = dni;
        this.gimnasio = gimnasio;
    }

    /**
     * Este método permite retornar el identificador del cliente
     * @return retorna el clienteId
     */
    public Integer getClienteId() {
        return clienteId;
    }

    /**
     * Este método permite cambiar el identificador del cliente almacenado
     * @param clienteId indica el identificador que sera el remplazado
     */
    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * Este método permite retornar el nombreCliente del cliente almacenado
     * @return retorna el nombreCliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * Este método permite cambiar el nombreCliente del cliente almacenado
     * @param nombreCliente indica el nombreCliente que sera el remplazado
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * Este método permite retornar el primer apellido del cliente almacenado
     * @return retorna el primer apellido
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * Este método permite modificar el primer apellido del cliente almacenado
     * @param apellido1 indica el primer apellido que sera el remplazado
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

     /**
     * Este método permite retornar el segundo apellido del cliente almacenado
     * @return retorna el segundo apellido
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * Este método permite modificar el segundo apellido del cliente almacenado
     * @param apellido2 indica el segundo apellido que sera el remplazado
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }
    

    /**
     * Este método permite retornar el DNI del cliente almacenado
     * @return retorna el DNI apellido
     */
    public String getDni() {
        return dni;
    }

    /**
     * Este método permite modificar el DNI del cliente almacenado
     * @param dni indica el DNI que sera el remplazado
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    public Gimnasio getGimnasio() {
        return gimnasio;
    }

    public void setGimnasio(Gimnasio gimnasio) {
        this.gimnasio = gimnasio;
    }

    /**
     * Este método permite mostrar todos los atributos de la clase Cliente en versión cadena de texto.
     * @return retorna los atributos de la clase
     */
    @Override
    public String toString() {
        return "Cliente{" + "clienteId=" + clienteId + ", nombre=" + this.nombreCliente + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", email=" + emailCliente + ", dni=" + dni + ", gimnasio=" + gimnasio + '}';
    }
     
    
}
