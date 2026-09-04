pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }

        stage('Build Backend') {
            steps {
                echo 'Building Spring Boot backend...'

                dir('stickers') {
                    sh '''
                        if [ -f "./mvnw" ]; then
                            chmod +x mvnw
                            ./mvnw clean package -DskipTests
                        elif [ -f "pom.xml" ]; then
                            mvn clean package -DskipTests
                        else
                            echo "ERROR: pom.xml or mvnw not found"
                            exit 1
                        fi
                    '''
                }
            }
        }

        stage('Build Frontend') {
            steps {
                echo 'Building React frontend...'

                dir('eazystore-ui') {
                    sh '''
                        if [ -f "package-lock.json" ]; then
                            npm ci
                        else
                            npm install
                        fi

                        npm run build
                    '''
                }
            }
        }

        stage('Build Successful') {
            steps {
                echo '========================================='
                echo 'Frontend and Backend built successfully!'
                echo '========================================='
            }
        }
    }

    post {
        success {
            echo 'Jenkins pipeline completed successfully!'
        }

        failure {
            echo 'Jenkins pipeline failed. Check the console output.'
        }
    }
}
