# Clean Code

![Java](https://img.shields.io/badge/Java-21-blue?style=flat-square&logo=openjdk&logoColor=white)
![Apache POI](https://img.shields.io/badge/Apache%20POI-6.0.0-D22128?style=flat-square&logo=apache&logoColor=white)
![JUnit 5](https://img.shields.io/badge/JUnit-5.14.4-25A162?style=flat-square&logo=junit5&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-9.6.1-02303A?style=flat-square&logo=gradle&logoColor=white)
[![License: Apache 2.0](https://img.shields.io/badge/License-Apache_2.0-blue?style=flat-square)](LICENSE)

Taller breve de introducción al **código limpio**: una guía de conceptos y un ejercicio real para
aplicarlos. Cualquier sugerencia, a **cdanielmg200@gmail.com**. Para profundizar:
[cedaniel200.blogspot.com.co](https://cedaniel200.blogspot.com/).

## El taller

El ejercicio es `GetExcel`: lee un archivo de Excel con Apache POI y devuelve sus filas como una lista
de mapas. Funciona, pero está escrito sin cuidado: nombres pobres, métodos largos, estado estático,
`switch` sobre tipos y un `catch` que se traga los errores. La tarea es refactorizarlo sin romper las
pruebas.

| Rama | Contenido |
|---|---|
| `master` | El código del ejercicio y sus tres pruebas |
| `solucion` | La versión refactorizada, con el mismo comportamiento |

```text
src/main/java/com/cedaniel200/cleancode/GetExcel.java    # el código a refactorizar
src/test/java/com/cedaniel200/cleancode/GetExcelTest.java
src/test/resources/Clientes.xlsx                         # datos de la hoja «Hoja1»
```

Requiere JDK 21; el wrapper descarga Gradle 9.6.1.

```bash
./gradlew test
```

## ¿Qué es Clean Code?

Es una filosofía (escuela de pensamiento) que promueve el código con sentido, concreto, dotado de
características como la simplicidad, la eficiencia, la legibilidad y la sencillez: debe estar testeado,
tener dependencias mínimas y hacer una cosa bien.

## Origen

Robert C. Martin (Uncle Bob) escribe en 2008 *Código Limpio: Manual de estilo para el desarrollo ágil
de software*, donde expone conceptos, técnicas y enseñanzas de mentores del código limpio. Afirma
también que el libro es una precuela de *Desarrollo de Software Ágil: Principios, Patrones y Prácticas*
(PPP), publicado en 2002.

## ¿Para qué sirve?

- Tomar conciencia
- Ser honestos
- Ser más profesionales
- Dar lo mejor de sí mismos
- Adoptar la refactorización

## ¿Qué beneficios trae aplicar Clean Code?

- Código con sentido
- Código sencillo
- Código simple
- Código legible
- Código más mantenible
- Código eficiente
- Más disciplina

Y seguro que hay más.

## Nombres con sentido

- Usar nombres que revelen las intenciones
- Evitar la desinformación
- Realizar distinciones con sentido
- Usar nombres que se puedan pronunciar
- Usar nombres que se puedan buscar
- Evitar codificaciones
- Evitar la notación húngara (prefijo de tipos)
- Evitar prefijos de miembros (`m_`)
- Evitar asignaciones mentales
- Cuidar los nombres de clases
- Cuidar los nombres de métodos
- No excederse con el ingenio
- Un nombre por concepto
- No hacer juegos de palabras
- Usar nombres del dominio de la solución
- Usar nombres del dominio del problema
- No añadir contextos innecesarios

## Funciones

- Debe hacer una cosa
- Deben contar una historia: una debe llevar a la siguiente
- Responde a algo o hace algo, pero no las dos cosas
- Debe ser pequeña o de tamaño reducido (unas 20 líneas)
- Bloques (código entre `if`, `for`, etc.) con sangrado no mayor a dos niveles
- Un nivel de abstracción por función
- El procesamiento de errores es una cosa
- Instrucciones `switch`: una vez, crear objetos polimórficos u ocultarlas tras la herencia
- Cumplir la regla descendente

## Funciones: argumentos

- Lo ideal es que tengan cero argumentos
- En su defecto, uno (monádico) o dos (diádico)
- Tres argumentos (triádico) se deben evitar a toda costa
- Más de tres (poliádico) debe tener un motivo especial
- Desaconseja los argumentos de indicador (pasar un booleano a la función)
- El orden de los argumentos debe tener un orden natural
- Utilizar objetos como argumentos
- Evitar argumentos de salida

## Comentarios

No se debería tener la necesidad de escribir comentarios, a excepción de unos pocos casos, si se sigue
y aplica lo visto en las secciones anteriores.

Debemos tomarnos el tiempo necesario para escribir un comentario:

- Hacerlo de manera consciente
- Aportar información

## Cuándo escribir un comentario

- Comentarios legales
- Comentarios informativos
- Explicar la intención
- Clarificación
- Advertir las consecuencias
- Comentarios `TODO` (referencias a tareas que el programador piensa que se deben hacer pero no realizó)
- Amplificación
- Javadoc en API públicas

> No comente el código incorrecto, reescríbalo.
> — Brian W. Kernighan y P. J. Plauger

## Procesar errores

El control de errores es importante, pero si oscurece la lógica, es incorrecto.

- Usar excepciones en lugar de códigos de retorno
- Crear primero la instrucción `try-catch-finally`
- Ofrecer contexto junto a las excepciones
- Definir el flujo normal
- No devolver nulos
- No pasar `null` (a menos que se trabaje con una API)

## Límites

Hace referencia a dónde empieza y dónde termina nuestro código y el de terceros que utilicemos.

- Explorar y aprender límites (pruebas de aprendizaje, experimentos controlados, centrados en qué
  queremos obtener de la API)
- Usar código que todavía no existe (definir nuestra propia interfaz)
- Límites limpios

## Límites limpios

- Proteger nuestra inversión asegurando que los cambios futuros no sean demasiado costosos
- Separación evidente
- Evitar que el código conozca detalles de terceros (puntos mínimos de referencia):
  - Envolverlos
  - Usar un adaptador (convertir la interfaz perfecta en la interfaz proporcionada)

## Lectura recomendada

[Código Limpio: Manual de estilo para el desarrollo ágil de software](https://www.amazon.es/Código-Limpio-desarrollo-software-Programación/dp/8441532109)
