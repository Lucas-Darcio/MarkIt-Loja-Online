# Estágio de desenvolvimento e build
FROM maven:3.9.6-eclipse-temurin-21-alpine AS development
WORKDIR /usr/app

# Copia apenas o pom.xml primeiro para baixar as dependências (cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código fonte
COPY src ./src

# Comando para rodar em modo desenvolvimento com Hot Reload (via DevTools)
CMD ["mvn", "spring-boot:run"]

# Estágio de Build para produção
FROM development AS build
RUN mvn clean package -DskipTests

# Estágio final de Produção (Imagem leve)
FROM eclipse-temurin:21-jre-alpine AS production
WORKDIR /usr/app
COPY --from=build /usr/app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]