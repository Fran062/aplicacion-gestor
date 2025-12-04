# Usamos una imagen base de Java 17
FROM eclipse-temurin:25-jre

# Especifica el nombre del archivo JAR generado.
# Reemplaza 'nombre-de-tu-app.jar' con el nombre de tu archivo en la carpeta target.
ARG JAR_FILE=target/*.jar


# Copia el JAR generado al contenedor
COPY ${JAR_FILE} app.jar

# Puerto que expone el contenedor
EXPOSE 8080

# Comando de ejecución al iniciar el contenedor
ENTRYPOINT ["java","-jar","/app.jar"]