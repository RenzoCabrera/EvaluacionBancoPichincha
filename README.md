# API REST INVENTORY

### Logica:
En este repositorio se encuentran 2 proyectos uno es el que contiene todos los servicios API REST el cual permite realizar el inventario de Bienes de una empresa, se encuentra desarrollado en SpringBoot con base de datos MySql. 
El otro Proyecto está desarrollado con Primefaces el cual se encarga de consumir los endPoints expuestos por el API REST
------------------------------------------------------------------------------------------------------------

## Herramientas Utilizadas
* Lenguaje: JAVA -Framework: Spring boot. para el server
* Lenguaje: JAVA -Framework: Primefaces/Spring boot. para el cliente
* Persistencia de datos: Base de datos MySql.
* Documentación de la API hecha en (Swagger-OpenAPI)
* Se incluye el modelo de datos.



Pasos para Levantar la aplicación:
1. Crear una carpeta vacia
2. Descargar del repositorio en la carpeta https://github.com/RenzoCabrera/EvaluacionBancoPichincha
3. Correr el script ubicado en la carpeta db en mysql para crear la base de datos con sus tablas y sus registros iniciales.
4. Se debe importar los proyectos  inventario y el proyecto demo al ide en este caso se usó IntelliJ IDEA
   
5. En el archivo application-dev.properties se puede cambiar los parámetros referentes a la conexión con la base de datos (usuario-contraseña)
6. Correr las clases principales de los proyectos inventario (InventarioAplication) http://localhost:8095/inventario/api/ y demo (DemoAplication) http://localhost:8096/pages/frmBienes.xhtml para que esten arriba y poder relizar las verificaciones del consumo de la API REST.
   
7. Para poder visualizar la documentacion de Swagger, se debe dirigir a la siguente ruta  http://localhost:8095/inventario/api/swagger-ui.html#/


