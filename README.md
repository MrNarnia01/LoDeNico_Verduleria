#Verduleria Lo De Nico - Proyecto Final

## Descripcion

_Web para la organizacion y administracion de fruteria y verduleria Lo De Nico_

### Requisitos

* [Docker](https://docs.docker.com/engine/install/ubuntu/) -Plataforma para crear, empaquetar y distribuir aplicaciones en contenedores.
* [Java 21](https://www.oracle.com/ar/java/technologies/downloads/#java21) -Lenguaje de programación multiplataforma orientado a objetos.

### Levantar los container en **Docker**

_Teniendo ya los archivos del **GITHUB** descargados en el sistema:_

#### Backend:

* Entrar a la carpeta **BVerduleria** y ejecutar:

```
chmod +x mvnw
./mvnw clean package -DskipTests
docker-compose build java_app
```

_Esto crea la imagen para docker_

* Para crear y ejecutar esa imagen en un container hacer:

```
docker-compose up -d
```

#### Frontend:

* Entrar a la carpeta **FVerduleria** y ejecutar:

```
docker build -t vue-app .
docker run -d -p 5173:5173 vue-app
```

_Con la primera linea construimos la imagen y con la segunda la ejecutamos en un container_

## Herramientas usadas

* [PostgreSQL](https://www.postgresql.org/) -Sistema de gestión de bases de datos relacional orientado a objetos y de código abierto.
* [Vue.js](https://vuejs.org/) -Framework de JavaScript de código abierto.
* [Springboot](https://spring.io/projects/spring-boot) -Framework para crear aplicaciones Java de manera eficiente.

## Autor

_Trabajo hecho por el alumno **Petti Bruno**, numero de legajo: **6463**_

---

