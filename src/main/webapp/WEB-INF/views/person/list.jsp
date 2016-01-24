<c:import url="/WEB-INF/template/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="PersonList" />
	<c:param name="navigation" value="Person" />
	<c:param name="body">
		<p>
			<a href='${pageContext.request.contextPath}/person/form/' class="btn btn-info">CREATE</a>
		</p>
		<table class="table table-hover table-condensed table-bordered" style="margin-bottom: 0px;">
			<tr>
				<th>ID</th>
				<th>NAME</th>
				<th>AGE</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${pagingPerson.content}" var="person">
				<tr>
					<td style="width: 400px;">${person.id}</td>
					<td>${person.name}</td>
					<td>${person.age}</td>
					<td>
						<a class="btn btn-primary btn-xs" href='${pageContext.request.contextPath}/person/edit/${person.id}'>edit</a>&nbsp;&nbsp; 
						<a class="btn btn-primary btn-xs" href='${pageContext.request.contextPath}/person/delete/${person.id}'>delete</a>
					</td>
				<tr>
			</c:forEach>
		</table>
		<div style="text-align:right;padding: 0 5px 0 0;">
			<jsp:include page="/WEB-INF/template/pagination.jsp"/>
		</div>
	</c:param>
</c:import>

