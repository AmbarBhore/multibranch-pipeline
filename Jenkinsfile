pipeline {
    agent any

    parameters {
	string(name: 'IMAGE_TAG', defaultValue: '', description: 'Docker Image Tag to deploy')
    }

    stages {
	stage('Deploy to Kubernetes - Stage') {
	    steps {
		script {
		   echo "Received params: IMAGE_TAG = ${params.IMAGE_TAG}"
	   	   echo "Deploying image with tag1111: ${params.IMAGE_TAG}"
			
		   // Apply deployment and service YAMLs
		   sh 'kubectl apply -f k8s/deployment.yaml'
		   sh 'kubectl apply -f k8s/service.yaml'
		
		   // set image using IMAGE_TAG passed from the dev
		   def image="ambarbhore1234/multi-branch-agent:${params.IMAGE_TAG}"
		   echo "Setting image: ${image}"
		  
		   sh "kubectl set image deployment/multi-branch multi-branch=${image} -n stage" 		
	   	}
	    }	
        }
    }
}
