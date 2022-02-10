#!/usr/bin/env groovy
    node {

      stage('Initialise') {
        /* Checkout the scripts */
        checkout scm: [
                $class: 'GitSCM',
                userRemoteConfigs: [
                        [
                                url: "https://github.com/LipMik/JMETER.git",
                                credentialsId: "LipMik"
                        ]
                ],
                branches: [[name: "main"]]
        ], poll: false
      }

      stage('Complete any setup steps') {
		echo "Complete set-up steps"
      }

      stage('Execute Performance Tests') {
        dir("${WORKSPACE}/script") {
			bat 'C:\Users\Lenovo\Downloads\apache-jmeter-5.4.3\bin\jmeter -j jmeter.save.saveservice.output_format=xml -n -t C:\Users\Lenovo\Downloads\apache-jmeter-5.4.3\bin\postgre_tests.jmx -l C:\Users\Lenovo\Downloads\apache-jmeter-5.4.3\bin\report.jtl'
        }
      }

      stage('Analyse Results') {
		echo "Analyse results"
      }
    }