Consists of [server](./server/) and [client](./client/) modules.

In production mode, they both are compiled and built into a single WAR archive which can be deployed to a web application server or run directly as a standalone Java application.

In development mode, you work with both modules separately. The server is built and run with the help of Maven from the 'server' directory. The client is operated via Yarn from the 'client/src/main/ng' directory.

### Build
##### Production mode
```bash
# build both server and client
mvn clean install -Pprod
```
##### Development mode
```bash
# build the server module without the client jar dependency 
# (can be run from the root or from the 'server' directories)
mvn clean install

# install client's npm dependencies (it's necessary for the first build only)
# navigate to the 'client/src/main/ng' directory and run the following command
npm install
```
### Run
##### Production mode
```bash
# navigate to the 'server' directory and run the following command
mvn spring-boot:run
```
> Access UI App at [http://localhost:9001](http://localhost:9001)
##### Development mode
```bash
# navigate to the 'server' directory and run the following command
mvn spring-boot:run

# navigate to the 'client/src/main/ng' directory and run the following command
npm start
```
> Access UI App at [http://localhost:9002](http://localhost:9002)
