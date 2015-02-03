
<%@ page import="blogger.User" %>
<!DOCTYPE html>
<html>
    <!-- LANDING PAGE -->
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link action="create">Create Account</g:link></li>
				<g:if test="${currentUser}">
					Welcome ${currentUser.username}!
					<li><g:link action="logout">Logout</g:link></li>
				</g:if>
				<g:else>
					<li><g:link action="loginRedirect">LogIn</g:link></li>
				</g:else>
			</ul>
		</div>
		<div id="list-user" class="content scaffold-list" role="main">
			<!-- <h1><g:message code="default.list.label" args="[entityName]" /></h1> -->
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="username" title="Blogs" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${userInstanceList}" status="i" var="userInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link action="show" id="${userInstance.id}">${fieldValue(bean: userInstance, field: "username")}</g:link></td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${userInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
