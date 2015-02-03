package blogger

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CommentController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Comment.list(params), model: [commentInstanceCount: Comment.count()]
    }

    @Secured("ROLE_USER")
    def show(Comment commentInstance) {
        respond commentInstance
    }

    @Secured("ROLE_USER")
    def create() {
        Post p = Post.get(params.id)
        if (p) {
            springSecurityService.currentUser
            def comment = new Comment(params)
            comment.post = p
            respond comment, model: [post: p]
        } else {
            respond 'error':"Invalid post id ${params.id}",status:403
        }
    }

    @Transactional
    @Secured("ROLE_USER")
    def save(Comment commentInstance) {
        if (commentInstance == null) {
            notFound()
            return
        }
        commentInstance.creationTime = new Date()
        commentInstance.post = Post.get(params.postId)
        if (!commentInstance.post) {
            notFound()
            return
        }
        println "Creating comment for ${commentInstance.post}"

        commentInstance.commentAuthor = springSecurityService.currentUser.username

        commentInstance.save flush: true

        def showPostButton = springSecurityService.currentUser == commentInstance.post.user

        render(view:"/post/show",  model: [postInstance: commentInstance.post, showPostButton: showPostButton])
    }

    def edit(Comment commentInstance) {
        respond commentInstance
    }

    @Transactional
    def update(Comment commentInstance) {
        if (commentInstance == null) {
            notFound()
            return
        }

        if (commentInstance.hasErrors()) {
            respond commentInstance.errors, view: 'edit'
            return
        }

        commentInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Comment.label', default: 'Comment'), commentInstance.id])
                redirect commentInstance
            }
            '*' { respond commentInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Comment commentInstance) {

        if (commentInstance == null) {
            notFound()
            return
        }

        commentInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Comment.label', default: 'Comment'), commentInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'commentInstance.label', default: 'Comment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
