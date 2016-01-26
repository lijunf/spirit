<c:import url="/WEB-INF/template/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="PersonList" />
	<c:param name="resCode" value="customer:manage" />
	<c:param name="url" value="/person/list" />
	<c:param name="navigation" value="Person" />
	<c:param name="body">
		<p>
			<a href='${pageContext.request.contextPath}/person/form/' class="btn btn-info">CREATE</a>
		</p>
		<div class="table-responsive">
			<table class="table table-hover table-condensed table-bordered" style="margin-bottom: 0px;">
				<tr>
					<th>ID</th>
					<th>Action</th> 
					<th>NAME</th>
					<th>AGE</th>
				</tr>
				<c:forEach items="${pagingPerson.content}" var="person">
					<tr>
						<td>${person.id}</td>
						<td>
							<a class="btn btn-primary btn-xs" href='${pageContext.request.contextPath}/person/edit/${person.id}'>edit</a>&nbsp;&nbsp; 
							<a class="btn btn-primary btn-xs" href='${pageContext.request.contextPath}/person/delete/${person.id}'>delete</a>
						</td>
						<td>${person.name}</td>
						<td>${person.age}</td>
					<tr>
				</c:forEach>
			</table>
		</div>
		<div style="text-align:right;padding: 0 5px 0 0;">
			<jsp:include page="/WEB-INF/template/pagination.jsp"/>
		</div>
	</c:param>
</c:import>

