# Proyecto Akihabara

Aplicación de consola en Java para la gestión de productos y clientes en una tienda temática, con integración de inteligencia artificial mediante la API de OpenRouter.

---

## 1. Configuración de la Base de Datos MySQL

- Asegúrate de tener MySQL instalado y en ejecución.
- Crea una base de datos llamada: **akihabara_db**.
- Define las siguientes tablas:

**Tabla productos:**

- id: INT, AUTO_INCREMENT, PRIMARY KEY  
- nombre: VARCHAR  
- categoria: VARCHAR  
- precio: DECIMAL  
- stock: INT  

**Tabla clientes:**

- id: INT, AUTO_INCREMENT, PRIMARY KEY  
- nombre: VARCHAR  
- email: VARCHAR  
- fecha_registro: DATE  

- Verifica que el usuario y contraseña definidos en `config.properties` tengan acceso a la base de datos.

---

## 2. Configuración de la Inteligencia Artificial (IA)

Este proyecto utiliza la API de OpenRouter para generar descripciones automáticas y sugerencias de categorías mediante inteligencia artificial.

### Paso 1: Obtener tu clave API

- Accede a [https://openrouter.ai/](https://openrouter.ai/).  
- Regístrate o inicia sesión.  
- Ve a la sección **API Keys** y genera una nueva clave.  
- Copia la clave generada.

### Paso 2: Configurar la clave en el proyecto

- En el archivo `config.properties` que ya está incluido en el proyecto, busca la línea:

api_key=xxxxxxx

- Reemplaza las `xxxxxxx` por tu clave real de OpenRouter.

> **Importante:** No subas tu clave real a repositorios públicos.

### Paso 3: Verificar la carga de configuración

La clase `ConfigLoader` lee automáticamente el archivo `config.properties`. No necesitas modificar el código si está bien configurado.

### Paso 4: Ejecutar la aplicación

- Al iniciar la aplicación desde consola, las funciones de IA estarán disponibles si la clave es válida y hay conexión a internet.  
- Si recibes un error 401 ("No auth credentials found"), verifica:  
- Que la clave esté bien escrita.  
- Que el archivo `config.properties` esté en la ubicación correcta.  
- Que la clave no haya sido revocada o expirado.

---

## Cómo compilar y ejecutar la aplicación

1. Compila el proyecto respetando la estructura de paquetes:

 javac -d bin src/**/*.java
Asegúrate de que:

La base de datos esté creada y activa.

El archivo config.properties esté correctamente configurado.

Ejecuta la clase principal desde la carpeta bin:

java view.MainMenu
Cambia view.MainMenu por el nombre real de la clase principal si es diferente.

Funcionalidades del Proyecto
Gestión de Productos: crear, consultar, listar, actualizar y eliminar productos.

Gestión de Clientes: registrar, consultar, listar, actualizar y eliminar clientes.

Integración con IA:

Generación automática de descripciones para productos.

Sugerencia de categorías basada en texto usando el modelo de OpenRouter.

Interfaz de Usuario: menú interactivo en consola para navegar por las opciones.

Estructura del Proyecto
model → Clases entidad: ProductoOtaku, ClienteOtaku

dao → Acceso a base de datos y operaciones CRUD

service → Conexión con la API de inteligencia artificial

util → Utilidades generales (carga de datos, helpers)

view → Menú e interacción en consola

config → Carga de configuración externa (API Key, modelo, DB)

Archivos de Configuración
El archivo config.properties ya está incluido con esta plantilla:

properties

db.url=jdbc:mysql://localhost:3306/akihabara_db
db.user=proyecto
db.password=campusfp

api_key=xxxxxxx
model=mistralai/mistral-7b-instruct:free
Solo tienes que editar la línea api_key con tu clave de OpenRouter.

Requisitos
Java 8 o superior

MySQL 5.7 o superior

Conexión a internet (para acceder a la API de OpenRouter)

Cuenta de OpenRouter con una API key válida








