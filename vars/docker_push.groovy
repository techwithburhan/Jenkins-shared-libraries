def call(Map config = [:]) {

    def imageName     = config.imageName
    def tag           = config.tag ?: "latest"
    def credentialsId = config.credentialsId

    if (!imageName) {
        error "imageName is required!"
    }

    if (!credentialsId) {
        error "credentialsId is required!"
    }

    echo "Logging into DockerHub..."
    echo "Pushing Image: ${imageName}:${tag}"

    withCredentials([usernamePassword(
        credentialsId: credentialsId,
        usernameVariable: 'DOCKER_USER',
        passwordVariable: 'DOCKER_PASS'
    )]) {

        sh '''
            echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
        '''

        sh "docker push ${imageName}:${tag}"

        sh "docker logout"
    }
}
