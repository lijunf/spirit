<c:import url="/WEB-INF/template/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="资源列表" />
	<c:param name="resCode" value="system:manage" />
	<c:param name="url" value="/security/resource/list" />
	<c:param name="navigation" value="Resource" />
	<c:param name="body">
		<p>
			<a href='${pageContext.request.contextPath}/security/resource/form' class="btn btn-info">CREATE</a>
		</p>
		<div class="table-responsive">
			<table class="table table-hover table-condensed table-bordered" style="margin-bottom: 0px;">
				<tr>
					<th>名称</th>
					<th>操作</th>
					<th>权限</th>
					<th>地址</th>
				</tr>
				<c:forEach items="${resourceList}" var="resource">
					<tr>
						<td>${resource.name}</td>
						<td>
							<a class="btn btn-primary btn-xs" href='${pageContext.request.contextPath}/security/resource/edit/${role.id}'>edit</a>&nbsp;&nbsp; 
							<a class="btn btn-primary btn-xs" href='${pageContext.request.contextPath}/security/resource/delete/${role.id}'>delete</a>
						</td>
						<td>${resource.resCode}</td>
						<td>${resource.href}</td>
					<tr>
				</c:forEach>
			</table>
		</div>
	</c:param>
</c:import>

