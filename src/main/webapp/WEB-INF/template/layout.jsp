<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>${param.title}</title>

    <!-- Bootstrap -->
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet" />
    <link href="<c:url value="/resources/bootstrap/css/sb-admin.css" />" rel="stylesheet" />
    <link href="<c:url value="/resources/bootstrap/font-awesome/css/font-awesome.min.css" />" rel="stylesheet" />

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

	<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
</head>
<body>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/home">Spirit</a>
			</div>
			<!-- Top Menu Items -->
			<ul class="nav navbar-right top-nav">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b class="caret"></b></a>
					<ul class="dropdown-menu message-dropdown">
						<li class="message-preview">
							<a href="#">
								<div class="media">
									<span class="pull-left">
										<img class="media-object" src="" alt="">
									</span>
									<div class="media-body">
										<h5 class="media-heading"><strong>John Smith</strong></h5>
										<p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
										<p>Lorem ipsum dolor sit amet, consectetur...</p>
									</div>
								</div>
							</a>
						</li>
						<li class="message-preview">
							<a href="#">
								<div class="media">
									<span class="pull-left">
										<img class="media-object" src="" alt="">
									</span>
									<div class="media-body">
										<h5 class="media-heading"><strong>John Smith</strong>
										</h5>
										<p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
										<p>Lorem ipsum dolor sit amet, consectetur...</p>
									</div>
								</div>
							</a>
						</li>
						<li class="message-preview">
							<a href="#">
								<div class="media">
									<span class="pull-left">
										<img class="media-object" src="" alt="">
									</span>
									<div class="media-body">
										<h5 class="media-heading"><strong>John Smith</strong>
										</h5>
										<p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
										<p>Lorem ipsum dolor sit amet, consectetur...</p>
									</div>
								</div>
							</a>
						</li>
						<li class="message-footer">
							<a href="#">Read All New Messages</a>
						</li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i> <b class="caret"></b></a>
					<ul class="dropdown-menu alert-dropdown">
						<li>
							<a href="#">Alert Name <span class="label label-default">Alert Badge</span></a>
						</li>
						<li>
							<a href="#">Alert Name <span class="label label-primary">Alert Badge</span></a>
						</li>
						<li>
							<a href="#">Alert Name <span class="label label-success">Alert Badge</span></a>
						</li>
						<li>
							<a href="#">Alert Name <span class="label label-info">Alert Badge</span></a>
						</li>
						<li>
							<a href="#">Alert Name <span class="label label-warning">Alert Badge</span></a>
						</li>
						<li>
							<a href="#">Alert Name <span class="label label-danger">Alert Badge</span></a>
						</li>
						<li class="divider"></li>
						<li>
							<a href="#">View All</a>
						</li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> John Smith <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li>
							<a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
						</li>
						<li>
							<a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
						</li>
						<li>
							<a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
						</li>
						<li class="divider"></li>
						<li>
							<a href="${pageContext.request.contextPath}/logout"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
						</li>
					</ul>
				</li>
			</ul>
			<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav side-nav">
					<li class="active">
						<a href="${pageContext.request.contextPath}/home"><i class="fa fa-fw fa-dashboard"></i> Home</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/person/list"><i class="fa fa-fw fa-dashboard"></i> Person</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/security/user/list"><i class="fa fa-fw fa-dashboard"></i> User</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/security/role/list"><i class="fa fa-fw fa-dashboard"></i> Role</a>
					</li>
					<li>
						<a href="charts.html"><i class="fa fa-fw fa-bar-chart-o"></i> Charts</a>
					</li>
					<li>
						<a href="tables.html"><i class="fa fa-fw fa-table"></i> Tables</a>
					</li>
					<li>
						<a href="forms.html"><i class="fa fa-fw fa-edit"></i> Forms</a>
					</li>
					<li>
						<a href="bootstrap-elements.html"><i class="fa fa-fw fa-desktop"></i> Bootstrap Elements</a>
					</li>
					<li>
						<a href="bootstrap-grid.html"><i class="fa fa-fw fa-wrench"></i> Bootstrap Grid</a>
					</li>
					<li>
						<a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-arrows-v"></i> Dropdown <i class="fa fa-fw fa-caret-down"></i></a>
						<ul id="demo" class="collapse">
							<li>
								<a href="#">Dropdown Item</a>
							</li>
							<li>
								<a href="#">Dropdown Item</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</nav>
	
		<div id="page-wrapper">
			<div class="container-fluid">
				<c:if test="${param.navigation ne null}">
					<!-- Page Heading -->
			        <div class="row">
			            <div class="col-lg-12">
			                <h1 class="page-header">
			                    ${param.navigation}
			                </h1>
			                <ol class="breadcrumb">
			                    <li>
			                        <i class="fa fa-dashboard"></i><a href="${pageContext.request.contextPath}/home">Home</a>
			                    </li>
			                    <li class="active">
			                        <i class="fa fa-bar-chart-o"></i> ${param.navigation}
			                    </li>
			                </ol>
			            </div>
			        </div>
				</c:if>
				${param.body}
			</div>
		</div>
		<!-- /#page-wrapper -->
	
	</div>
	<!-- /#wrapper -->
	
</body>
</html>