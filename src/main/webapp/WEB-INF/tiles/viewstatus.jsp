<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lv"%>

<c:url var="url" value="/viewstatus" />

<div class="row">
	<div class="col-md-8 col-md-offset-2">

		<lv:pagination url="${url}" page="${page}" size="5"/>

		<c:forEach var="statusUpdate" items="${page.content}">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">
						Status update added on
						<fmt:formatDate pattern="EEEE d MMMM y 'at' HH:mm:ss"
							value="${statusUpdate.added}" />
					</div>
				</div>
				<div class="panel-body">
					<c:out value="${statusUpdate.text}" />
				</div>
			</div>
		</c:forEach>
	</div>
</div>