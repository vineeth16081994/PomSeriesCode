pipeline {
    agent any
    tools { 
      maven 'maven'
      jdk 'jdk'
    }

    stages {
        stage('Sanity Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/vineeth16081994/PomSeriesCode.git'
                    sh "mvn clean install"
                }
            }
        }
    }
}
