package blogger

import groovy.transform.ToString

@ToString
class Post {
    String content
    String teaser
    String title
    Date creationTime

    static belongsTo = [user: User]

    static hasMany = [comments: Comment]

    static constraints = {
        title maxSize: 50, minSize: 1
        teaser maxSize: 100
        content maxSize:  1255, nullable: false, minSize: 3

    }

}
