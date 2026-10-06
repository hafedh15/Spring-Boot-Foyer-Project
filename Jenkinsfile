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

                // SCA Security Gate: HIGH and CRITICAL block the pipeline
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
                // Full Docker image vulnerability report
                sh '/usr/bin/trivy image --scanners vuln --format json --output trivy-image-report.json tp-foyer:devsecops'

                // Image Security Gate: HIGH and CRITICAL block the pipeline
                sh '/usr/bin/trivy image --scanners vuln --severity HIGH,CRITICAL --exit-code 1 tp-foyer:devsecops'
            }
        }

        stage('Deploy - Staging') {
            steps {
                sh '''
                    echo "Deploying application to staging..."

                    docker rm -f tp-foyer-staging || true

                    docker run -d \
                      --name tp-foyer-staging \
                      --network tpfoyer-net \
                      -p 8082:8081 \
                      -e SPRING_PROFILES_ACTIVE=staging \
                      -e SPRING_DATASOURCE_URL='jdbc:mysql://tpfoyer-mysql-ci:3306/tpfoyerdb?createDatabaseIfNotExist=true' \
                      -e SPRING_DATASOURCE_USERNAME=root \
                      -e SPRING_DATASOURCE_PASSWORD='' \
                      tp-foyer:devsecops
                '''

                sh '''
                    echo "Waiting for staging application to become ready..."

                    for i in $(seq 1 30); do
                        if curl -fsS http://localhost:8082/v3/api-docs > /dev/null; then
                            echo "Staging environment is ready."
                            exit 0
                        fi

                        echo "Waiting for staging... attempt $i/30"
                        sleep 2
                    done

                    echo "Staging failed to become ready."
                    docker logs tp-foyer-staging
                    exit 1
                '''
            }
        }

        stage('DAST - OWASP ZAP') {
            steps {
                sh '''
                    echo "Starting OWASP ZAP DAST scan..."

                    rm -rf zap-reports
                    mkdir -p zap-reports
                    chmod 777 zap-reports

                    docker run --rm \
                      --network tpfoyer-net \
                      -v "$WORKSPACE/zap-reports:/zap/wrk/:rw" \
                      zaproxy/zap-stable \
                      zap-api-scan.py \
                      -t http://tp-foyer-staging:8081/v3/api-docs \
                      -f openapi \
                      -r zap-report.html \
                      -J zap-report.json \
                      -I

                    echo "DAST scan completed."
                '''
            }
        }

        stage('Controlled DAST Gate Test') {
            steps {
                sh '''
                    echo "=== CONTROLLED DAST SECURITY GATE TEST ==="
                    echo "The real ZAP report will remain unchanged."
                    echo "Creating a copy containing one synthetic HIGH finding..."

                    cp zap-reports/zap-report.json zap-reports/zap-report-gate-test.json

                    sed -i '2i\\        "riskdesc": "High (High)",' zap-reports/zap-report-gate-test.json

                    echo "Controlled test report created."
                '''
            }
        }

        stage('DAST Security Gate - Controlled Test') {
            steps {
                sh '''
                    echo "=== CONTROLLED DAST SECURITY GATE VALIDATION ==="

                    HIGH_COUNT=$(grep -c '"riskdesc"[[:space:]]*:[[:space:]]*"High (' zap-reports/zap-report-gate-test.json || true)

                    echo "HIGH DAST findings: $HIGH_COUNT"

                    if [ "$HIGH_COUNT" -gt 0 ]; then
                        echo "DAST Security Gate FAILED: HIGH vulnerability detected."
                        echo "This HIGH finding is synthetic and used only to validate blocking behavior."
                        exit 1
                    fi

                    echo "DAST Security Gate PASSED."
                '''
            }
        }
    }

    post {
        always {
            // Archive the real reports and the controlled test report
            archiveArtifacts artifacts: 'trivy-sca-report.json, trivy-image-report.json, zap-reports/zap-report.html, zap-reports/zap-report.json, zap-reports/zap-report-gate-test.json',
                             allowEmptyArchive: true,
                             fingerprint: true
        }

        success {
            echo 'Build, tests, security scans, Security Gates, staging deployment and DAST succeeded.'
        }

        failure {
            echo 'Pipeline failed.'
        }
    }
}