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
                sh './mvnw clean package'
            }
        }
    }

    post {
        success {
            echo 'Build and unit tests succeeded.'
        }

        failure {
            echo 'Pipeline failed.'
        }
    }
}