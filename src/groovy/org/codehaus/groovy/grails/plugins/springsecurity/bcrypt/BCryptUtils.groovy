package org.codehaus.groovy.grails.plugins.springsecurity.bcrypt

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

class BCryptUtils {

    /**
     * Retrieve the user customized log rounds number or use the default one.
     */
    static int getLogRounds() {
        CH.config.grails.plugins.springsecurity.bcrypt.logrounds ?: 10
    }
}

