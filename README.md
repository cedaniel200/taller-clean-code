# Clean Code

Este taller busca ser una breve introducción al código limpio (Clean Code).
Cualquier sugerencia me pueden escribir a cdanielmg200@gmail.com.
Si quieres profundizar aun más puedes visita [cedaniel200.blogspot.com.co](https://cedaniel200.blogspot.com/)

## ¿Qué es clean code?
   
   Es una filosofía (Escuela de Pensamiento) que promueve el código con sentido, concreto, dotado de características 
   como la simplicidad, eficiencia, legibilidad, sencillez, debe estar testeado, tener dependencias mínimas y hacer una 
   cosa bien.

## Origen de Clean code

   Robert C. Martin (Uncle bob), escribe un libro en el 2008 titulado Código Limpio: Manual de estilo para el desarrollo 
   ágil de software, donde expone conceptos, técnicas y enseñanzas de mentores del código limpio. Donde afirma también 
   que dicho libro es una precuela del libro Desarrollo de Software Ágil: Principios, Patrones y Prácticas (PPP) 
   publicado en 2002.

## ¿Para qué sirve clean code?
   * Tomar conciencia
   * Ser honestos 
   * Ser más profesional 
   * Dar lo mejor de sí mismos 
   * Adoptar la Refactorización
   
## ¿Qué beneficios nos trae Aplicar Clean Code?
   * Código con sentido 
   * Código sencillo 
   * Código Simple 
   * Código legible 
   * Código más mantenible 
   * Código eficiente 
   * Más Disciplinado
   
   Estoy seguro que hay más ...
   
## Nombres con Sentido
   
   * Usar nombre que revelen las intenciones 
   * Evitar la desinformación 
   * Realizar distinciones con sentido 
   * Usar nombres que se puedan pronunciar 
   * Usar Nombres que se puedan buscar 
   * Evitar codificaciones 
   * Notación húngara (Prefijo de Tipos) 
   * Prefijo de miembros (m_)
   * Evitar asignaciones mentales 
   * Nombres de clases 
   * Nombre de métodos 
   * No se exceda con el atractivo 
   * Un nombre por concepto 
   * No haga juego de palabras 
   * Usar nombres de dominio de soluciones 
   * Usar nombre de dominios de problema 
   * No añadir contextos innecesarios
   
## Funciones
 
   * Debe hacer una cosa 
   * Deben contar una historia, una debe llevar a la siguiente 
   * Responde a algo o hace algo pero no las dos cosas 
   * Debe ser pequeña o de tamaño reducido (Aprox. 20 líneas) 
   * Bloques(Código entre if for etc)-Sangrado(No mayor a dos) 
   * Un nivel de abstracción por función 
   * EL procesamiento de errores es una cosa 
   * Instrucciones Switch (Una vez - Crear Objetos Polimórficos - Oculta tras la herencia) 
   * Cumplir Regla descendente 
   * Debe hacer una cosa 
   * Deben contar una historia, una debe llevar a la siguiente 
   * Responde a algo o hace algo pero no las dos cosas 
   * Debe ser pequeña o de tamaño reducido (Aprox. 20 líneas) 
   * Bloques(Código entre if for etc)-Sangrado(No mayor a dos) 
   * Un nivel de abstracción por función 
   * EL procesamiento de errores es una cosa 
   * Instrucciones Switch (Una vez - Crear Objetos Polimórficos - Oculta tras la herencia) 
   * Cumplir Regla descendente 
   
## Funciones - Argumentos

   * Lo ideal es que tengan cero argumentos 
   * En su defecto 1 argumento(Monádico) o 2 argumentos(Diádico) 
   * 3 argumentos (Triádico)  se deben evitar a toda costa 
   * Más de 3 argumentos (Poliádico) debe tener un motivo especial 
   * Desaconseja utilizar argumentos de indicador (Pasar un booleano a la función) 
   * El orden de los argumentos deben tener un orden natural 
   * Utilizar objetos como argumentos 
   * Evitar argumentos de salida
   
 ## Comentarios
 
   No se debería tener la necesidad de escribir algún comentario a excepción de unos pocos casos, si se sigue y aplica lo 
   visto en las láminas anteriores. 
   
    Debemos tomar el tiempo necesario para escribir un comentario
   
   * Hacerse de manera consciente 
   * Aportar Informació
   
## Casos en que debemos escribir un comentario
   
   * Comentarios legales 
   * Comentarios informativos 
   * Explicar la intención 
   * Clarificación 
   * Advertir las consecuencias 
   * Comentarios TODO (Referencias tareas que el programador piensa que se debe hacer pero que no realizó) 
   * Amplificación 
   * Javadoc en API públicas
   
    No comente el código incorrecto, reescríbal
        Brian W. Kernighan y P.J. Plaugher

## Procesar Errores

   El control de errores es importante, pero si oscurece la lògica, es incorrecto. 
   * Usar excepciones en lugar de código devuelto. 
   * Crear primero la instrucción try-catch-finally 
   * Ofrecer contexto junto a las excepciones 
   * Definir el flujo normal 
   * No devolver nulos 
   * No pasar null (A menos que trabaje con una API)
   
## Límites
   
   Hace referencia a donde empieza y en donde termina nuestro código y el de terceras parte que utilicemos. 
   * Explorar y aprender límites (Pruebas de aprendizaje - experimentos controlados - centrado en que queremos obtener de la API) 
   * Usar código que todavía no existe (Definir nuestra propia interfaz) 
   * Límites limpios
   
## Límites Limpios
   
   * Proteger nuestra inversión asegurando que cambios futuros no sean demasiados costosos. 
   * Separación evidente. 
   * Evitar que el código conozca detalles de terceros (Puntos mínimos de referencia) 
        * Envolverlos 
        *   Usar un adaptador (Convertir la interfaz perfecta en la interfaz proporcionada)
        
## Lectura Recomendada
   
   [Código Limpio: Manual de estilo para el desarrollo ágil de software](https://www.amazon.es/Código-Limpio-desarrollo-software-Programación/dp/8441532109/ref=sr_1_1?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=codigo+limpio&qid=1554923237&s=gateway&sr=8-1)