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