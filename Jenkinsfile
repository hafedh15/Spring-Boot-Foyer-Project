pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Unit Tests') {
            steps {
                sh './mvnw clean package -Dspring.datasource.url=jdbc:mysql://localhost:3307/tpfoyerdb?createDatabaseIfNotExist=true'
            }
        }

        stage('SAST - SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube-DevSecOps') {
                    sh './mvnw sonar:sonar -Dsonar.projectKey=tp-foyer-devsecops'
                }
            }
        }
    }

    post {
        success {
            echo 'Build, unit tests and SonarQube analysis succeeded.'
        }

        failure {
            echo 'Pipeline failed.'
        }
    }
}