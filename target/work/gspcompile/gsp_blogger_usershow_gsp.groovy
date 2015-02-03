import blogger.User
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_blogger_usershow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/user/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
invokeTag('set','g',6,['var':("entityName"),'value':(message(code: 'user.label', default: 'User'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',7,['code':("default.show.label"),'args':([entityName])],-1)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('message','g',10,['code':("default.link.skip.label"),'default':("Skip to content&hellip;")],-1)
printHtmlPart(4)
expressionOut.print(createLink(uri: '/'))
printHtmlPart(5)
invokeTag('message','g',13,['code':("default.home.label")],-1)
printHtmlPart(6)
if(true && (showPostButton)) {
printHtmlPart(7)
createClosureForHtmlPart(8, 3)
invokeTag('link','g',15,['controller':("post"),'action':("create")],3)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (userInstance?.username)) {
printHtmlPart(11)
expressionOut.print(userInstance?.username)
printHtmlPart(12)
}
printHtmlPart(13)
if(true && (userInstance?.posts)) {
printHtmlPart(14)
for( p in (userInstance.posts) ) {
printHtmlPart(15)
createTagBody(4, {->
printHtmlPart(16)
expressionOut.print(p?.title)
printHtmlPart(17)
expressionOut.print(p?.teaser)
printHtmlPart(18)
})
invokeTag('link','g',35,['controller':("post"),'action':("show"),'id':(p.id)],4)
printHtmlPart(19)
}
printHtmlPart(20)
}
else {
printHtmlPart(21)
}
printHtmlPart(22)
})
invokeTag('captureBody','sitemesh',46,[:],1)
printHtmlPart(23)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1422204971000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
