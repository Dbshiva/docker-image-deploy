1 pipeline {
    agent none
    	    parameters {
			string (name:'USER_ID' , defaultValue:'Jenkins_user')
			choice choices: ['Dev', 'Test', 'Prod'], name: 'Role'
			booleanParam(name: 'DEBUG_BUILD', defaultValue: true)
	    }
    	    triggers {
        		cron('H */4 * * 1-5')
        		pollSCM('H */4 * * 1-5')
	    }
  	    environment {
			SSH_KEY=credentials('ssh-keys')
			GITHUB_CRED=credentials('Github_cred')
        		MY_STRING = "${params.USER_ID}"
        		MY_CHOICE = "${params.Role}"
        		MY_BOOLEAN = "${params.DEBUG_BUILD}"
    	    }  
     stages {
            stage('Checkout') {
                agent {label 'Java1'}
			when{
			      anyOf{
				     branch 'master'
				     branch 'dev'
			      }
			}
                steps {
		       git branch: 'master', url: 'https://github.com/Dbshiva/war-web-project.git'
                       echo 'Checked out data'
                }
            }
            stage('Test') {
                 agent {label 'Java1'}
                 steps {
			echo "Environment Variable MY_STRING: ${MY_STRING}"
			echo "Environment Variable MY_CHOICE: ${MY_CHOICE}"
			echo "Environment Variable MY_BOOLEAN: ${MY_BOOLEAN}"
                        echo 'Test run successful'
                }
            }
            stage('Build') {
                 agent {label 'Java2'}
			when {      
                    		expression { 
					   (currentBuild.result == null || currentBuild.result == 'SUCCESS') 
		    		} 
           		}
                 steps {
                      echo 'Build package successful'
                 }
            }   
            stage('Deploy_to_tomcat') {
                agent {label 'Java2'}
			when {
                		expression { 
					   (currentBuild.result == null || currentBuild.result == 'SUCCESS') 
				} 
            		}
                steps {
                    	echo 'Web app deployed successfully'
                        sh 'echo $SSH_KEY'			
			sh 'echo USERNAME:PASSWORD:: $GITHUB_CRED'
			sh 'echo username: $GITHUB_CRED_USR'
			sh 'echo password: $GITHUB_CRED_PSW'
                }
            } 
        }
		post { 
		  	always {
    	 			emailext body:'''
				jobname :'$JOB_NAME'
           			build no:'$BUILD_NUMBER'
            			job URL:'$BUILD_URL'
            			build status:'$BUILD_STATUS'
				''',
	    			subject:'$JOB_NAME' , 
	    			to: 'dbshivanand003@gmail.com'
	  		}
    		}	
}
