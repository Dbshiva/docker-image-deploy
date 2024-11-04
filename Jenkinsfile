pipeline {
    agent none
    parameters {
			string (name:'USER_ID' , defaulValue:'Jenkins_user')
			choice choices: ['Dev', 'Test', 'Prod'], name: 'Role'
			booleanParam(name: 'DEBUG_BUILD', defaultValue: true
}
    triggers {
        cron('0 */4 * * 1-5')
        pollSCM('0 */4 * * 1-5')
        
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
}
