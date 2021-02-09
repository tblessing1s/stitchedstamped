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
                            script {
                                // Assume the Docker Hub registry by passing an empty string as the first parameter
                                sh '''
                                 docker logout
                                 docker login -u tblessin -p Cardinals3123!
                                 docker push "tblessin/stitchedstamped"
                                '''
                                }
                            }
                        }
                    }
            }
}
