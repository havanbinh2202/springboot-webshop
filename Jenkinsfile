pipeline {
    agent any
    stages{
        stage('Clone') {
            steps {
                git branch: 'main', credentialsId: '17cc180e-b758-4642-92e3-9a54744e5ca4', url: 'https://github.com/havanbinh2202/btvnthymleaf.git'
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