pipeline {
     agent any

     parameters {
	string(name: 'IMAGE_TAG', defaultValue: '', description: 'Docker Image Tag to deploy')
     }

     stages {
	stage('Print Image Tag') {
	    steps {
		echo "Received params: IMAGE_TAG = ${params.IMAGE_TAG}"
	   	echo "Deploying image with tag1111: ${params.IMAGE_TAG}"
	    }
	}	
     }
}
