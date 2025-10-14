# Arquitectura_Capas
Integración de las Capas y Comunicación entre ellas. Conceptos de conectores y API. 

🧩 Proyecto: Integración de Capas y Comunicación entre ellas con API REST
🔖 Descripción general

Este proyecto tiene como objetivo demostrar la integración de las capas de una arquitectura en software y su comunicación a través de una API REST, utilizando el framework Spring Boot.
A través de un ejemplo práctico, se desarrollará una pequeña aplicación de gestión (por ejemplo, Gestión de Estudiantes) donde cada capa cumple una función específica y se comunica con las demás de manera desacoplada.

🧠 Objetivo del proyecto

Aplicar el concepto de arquitectura en capas (presentación, negocio y datos).

Entender cómo se comunican las capas mediante conectores (interfaces, repositorios, servicios).

Exponer y consumir datos mediante una API REST.

Aprender a usar dependencias esenciales de Spring Boot, como Web, JPA y H2 Database.

⚙️ Tecnologías y dependencias utilizadas
Tecnología	Descripción	Función principal
Java 17+	Lenguaje de programación principal	Base del desarrollo
Spring Boot 3.x	Framework para construir aplicaciones rápidas	Núcleo del proyecto
Spring Web	Permite crear y exponer APIs REST	Capa de presentación
Spring Data JPA	Abstrae el acceso a la base de datos mediante repositorios	Capa de persistencia
H2 Database	Base de datos ligera en memoria	Permite pruebas rápidas sin instalación
Lombok	Simplifica el código eliminando getters/setters manuales	Mejora legibilidad
(Opcional) DevTools / Validation / Swagger	Herramientas complementarias	Mejora la experiencia de desarrollo
🏗️ Arquitectura del proyecto

La aplicación se basa en una arquitectura multicapa, donde cada parte cumple un rol definido y se comunica solo con la capa que le corresponde.

src/
 └── main/java/com/example/demo/
      ├── controller/    → Capa de Presentación (exposición de endpoints REST)
      ├── service/       → Capa de Negocio (lógica y reglas del dominio)
      ├── repository/    → Capa de Datos (interacción con la base de datos)
      ├── model/         → Capa de Modelo (entidades o clases de datos)
      └── DemoApplication.java

🧱 Capas explicadas

Capa de Presentación (Controller)

Expone los endpoints REST (@RestController).

Se comunica con la capa de servicio.

Envía y recibe datos en formato JSON.

Capa de Negocio (Service)

Contiene la lógica del negocio.

Define la interfaz de servicios (@Service) y llama a los repositorios.

Evita que el controlador acceda directamente a la base de datos.

Capa de Datos (Repository)

Gestiona la persistencia de datos usando Spring Data JPA.

Utiliza interfaces que heredan de JpaRepository.

Capa de Modelo (Entity)

Representa las entidades del dominio (por ejemplo, “Estudiante”).

Se mapea con la base de datos mediante anotaciones JPA (@Entity, @Id).

🔗 Comunicación entre las capas

El flujo de comunicación es unidireccional y estructurado:

Cliente (Postman / Navegador)
        ↓
Controller (API REST)
        ↓
Service (Lógica de Negocio)
        ↓
Repository (Acceso a Datos)
        ↓
Base de Datos (H2)


Cada capa depende solo de la capa inferior inmediata, lo que garantiza un diseño modular, mantenible y escalable.

🧰 Instalación y ejecución
1️⃣ Clonar el repositorio
git clone https://github.com/tuusuario/integracion-capas-api.git
cd integracion-capas-api

2️⃣ Importar el proyecto

Abrir Visual Studio Code o IntelliJ IDEA.

Asegurarse de tener instalado el plugin de Spring Boot.

3️⃣ Configurar dependencias (pom.xml)

El proyecto usa Maven, por lo que las dependencias están definidas en el archivo pom.xml.

Ejemplo de dependencias principales:

<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
  </dependency>
  <dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
  </dependency>
  <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
  </dependency>
</dependencies>

4️⃣ Ejecutar la aplicación
mvn spring-boot:run

5️⃣ Acceder a la API

La aplicación se ejecutará por defecto en:
👉 http://localhost:8080

🧪 Prueba rápida de la API

Supongamos que la aplicación gestiona estudiantes:

Método	Endpoint	Descripción
GET	/estudiantes	Listar todos los estudiantes
POST	/estudiantes	Registrar un nuevo estudiante
GET	/estudiantes/{id}	Buscar un estudiante por ID
DELETE	/estudiantes/{id}	Eliminar un estudiante

Puedes probar estos endpoints en Postman o directamente desde el navegador.

🧩 Conectores y APIs

Los conectores son las interfaces que permiten la comunicación entre capas (por ejemplo, un @Repository actúa como conector entre la base de datos y la lógica de negocio).

Las APIs REST actúan como conectores externos, permitiendo que otras aplicaciones o usuarios se comuniquen con el sistema.

De esta manera, la aplicación demuestra tanto conectividad interna (capas) como conectividad externa (API REST).

📘 Conclusión

Este proyecto ilustra cómo las diferentes capas de una aplicación interactúan entre sí de forma organizada, respetando los principios de separación de responsabilidades y bajo acoplamiento.
Gracias al uso de Spring Boot y sus módulos, la integración se vuelve más sencilla, robusta y escalable
