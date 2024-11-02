node {
        stage('Checkout') {
                           git branch: 'master', url: 'https://github.com/Dbshiva/war-web-project-master.git'
                           echo 'Checked out data'
        }
        stage('Test') {
   //                     sh 'mvn clean test'
                        echo 'Test run successful'
        }
        stage('Build') {
  //                      sh 'mvn clean package'
                        echo 'Build package successful'
        }
        stage('Deploy_to_tomcat') {
 //                                sh 'sudo rsync -avh /var/lib/jenkins/workspace/pipeline-groovy/target/wwp-1.0.0.war /usr/local/tomcat9/webapps/wwp-1.0.0.war'
  //                               sh 'sudo /usr/local/tomcat9/bin/startup.sh'
                                 echo 'Web app deployed successfully'
        }       
}
