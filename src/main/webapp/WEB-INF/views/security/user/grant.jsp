<c:import url="/WEB-INF/template/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="用户授权" />
	<c:param name="resCode" value="system:manage" />
	<c:param name="url" value="/security/user/list" />
	<c:param name="navigation" value="用户管理" />
	<c:param name="body">

		<script type="text/javascript">
			$(document).ready(function() {
		        
			});
		</script>
		<h3 class="page-header" style="margin:0 auto;max-width: 600px;">给用户 ${user.name} 分配角色</h3>
		<form class="form-horizontal" role="form" action="" method="post">
			<div align="left" style="max-width: 600px; margin-right: auto; margin-left: auto;">
				<div class="form-group">
					<label class="col-sm-2 control-label">选择角色</label>
					<div class="col-sm-10">
						<c:forEach items="${roles}" var="role">
							<div class="checkbox">
								<label>
									<input type="checkbox" name="roleId" value="${role.id}"
										<c:if test="${f:contains(user.roles, role)}">checked="checked"</c:if>>
									${role.name}
								</label>
							</div>
						</c:forEach>
					</div>
				</div>
				<input type="text" name="id" hidden="true" value="${user.id}"/>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<input type="submit" value="提交" class="btn btn-primary" /> &nbsp;
						<input type="reset" value="重置"	class="btn btn-default" />&nbsp;
						<a href='${pageContext.request.contextPath}/security/user/list' class="btn">返回</a>
					</div>
				</div>
			</div>
		</form>
		
		<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap-treeview.min.js" />"></script>
	</c:param>
</c:import>

