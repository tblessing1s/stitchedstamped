pipeline {
    agent any
    stages {
            stage('Building our image') {
                steps {
                    script {
                        sh '''
                            ./gradlew build jibDockerBuild
                        '''
                    }
                }
            }
            stage('Deploy our image') {
                        steps {
                            script {
                                // Assume the Docker Hub registry by passing an empty string as the first parameter
                                docker.withRegistry('' , 'dockerhub') {
                                    dockerImage.push()
                                }
                            }
                        }
                    }
            }
}
