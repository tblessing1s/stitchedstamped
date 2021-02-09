pipeline {
    agent {
        label 'docker'
    }
    stages {
        stage('Building our image') {
            steps {
                script {
                    sh '''
                            ./gradlew clean
                            ./gradlew build jibDockerBuild --image=tblessin/stitchedstamped
                        '''
                }
            }
        }
        stage('Deploy our image') {
            steps {
                // Assume the Docker Hub registry by passing an empty string as the first parameter
                withCredentials([
                        usernamePassword(
                                credentialsId: 'dockerhub',
                                usernameVariable: 'username',
                                passwordVariable: 'password'
                        )
                ]) {
                    sh '''
                           docker logout
                           docker login -u $username -p $password
                           docker push "tblessin/stitchedstamped"
                      '''
                }
            }
        }
    }
}
