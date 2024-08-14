pipeline {
    agent none
    stages {
        stage('Setup') {
            steps {
                script {
                docker.image('alpine/git').inside(''){
                    withCredentials([gitUsernamePassword(credentialsId:'GIT_HUB_ACCESS', gitToolName:'git-tool')])
                        sh git clone https://github.com/mcruzt/edu-epenal-back-end.git
                    }

                }
            }
        }
    }
}