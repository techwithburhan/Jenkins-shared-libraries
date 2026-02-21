def call(String imageName, String tag, String buildContext = '.') {

    if (!imageName?.trim()) {
        error "Image name is required!"
    }

    if (!tag?.trim()) {
        tag = "latest"
    }

    echo "Building Docker Image: ${imageName}:${tag}"
    echo "Build Context: ${buildContext}"

    sh """
        docker build -t ${imageName}:${tag} ${buildContext}
    """
}
