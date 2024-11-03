pipeline {
    agent none
        stages {
            stage('Checkout') {
                agent {label 'Java1'}
                steps {
//                      git url: https://github.com/Dbshiva/war-web-project'
                      echo 'Checked out data'
                }
            }
            stage('Test') {
                steps {
   //                 sh 'mvn clean test'
                      echo 'Test run successful'
                }
            }
            stage('Build') {
             agent {label 'Java2'}
                steps {
                    sh 'mvn clean' // package'
                      echo 'Build package successful'
                }
            }   
            stage('Deploy_to_tomcat') {
                steps {
                    sh 'sudo rsync -avh /var/lib/jenkins/workspace/pipeline-deploy-tomcat/target/wwp-1.0.0.war /usr/local/tomcat9/webapps/wwp-1.0.0.war'
                    sh 'sudo /usr/local/tomcat9/bin/startup.sh'
                    echo 'Web app deployed successfully'
                }
            } 
        }
}
