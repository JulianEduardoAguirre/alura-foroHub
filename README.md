# ONE - Challenge 03 - ForoHub
Implementación de un programa para la gestión de un 
foro, mediante la creación de una API usando Spring Boot.

## Tecnologías Utilizadas
- Java
- SpringBoot
- Maven
- MySQL 
- JWT


## Configuración
De momento, el programa se debe ejecutar en CLI o en una terminal dentro del IDE elegido por el usuario. El desarrollo del mismo se realizó con IntelliJ IDEA.

## Requisitos
- JDK 17
- MySQL Server
- IDE (IntelliJ IDEA recomendado)
- Insomnia o Postman (para pruebas)

## Instrucciones

1. Clonar o descargar el repositorio y abrirlo en IntelliJ IDEA, proceso en el cual, además, se instalarán las dependencias necesarias
2. Crear una nueva base de datos MySQL con el nombre 'forohub'
3. Crear las siguientes variables de entorno del sistema:
    - DB_HOST -> Dominio donde se aloja la base de datos
    - DB_MYSQL_NAME -> Nombre de la base de datos (forohub)
    - DB_MYSQL_USER -> Nombre de usuario
    - DB_MYSQL_PASSWORD -> Contraseña
    - JWT_SECRET -> Palabra secreta para la generación JWT

**De no querer usar variables de entorno, se pueden establecer los valores en el archivo 'aplication.properties' **

## Pruebas a la API
_Probar mediante Insomnia o Postman los siguientes endpoints_

---

### Autenticación en la API (login)


```http
  POST /login
```
Envía los datos de inicio de sesión (nombre y contrasenia). Devuelve el token de autorización.


* Se implementa JWT para la autenticación, con una duración de sesión de 120 minutos. 
* Por default, utilizar:
    - nombre: prueba
    - contrasenia: 123456
* En caso de inicio autenticación exitosa (codigo 200), copiar el token generado por la API para autorización del resto de endpoints.

__Payload__
```
{
	"nombre": "prueba",
	"contrasenia": "123456"
}
```
---
### Obtener todos los tópicos


```http
  GET /topicos
```
Consulta todos los registros de la entidad 'Topico' en base de datos.

__Payload__
```
No requerido
```

---
### Consultar un tópico por Id

```http
  GET /topicos/${id}
```
Consulta la base de datos en busca de un tópico específico.

__Payload__
```
No requerido
```
---
### Crear un tópico
```http
  POST /topicos
```
Crea un tópico nuevo en la base de datos, siempre que no exista uno previamente con el mismo título y mensaje.

__Payload__
```
{
	"titulo": "título_del_tópico",
	"mensaje": "mensaje_del_tópico",
	"autor": "autor_del_tópico",
	"curso": "curso_del_tópico"
}
```

---
### Actualizar un tópico

```http
  PUT /topicos/${id}
```
Actualiza un tópico existente en la base de datos. Sigue las mismas restricciones que en la creación de un tópico pero no permite la modificación del autor.

__Payload__
```
{
	"titulo": "nuevo_título",
	"mensaje": "nuevo_mensaje",
	"curso": "nuevo_curso"
}
```
---

### Eliminar un tópico
```http
  DELETE /topicos/${id}
```
(Cuidado) Elimina un tópico existente de la base de datos

__Payload__
```
No requerido
```

