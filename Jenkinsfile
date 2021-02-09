pipeline {
    agent {
        label 'docker'
    }
    parameters {
            string(defaultValue: 'Spaces-1', description: '', name: 'SpaceId', trim: true)
            string(defaultValue: 'stitchedstamped', description: '', name: 'ProjectName', trim: true)
            string(defaultValue: 'Dev', description: '', name: 'EnvironmentName', trim: true)
            string(defaultValue: 'Octopus', description: '', name: 'ServerId', trim: true)
        }
    stages {
        stage("Env Variables") {
            steps {
                sh "printenv"
            }
        }
        stage('Add tools') {
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
        stage('deploy') {
            steps {
                octopusCreateRelease deployThisRelease: true, environment: "${EnvironmentName}", project: "${ProjectName}", releaseVersion: "1.0.${BUILD_NUMBER}", serverId: "${ServerId}", spaceId: "${SpaceId}", toolId: 'Default', waitForDeployment: true
            }
        }
    }
}
