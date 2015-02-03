<%@ page import="blogger.Post" %>


<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="post.title.label" default="Title" />
		
	</label>
	<g:textField name="title" maxlength="50" value="${postInstance?.title}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'teaser', 'error')} ">
	<label for="teaser">
		<g:message code="post.teaser.label" default="Teaser" />
		
	</label>
	<g:textField name="teaser" maxlength="100" value="${postInstance?.teaser}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'content', 'error')} ">
	<label for="content">
		<g:message code="post.content.label" default="Content" />
		
	</label>
	<g:textArea name="content" cols="40" rows="5" maxlength="1255" value="${postInstance?.content}"/>

</div>

