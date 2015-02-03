import blogger.Post
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_blogger_post_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/post/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: postInstance, field: 'title', 'error'))
printHtmlPart(1)
invokeTag('message','g',7,['code':("post.title.label"),'default':("Title")],-1)
printHtmlPart(2)
invokeTag('textField','g',10,['name':("title"),'maxlength':("50"),'value':(postInstance?.title)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: postInstance, field: 'teaser', 'error'))
printHtmlPart(4)
invokeTag('message','g',16,['code':("post.teaser.label"),'default':("Teaser")],-1)
printHtmlPart(2)
invokeTag('textField','g',19,['name':("teaser"),'maxlength':("100"),'value':(postInstance?.teaser)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: postInstance, field: 'content', 'error'))
printHtmlPart(5)
invokeTag('message','g',25,['code':("post.content.label"),'default':("Content")],-1)
printHtmlPart(2)
invokeTag('textArea','g',28,['name':("content"),'cols':("40"),'rows':("5"),'maxlength':("1255"),'value':(postInstance?.content)],-1)
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1422145272000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
