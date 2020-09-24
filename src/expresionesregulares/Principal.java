package expresionesregulares;

import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author fnang
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("===BUSQUEDA UNA PALABRA===");
        busquedaUnaPalabra();
        
        System.out.println("\n===CLASE DE CARACTERES===");
        claseDeCaracteres();
        
        System.out.println("\n===CLASE DE CARACTERES PREDEFINIDAS===");
        clasesDeCaracteresPredefinidas();
        
        System.out.println("\n===CLASE DE CUANTIFICADORES===");
        cuantificadores();
        
        System.out.println("\n===CLASE DE LIMITES===");
        limites();
    }

    /**
     * La busqueda mas simple, donde agarramos solo una palabra
     * y buscamos si existe dentro de la cadena.
     */
    public static void busquedaUnaPalabra() {
        String cadena = "Esto es una cadena en una variable de Java";

        Pattern pat = Pattern.compile("una");
        Matcher mat = pat.matcher(cadena);

        //Si solo quiero saber que existe la palabra en la cadena
        //if(mat.find()) System.out.println("Se encontro la palabra en la cadena");
        
        //Si quiero mostrar la cantidad de veces que se repite
        while (mat.find()) {
            System.out.println(mat.group());
        }
    }

    /**
     * Clases de caracteres.
     * Se utiliza: ., [abc], [^abc], [a-c], |
     */
    public static void claseDeCaracteres() {
        //Patron m.a   Ejemplo: maa - mia - m a - m9a - etc.
        String patron = "m.a";
        Pattern p1 = Pattern.compile(patron);
        Matcher m1 = p1.matcher(Caperucita.cuento);
        while (m1.find()) {
            System.out.printf("Se encontró el patrón '%s' con la palabra: %s %n", patron, m1.group());
        }

        //Patron e[sc]o  Ejemplo: eso o eco
        patron = "e[sc]o";
        p1 = Pattern.compile(patron);
        m1 = p1.matcher(Caperucita.cuento);
        while (m1.find()) {
            System.out.printf("Se encontró el patrón '%s' con la palabra: %s %n",patron, m1.group());
        }

        //Patron a[a-z]e   Ejemplo: aae abe ace ade ... aze.
        patron = "a[a-z]e";
        p1 = Pattern.compile(patron);
        m1 = p1.matcher(Caperucita.cuento);
        while (m1.find()) {
            System.out.printf("Se encontró el patrón '%s' con la palabra: %s %n",patron, m1.group());
        }
        
        //Patron u[^scb]a  Ejemplo: uaa uoa uta. NO SE PERMITE: usa uca uba u a.
        patron = "u[^scb ]a";
        p1 = Pattern.compile("u[^scb ]a");
        m1 = p1.matcher(Caperucita.cuento);
        while (m1.find()) {
            System.out.printf("Se encontró el patrón '%s' con la palabra: %s %n", patron, m1.group());
        }
    }

    /**
     * CLASES DE CARACTERES PREDEFINIDAS.
     * Se utiliza: 
     * \\d \\D - digitos SINDIGITOS
     * \\w \\W - caracteres SINCARACTERES
     * \\s \\S - espacios SINESPACIOS
     */
    public static void clasesDeCaracteresPredefinidas() {
        //Patron \\d\\d Busca dos digitos - Ejemplo: 98 85 45 20 36
        String patron = "\\d\\d";
        Pattern p1 = Pattern.compile(patron);
        Matcher m1 = p1.matcher(Caperucita.cuento);
        if (m1.find()) {
            System.out.printf("Se encontró el patrón '%s' con la palabra: %s %n", patron, m1.group());
        }        
        
        //Patron \\war\\w busca 'ar' entre letras. Ejeplo: aarb larp mart narp
        patron = "\\wen\\w";
        p1 = Pattern.compile(patron);        
        m1 = p1.matcher(Caperucita.cuento);
        while (m1.find()) {
            System.out.printf("Se encontró el patrón '%s' con la palabra: %s %n", patron, m1.group());            
        }        

        //Patron \\sen\\s busca en con espacios. Ejemplo: ( en )
        patron = "\\sen\\s";
        p1 = Pattern.compile(patron);
        m1 = p1.matcher(Caperucita.cuento);
        int cont1 = 0;
        while (m1.find()) {
            System.out.printf("Se encontró el patrón '%s' con la palabra: %s %n", patron,  m1.group());
            cont1++;
        }
        System.out.printf("La expresion ' en ' aparece %d veces %n%n", cont1);

        //Patron \\Sen\\S busca 'en' sin espacios. Ejeplo: (en)
        patron = "\\Sen\\S";
        p1 = Pattern.compile(patron);
        int cont2 = 0;
        m1 = p1.matcher(Caperucita.cuento);
        while (m1.find()) {
            System.out.printf("Se encontró el patrón '%s' con la palabra: %s %n", patron, m1.group());
            cont2++;
        }
        System.out.printf("La expresion 'en' aparece %d veces%n", cont2);
        
        
    }

    /**
     * CUANTIFICADORES.
     * Se utiliza: *, +, ?, {n}, {m,n}, {m,}, (xx){n}
     */
    public static void cuantificadores() {
        //Patron ma.*  Busca ma y cero o todos los caracteres restante en la línea
        //Ejemplo: ma, mad, mads, masew, maslwq, malpwoq, masda we, mayarwaf
        String patron = "ma.*";
        Pattern p1 = Pattern.compile(patron); //ma.
        Matcher m1 = p1.matcher(Caperucita.cuento);
        while (m1.find()) {
            System.out.printf("Se encontró el patrón '%s' con la palabra: %s %n", patron, m1.group());
        }

        //Patron ma.{2} Coincide ma y dos caracteres siguientes.
        //Ejemplo: mads maol maei matr maw? ma_?
        patron = "ma.{2}";
        p1 = Pattern.compile(patron); 
        m1 = p1.matcher(Caperucita.cuento);
        while (m1.find()) {
            System.out.printf("Se encontró el patrón '%s' con la palabra: %s %n", patron, m1.group());
        }

        System.out.println("==============================");
        //Patron (lobo){3}  Busca la palabra lobo repetido solo 3 veces.
        //Ejemplo: lobolobolobo
        patron = "(lobo){3}";
        p1 = Pattern.compile(patron);
        m1 = p1.matcher(Caperucita.cuento);
        while (m1.find()) {
            System.out.printf("Se encontró el patrón '%s' con la palabra: %s %n", patron, m1.group());
        }
    }

    /**
     * COINCIDENCIAS CON LIMITES.
     * Se utiliza: ^, $, \b, \B.
     */
    public static void limites() {
        //^ coincide con el PRINCIPIO DE UNA LINEA.
        //El .* quiere decir Que encuentra Érase junto con cero o más veces el caracter . (punto) 
        //Todo entre Érase y roja.
        String patron = "^Érase.*roja";
        Pattern p1 = Pattern.compile(patron);
        Matcher m1 = p1.matcher(Caperucita.cuento);
        while (m1.find()) {
            System.out.printf("Se encontró el patrón '%s' con la palabra: %s %n", patron, m1.group());
        }
        
        //$ coincide con el FINAL DE UNA LINEA.        
        patron = ".*1812.$";
        p1 = Pattern.compile(patron);
        m1 = p1.matcher(Caperucita.cuento);
        while (m1.find()) {
            System.out.printf("Se encontró el patrón '%s' con la palabra: %s %n", patron, m1.group());
        }
        
        // \\b coincide con el inicio o final de una palabra.
        patron = "\\bmal.*";
        p1 = Pattern.compile(patron);
        m1 = p1.matcher(Caperucita.cuento);
        while (m1.find()) {
            System.out.printf("Se encontró el patrón '%s' con la palabra: %s %n", patron, m1.group());
        }
        
        // \\b coincide con el inicio o final de una palabra.
        patron = ".*ienes\\b";
        p1 = Pattern.compile(patron);
        m1 = p1.matcher(Caperucita.cuento);
        while (m1.find()) {
            System.out.printf("Se encontró el patrón '%s' con la palabra: %s %n", patron, m1.group());
        }
    }

}
