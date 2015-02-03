package blogger

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured('ROLE_USER')
class PostController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    def springSecurityService

    @Secured('ROLE_USER')
    def singlePage() {
        def user = springSecurityService.currentUser
        [user: user]
    }

    def index(Integer max) {
        println "\n\nPostController called!!! ${new Date()}"
        params.max = Math.min(max ?: 10, 100)
        respond Post.list(params), model:[postInstanceCount: Post.count()]
    }

    @Secured('permitAll')
    def show(Post postInstance) {
        if (postInstance) {
            def showPostButton = postInstance.user == springSecurityService.currentUser
            respond postInstance, model: [showPostButton: showPostButton]
        } else {
            response.sendError(404)
        }
    }

    @Secured("ROLE_USER")
    def create() {
//        def springSecurityService
//        Post p = new Post(user: springSecurityService.currentUser, )

        def post = new Post(params)
        println "BOB >>> ${post}"
        respond post
    }

    @Secured("ROLE_USER")
    @Transactional
    def save(Post postInstance) {
        println "BOB >>> ${postInstance}"
        if (postInstance == null) {
            notFound()
            return
        }

        postInstance.creationTime = new Date()
        postInstance.user = springSecurityService.currentUser

//        if (postInstance.hasErrors()) {
//            respond postInstance.errors, view:'create'
//            return
//        }


        postInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'postInstance.label', default: 'Post'), postInstance.id])
                redirect ((Post) postInstance)
            }
            '*' { respond postInstance, [status: CREATED] }
        }
    }

    def edit(Post postInstance) {
        respond postInstance
    }

    @Transactional
    def update(Post postInstance) {
        if (postInstance == null) {
            notFound()
            return
        }

        if (postInstance.hasErrors()) {
            respond postInstance.errors, view:'edit'
            return
        }

        postInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Post.label', default: 'Post'), postInstance.id])
                redirect postInstance
            }
            '*'{ respond postInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Post postInstance) {

        if (postInstance == null) {
            notFound()
            return
        }

        postInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Post.label', default: 'Post'), postInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'postInstance.label', default: 'Post'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
