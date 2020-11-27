# Trabajo_PSP_1T_Ej2_AlvaroMi
Segundo ejercicio del trabajo del primer trimestre de PSP

Para que funcione el código correctamente es necesario tener una BBDD con las siguientes características:

Database: BBDD_PSP_1
Table: EMPLEADOS
DB_USER: DAM2020_PSP
DB_PASSWORD: DAM2020_PSP
- ID: PK. Integer. Autoincremental
- EMAIL: varchar(100)
- INGRESOS: Integer

El funcionamiento del código es el siguiente: 
  -Lee todas los registros de la BBDD secuencialmente y muestra el tiempo que tarda en hacerlo.
  -Lee otra vez todos los registros pero esta vez con 5 hilos y muestra el tiempo que tarda.
  
 No se por qué pero el tiempo que tarda a través de los hilos me lo hace mal y no se por qué, ya que me muestra muy pocos milisegundos :(
