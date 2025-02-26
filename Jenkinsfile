pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/liukkari/amir-26-2'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Code Coverage') {
            steps {
                bat 'mvn jacoco:report'
            }
        }
    }
}
