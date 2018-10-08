/**
 * http://usejsdoc.org/
 */
Date.prototype.toString = function() {
	var yy = this.getFullYear();
	var mm = this.getMonth() + 1;
	var dd = this.getDate();
	var hh = this.getHours();
	var MM = this.getMinutes();
	var ss = this.getSeconds();
	return [ yy, , '/', (mm > 9 ? '' : '0') + mm, '/', (dd > 9 ? '' : '0') + dd, ' ', (hh > 9 ? '' : '0') + hh, ':', (MM > 9 ? '' : '0') + MM, ':', (ss > 9 ? '' : '0') + ss ].join('');
}
var addLog = function(log) {
	var fulllog = $('#logs').val();
	fulllog += '[' + (new Date()).toString() + ']   ' + log + '\n';
	$('#logs').val(fulllog);
	$('#logs').scrollTop($('#logs')[0].scrollHeight);
}
var controller;

var load_controller = function() {
	try {
		controller = new Kollus.VideogatewayController({
			target_window : $('#player').get(0).contentWindow
		});
		controller.on('progress', function(percent, position, duration) {
			$('#prog_play').html(percent + '% (' + position + '/' + duration + ')');
			$('#prog_play').attr('aria-valuenow', percent);
			$('#prog_play').css('width', percent + '%');
			$('#cpTitle').html($('.vjs-caption-overlay-text').html());
		}).on('screenchange', function(screen) {
			var prev_screen = $('#fullscreen').val();
			$('#fullscreen').val(screen);
			addLog('Volume Change : ' + prev_screen + '-->' + screen);
		}).on('volumechange', function(volume) {
			var prev_volume = $('#volume').val();
			$('#volume').val(volume);
			addLog('Volume Change : ' + prev_volume + '-->' + volume);
		}).on('muted', function(muted) {
			addLog("Volume Muted : " + muted);
		}).on('play', function() {
			addLog('Play');
			$('#control_visible').val(controller.get_control_visibility());
			$('#fullscreen').val(controller.get_screen());
			$('#topmost').val(controller.get_topmost());
			$('#scalemode').val(controller.get_scalemode());
			$('#brightness').val(controller.get_brightness());
			$('#contrast').val(controller.get_contrast());
			$('#saturation').val(controller.get_saturation());
			
		}).on('pause', function() {
			addLog('Pause');
		}).on('done', function() {
			addLog('Done');
		}).on('ready', function() {
			addLog('Ready');
		}).on('loaded', function() {
			addLog('Loaded');
		}).on('jumpstepchange', function(jumpstep) {
			var prev_step = $('#step').val();
			$('#step').val(jumpstep);
			addLog('Jumpstep Change : ' + prev_step + '-->' + jumpstep);
		}).on('scalemode', function(scalemode) {
			var prev_scale = $('scalemode').val();
			$('scalemode').val(scalemode);
			addLog('Scalemode Change : ' + prev_scale + '-->' + scalemode);
		}).on('speedchange', function(speed) {
			var prev_speed = $('#speed').val();
			$('#speed').val(speed);
			addLog('Speed Change : ' + prev_speed + '-->' + speed);
		}).on('topmost', function(topmost) {
			var prev_topmost = $('#topmost').val()
			$('#topmost').val(topmost);
			addLog('Topmost Change : ' + prev_topmost + '-->' + topmost);
		}).on('error', function(error_code) {
			addLog('ERR_CD : ' + error_code);
		}).on('videosettingchange', function(videosetting) {
			var b = videosetting.brightness;
			var c = videosetting.contrast;
			var s = videosetting.saturation;
			var prev_videosetting = {};
			prev_videosetting.brightness = $('brightness').val();
			prev_videosetting.contrast = $('contrast').val();
			prev_videosetting.saturation = $('saturation').val();
			$('brightness').val(b);
			$('contrast').val(c);
			$('saturation').val(s);
			addLog('Video Setting Change : ' + JSON.stringify(prev_videosetting) + '-->' + JSON.stringify(videosetting));
		}).on('html5_video_supported', function(is_supported) {
		});

		$('#back').on('click', function() {
			controller.rw();
		});
		$('#start').on('click', function() {
			controller.play();
		});
		$('#pause').on('click', function() {
			controller.pause();
		});
		$('#forward').on('click', function() {
			controller.ff();
		});

		$('#s_up').on('click', function() {
			var speed = parseFloat($('#speed').val()) + 0.1;
			controller.set_speed(speed);
		});
		$('#s_down').on('click', function() {
			var speed = $('#speed').val() - 0.1;
			controller.set_speed(speed);
		});
		$('#step_get').on('click', function() {
			$('#step').val(controller.get_jumpstep());
		});
		$('#step_set').on('click', function() {
			controller.set_jumpstep(parseInt($('#step').val()));
		});
		$('#r_start').on('click', function() {
			controller.set_repeat_start();
		});
		$('#repeat').on('click', function() {
			controller.set_repeat_end();
		});
		$('#remove').on('click', function() {
			controller.unset_repeat();
		});
		$('#v_up').on('click', function() {
			var volume = parseInt($('#volume').val()) + 1;
			controller.set_volume(volume);
		});
		$('#v_down').on('click', function() {
			var volume = parseInt($('#volume').val()) - 1;
			controller.set_volume(volume);
		});
		$('#mute').on('click', function() {
			controller.mute();
		});
		$('#show_control').on('click', function(){
			controller.set_control_visibility(true);
			var control_visible = controller.get_control_visibility()
			$('#control_visible').val(control_visible);
			addLog('Control Visibility : ' + control_visible);
		});
		$('#hide_control').on('click', function(){
			controller.set_control_visibility(false);
			var control_visible = controller.get_control_visibility()
			$('#control_visible').val(control_visible);
			addLog('Control Visibility : ' + control_visible);
		});
		$('#btnF').on('click', function(){
			controller.set_screen();
		});
		$('#btnT').on('click', function(){
			controller.set_topmost();
		});
		$('#btnB').on('click', function(){
			var b = parseInt($('#brightness').val());
			controller.set_brightness(b);
		});
		$('#btnC').on('click', function(){
			var c = parseInt($('#contrast').val());
			controller.set_contrast(c);
		});
		$('#btnS').on('click', function(){
			var s = parseInt($('#saturation').val());
			controller.set_saturation(s);
		});
		$('#div_scalemode > span > button').on('click', function(){
			var scalemode = $(this).attr('id');
			controller.set_scalemode(scalemode);
		});
	} catch (e) {
		console.error(e);
	}
}

$(document).ready(function() {
	getChannel(function(res) {
		var cntChAll = 0;
		$('#sb_channel').html('');
		$.each(res, function(idx, item) {
			$('#sb_channel').append($('<option>', {
				value : item.key,
				text : item.name + '(' + item.count_of_media_contents + ')'
			}));
			cntChAll += item.count_of_media_contents;
		});
	});
	$('#sb_channel').change(function() {
		var channelKey = $('#sb_channel option:selected').val();
		var txtChannel = $('#sb_channel option:selected').text();
		var start = txtChannel.lastIndexOf('(');
		var end = txtChannel.indexOf(')', start + 1);
		var cntChannel = txtChannel.substring(start + 1, end);
		getChannelItem(channelKey, cntChannel, function(res) {
			var list_media = $('#ulChannel');
			list_media.html('');
			$.each(res, function(idx, item) {
				var liItem = '<li class="media" data-url="' + item.jwt_play_url + '">' + '<div class="media-left"><img class="media-object" src="' + item.poster_url + '"></div>' + '<div class="media-body">' + '<h4 class="media-heading">' + item.title + '</h4>' + '</div>' + '</li>';
				list_media.append(liItem);
			});
			pagination('ulChannel');
		});
	});
	$('#ulChannel').on('click', 'li.media', function() {
		$(this).parent().find('li.media').removeClass('selectedli');
		$(this).addClass('selectedli');
		$('#player').attr('src', $(this).attr('data-url'));
	});
	load_controller();
	$('#plyaer').on('load', function() {
		if (controller == undefined) {
			load_controller();
		}
	});
})