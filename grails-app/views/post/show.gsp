<%@ page import="blogger.Post" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'post.label', default: 'Post')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-post" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<g:if test="${showPostButton}">
					<li><g:link controller="post" action="create">Create Blog Entry</g:link></li>
				</g:if>
				<li><g:link controller="comment" action="create" id="${postInstance.id}">Comment</g:link></li>
			</ul>
		</div>
		<div id="show-post" class="content scaffold-show" role="main">
			<h1>${postInstance.title}</h1>
			<div class="property-value" style="font-size: x-small; padding: 0 0.25em; margin: 0.3em 3em 0.3em">${postInstance?.user?.username}</div>
			<div class="property-value" style="font-size: x-small; padding: 0 0.25em; margin: 0.3em 3em 0.3em"><g:formatDate date="${postInstance.creationTime}" type="datetime" style="MEDIUM"/></div>
			<ol class="property-list post">
				<g:if test="${postInstance?.content}">
				<li class="fieldcontain">
						<span class="property-value" aria-labelledby="content-label"><g:fieldValue bean="${postInstance}" field="content"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${postInstance?.comments}">
					<li class="fieldcontain">
						<span id="comments-label" class="property-label"><g:message code="post.comments.label" default="Comments" /></span>

							<g:each in="${postInstance.comments}" var="c">
							<span class="property-value" aria-labelledby="comments-label"><g:link controller="comment" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
							</g:each>

					</li>
				</g:if>
			</ol>
		</div>
	</body>
</html>
