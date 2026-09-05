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
        stage('Build Backend Docker Image') {
            steps {
                echo 'Building backend Docker image...'
        
                sh '''
                    docker build \
                        -t easystore-backend:${BUILD_NUMBER} \
                        ./stickers
                '''
            }
        }
        stage('Deploy Backend Container') {
            steps {
                echo 'Deploying backend container...'
        
                sh '''
                    docker stop easystore-backend || true
                    docker rm easystore-backend || true
        
                    docker run -d \
                        --name easystore-backend \
                        -p 8081:8080 \
                        easystore-backend:${BUILD_NUMBER}
                '''
            }
        }
        stage('Verify Backend') {
            steps {
                echo 'Verifying backend container...'
        
                sh '''
                    sleep 10
        
                    docker ps
        
                    docker logs --tail 50 easystore-backend
                '''
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

        stage('Build Frontend Docker Image') {
            steps {
                echo 'Building frontend Docker image...'
        
                sh '''
                    docker build \
                        -t easystore-frontend:${BUILD_NUMBER} \
                        ./eazystore-ui
                '''
            }
        }

        stage('Deploy Frontend Container') {
            steps {
                echo 'Deploying frontend container...'
        
                sh '''
                    docker stop easystore-frontend || true
                    docker rm easystore-frontend || true
        
                    docker run -d \
                        --name easystore-frontend \
                        -p 5173:80 \
                        easystore-frontend:${BUILD_NUMBER}
                '''
            }
        }
        stage('Verify Frontend') {
            steps {
                echo 'Verifying frontend container...'
        
                sh '''
                    sleep 5
                    docker ps
                    docker logs --tail 30 easystore-frontend
                '''
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
