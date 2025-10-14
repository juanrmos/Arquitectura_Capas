# 🧪 Guía de Pruebas con Postman

## 🚀 Ejecutar la Aplicación

```bash
./mvnw spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

## 📋 Endpoints Disponibles

### 1. GET - Listar todos los estudiantes
- **URL**: `http://localhost:8080/api/estudiantes`
- **Método**: GET
- **Respuesta esperada**: Lista de estudiantes en formato JSON

---

### 2. GET - Obtener un estudiante por ID
- **URL**: `http://localhost:8080/api/estudiantes/1`
- **Método**: GET
- **Respuesta esperada**: Un estudiante específico

---

### 3. POST - Crear un nuevo estudiante
- **URL**: `http://localhost:8080/api/estudiantes`
- **Método**: POST
- **Headers**: 
  - `Content-Type: application/json`
- **Body (raw JSON)**:
```json
{
    "nombre": "Pedro",
    "apellido": "Sánchez",
    "email": "pedro.sanchez@universidad.edu",
    "codigo": "EST006",
    "carrera": "Ingeniería Civil",
    "semestre": 6
}
```

---

### 4. PUT - Actualizar un estudiante existente
- **URL**: `http://localhost:8080/api/estudiantes/1`
- **Método**: PUT
- **Headers**: 
  - `Content-Type: application/json`
- **Body (raw JSON)**:
```json
{
    "nombre": "Juan Carlos",
    "apellido": "Pérez González",
    "email": "juan.perez@universidad.edu",
    "codigo": "EST001",
    "carrera": "Ingeniería de Sistemas",
    "semestre": 6
}
```

---

### 5. DELETE - Eliminar un estudiante
- **URL**: `http://localhost:8080/api/estudiantes/1`
- **Método**: DELETE
- **Respuesta esperada**: Mensaje de confirmación

---

### 6. GET - Buscar por email
- **URL**: `http://localhost:8080/api/estudiantes/email/juan.perez@universidad.edu`
- **Método**: GET
- **Respuesta esperada**: Estudiante con ese email

---

### 7. GET - Buscar por código
- **URL**: `http://localhost:8080/api/estudiantes/codigo/EST001`
- **Método**: GET
- **Respuesta esperada**: Estudiante con ese código

---

## 🗄️ Acceder a la Consola H2

Para visualizar la base de datos directamente:

1. Abrir en el navegador: `http://localhost:8080/h2-console`
2. Configuración de conexión:
   - **JDBC URL**: `jdbc:h2:mem:estudiantesdb`
   - **User Name**: `sa`
   - **Password**: (dejar vacío)
3. Click en "Connect"

## 📊 Flujo de Prueba Recomendado

1. **Iniciar** la aplicación
2. **GET** `/api/estudiantes` - Ver estudiantes iniciales
3. **POST** `/api/estudiantes` - Crear un nuevo estudiante
4. **GET** `/api/estudiantes/{id}` - Ver el estudiante creado
5. **PUT** `/api/estudiantes/{id}` - Actualizar el estudiante
6. **GET** `/api/estudiantes/email/{email}` - Buscar por email
7. **DELETE** `/api/estudiantes/{id}` - Eliminar el estudiante
8. **GET** `/api/estudiantes` - Verificar que se eliminó

## ⚠️ Errores Comunes

### Error: "El email ya está registrado"
- **Causa**: Intentas crear/actualizar con un email que ya existe
- **Solución**: Usa un email diferente

### Error: "El código ya está registrado"
- **Causa**: Intentas crear/actualizar con un código que ya existe
- **Solución**: Usa un código diferente

### Error: "Estudiante no encontrado"
- **Causa**: El ID especificado no existe en la base de datos
- **Solución**: Verifica los IDs disponibles con GET `/api/estudiantes`

## 🎯 Ejemplo de Respuesta Exitosa

### Crear Estudiante (POST)
**Status**: 201 Created
```json
{
    "id": 6,
    "nombre": "Pedro",
    "apellido": "Sánchez",
    "email": "pedro.sanchez@universidad.edu",
    "codigo": "EST006",
    "carrera": "Ingeniería Civil",
    "semestre": 6
}
```

### Listar Estudiantes (GET)
**Status**: 200 OK
```json
[
    {
        "id": 1,
        "nombre": "Juan",
        "apellido": "Pérez",
        "email": "juan.perez@universidad.edu",
        "codigo": "EST001",
        "carrera": "Ingeniería de Sistemas",
        "semestre": 5
    },
    {
        "id": 2,
        "nombre": "María",
        "apellido": "García",
        "email": "maria.garcia@universidad.edu",
        "codigo": "EST002",
        "carrera": "Ingeniería Industrial",
        "semestre": 3
    }
]
```