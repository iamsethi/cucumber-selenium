pipeline {
    agent none
    stages {
        stage('Build Jar') {
            agent {
                docker {
                    image 'maven:3-alpine'
                	args '-v $HOME/.m2:/root/.m2:z -u root'
          		  	reuseNode true
                }
            }
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Build Image') {
            steps {
                script {
                	app = docker.build("iamsethi/cucumber-selenium-docker")
                }
            }
        }
        stage('Push Image') {
            steps {
                script {
			        docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
			        	app.push("${BUILD_NUMBER}")
			            app.push("latest")
			        }
                }
            }
        }
    }
}