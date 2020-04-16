mvn org.ops4j:maven-pax-plugin:create-project -DgroupId=org.dk.mmmi.asteroids -DartifactId=asteroid -Dversion=1.0-SNAPSHOT

mvn pax:create-bundle -Dpackage=dk.sdu.mmmi.core -Dname=core-bundle -Dversion=1.0-SNAPSHOT
 
pax-wrap-jar -g groupId -a artifactId -v version