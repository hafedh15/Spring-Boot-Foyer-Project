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

                // SCA Security Gate: HIGH and CRITICAL vulnerabilities block the pipeline
                sh '/usr/bin/trivy fs --scanners vuln --severity HIGH,CRITICAL --exit-code 1 .'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t tp-foyer:devsecops .'
            }
        }

        stage('Docker Image Scan - Trivy') {
            steps {
                // Full Docker image report containing all severity levels
                sh '/usr/bin/trivy image --scanners vuln --format json --output trivy-image-report.json tp-foyer:devsecops'

                // Image Security Gate: HIGH and CRITICAL vulnerabilities block the pipeline
                sh '/usr/bin/trivy image --scanners vuln --severity HIGH,CRITICAL --exit-code 1 tp-foyer:devsecops'
            }
        }
    }

    post {
        always {
            // Archive both security reports even if a Security Gate blocks the pipeline
            archiveArtifacts artifacts: 'trivy-sca-report.json, trivy-image-report.json',
                             allowEmptyArchive: true,
                             fingerprint: true
        }

        success {
            echo 'Build, tests, SAST, secret scanning, SCA, Docker build and image security scan succeeded.'
        }

        failure {
            echo 'Pipeline failed.'
        }
    }
}