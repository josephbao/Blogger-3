package blogger

import groovy.transform.ToString

@ToString
class Comment {

    String comment
    String commentAuthor
    Date creationTime

    static belongsTo = [post: Post]
    static constraints = {
        comment minSize: 5, maxSize: 1024
    }
}
