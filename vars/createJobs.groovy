def call() {

   node {

    stage('Checkout') {
      deleteDir()
      sh """
      git clone https://github.com/tranquilitybase-io/tb-gcp.git -b issue-398---Jenkins-CasC
      """
    }

    stage("Create jobs") {
      jobDsl targets: 'tb-jenkins/jobs/*.groovy',
        removedJobAction: 'DELETE',
        removedViewAction: 'DELETE',
        lookupStrategy: 'SEED_JOB',
        additionalClasspath: [].join('\n'),
        additionalParameters: [repos: repos]
    }
  }
}
