pipeline {
    agent none
    parameters {
			string (name:'USER_ID' , defaulValue:'Jenkins_user')
			choice choices: ['Dev', 'Test', 'Prod'], name: 'Role'
			booleanParam(name: 'DEBUG_BUILD', defaultValue: true)
}
    triggers {
        cron('0 */4 * * 1-5')
        pollSCM('0 */4 * * 1-5')
}
  environment {
	SSH_KEY=credentials('ssh-keys')
	GITHUB_CRED=credentials('Github_cred')
        MY_STRING = "${params.USER_ID}"
        MY_CHOICE = "${params.Role}"
        MY_BOOLEAN = "${params.DEBUG_BUILD}"
    }  
    tools {
          maven 'maven 3.9.9'
    }
        stages {
            stage('Checkout') {
                agent {label 'Java1'}
                steps {
//                      git url: https://github.com/Dbshiva/war-web-project'
                      echo 'Checked out data'
                }
            }
            stage('Test') {
                 agent {label 'Java1'}
                 steps {
                      echo 'Test run successful'
                }
            }
            stage('Build') {
                 agent {label 'Java2'}
                 steps {
                      echo 'Build package successful'
                }
            }   
            stage('Deploy_to_tomcat') {
                agent {label 'Java2'}
                steps {
                    echo 'Web app deployed successfully'
                }
            } 
        }
	post { 
		always {
    			subject: '${JOB_NAME}',
   			emailext body:
				jobname :  '$JOB_NAME'
           			build no:  '$BUILD_NUMBER'
            			job URL: '$BUILD_URL'
            			build status: '$BUILD_STATUS',
	    			subject: '$JOB_NAME' , 
	    			to: 'dbshivanand003@gmail.com'
	  	}
    	}	
}
