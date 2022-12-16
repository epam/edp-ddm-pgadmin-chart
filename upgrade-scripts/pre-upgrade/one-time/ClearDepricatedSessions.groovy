void call() {

    String replicas = sh(script: "oc get deployment pgadmin-deployment -o jsonpath='{.spec.replicas}' -n $NAMESPACE", returnStdout: true).trim()

    // Reload pgAdmin for remove deprecated sessions
    sh "oc scale deployment/pgadmin-deployment --replicas=0 -n $NAMESPACE || true"
    sh "oc scale deployment/pgadmin-deployment --replicas=${replicas} -n $NAMESPACE || true"

}

return this;