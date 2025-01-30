## Pokemon Pokedex API ![image](https://github.com/user-attachments/assets/4ff8f9b5-b3ab-4256-ac77-1334244dd8ee)



### Prerequisites
- Java 17
- Gradle
- Docker (optional)

### Running Locally
1. Build the application: `./gradlew build`
2. Run the application: `./gradlew bootRun`
3. Access endpoints at `http://localhost:8080`.

### Using Docker 
1. Compile java project to generate JAR file `./gradlew build`
2. Build the image: `docker build -t pokemon-pokedex .`
3. Run the container: `docker run -p 8080:8080 pokemon-pokedex`

### Testing
Run tests with: `./gradlew test`
