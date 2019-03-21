pipeline {
    agent none
    stages {
        stage('Build Jar') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v $HOME/.m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn clean package -DHUB_HOST=http://localhost:4444/wd/hub -DBROWSER=chrome -Dcucumber.options="--tags @regression"'
            }
        }
        stage('Build Image') {
            steps {
                script {
                	app = docker.build("iamsethi/cucumber-selenium")
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