/*
 * ModeloCalculadora.java
 *
 * Created on 20 de febrero de 2007, 12:32
 *
 */

package com.pruebas.micalculadora;
/**
 *
 * @author Alan
 */
public class ModeloCalculadora {
    
  int x; // Acumulador.
  int y; // Primer operando o acumulado de multiplicaciones y divisiones.
  int z; // Nuevo operando.
  boolean nvoOpe = true; // Indica que despues de un "=", un digito se interpreta como nuevo operando.
  char oprBajPrio = '+'; // Empieza con '+'.
  char oprAltPrio = '*'; // Empieza con '*'.
                         // Los otros operadores binarios son '/', '-' y '&' para maximo comun divisor.
 
  /** Creates a new instance of ModeloCalculadora */
  public ModeloCalculadora() {
      limpiarTodo();
  }
  
    /**
     * 
     * @PRE Estado es valido.
     * @param d Representa el digito recien pulsado por el usuario.
     * @POS En z un entero con d como digito mas significativo y todos los 
     * digitos que previamente haya pulsado el usuario.
     * @return z como instancia de Long para que la Vista lo despliegue.
     */
  public Integer digito(int d) {
    if ( nvoOpe ) {
      z = d;
      nvoOpe = false;
      }
      else z = z * 10 + d;
    return new Integer(z);
  }

    /**
     *
     * @PRE El estado es valido.
     * @return x como Long para ser desplegado por la Vista. x es el primer operando de la proxima suma.
     * @POS ( x = x oprBajPrio (y {oprAltPrio z) ) Y ( oprBajPrio = + ) Y ( oprAltPrio = * )
     *      Asi acumula en x el primer operando de la proxima suma y deja pendiente una 
     *      suma hasta capturar el segundo operando.
     * @return x como Long para ser desplegado por la Vista.
     */
  public Integer sumar() {
    omega();
    oprBajPrio = '+';
    oprAltPrio = '*';
    return new Integer(x);
  }
  
    /**
     * 
     * @PRE El estado es valido.
     * @POS ( x = x oprBajPrio (y {oprAltPrio z) ) Y ( oprBajPrio = - ) Y ( oprAltPrio = * )
     *      Asi acumula en x el primer operando de la proxima resta y deja pendiente una 
     *      resta hasta capturar el segundo operando.
     * @return x como Long para ser desplegado por la Vista.
     */
    public Integer restar() {
    omega();
    oprBajPrio = '-';
    oprAltPrio = '*';
    return new Integer(x);
  }

    /**
     * 
     * @PRE El estado es valido.
     * @POS ( y = y oprAltPrio z ) Y ( oprAltPrio = * )
     *      Asi acumula cualquier operador de alta prioridad en y, y deja todo listo para la proxima multiplicacion.
     * @return y como Long para que sea desplegado por la Vista.
     */
  public Integer multiplicar() {
    beta();
    oprAltPrio = '*';
    return new Integer(y);
  }
  
    /**
     * 
     * @PRE El estado es valido.
     * @POS ( y = y oprAltPrio z ) Y ( oprAltPrio = / )
     *      Asi acumula cualquier operador de alta prioridad en y, y deja todo listo para la proxima division.
     * @return y como Long para que sea desplegado por la Vista.
     */
  public Integer dividir() {
    beta();
    oprAltPrio = '/';
    return new Integer(y);
  }

  /*
   Calculo del módulo
  */
    public Integer modulo() {
        beta();
        oprAltPrio = '%';
        return new Integer(y);
 }     
      
    public Integer exponencial() {
        beta();
        oprAltPrio = '^';
        return new Integer(y);
    }

    /**
     * 
     * @PRE El estado es valido.
     * @POS z = !z
     *      Asi z queda listo para servir como operando en cualquier operacion pendiente o subsiguiente.
     *      Al ser ! un operador unario, tiene prioridad sobre los de baja prioridad y los de alta prioridad.
     * @return z como Long para que sea desplegado por la Vista.
     */  
  public Integer factorial() {
    int f = 1;
    for(int i = 1; i <= z; i++) f = f * i;
    z = f;
    return new Integer(z);
  }

    /**
     * 
     * @PRE El estado es valido.
     * @POS ( y = y oprAltPrio z ) Y ( oprAltPrio = & )
     *      Asi acumula cualquier operador de alta prioridad en y, y deja todo listo para la proxima MCD.
     * @return y como Long para que sea desplegado por la Vista.
     */  
  public Integer mcd () {
    beta();
    oprAltPrio = '&';
    return new Integer(y);
  }

  public Integer sumatoria() {
      int f = 0;
      for (int i = 0; i <= z; i++)
          f = f+i;
      z=f;
      return new Integer(z);
  }
    public Integer mcm () {
        beta();
        oprAltPrio = '$';
        return new Integer(y);
    }

    /**
     *
     * @PRE El estado es valido.
     * @POS ( y = y oprAltPrio z ) Y ( oprAltPrio = r )
     *      Asi acumula cualquier operador de alta prioridad en y, y deja todo listo para la proxima Raiz.
     * @return y como Long para que sea desplegado por la Vista.
     */
    public Integer raiz () {
        beta();
        oprAltPrio = 'r';
        return new Integer(y);
    }

    /**
     * 
     * @PRE El estado es valido.
     * @POS ( z = x oprBajPrio ( y oprAltPrio z ) ) Y ( reinicia estado )
     *      Asi completa todas las operaciones pendiente y queda lista para recibir un nuevo operando u operador 
     *      en caso de que el usuario desee realizar una operacion con el resultado de las operaciones anteriores.
     * @return z como Long para que sea desplegado por la Vista.
     */
  public Integer cerrar() {
    omega();
    z = x;
    x = 0;
    oprBajPrio = '+';
    oprAltPrio = '*';
    nvoOpe = true; // Si despues del "=" el usuario da un digito, se va a longerpretar como nuevo operando.
    return new Integer(z);
 }
  
    /**
     * 
     * @PRE N/A.
     * @POS ( oprbajPrio = + ) Y ( oprAltPrio = * ) Y ( x = 0 ) Y ( y = 0 ) Y ( z = 0 ) y ( nvoOpe = true ).
     * @POS Asi establece el estado inicial valido y queda lista para cualquier accion del usuario.
     * @return z como Long para que sea desplegado por la Vista.
     */
 public Integer limpiarTodo () {
    oprBajPrio = '+';
    oprAltPrio = '*';
    x = 0; y = 1; z = 0;
    nvoOpe = true;
    return new Integer(z);
 }

 public Integer limpiarZ () {
    z = 0;
    return new Integer(z);
 }

 private void omega () {
  beta(); // Va de primero porque procesa el operador del grupo de alta prioridad pendiente.
  alfa(); // Va de segunda porque procesa el operador del grupo de baja prioridad pendiente.
 }

 private void alfa () {
  if ( oprBajPrio == '+' ) x = x + y;
  if ( oprBajPrio == '-' ) x = x - y;
  y = 1;
 }

 private void beta () {
  if ( oprAltPrio == '*' ) y = y * z;
  if ( oprAltPrio == '/' ) y = y / z;
  if ( oprAltPrio == 'r' ) y = (int)calculeRaiz(y,z);
  if ( oprAltPrio == '&' ) y = calculeMCD();
  if (oprAltPrio == '^') y =(int)Math.pow(y, z);
  if ( oprAltPrio == '&' ) y = calculeMCM();
     if ( oprAltPrio == '%' ) y = y % z;
  z = 0;
 }

  private int calculeMCD() {
    int menor = minYZ();
    int mayor = maxYZ();
    int resto = mayor % menor;
    while ( (!( resto == 0 )) && ( menor > 1) ) {
      if ( ( mayor - menor ) > menor )
        mayor = mayor - menor;
        else {
          int otro = mayor - menor; // Se basa en que el mcd(X,Y) = Y o a mcd(Y,X-Y).
          mayor = menor;
          menor = otro;
        };
    resto = mayor % menor;
    };
    return menor;
  }

    private int calculeMCM() {
        int menor = minYZ();
        int mayor = maxYZ();
        int a = minYZ();
        int b = maxYZ();
        int mcm;
        int resto = mayor % menor;
        while ( (!( resto == 0 )) && ( menor > 1) ) {
            if ( ( mayor - menor ) > menor )
                mayor = mayor - menor;
            else {
                int otro = mayor - menor; // Se basa en que el mcd(X,Y) = Y o a mcd(Y,X-Y).
                mayor = menor;
                menor = otro;
            };
            resto = mayor % menor;
        };
        mcm = ((a*b)/menor);
        return mcm;
    }

  private int minYZ(){
    if ( y < z )
      return y;
      else return z;
  }

  private int maxYZ(){
    if ( y > z )
      return y;
      else return z;
  }

  /*
  *  Retorna el resultado de la función logaritmo en un número.
  */
  public Integer logaritmo () {
      z = (int)Math.log(z);
      return new Integer(z);
  }

    public double calculeRaiz(double n, double x)
    {
        return calculeRaizRe(n, x, .0001);
    }

    public double calculeRaizRe(double n, double x, double p)
    {
        if(x < 0)
        {
            return -1;
        }
        if(x == 0) {
            return 0;
        }
        double x1 = x;
        double x2 = x / n;
        while (Math.abs(x1 - x2) > p)
        {
            x1 = x2;
            x2 = ((n - 1.0) * x2 + x / Math.pow(x2, n - 1.0)) / n;
        }
        return x2;
    }
}
