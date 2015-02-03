<%@ page import="blogger.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<g:if test="${showPostButton}">
					<li><g:link controller="post" action="create">Create Blog Entry</g:link></li>
				</g:if>
			</ul>
		</div>
		<div id="show-user" class="content scaffold-show" role="main">
			<ol class="property-list user">
			
				<g:if test="${userInstance?.username}">
					<h1>${userInstance?.username.capitalize()}'s Blog</h1>
				</g:if>
			
				<g:if test="${userInstance?.posts}">
				<li class="fieldcontain">
					<span id="posts-label" class="property-label"></span>
					
						<g:each in="${userInstance.posts}" var="p">
						<div class="property-value" aria-labelledby="posts-label">
							<g:link controller="post" action="show" id="${p.id}">
								<div>${p?.title}</div>
							</g:link></div>
							<div class="property-value" style="font-size: x-small">${p?.teaser}</div>
							<div class="property-value" style="font-size: x-small">Posted on: <g:formatDate date="${p?.creationTime}" type="datetime" style="SMALL"/></div>
							<hr style="color: #9e9e9e" class="property-value" />
						</g:each>
					
				</li>
				</g:if>
				<g:else>
					<h4 style="margin: 0.8em 3em 0.3em">No blog entries</h4>
				</g:else>
			
			</ol>
		</div>
	</body>
</html>
