### What's inside?

This is simple and minimalistic demo of native code execution from JVM using project Panama. 
I'm using here latest Java 22 project Panama implementation - as I've noticed some API changes in previous versions of JDK where Panama has been available in early access builds. 

### Running the demo

1. Setup your JVM environment like a pro:
`sdk env`

2. Build:
`mvn clean install`

3. Execute:
`java --enable-native-access=ALL-UNNAMED -cp target/panama-poc-1.0-SNAPSHOT.jar com.codeappeal.panama.PanamaPocApplication`