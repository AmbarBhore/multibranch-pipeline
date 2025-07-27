pipeline {
    agent any
    parameters {
	choice(name: 'TARGET_COLOR', choices: ['blue','green'], description: 'Choose which enviornment to deploy')
        string(name: 'IMAGE_TAG', defaultValue: '', description: 'Docker Image tag to deploy')
    }
    
    environment {
	APP_LABEL="multi-branch-${params.TARGET_COLOR}"
	DEPLOYMENT_NAME="multi-agent-${params.TARGET_COLOR}"
	IMAGE="ambarbhore1234/multi-branch-agent:${params.IMAGE_TAG}"
    }
    
    stages {
	stage("Deploy to production") {
	   steps {
		script {
		    echo "deploying to ${params.TARGET_COLOR} with image ${env.IMAGE}"
			
		    // Apply the corrosponding YAML
		    sh "kubectl apply -f k8s/deployment-${params.TARGET_COLOR}.yaml - prod"

		    // Set image
		    sh "kubectl set image deployment/${env.DEPLOYMENT_NAME} multi-branch=${IMAGE} -n prod"
			
		    // Rollout check
		    sh "kubectl rollout status deployment/${env.DEPLOYMENT_NAME} -n prod"
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
