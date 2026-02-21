def call(String imageName, String tag) {

    echo "Logging into DockerHub..."
    echo "Pushing ${imageName}:${tag}"

    withCredentials([usernamePassword(
        credentialsId: 'dockerCred',
        passwordVariable: 'dockerHubPass',
        usernameVariable: 'dockerHubUser'
    )]) {

        sh '''
            echo $dockerHubPass | docker login -u $dockerHubUser --password-stdin
        '''

        sh "docker push ${imageName}:${tag}"

        sh "docker logout"
    }
}
