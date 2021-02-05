pipeline {
    agent {
//         label 'docker'
        dockerfile{
            dir 'src'
            filename 'Dockerfile.sdk'
        }
    }
    stages {
            stage('Building our image') {
                steps {
                    script {
                        dockerImage = docker.build "tblessin/stitchedstamped:latest"
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
