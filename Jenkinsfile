pipeline {
     agent any
     environment {
	IMAGE_TAG="${BUILD_NUMBER}"
	docker_registry="ambarbhore1234" 
     }
     
     stages {
	stage('Build the JAR') {
	   steps {
	      sh 'mvn clean package -DskipTests'
	   }
	}

	stage('Push to the Nexus') {
	   steps {
	      withCredentials([usernamePassword(credentialsId: 'nexus-cred', usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASS')]) {
		  sh '''
		     mvn deploy -DskipTests \
		     --settings /var/lib/jenkins/settings.xml \
		     -Dnexus.username=$NEXUS_USER \
		     -Dnexus.password=$NEXUS_PASS
		  '''
	        }
	    }
         }
     }
}
