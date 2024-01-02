# Sistema de Evaluación de Competencias 360

Este proyecto JavaFX 17 está diseñado para gestionar el proceso de evaluación de competencias 360 de los trabajadores de la empresa. El sistema incluye módulos para el registro de trabajadores, mantenimiento de competencias y puestos, así como la realización y revisión de evaluaciones.

## Requisitos del Sistema

- Java 17
- Jakarta EE 10
- Base de datos Oracle con esquema "EvaComUNA" y contraseña "una"
- Toad Data Modeler para el diseño del modelo de la base de datos

## Configuración del Proyecto

1. Clona este repositorio.
2. Importa el proyecto en tu IDE favorito (Eclipse, IntelliJ, etc.).
3. Configura la conexión a la base de datos en el archivo `persistence.xml`.
4. Ejecuta los scripts de creación de la base de datos desde la herramienta Toad Data Modeler.

## Configuración del Servidor de Correo

1. Edita el archivo `email-config.properties` en el directorio `src/main/resources`.
2. Agrega la información del servidor de correo, plantilla HTML, foto e información de la empresa.

## Ejecución del Proyecto

1. Asegúrate de tener todas las dependencias y configuraciones correctas.
2. Ejecuta la aplicación desde tu IDE.

## Estructura del Proyecto

- `src/main/java`: Contiene el código fuente de la aplicación.
- `src/main/resources`: Archivos de recursos, como la configuración del servidor de correo.
- `scripts`: Scripts SQL para la creación de la base de datos.

## Tecnologías Utilizadas

- JavaFX 17
- Jakarta EE 10
- Java Persistence API (JPA)
- SOAP para Web Services

## Contribuciones

Las contribuciones son bienvenidas. Si encuentras algún problema o tienes sugerencias, por favor, crea un problema o una solicitud de extracción.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

 
