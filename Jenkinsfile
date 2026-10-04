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
                // Full SCA report containing all severity levels
                sh '/usr/bin/trivy fs --scanners vuln --format json --output trivy-sca-report.json .'

                // Security Gate: HIGH and CRITICAL vulnerabilities block the pipeline
                sh '/usr/bin/trivy fs --scanners vuln --severity HIGH,CRITICAL --exit-code 1 .'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'trivy-sca-report.json',
                             allowEmptyArchive: true,
                             fingerprint: true
        }

        success {
            echo 'Build, unit tests, SAST, secret scanning and SCA succeeded.'
        }

        failure {
            echo 'Pipeline failed.'
        }
    }
}