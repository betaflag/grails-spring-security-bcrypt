class SpringSecurityBcryptGrailsPlugin {
    def version = "0.1"
    def grailsVersion = "1.2.3 > *"
    def dependsOn = [springSecurityCore: '1.1 > *']

    def author = "Nicolas Lupien"
    def authorEmail = ""
    def title = "Spring Security Blowfish Encryption (bcrypt)"
    def description = '''\\
This plugin let you use the bcrypt file encryption algorithm
to hash your users passwords. You can customize the computation cost
of the algorithm to get stronger hashes.
'''

    def documentation = "http://grails.org/plugin/spring-security-bcrypt"
}
