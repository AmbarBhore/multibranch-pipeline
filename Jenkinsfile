pipeline {
     agent any
     environment {
	IMAGE_TAG="${BUILD_NUMBER}"
	image_name='multi-branch-agent'
	docker_registry='ambarbhore1234' 
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
		
	 stage('Build and Push Image to DockerHub') {
	    steps {
		script {
		    def version = sh(
			  script: "mvn help:evaluate -Dexpression=project.version -q -DforceStdout",
			  returnStdout: true
		    ).trim()
		    
		    echo "Docker Image version from pom.xml: ${version}"
		    echo "Jenkins Build Number: ${BUILD_NUMBER}"
		
		    def jarName = sh(script: "ls target/*.jar | grep -v original | head -n 1", returnStdout: true).trim()
		    sh "cp ${jarName} target/legacy-agent.jar"
		    
		    //Build docker images with maven version
		    withCredentials([usernamePassword(credentialsId: 'dockerhub-cred', usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
		    	sh """
			     echo "${DOCKER_HUB_PASSWORD}" | docker login -u "${DOCKER_HUB_USERNAME}" --password-stdin
			     
			     docker build -t ${docker_registry}/${image_name}:${version} .
			     docker tag ${docker_registry}/${image_name}:${version} ${docker_registry}/${image_name}:${IMAGE_TAG}
			     docker push ${docker_registry}/${image_name}:${version}
			     docker push ${docker_registry}/${image_name}:${IMAGE_TAG}
			"""
		    }
	        }
	    }
	}
	
	stage('Deploy to the Dev k8s') {
	   steps {
	      script {
		  echo "Deploying application to the kubernetes"
		  sh 'kubectl apply -f k8s/deployment.yaml'
		  sh 'kubectl apply -f k8s/service.yaml'
		
		  sh 'kubectl set image deployment/multi-branch multi-branch=${docker_registry}/${image_name}:${BUILD_NUMBER} -n dev1'
	      }
	   }
	}
    }
}
