# Vacunacion-TP
Trabajo práctico integrador - Programación II - UNGS
--
Programación II - Trabajo Práctico Integrador 
1er. Cuatrimestre 2021 
PRIMERA PARTE
--
Fecha de presentación: 6 de mayo
Fecha de entrega: 14 de mayo
--
Este Trabajo Práctico consta de dos etapas. La primera requerirá la entrega del análisis del 
problema y el diseño de la solución propuesta, o sea la especificación de los TADs necesarios, 
diagrama de clases y la interfaz de la solución. En la segunda etapa se deberá entregar la 
implementación, cuyas condiciones de entrega se darán posteriormente en un segundo 
enunciado. El diseño se hará utilizando los conceptos de programación orientada a objetos, 
que incluyen herencia y polimorfismo.
Requerimientos técnicos para la primera parte
Se puede trabajar en grupos de 2 personas (recomendado) o individualmente. En ambos 
casos se debe enviar, para la siguiente clase, si trabajarán en grupo o individualmente y el o 
los nombres de cada grupo. 
El diseño de la solución propuesta debe utilizar, al menos una vez, Herencia de manera 
adecuada.
Detalles del problema
A partir de 2021 la ungs comenzó a prestar servicios de centro de vacunación.
A tales fines las autoridades nos pidieron ayuda para gestionar la administración de 
vacunas.
A la ungs van llegando vacunas(1): pfizer, Sputnik, Sinopharm, Moderna y Astrazeneca.
Las vacunas Pfizer y Sputnik son para mayores de 60 y Sinopharm, Moderna y Astrazeneca 
para todo público. Pfizer y Moderna se almacenan a -18 grados centígrados mientras que 
las otras a 3 grados.
Se inscribe a la población mayor de 18 años que soliciten vacunarse. Los datos a registrar 
son el dni, la edad, si trabaja en salud y si tiene enfermedades preexistentes que lo 
convierta en persona de riesgo (tiene prioridad de vacunación).
La asignación de turnos se realiza con la siguiente prioridad:
1. Los trabajadores de la salud.
2. Mayores de 60 años,
3. Personas con enfermedades preexistentes.
4. El resto de la población.
El sistema debe poder generar turnos para vacunación teniendo en cuenta las vacunas 
disponibles y la prioridad definida anteriormente.
A partir de una fecha se generan los turnos de manera consecutiva según la capacidad de 
vacunación diaria de la universidad, hasta que se acaben las vacunas disponibles o la 
población inscripta que aún no fue vacunada. Cada turno debe indicar la persona y la 
vacuna que le será aplicada.
Se debe generar un listado por fecha con las personas que tengan turno para esa fecha.
También, se espera poder generar un reporte que informe las vacunas aplicadas y a que 
persona se aplicó cada una. 
Por último, es necesario conocer las personas que se encuentran en lista de espera.
(1) Toda esta información no es real, es solo a fines didácticos del TP. Por simplicidad 
asumimos que solo se aplica 1 dosis


# SEGUNDA PARTE

Fecha de presentación: 24 de mayo
Fecha de entrega: Viernes 11 de junio
--
Este Trabajo Práctico consta de dos etapas. La primera requerirá la entrega del análisis del
problema y el diseño de la solución propuesta, o sea la especificación de los TADs
necesarios, diagrama de clases y la interfaz de la solución. En la segunda etapa se deberá
entregar la implementación, cuyas condiciones de entrega se darán posteriormente en un
segundo enunciado. El diseño se hará utilizando los conceptos de programación orientada a
objetos, que incluyen herencia y polimorfismo.
--
Requerimientos técnicos para la 2da parte
Se deben realizar las correcciones del diseño de la 1er parte antes de comenzar la 2da.
Se debe hacer el diseño de la estructura de datos y el invariante de representación(IREP)
que soporte el diseño.
-El IREP tiene que considerar cosas como la consistencia interna, por ejemplo, cómo se
relaciona el stock de una vacuna con la gente vacunada y las vacunas que llegan.
Por último se debe hacer la implementación, la cual debe cumplir satisfactoriamente el junit
otorgado por la cátedra.
Se deben utilizar al menos 2 tecnologías java: (StringBuilder, For range, Iteradores),
además de junit en si, en alguna parte del tp.
Se debe implementar(sobreescribir) el toString de las clases principales.Como mínimo se
debe mostrar el nombre del centro, la capacidad de vacunación diaria, la cantidad de
vacunas disponibles, cantidad de personas en lista de espera, cantidad de turnos asignados
y cantidad de vacunas aplicadas hasta el momento.
Se debe implementar(sobreescribir) el equals de vacuna y sus clases derivadas.
Aclaraciones y agregados del problema
Las personas con turno asignado se deben presentar el día del turno para vacunarse, de
otra manera “se pierde el turno”. Esto significa que la siguiente vez que se asignen turnos:
● Se debe devolver esa vacuna al stock
● Se debe eliminar a la persona del sistema, de manera que tiene que volver a darse
de alta si quiere una vacuna.
Se debe poder conocer la cantidad de vacunas disponibles para cada vacuna.
Las vacunas que se almacenan a -18 grados() tienen una fecha de vencimiento que se
cuenta a partir de la fecha en que son ingresadas al centro de vacunación:
● Pfizer dura 30 días a partir de su ingreso
● Moderna dura 60 días a partir de su ingreso.
Como hay vacunas con vencimiento, se solicita poder consultar la cantidad de vacunas
vencidas agrupadas por tipo en O(1).
Les damos una clase “Fecha” de la cátedra que debe ser utilizada para modelar las fechas
del TP
