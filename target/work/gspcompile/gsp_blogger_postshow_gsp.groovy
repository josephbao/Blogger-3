import blogger.Post
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_blogger_postshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/post/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'post.label', default: 'Post'))],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("default.show.label"),'args':([entityName])],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',11,['code':("default.link.skip.label"),'default':("Skip to content&hellip;")],-1)
printHtmlPart(5)
expressionOut.print(createLink(uri: '/'))
printHtmlPart(6)
invokeTag('message','g',14,['code':("default.home.label")],-1)
printHtmlPart(7)
if(true && (showPostButton)) {
printHtmlPart(8)
createClosureForHtmlPart(9, 3)
invokeTag('link','g',16,['controller':("post"),'action':("create")],3)
printHtmlPart(10)
}
printHtmlPart(11)
createClosureForHtmlPart(12, 2)
invokeTag('link','g',18,['controller':("comment"),'action':("create"),'id':(postInstance.id)],2)
printHtmlPart(13)
expressionOut.print(postInstance.title)
printHtmlPart(14)
expressionOut.print(postInstance.creationTime)
printHtmlPart(15)
if(true && (postInstance?.content)) {
printHtmlPart(16)
invokeTag('fieldValue','g',26,['bean':(postInstance),'field':("content")],-1)
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (postInstance?.comments)) {
printHtmlPart(19)
invokeTag('message','g',33,['code':("post.comments.label"),'default':("Comments")],-1)
printHtmlPart(20)
for( c in (postInstance.comments) ) {
printHtmlPart(21)
createTagBody(4, {->
expressionOut.print(c?.encodeAsHTML())
})
invokeTag('link','g',36,['controller':("comment"),'action':("show"),'id':(c.id)],4)
printHtmlPart(22)
}
printHtmlPart(23)
}
printHtmlPart(24)
})
invokeTag('captureBody','sitemesh',43,[:],1)
printHtmlPart(25)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1422204772000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
