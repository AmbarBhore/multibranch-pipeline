pipeline {
    agent any
    
    environment {
	APP_LABEL= ''
	DEPLOYMENT_NAME= ''
	IMAGE= ''
	CONTAINER_NAME = ''
    }
    
    stages {
        stage("Manual Approval for Production") {
            steps {
                script {
                    def userInput = input(
                        id: 'ProdApproval',
                        message: "Approve deployment to PROD?",
                        parameters: [
                            choice(name: 'TARGET_COLOR', choices: ['blue', 'green'], description: 'Select which color to deploy'),
                            string(name: 'IMAGE_TAG', defaultValue: '', description: 'Enter Image Tag from dev/stage pipeline (e.g., 105)')
                        ]
                    )
		    // Assigned selected values to enviornment vars
		    env.TARGET_COLOR = userInput['TARGET_COLOR']
		    env.IMAGE_TAG = userInput['IMAGE_TAG']
		    env.APP_LABEL = "multi-branch-${env.TARGET_COLOR}"
		    env.DEPLOYMENT_NAME = "multi-agent-${env.TARGET_COLOR}"
		    env.CONTAINER_NAME = "multi-agent-${env.TARGET_COLOR}"
		    env.IMAGE = "ambarbhore1234/multi-branch-agent:${env.IMAGE_TAG}"
		}
	   }
	}	    
	stage("Deploy to production") {
	   steps {
		script {
		    // Replace image tag in YAML
		    sh """
		      echo "Updating image tag in deployment-${env.TARGET_COLOR}.yaml"
		      sed -i 's|image: .*$|image: ${env.IMAGE}|' k8s/deployment-${env.TARGET_COLOR}.yaml
		    """
		
		    // Apply service (once or every time)
		    sh "kubectl apply -f k8s/service.yaml -n prod"
			
		    // Apply the corrosponding YAML
		    sh "kubectl apply -f k8s/deployment-${env.TARGET_COLOR}.yaml -n prod"

		    // Set image
		    sh "kubectl set image deployment/${env.DEPLOYMENT_NAME} ${env.CONTAINER_NAME}=${env.IMAGE} -n prod"
			
		    // Rollout check
		    sh "kubectl rollout status deployment/${env.DEPLOYMENT_NAME} -n prod"
		}
	   }
	}
	stage("Smoke Test on deployment") {
	   steps {
		script {
		    echo "Runing smoke test ${DEPLOYMENT_NAME}"
		    sh "./smoke-test.sh"
		}
	  }
	}
	stage("Switch Traffic") {
	   steps {
		script {
		    echo "switching traffice to ${env.TARGET_COLOR}"
		    sh "kubectl patch svc multi-branch-service -n prod -p '{\"spec\":{\"selector\":{\"app\": \"${env.APP_LABEL}\"}}}'"
     		}
	   }
	}
    }
}
