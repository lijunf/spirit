<c:import url="/WEB-INF/template/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="客户管理" />
	<c:param name="permission" value="person:manage" />
	<c:param name="url" value="/person/list" />
	<c:param name="navigation" value="客户管理" />
	<c:param name="body">
		<shiro:hasPermission name="person:add">
		<p>
			<a href='${pageContext.request.contextPath}/person/create' class="btn btn-info">CREATE</a>
		</p>
		</shiro:hasPermission>
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
							<shiro:hasPermission name="person:edit">
								<a class="btn btn-primary btn-xs" href='${pageContext.request.contextPath}/person/edit/${person.id}'>edit</a>&nbsp; 
							</shiro:hasPermission>
							<shiro:hasPermission name="person:delete">
								<button onclick="deletePerson('${person.id}', '${person.name}')" class="btn btn-primary btn-xs">delete</button>
							</shiro:hasPermission>
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

