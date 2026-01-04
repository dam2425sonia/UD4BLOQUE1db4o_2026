public class Jefe {
    private String nombre;
    private int edad;
    private int antiguedad;
    private Hijo hijo; // referencia a objeto Hijo. Se supone que solo va a tener un hijo si fueran varios debería ser una lista

    // Constructor siguiendo el orden del ejercicio: nombre, antiguedad, edad, hijo
    public Jefe(String nombre, int antiguedad, int edad, Hijo hijo) {
        this.nombre = nombre;
        this.antiguedad = antiguedad;
        this.edad = edad;
        this.hijo = hijo;
    }

    // Getters y setters
    public Hijo getHijo() {
        return hijo;
    }

    public void setHijo(Hijo hijo) {
        this.hijo = hijo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    // toString controlando si no hay hijo
    @Override
    public String toString() {
        return "Nombre: " + nombre + 
               ", Edad: " + edad + " años" + 
               ", Antiguedad: " + antiguedad + 
               ", HIJO: " + (hijo != null ? hijo : "Ninguno");
    }
}
