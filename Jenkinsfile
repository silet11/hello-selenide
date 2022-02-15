pipeline {
    agent any

    stages {
        stage('Clean'){
            steps{
                sh './gradlew clean'
            }
        }
        stage('Test'){
            steps{
                 sh './gradlew test'
            }
            post{
                always{
                    junit 'buil/test-results/test/*.xml'
                }
            }
         }

    }
}