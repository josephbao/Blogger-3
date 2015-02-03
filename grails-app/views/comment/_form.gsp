<%@ page import="blogger.Comment" %>



<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'comment', 'error')} ">
	<label for="comment">
		<g:message code="comment.comment.label" default="Comment" />
		
	</label>
	<g:hiddenField name="postId" value="${commentInstance?.post?.id}" />
	<g:textArea rows="10" cols="60" name="comment" value="${commentInstance?.comment}"/>

</div>

