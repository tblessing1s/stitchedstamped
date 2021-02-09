pipeline {
    agent {
        label 'docker'
    }
    stages {
        stage ('Add tools') {
            steps {
                tool('OctoCLI')
            }
        }
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
                withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
                    sh '''
                           docker logout
                           docker login -u $USERNAME -p $PASSWORD
                           docker push "tblessin/stitchedstamped"
                      '''
                }
            }
        }
    }
}
