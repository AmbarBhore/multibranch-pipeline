pipeline {
    agent any
    parameters {
	string(name: 'IMAGE_TAG', defaultValue: '', description: 'Docker Image tag to deploy')
    }

    stages {
       stage ("Deploy to kubernetes Production") {
          steps {
	     script {
            	echo "Received Parameters: IMAGE_TAG = ${params.IMAGE_TAG}"
		echo "Deploying Image with tag: ${params.IMAGE_TAG}"
		
		// Apply k8s and service YAMLs
		sh 'kubectl apply -f k8s/deployment-blue.yaml'
		sh 'kubectl apply -f k8s/service.yaml'

		// set image using IMAGE_TAG passed from stage
		def image="ambarbhore1234/multi-branch-agent:${params.IMAGE_TAG}"
		echo "Setting up this image: ${image}"
		
		sh "kubectl set image deployment/multi-agent-blue multi-agent=${image} -n prod"
	     }
          }
        }
    }
}
