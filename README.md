### Back End Project With Quarkus

![GitHub top language](https://img.shields.io/github/languages/top/CarlosRobertoMedeiros/Backend-Java-red)
### Quarkus 2.1.1.Final - Study

## Project Arch
	- First Project BackEnd: Quarkus 2.1.1.Final
	
## Owner

	- Carlos Roberto Medeiros de Lima

### Tools and Technologies Used ###
	
	#Environment 
		In a first terminal, inside iFood Project run:
			- docker-compose up
			KeyCloak Problem login solved:
			 	- docker exec -it keycloak_ifood /opt/jboss/keycloak/bin/add-user-keycloak.sh -u admin -p admin
				- docker exec -it keycloak_ifood /opt/jboss/keycloak/bin/jboss-cli.sh --connect --command=:reload
			
		In a second terminal, inside iFood Project run:
			mvn quarkus:dev
			
		KeyCloak  Link: http://localhost:8180/auth/
		Jaeger-ui Link: http://localhost:16686/search
		
		

	#Back End
		
		Skills
			Microservice 1 
				- Creating project with CLI
				- Creating docker-compose
				- Creating endPoint Rest
				- Crud Implementing with Panache
				- Testing with TestContainers, Database Rider and Approval Tests
				- Working DTO or TO with MapStruct
				- Implementing Hibernate Validator
				- Add KeyCloak
				- Protecting EndPoints
				- Implementing Jaeger
				