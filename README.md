# Sistema_Administracion_Doctor_Ramiro
El doctor Ramiro Fernandez nos contactó porque quiere adquirir un sistema para la administración de las historias médicas y el manejo de citas de sus pacientes.
Por ello nosotros tenemos la tarea de realizar un sistema:
● Que le permita al doctor establecer un horario de disponibilidad por día de la
semana. ej (lunes 8am a 5pm, martes 9am a 3 pm ... )
● Que le permita a el doctor agregar un paciente nuevo
● Que le permita al doctor modificar la información personal del paciente de ser
necesario
● Que le permita a los pacientes que están inscritos agendar citas pero sólo
en los horarios disponibles, es decir deben ir saliendo de disponibilidad los
horarios que ya han sido tomados.(Hay que definir también cuanto toma cada
cita como un estándar para saber qué franja horaria se va ocupando a
medida que se van agendando citas)
● Que le permita al doctor aceptar o cancelar las citas que se van
agendando.(Si el doctor cancela la cita el horario debe volver a pasar a estar
disponible)
● Que le permita al doctor listar todo el historial médico de un paciente con
todas las interacciones que han tenido.
● Por último que le permita eliminar pacientes.
Recuerden aplicar el principio más importante “KISS”!!
Recuerden planear primero a papel y lápiz antes de empezar a
generar la solución en código.
Definan:
● Cuales son las clases que deben crear
● Qué atributos van a tener esas clases
● Que endpoints debo habilitar para poder aceptar esas
peticiones
● Que estructuras de datos me van a permitir a mi almacenar la
información
● que metodos debo tener
● Visualizar el flujo
● Por último codificar.
-Aplicar polimorfismo con mínimo dos interfaces y dos clases abstractas
-Prohibido el uso de ciclos FOR, siempre que la intención sea recorrer un arreglo deben usar el
api Stream.
-Tener siempre presente los principios Solid
-Aplicar patrones de diseño, identificar al menos 2 en tu código al final a la hora de documentar.
-Añadir documentacion (Readme.md)
-Desarrollar el proyecto bajo una estructura adecuada y por capas (Controller, Service y repository),
pueden añadir más capas si consideran necesario.
-Se debe utilizar java y spring boot como framework para construir la api y como gestor de
dependencias.
-Mínimo dos controllers
-Se deben utilizar al menos una vez un arrayList, un set y un mapa.



**Local Installation**
http://localhost:8080

Peticiones HTTP - ESTABLECER HORARIO MEDICO
http://localhost:8080/establecerhorariomedico
POST:
{
  "id": 1,
  "medico": {
    "id": 1,
    "nombre": "Juan Pérez",
    "especialidad": "Pediatría"
  },
  "fecha": "2023-02-12",
  "horaInicio": "08:00",
  "horaFinal": "12:00"
}

Mostrara este mensaje:
Horario Registrado!



 Peticiones HTTP - Mostrar el  HORARIO MEDICO
 GET: 

 http://localhost:8080/consultarhorariomedico/{idMedico}
 Donde dice {idMedico} poner el Id del medico en este caso 1 

Mostrara el siguiente mensaje:

"fecha": "2023-02-12",
"horaInicio": "08:00",
"horaFinal": "12:00"



GET PARA MOSTRAR EL HISTORIAL MEDICO DEL PACIENTE:
http://localhost:8080/listarhistorialmedicopaciente/{pacienteId}

{
   "pacienteId": <valor_entero_del_ID_del_paciente>
}


PARA AGENDAR UNA CITA SE HACE UN POST EN LA SIGUIENTE RUTA:
localhost:8080/citas/agendarcitamedica

PUEDE HACERLO CON EL SIGUIENTE JSON:

{
   "fecha": "<fecha_en_formato_YYYY-MM-DD>",
   "hora": "<hora_en_formato_HH:MM:SS>",
   "medicoId": <valor_entero_del_ID_del_médico>,
   "pacienteId": <valor_entero_del_ID_del_paciente>
}

MOSTRARA EL SIGUIENTE MENSAJE:
Cita registrada!!


PARA ACTUALIZAR EL ESTADO DE UNA CITA SE PUEDE HACER LO SIGUIENTE: 
Entrar en la ruta: localhost:8080/citas/actualizarestadocita/estado/{estadoCita}/{idCita}

PUEDE HACERLO CON EL SIGUIENTE JSON:
{
   "estadoCita": "<nuevo_estado_de_la_cita>"
}


PARA REGISTRAR UN NUEVO PACIENTE SE HACE DE LA SIGUIENTE FORMA:
HACEMOS UN POST EN ESTA RUTA 
http://localhost:8080/api/personas/crear

PODEMOS HACERLO CON ESTE JSON:

{
    "nombre": "Juan",
    "apellido": "Perez",
    "dni": "12345678",
    "telefono": "987654321",
    "email": "juanperez@email.com",
    "fechaNacimiento": "1999-01-01"
}

MOSTRARA EL SIGUIENTE MENSAJE:
El paciente se ha registrado!











