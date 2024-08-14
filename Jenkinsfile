pipeline {
    agent any

    environment {
        // Define environment variables
        GITHUB_REPO = 'https://github.com/mcruzt/edu-epenal-back-end.git'
        DOCKER_IMAGE = 'mcruzt/epenal-backend:latest'
        K8S_NAMESPACE = 'default'
        K8S_DEPLOYMENT = 'epenal-backend'
        ENV_PROFILE = 'pr'
    }

    stages {
        stage('Setup') {
            steps {
                // Checkout code from GitHub
                git url: "${GITHUB_REPO}", branch: 'master'
            }
        }

        stage('Build') {
            steps {
                // Build the project using Gradle
                sh './gradlew clean build'
            }
        }

        stage('SonarQube Analysis') {
            environment {
                // SonarQube environment variables
                SONARQUBE_URL = 'http://localhost:9003'
                SONARQUBE_TOKEN = credentials('SONAR_ACCESS')
            }
            steps {
                // Run SonarQube analysis
                sh './gradlew sonar -Dsonar.host.url=${SONARQUBE_URL} -Dsonar.login=${SONARQUBE_TOKEN}'
            }
        }

        stage('Dockerization') {
            steps {
                script {
                    // Build Docker image
                    docker.build("${DOCKER_IMAGE}")
                }
            }
        }

        stage('Kubernetes Deployment') {
            steps {
                script {
                    // Push Docker image to repository
                    docker.withRegistry('https://your-docker-repo', 'docker-credentials-id') {
                        docker.image("${DOCKER_IMAGE}").push()
                    }

                    // Deploy to Kubernetes
                    sh """
                    kubectl set image deployment/${K8S_DEPLOYMENT} ${K8S_DEPLOYMENT}=${DOCKER_IMAGE} --namespace=${K8S_NAMESPACE}
                    kubectl rollout status deployment/${K8S_DEPLOYMENT} --namespace=${K8S_NAMESPACE}
                    """
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
