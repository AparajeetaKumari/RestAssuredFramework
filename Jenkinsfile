pipeline 
{
    agent any
    
    tools{
    	maven 'MAVEN_HOME'
        }
        
    environment{
   
        BUILD_NUMBER = "${BUILD_NUMBER}"
   
    }
    

    stages 
    {
        stage('Build') 
        {
            steps
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post 
            {
                success
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }

        
        
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa done")
            }
        }
             
             
                
                
      stage('Run Docker Image with Regression Tests') {
    steps {
        script {
           def exitCode = sh{script: "docker run --name apitesting{BUILD_NUMBER} -e MAVEN_OPTS='-Dsurefire.suiteXmlFiles=src/test/resource/testrunners/testng.xml'
           if(exitCode !=0){
           currentBuild.result = 'FAILURE'
           }
           sh "docker start apitesting{BUILD_NUMBER}"
           sh "docker cp apitesting${BUILD_NUMBER}:/app/target/APIExecutionReport.html ${WORKSPACE}/target"
           sh "docker rm -f apitesting${BUILD_NUMBER}"
        }
    }
}



		
		stage('Publish Regression Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: false, 
                                  reportDir: 'target', 
                                  reportFiles: 'APIExecutionReport.html', 
                                  reportName: 'API HTML Regression Extent Report', 
                                  reportTitles: ''])
            }
        }
        
        
         

         
    }
}