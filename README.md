Proyecto Akihabara

Aplicación de consola en Java para la gestión de productos y clientes en una tienda temática, con integración de inteligencia artificial mediante la API de OpenRouter.

---

## 1. Configuración de la Base de Datos MySQL

1. Asegúrate de tener MySQL instalado y en ejecución.
2. Crea una base de datos llamada: `akihabara_db`.
3. Define las siguientes tablas:

### Tabla `productos`:

- `id` (INT, AUTO_INCREMENT, PRIMARY KEY)
- `nombre` (VARCHAR)
- `categoria` (VARCHAR)
- `precio` (DECIMAL)
- `stock` (INT)

### Tabla `clientes`:

- `id` (INT, AUTO_INCREMENT, PRIMARY KEY)
- `nombre` (VARCHAR)
- `email` (VARCHAR)
- `fecha_registro` (DATE)

4. Verifica que el usuario y contraseña definidos en `config.properties` tengan acceso a la base de datos.

---

## 2. Configuración de la Inteligencia Artificial (IA)

Este proyecto utiliza la API de OpenRouter para generar descripciones automáticas y sugerencias de categorías mediante inteligencia artificial.

### Paso 1: Crear una cuenta en OpenRouter

1. Accede a [https://openrouter.ai/](https://openrouter.ai/).
2. Regístrate o inicia sesión.
3. Ve a la sección "API Keys" y genera una nueva clave.
4. Copia la clave generada.

### Paso 2: Configurar la clave en el proyecto

1. Copia el archivo de ejemplo:

   ```bash
   cp config.properties.example config.properties
Abre config.properties y reemplaza el valor de api_key con tu clave real de OpenRouter.

properties
Copiar
Editar
api_key=sk-tu-clave-aqui
model=mistralai/mistral-7b-instruct:free
Guarda el archivo. Asegúrate de que config.properties esté en .gitignore para evitar subir claves privadas a GitHub.

Paso 3: Verificar la carga de configuración
La clase ConfigLoader se encarga de leer automáticamente el archivo config.properties. No necesitas modificar el código si ya está correctamente implementado.

Paso 4: Ejecutar la aplicación
Al iniciar la aplicación desde consola, las funciones de inteligencia artificial estarán disponibles en el menú si la clave es válida y hay conexión a internet.

Si recibes un error 401 ("No auth credentials found"), verifica que:

La clave esté bien escrita.

El archivo config.properties esté en la ubicación correcta.

La clave no haya sido revocada o haya expirado.

3. Cómo compilar y ejecutar la aplicación
Compila el proyecto respetando la estructura de paquetes:

bash
Copiar
Editar
javac -d bin src/**/*.java
Asegúrate de que:

La base de datos esté creada y activa.

El archivo config.properties esté correctamente configurado.

Ejecuta la clase principal desde la carpeta bin:

bash
Copiar
Editar
java view.MainMenu
Reemplaza view.MainMenu con el nombre real de tu clase principal si es diferente.

4. Funcionalidades del Proyecto
Gestión de Productos
Crear, consultar, listar, actualizar y eliminar productos.

Gestión de Clientes
Registrar, consultar, listar, actualizar y eliminar clientes.

Integración con IA
Generación automática de descripciones para productos.

Sugerencia de categorías basada en texto usando el modelo de lenguaje de OpenRouter.

Interfaz de Usuario
Menú interactivo en consola para navegar por todas las opciones del sistema.

5. Estructura del Proyecto
java
Copiar
Editar
├── model       → Clases entidad: ProductoOtaku, ClienteOtaku
├── dao         → Acceso a base de datos y operaciones CRUD
├── service     → Conexión con la API de inteligencia artificial
├── util        → Utilidades generales (carga de datos, helpers)
├── view        → Menú e interacción en consola
├── config      → Carga de configuración externa (API Key, modelo, DB)
6. Archivos de Configuración
config.properties.example
Archivo de ejemplo que debe copiarse para crear la configuración real:

properties
Copiar
Editar
# config.properties.example

db.url=jdbc:mysql://localhost:3306/akihabara_db
db.user=proyecto
db.password=campusfp

api_key=REEMPLAZAR_CON_TU_API_KEY
model=mistralai/mistral-7b-instruct:free
Para usarlo, copia el archivo y reemplaza el valor de api_key:

bash
Copiar
Editar
cp config.properties.example config.properties
Recuerda: nunca subas config.properties con datos reales al repositorio.

7. Requisitos
Java 8 o superior

MySQL 5.7 o superior

Conexión a internet (para acceder a la API de OpenRouter)

Cuenta de OpenRouter con una API key válida