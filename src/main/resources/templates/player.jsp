<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="static/external/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="static/external/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="static/external/jPages/jPages.css" type="text/css" media="all">
<link rel="stylesheet" href="static/css/main.css">
<style type="text/css">
.tab-pane {
	padding-left: 5px;
}

.noPadding {
	padding: 0;
}

.input-group {
	padding-left: 3px;
	padding-right: 3px;
}

textarea {
	width: 100%;
	resize: none;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="row">
				<a href="/"> <span><h1>
							Kollus Example Page <small>for Jsp</small>
						</h1> </span></a>
			</div>
			<div class="row">
				<h4>Player Controller for javascript</h4>
			</div>
		</div>
		<div class="container-fluid">
			<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
				<div class="panel panel-default" id="pn_paylod">
					<div class="panel-heading" id="ph_paylod">
						<h4>Channel</h4>
					</div>
					<div class="panel-body" id="pb_paylod">
						<select class="form-control info" id="sb_channel"></select>
						<ul id="ulChannel" class="media-list">
						</ul>
						<div class="holder"></div>
					</div>
					<div class="panel-footer" id="pf_payload"></div>
				</div>
			</div>
			<div class="col-md-6 col-xs-6 col-sm-6 col-lg-6 col-md-offset-1 col-xs-offset-1 col-sm-offset-1 col-lg-offset-1">
				<div class="col-md-6 col-xs-6 col-sm-6 col-lg-6 col-md-offset-3 col-xs-offset-3 col-sm-offset-3 col-lg-offset-3">
					<div class="embed-responsive embed-responsive-4by3" style="border: 1px dotted #333;">
						<iframe id="player" class="embed-responsive-item" src=""> </iframe>
					</div>
					<div class="progress">
						<div id="prog_play" class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">0%</div>
					</div>
					<div id="cpTitle"></div>
				</div>
				<ul class="nav nav-tabs nav-pills nav-justified" id="nav_controller">
					<li role="presentation" class="active"><a href="#basic" data-toggle="tab">BASIC</a></li>
					<li role="presentation"><a href="#exclusive" data-toggle="tab">EXCLUSIVE</a></li>
					<li role="presentation"><a href="#flash" data-toggle="tab">FLASH</a></li>
					<li role="presentation"><a href="#etc" data-toggle="tab">ETC</a></li>
					<li role="presentation"><a href="#log" data-toggle="tab">LOG</a></li>
				</ul>
				<div id="tabContent" class="tab-content">
					<div id="basic" class="tab-pane fade active in">
						<div class="row">
							<div class="col-md-4 col-xs-4 col-sm-4 col-lg-4 noPadding">
								<h5>Play</h5>
								<div class="btn-group" id="g_playback">
									<button id="back" class="btn btn-default">
										<span class="glyphicon glyphicon-fast-backward"></span>
									</button>
									<button id="start" class="btn btn-default">
										<span class="glyphicon glyphicon-play"></span>
									</button>
									<button id="pause" class="btn btn-default">
										<span class="glyphicon glyphicon-pause"></span>
									</button>
									<button id="forward" class="btn btn-default">
										<span class="glyphicon glyphicon-fast-forward"></span>
									</button>
								</div>
							</div>
							<div class="col-md-4 col-xs-4 col-sm-4 col-lg-4 noPadding">
								<h5>Speed</h5>
								<div class="input-group" id="g_speed">
									<span class="input-group-btn">
										<button type="button" id="s_up" class="btn btn-default">
											<span class="glyphicon glyphicon-chevron-up"></span>
										</button>
									</span> <span class="input-group-btn">
										<button type="button" id="s_down" class="btn btn-default">
											<span class="glyphicon glyphicon-chevron-down"></span>
										</button>
									</span> <input type="number" id="speed" class="form-control" value="1.0" step="0.1" min="0.0" max="4.0" disabled />
								</div>
							</div>
							<div class="col-md-4 col-xs-4 col-sm-4 col-lg-4 noPadding">
								<h5>Step</h5>
								<div class="input-group" id="g_step">
									<input type="number" id="step" class="form-control" value="5" step="10" min="0" /> <span class="input-group-btn">
										<button type="button" id="step_get" class="btn btn-default">Get</button>
									</span> <span class="input-group-btn">
										<button type="button" id="step_set" class="btn btn-default">Set</button>
									</span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4 col-xs-4 col-sm-4 col-lg-4 noPadding">
								<h5>Repeat</h5>
								<div class="btn-group" id="g_repeat">
									<button id="r_start" class="btn btn-default">
										<span class="glyphicon glyphicon-triangle-bottom"></span>
									</button>
									<button id="repeat" class="btn btn-default">
										<span class="glyphicon glyphicon-repeat"></span>
									</button>
									<button id="remove" class="btn btn-default">
										<span class="glyphicon glyphicon-remove"></span>
									</button>
								</div>
							</div>
							<div class="col-md-4 col-xs-4 col-sm-4 col-lg-4 noPadding">
								<h5>Volume</h5>
								<div class="input-group" id="g_volume">
									<span class="input-group-btn">
										<button type="button" id="v_up" class="btn btn-default">
											<span class="glyphicon glyphicon-volume-up"></span>
										</button>
									</span> <span class="input-group-btn">
										<button type="button" id="v_down" class="btn btn-default">
											<span class="glyphicon glyphicon-volume-down"></span>
										</button>
									</span> <input type="number" id="volume" class="form-control" value="50" step="0" min="0" max="100" disabled /> <span class="input-group-btn">
										<button type="button" id="mute" class="btn btn-default">
											<span class="glyphicon glyphicon-volume-off"></span>
										</button>
									</span>
								</div>
							</div>
							<div class="col-md-4 col-xs-4 col-sm-4 col-lg-4 noPadding">
								<h5>Control Visiblity</h5>
								<div class="input-group" id="g_control_visible">
									<span class="input-group-btn">
										<button type="button" id="show_control" class="btn btn-default">
											<span class="glyphicon glyphicon-collapse-up"></span>
										</button>
									</span> <span class="input-group-btn">
										<button type="button" id="hide_control" class="btn btn-default">
											<span class="glyphicon glyphicon-collapse-down"></span>
										</button>
									</span> <input type="text" id="control_visible" class="form-control" disabled />
								</div>
							</div>
						</div>
					</div>
					<div id="exclusive" class="tab-pane fade ">
						<div class="row">
							<div class="col-md-6 col-xs-6 col-sm-6 col-lg-6 noPadding">
								<h5>Screen</h5>
								<div class="col-md-6 col-xs-6 col-sm-6 col-lg-6 noPadding">
									<div class="input-group">
										<span class="input-group-btn">
											<button id="btnF" type="button" class="btn btn-default">
												<span class="glyphicon glyphicon-fullscreen"></span>
											</button>
										</span> <input id="fullscreen" type="button" class="form-control" disabled />
									</div>
								</div>
								<div class="col-md-6 col-xs-6 col-sm-6 col-lg-6 noPadding">
									<div class="input-group">
										<span class="input-group-btn">
											<button id="btnT" type="button" class="btn btn-default">
												<span class="glyphicon glyphicon-object-align-bottom"></span>
											</button>
										</span> <input id="topmost" type="text" class="form-control" disabled />
									</div>
								</div>
							</div>
							<div class="col-md-6 col-xs-6 col-sm-6 col-lg-6 noPadding">
								<h5>Video Setting</h5>
								<div class="col-md-4 col-xs-4 col-sm-4 col-lg-4 noPadding">
									<div class="input-group">
										<span class="input-group-addon">B</span> <input type="text" class="form-control" id="brightness" /> <span class="input-group-btn">
											<button type="button" class="btn btn-default" id="btnB">
												<span class="glyphicon glyphicon-ok"></span>
											</button>
										</span>
									</div>
								</div>
								<div class="col-md-4 col-xs-4 col-sm-4 col-lg-4 noPadding">
									<div class="input-group">
										<span class="input-group-addon">C</span> <input type="text" class="form-control" id="contrast" /> <span class="input-group-btn">
											<button type="button" class="btn btn-default" id="btnC">
												<span class="glyphicon glyphicon-ok"></span>
											</button>
										</span>
									</div>
								</div>
								<div class="col-md-4 col-xs-4 col-sm-4 col-lg-4 noPadding">
									<div class="input-group">
										<span class="input-group-addon">S</span> <input type="text" class="form-control" id="saturation" /> <span class="input-group-btn">
											<button type="button" class="btn btn-default" id="btnS">
												<span class="glyphicon glyphicon-ok"></span>
											</button>
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div id="flash" class="tab-pane fade ">
						<div class="row">
							<h5>Scalemode</h5>
							<div class="input-group" id="div_scalemode">
								<span class="input-group-btn">
									<button id="letterbox" type="button" class="btn btn-default">letterbox</button>
								</span> <span class="input-group-btn">
									<button id="strech" type="button" class="btn btn-default">strech</button>
								</span> <span class="input-group-btn">
									<button id="zoom" type="button" class="btn btn-default">zoom</button>
								</span> <span class="input-group-btn">
									<button id="none" type="button" class="btn btn-default">none</button>
								</span> <input id="scalemode" type="text" class="form-control" disabled />
							</div>
						</div>
					</div>
					<div id="etc" class="tab-pane fade "></div>
					<div id="log" class="tab-pane fade ">
						<textarea id="logs" rows="7"></textarea>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="static/external/jquery/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="static/external/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="static/external/jPages/jPages.js"></script>
	<script type="text/javascript" src="static/external/kollus/kollus.videogateway-controller.min.js"></script>
	<script type="text/javascript" src="static/js/common.js"></script>
	<script type="text/javascript" src="static/js/player.js"></script>
</body>
</html>