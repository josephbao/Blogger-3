package blogger

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['permitAll'])
    def index(Integer max) {

        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model: [userInstanceCount: User.count(), currentUser: springSecurityService.currentUser]
    }

    @Secured(['permitAll'])
    def show(User userInstance) {
        def showPostButton = userInstance == springSecurityService.currentUser
        respond userInstance, model: [showPostButton: showPostButton]
    }

    @Secured(['permitAll'])
    def create() {
        respond new User(params)
    }

    @Transactional
    @Secured(['permitAll'])
    def save(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view: 'create'
            return
        }

        def userRole = Role.findByAuthority('ROLE_USER')
        if (!userRole) {
            throw new Exception("Unable to find ROLE_USER")
        }

        userInstance.save flush: true

        UserRole.create userInstance, userRole

        redirect(action:"index")

//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.created.message', args: [message(code: 'userInstance.label', default: 'User'), userInstance.id])
//                redirect userInstance
//            }
//            '*' { respond userInstance, [status: CREATED] }
//        }
    }

    def edit(User userInstance) {
        respond userInstance
    }

    @Secured('ROLE_USER')
    def loginRedirect() {
        redirect( action: index(100))
    }

    @Secured('ROLE_USER')
    def logout() {
         session.invalidate()
        redirect( action: index(100))
    }

    @Transactional
    def update(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view: 'edit'
            return
        }

        userInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*' { respond userInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(User userInstance) {

        if (userInstance == null) {
            notFound()
            return
        }

        userInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userInstance.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
