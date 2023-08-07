pipeline {
    agent any

    stages {
        stage('Sanity Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/vineeth16081994/PomSeriesCode.git'
                    sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunner/testng_sanity.xml"
                }
            }
        }
    }
}
