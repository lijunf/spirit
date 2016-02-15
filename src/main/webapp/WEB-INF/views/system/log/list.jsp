<c:import url="/WEB-INF/template/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="系统参数管理" />
	<c:param name="permission" value="system:manage" /><%-- 当前页面资源上级资源权限 --%>
	<c:param name="url" value="/system/log/list" /><%-- 当前页面所属资源url地址，list和form一致 --%>
	<c:param name="navigation" value="系统日志查询" /><%-- 当前页面导航名称 --%>
	<c:param name="body">
		<div class="table-responsive">
			<table class="table table-hover table-condensed table-bordered" style="margin-bottom: 0px;">
				<tr>
					<th>级别</th>
					<th>用户</th>
					<th>信息</th>
					<th>时间</th>
					<th>IP</th>
				</tr>
				<c:forEach items="${paging.content}" var="log">
					<tr>
						<td>${log.priority}</td>
						<td>${log.loginId}</td>
						<td>${log.msg}</td>
						<td>${log.logDate}</td>
						<td>${log.ipAddr}</td>
					<tr>
				</c:forEach>
			</table>
		</div>
		<div class="pagination-wrapper">
			<jsp:include page="/WEB-INF/template/pagination.jsp"/>
		</div>
	</c:param>
</c:import>