<c:import url="/WEB-INF/template/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="资源管理" />
	<c:param name="resCode" value="system:manage" />
	<c:param name="url" value="/security/resource/list" />
	<c:param name="navigation" value="资源管理" />
	<c:param name="body">
		<p>
			<a href='${pageContext.request.contextPath}/security/resource/create' class="btn btn-info">CREATE</a>
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
					<tr <c:if test="${resource.href eq '/'}">class="success"</c:if>>
						<td>${resource.text}</td>
						<td>
							<a class="btn btn-primary btn-xs" href='${pageContext.request.contextPath}/security/resource/edit/${role.id}'>edit</a>&nbsp;&nbsp; 
							<button onclick="deleteResource('${resource.id}', '${resource.name}')" class="btn btn-primary btn-xs">delete</button>
						</td>
						<td>${resource.resCode}</td>
						<td>${resource.href}</td>
					<tr>
				</c:forEach>
			</table>
		</div>
		
		<script type="text/javascript">
			function deleteResource(id, name) {
				bootbox.confirm("确定删除资源<font color='red'>" + name + "</font>?", function(result) {
					if (result == true) {
						window.location.href = '${pageContext.request.contextPath}/security/resource/delete/' + id;
					}
				});
			}
		</script>
	</c:param>
</c:import>

