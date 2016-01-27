<c:import url="/WEB-INF/template/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="客户管理" />
	<c:param name="resCode" value="person:manage" />
	<c:param name="url" value="/person/list" />
	<c:param name="navigation" value="客户管理" />
	<c:param name="body">
		<p>
			<a href='${pageContext.request.contextPath}/person/create' class="btn btn-info">CREATE</a>
		</p>
		<div class="table-responsive">
			<table class="table table-hover table-condensed table-bordered" style="margin-bottom: 0px;">
				<tr>
					<th>ID</th>
					<th>Action</th> 
					<th>NAME</th>
					<th>AGE</th>
				</tr>
				<c:forEach items="${paging.content}" var="person">
					<tr>
						<td>${person.id}</td>
						<td>
							<a class="btn btn-primary btn-xs" href='${pageContext.request.contextPath}/person/edit/${person.id}'>edit</a>&nbsp;&nbsp; 
							<button onclick="deletePerson('${person.id}', '${person.name}')" class="btn btn-primary btn-xs">delete</button>
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
		
		<script type="text/javascript">
			function deletePerson(id, name) {
				bootbox.confirm("确定删除客户<font color='red'>" + name + "</font>?", function(result) {
					if (result == true) {
						window.location.href = '${pageContext.request.contextPath}/person/delete/' + id;
					}
				});
			}
		</script>
	</c:param>
</c:import>

