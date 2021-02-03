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
public class Gimnasio {
    
    Integer gymId;
    String nombreGimnasio;
    String localidad;
    Integer codigoPostal;
    String direccionCalle;
    String emailGimnasio;
    Integer telefono;

    /**
     * Constructor vacio de la clase
     */
    public Gimnasio() {
    }

    /**
     * Constructor Completo Parametrizado
     * @param gymId Almacena el identificador del gimnasio
     * @param nombre Almacena el nombreGimnasio del gimnasio
     * @param localidad Almacena la localida del gimnasio
     * @param codigoPostar Almacena el código postal del gimnasio
     * @param direccionCalle Almacena la direccion donde su ubica el gimnasio
     * @param emailGym Almacena el emailGimnasio acorde al gimnasio
     * @param telefono Almacena el telefono principal del gimnasio
     */
    public Gimnasio(Integer gymId, String nombre, String localidad, Integer codigoPostar, String direccionCalle, String emailGym, Integer telefono) {
        this.gymId = gymId;
        this.nombreGimnasio = nombre;
        this.localidad = localidad;
        this.codigoPostal = codigoPostar;
        this.direccionCalle = direccionCalle;
        this.emailGimnasio = emailGym;
        this.telefono = telefono;
    }
    
    
    
        /**
     * Constructor Semi-Completo Parametrizado
     * @param nombre Almacena el nombreGimnasio del gimnasio
     * @param localidad Almacena la localida del gimnasio
     * @param codigoPostar Almacena el código postal del gimnasio
     * @param direccionCalle Almacena la direccion donde su ubica el gimnasio
     * @param emailGym Almacena el emailGimnasio acorde al gimnasio
     * @param telefono Almacena el telefono principal del gimnasio
     */
    public Gimnasio(String nombre, String localidad, Integer codigoPostar, String direccionCalle, String emailGym, Integer telefono) {
        this.nombreGimnasio = nombre;
        this.localidad = localidad;
        this.codigoPostal = codigoPostar;
        this.direccionCalle = direccionCalle;
        this.emailGimnasio = emailGym;
        this.telefono = telefono;
    }

    /**
     * Este método permite retornar el identificador del gimnasio
     * @return retorna el gymId
     */
    public Integer getGymId() {
        return gymId;
    }

    /**
     * Este método permite cambiar el identificador del gimansio almacenado
     * @param gymId indica el identificador que sera el remplazado
     */
    public void setGymId(Integer gymId) {
        this.gymId = gymId;
    }

    /**
     * Este método permite retornar el nombreGimnasio del gimnasio almacenado
     * @return retorna el nombreGimnasio
     */
    public String getNombreGimnasio() {
        return nombreGimnasio;
    }

    /**
     * Este método permite cambiar el nombreGimnasio del gimansio almacenado
     * @param nombreGimnasio indica el nombreGimnasio que sera el remplazado
     */
    public void setNombreGimnasio(String nombreGimnasio) {
        this.nombreGimnasio = nombreGimnasio;
    }

    /**
     * Este método permite retornar la localidad del gimnasio almenada
     * @return retorna la localidad
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Este método permite cambiar la localidad del gimansio almacenado
     * @param localidad indica la localidad que sera el remplazado
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * Este método permite retornar el codigo postal del gimnasio almenado
     * @return retorna el codigo postal
     */
    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Este método permite cambiar el codigo postal del gimansio almacenado
     * @param codigoPostal indica el codigo postal que sera el remplazado
     */
    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * Este método permite retornar la direcccion del gimnasio almenada
     * @return retorna la direccion
     */
    public String getDireccionCalle() {
        return direccionCalle;
    }

    /**
     * Este método permite cambiar la direcccion del gimansio almacenado
     * @param direccionCalle indica la direcccion que sera el remplazado
     */
    public void setDireccionCalle(String direccionCalle) {
        this.direccionCalle = direccionCalle;
    }

    /**
     * Este método permite retornar el emailGimnasio del gimnasio almenado
     * @return retorna el emailGimnasio
     */
    public String getEmailGimnasio() {
        return emailGimnasio;
    }

    /**
     * Este método permite cambiar el emailGimnasio del gimansio almacenado
     * @param emailGimnasio indica el emailGimnasio que sera el remplazado
     */
    public void setEmailGimnasio(String emailGimnasio) {
        this.emailGimnasio = emailGimnasio;
    }

    /**
     * Este método permite retornar el telefono principal del gimnasio almenado
     * @return retorna la localizacion
     */
    public Integer getTelefono() {
        return telefono;
    }

    /**
     * Este método permite cambiar el telefono del gimansio almacenado
     * @param telefono indica el telefono que sera el remplazado
     */
    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }
    
    
    /**
     * Este método permite mostrar todos los atributos de la clase Gimnasio en versión cadena de texto.
     * @return retorna los atributos de la clase
     */
    @Override
    public String toString() {
        return "Gimnasio{" + "gymId=" + gymId + ", nombre=" + nombreGimnasio + ", localidad=" + localidad + ", codigoPostar=" + codigoPostal + ", direccionCalle=" + direccionCalle + ", emailGym=" + emailGimnasio + ", telefono=" + telefono + '}';
    }  
    
}
