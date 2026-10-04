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
                    sh './mvnw org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=tp-foyer-devsecops'
                }
            }
        }

        stage('SAST - Semgrep') {
            steps {
                sh '/home/hafedhchaibi/.local/bin/semgrep scan --config=p/java --error'
            }
        }

        stage('Secrets Scan - Gitleaks') {
            steps {
                sh '/usr/local/bin/gitleaks git . --redact'
            }
        }

        stage('SCA - Trivy') {
            steps {
                sh '/usr/bin/trivy fs --scanners vuln --severity HIGH,CRITICAL --exit-code 1 .'
            }
        }
    }

    post {
        success {
            echo 'Build, unit tests, SAST, secret scanning and SCA succeeded.'
        }

        failure {
            echo 'Pipeline failed.'
        }
    }
}