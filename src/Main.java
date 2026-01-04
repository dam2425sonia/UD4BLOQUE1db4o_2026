import com.db4o.*;
import com.db4o.ext.Db4oException;
import com.db4o.query.Query;
import java.io.File;

public class Main {

  public static void main(String[] args) {

    /*Este código lo ponemos por si la base de datos ya existiera y quisiéramos empezar desde el principio.*/
    File fichero = new File("BDJefeHijo");
    fichero.delete(); // borrar BD anterior

    ObjectContainer baseDatos = null;

    try {
      /*Conexión a la base de objetos y apertura de la base de objetos*/
      baseDatos = Db4oEmbedded.openFile("BDJefeHijo");

      //Añadir jefes y sus hijos
      baseDatos.store(new Jefe("Ángel", 5, 53,new Hijo("Gustavo", 7)));
      baseDatos.store(new Jefe("Nieves", 3, 45,new Hijo("Iván", 3)));
      baseDatos.store(new Jefe("Jesús", 3, 5,new Hijo("Noelia", 3)));
      baseDatos.store(new Jefe("Dolores", 5,63,new Hijo("Sergio", 7)));
      baseDatos.store(new Jefe("Vicki", 3, 5,null));
      baseDatos.store(new Jefe("Fátima", 5,63,new Hijo("Lidia", 27)));
      baseDatos.store(new Jefe("Juan Luís", 3, 5,null));
      baseDatos.store(new Jefe("Elena", 1,42,new Hijo("David", 19)));
      baseDatos.store(new Jefe("Miguel", 20,45,new Hijo("Paula", 3)));
      baseDatos.store(new Jefe("Jesús", 19, 44,new Hijo("Rubén", 12)));
      
      //Consulta 1: jefe mayor de 55 años
      System.out.println("Jefes con más de 55 años:");
      Query q = baseDatos.query();
      q.constrain(Jefe.class);
      q.descend("edad").constrain(55).greater();
      ObjectSet<Jefe> res = q.execute();
      for (Jefe jefe : res) {
          System.out.println(jefe);
      }

      //Consulta 2: Incrementar edad de Miguel en 1
      Query q2 = baseDatos.query();
      q2.constrain(Jefe.class);
      q2.descend("nombre").constrain("Miguel");
      ObjectSet<Jefe> res2 = q2.execute();
      if (res2.hasNext()) {
          Jefe miguel = res2.next();
          miguel.setEdad(miguel.getEdad() + 1);
          baseDatos.store(miguel);
          System.out.println("Edad de Miguel incrementada a: " + miguel.getEdad());
      }

      // Consulta 3: Borrar jefes con antigüedad > 6 mostrando nombre
      Query q3 = baseDatos.query();
      q3.constrain(Jefe.class);
      q3.descend("antiguedad").constrain(6).greater();
      ObjectSet<Jefe> res3 = q3.execute();
      for (Jefe jefe : res3) {
          System.out.println("Borrando: " + jefe.getNombre());
          baseDatos.delete(jefe);
      }

      // Consulta 4: Mostrar todos los jefes restantes con sus hijos
      System.out.println("\nJefes restantes:");
      ObjectSet<Jefe> res4 = baseDatos.query(Jefe.class);
      for (Jefe jefe : res4) {
          System.out.println(jefe);
      }

    } catch (Db4oException e) {
        System.err.println("Error en la base de datos DB4O: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Ocurrió un error inesperado: " + e.getMessage());
    } finally {
        if (baseDatos != null) {
            baseDatos.close(); // siempre cerrar la base de datos
        }
    }

  }
}
