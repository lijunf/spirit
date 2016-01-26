<c:import url="/WEB-INF/template/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="角色列表" />
	<c:param name="resCode" value="system:manage" />
	<c:param name="url" value="/security/role/list" />
	<c:param name="navigation" value="Role" />
	<c:param name="body">
		<p>
			<a href='${pageContext.request.contextPath}/security/role/form' class="btn btn-info">CREATE</a>
		</p>
		<div class="table-responsive">
			<table class="table table-hover table-condensed table-bordered" style="margin-bottom: 0px;">
				<tr>
					<th>名称</th>
					<th>操作</th>
					<th>资源</th>
				</tr>
				<c:forEach items="${roleList}" var="role">
					<tr>
						<td>${role.name}</td>
						<td>
							<a class="btn btn-primary btn-xs" href='${pageContext.request.contextPath}/security/role/edit/${role.id}'>edit</a>&nbsp;&nbsp; 
							<a class="btn btn-primary btn-xs" href='${pageContext.request.contextPath}/security/role/delete/${role.id}'>delete</a>
						</td>
						<td>${role.viewResources}</td>
					<tr>
				</c:forEach>
			</table>
		</div>
	</c:param>
</c:import>

