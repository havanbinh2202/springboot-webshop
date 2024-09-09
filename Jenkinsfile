pipeline {
    agent any
    stages{
        stage('Clone') {
            steps {
                git branch: 'main', url: 'https://github.com/havanbinh2202/btvnthymleaf.git'
            }
        }
        stage('Push Docker Hub') {
            steps {
                    // This step should not normally be used in your script. Consult the inline help for details.
                withDockerRegistry(credentialsId: 'docker-hub', url:'') {
                    // some block
                    // sh label: '', script: 'docker build -t binhha2202/btvnthymleaf .'
                    // sh label: '', script: 'docker push binhha2202/btvnthymleaf'

                    // sh label: '', script: 'docker push binhha2202/btvnthymleaf'
                    sh label: '', script: 'docker pull nginx '
                }
            }
        }
        stage('Push Docker Hub'){
            steps{
                withDockerRegistry(credentialsId: 'dockerhub', url: ''){

                    sh label: '', script: ''
                    sh label: '', script: ''
                }
            }
        }
    }
}