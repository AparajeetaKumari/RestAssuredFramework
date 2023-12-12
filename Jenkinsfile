pipeline 
{
    agent any
    
   tools{
    	maven 'MAVEN_HOME'
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
             
             

		
		stage('Regression API Automation Tests'){
            steps{
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE'){
                    git 'https://github.com/AparajeetaKumari/RestAssuredFramework.git'
                    sh "mvn clean install -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng.xml"
                    }
            }
        }
        
        stage('Publish Allure Report'){
            steps{
                   allure([
                   	includeProperties: false,
                   	jdk: '',
                   	properties: [],
                   	reportBuildPolicy: 'ALWAYS',
                   	results:[[path: '/allure-results']]
                   ])
            }
        }
        
        
		stage('Deploy to STAGE'){
            steps{
                   echo("deploy to STAGE")
            }
        } 
        
    
  
		stage('Sanity API Automation Tests'){
            steps{
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE'){
                    git 'https://github.com/AparajeetaKumari/RestAssuredFramework.git'
                    sh "mvn clean install -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_sanity.xml"
                    }
            }
        }
         
    }
}