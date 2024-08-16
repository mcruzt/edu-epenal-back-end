pipeline {
    agent any

    environment {
        // Define environment variables
        GITHUB_REPO = 'https://github.com/mcruzt/edu-epenal-back-end.git'
        DOCKER_REPO = 'https://registry.hub.docker.com'
        DOCKER_IMAGE = 'mcruzt/epenal-backend:v.0.3'
        // SonarQube environment variables
        SONARQUBE_URL = 'http://192.168.100.126:9003'
        SONARQUBE_TOKEN = credentials('SONAR_ACCESS')
        SONAR_PROJECT_KEY = 'epenal-backend'
    }

    stages {
        stage('Setup') {
            steps {
                // Checkout code from GitHub
                git url: "${GITHUB_REPO}", branch: 'master'
            }
        }

        stage('Unit Test') {
            steps {
                // Build the project using Gradle
                sh './gradlew clean test'
            }
        }

        stage('Sonar Analysis') {
            steps {
                // Run SonarQube analysis
                sh './gradlew sonar -Dsonar.projectKey=${SONAR_PROJECT_KEY} -Dsonar.host.url=${SONARQUBE_URL} -Dsonar.login=${SONARQUBE_TOKEN}'
            }
        }

        stage('Dockerization') {
            steps {
                script {
                    // Build Docker image
                   def epenalImage = docker.build("${DOCKER_IMAGE}",". --build-arg ENV_PROFILE=pr")
                    // Push Docker image to repository
                    docker.withRegistry("${DOCKER_REPO}", 'DOCKER_HUB') {
                        epenalImage.push()
                    }
                }
            }
        }

        stage('Kubernetes Deployment') {
            steps {
                
                   withCredentials([string(credentialsId: 'MINIKUBE', variable: 'api_token')]) 
                   {
                      sh 'kubectl --token $api_token --server https://192.168.64.13:8443  --insecure-skip-tls-verify=true apply -f kubernetes/back-end/epenal-backend-deployment.yaml '
                    }
                
            }
        }
    }

    post {
        always {
            // Clean up workspace
            cleanWs()
        }
    }
}
