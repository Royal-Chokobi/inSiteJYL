//***********************************
// Kollus Player V3 Module - agent.agentcheck.js 
//***********************************

$(document).ready(function() {
	//최초에 agentCheck 메세지를 보여준다.
	checkAlert();

	 //AgentCheck Process실행.
	try{
		var agentcheck = new Agentcheck();

		//Option값은 agent.common.js에 설정되어 있는 값일 기본으로 가져온다.
		var opt = {
			//LocalHost URL (http://~~~~~:port)
			agentURL: agent_url,

			//Version Check API URL
			versionCheckURL: agentVersionCheckURL,

			//Update URL
			updateURL: agentUpdateURL,

			//EXE file Download URL
			installURL: agentInstallURL,

			//Execute KollusPlayer URL (kollus://~)
			executeURL: agentExecuteURL,

			//정상적으로 Agent가 실행되었을 경우 실행함수
			runningFn: agentCheckComplete,

			//업데이트가 있을 경우 실행함수
			updateFn: updateAlert,

			//인스톨(다운로드) 시작할 때 실행함수
			installFn: downloadAlert,

			//V3Detect.tff 폰트파일 적용 테스트 컨텐츠.
			test_content: "abcdefghijklmnopqrstuvwxyz",

			//V3Detect.tff 파일 이름.
			detect_font_name: "V3Detect"
		};

		agentcheck.options(opt);
		agentcheck.start();

	} catch(e) {
		throw new Error("agentcheck.js is not loaded...");
	}

	/*
	* agentCheckAlert를 생성하고 화면에 보여준다.
	*/
	function setAgentCheckAlert(message) {
		//열려있는 agentCheckAlert가 있으면 먼저 삭제한다.
		removeAgentCheckAlert();

		//로딩 spinner 아이콘과 텍스트를 포함하는 div를 생성하여 body에 붙인다.
		agentCheckAlert = $("<div/>").attr("class", "agent-check-alert").appendTo("body");

		//agentCheck icon 생성
		$(agentCheckAlert).append($("<div class='agent-check-icon check' />"));

		//text_el 생성
		if(typeof message!=="undefined") {
			$(agentCheckAlert).append($("<span class='agent-check-alert-text'>" + message + "</span>"));
		}

		//agentCheckAlert 정렬
		windowResizeHandler();
	}

	/*
	* ModalMask HTML을 생성하고 보여준다.
	*/
	function addModalMask(alpha, fn) {
		var modal_el = $("<div id='mask' />");
		$("body").before(modal_el);

		//애니메이션 효과 
		if(fn===null) {
		$('#mask').fadeTo("fast", alpha);
		} else {
		$('#mask').fadeTo("fast", alpha, fn);
		}
	}

	/*
	* 화면에서 agentCheckAlert를 제거한다.
	*/
	function removeAgentCheckAlert() {
		$(".agent-check-alert").remove();
        $(".popup-download-confirm").remove();
	}

	function checkAlert() {
		//모달마스크를 보여준다.
		addModalMask(0.6);

		setAgentCheckAlert();
	}

	function updateAlert() {
		setAgentCheckAlert("KollusPlayer V3가 업데이트 중입니다.<br/>업데이트 이후 자동으로 새로고침 됩니다.");
	}

	function installAlert() {
		setAgentCheckAlert("KollusPlayer V3가 설치 중입니다.<br/>설치 이후 자동으로 새로고침 됩니다.");
	}

	function downloadAlert() {
		//화면의 높이와 너비를 구한다.
		/*var h = $(window).height() + $(window).scrollTop();
		var w = $(window).width() + $(window).scrollLeft();*/

		var width = 350;
		var height = 150;
		var content = "KollusPlayer V3가 설치되어 있지 않습니다.<br/>다운로드 버튼을 클릭하여 설치하시기 바랍니다.";
		//PopupWindow HTML
		var popup_el = "<div id='popup' class='popup-refresh-confirm'>"+
		    "<div class='content'><span>" + content + "</span></div>"+
		    "<div class='footer'><span class='button-download button'>다운로드</span></div>"+
		"</div>";

		//#header 앞에 popup html을 붙인다.
		$("body").append($(popup_el).css({'width': width, 'height': height}));

		//width값이 가변이기 때문에 동적으로 footer width와 padding을 적용해준다.
		$("#popup .footer").css({'width': width, 'padding': 10});

			//content 내부의 span 스타일을 설정한다.
			$("#popup .content > span").css({'width': width, 'height': (height - 110 - $("#popup .header").height()), 'text-align': 'center'});

			//버튼 이벤트를 등록한다.
			$("#popup .footer .button-download").one("click", function(){
			//팝업제거
			$("#popup").remove();

			//agentCheckAlert를 호출한다.
			installAlert();

			//파일 다운로드 실행
			downloadURL(agentInstallURL);
		});

		windowResizeHandler();
	}

	/*
	* 파일 다운로드시 화면갱신 없이 다운로드 받도록 한다.
	* @url download file path string
	*/
	function downloadURL(url) {
		if( $('#idown').length ){
			$('#idown').attr('src',url);
		}else{
			$('<iframe>', { id:'idown', src:url }).hide().appendTo('body');
		}
	}

	/*
	* AgentCheck가 끝나면 실행.
	*/
	function agentCheckComplete(){
		//ModalMast 제거 
		$("#mask").fadeTo("fast", 0, function(){
			$(this).remove();
		});

		removeAgentCheckAlert();
	}

	/*
	* Window resize handler
	*/
	function windowResizeHandler() {
		//화면의 높이와 너비를 구한다.
		var h = $(window).height() + $(window).scrollTop();
		var w = $(window).width() + $(window).scrollLeft();

		//console.log(w + ", " + h);
		//PopupWindow의 위치값을 변경한다.
		// *outerHeight(), outerWidth()는 padding, margin값을 포함한 높이, 넓이값 반환.
		$(".agent-check-alert").css({
			"top": (h - 100)/2,
			"left": (w - 300)/2
		});

		//PopupWindow의 위치값을 변경한다.
		// *outerHeight(), outerWidth()는 padding, margin값을 포함한 높이, 넓이값 반환.
		$("#popup").css({
			"top": Math.max(0, ((h - $("#popup").outerHeight())/2)),
			"left": Math.max(0, ((w - $("#popup").outerWidth())/2))
		});

		//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
		$('#mask').css({'width':w,'height':h});
	}

	//윈도우 리사이즈 이벤트를 걸어준다.
	$(window).resize(function(){
		windowResizeHandler();
	});
});