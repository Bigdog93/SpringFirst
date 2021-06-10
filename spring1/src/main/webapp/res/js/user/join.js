var frmElem = document.querySelector('#frm');
var uidElem = frmElem.uid; // 속성으로 접근, 속성이 없을 경우 자식중에 id값이나 name값으로 찾는다.
var upwElem = frm.upw; // id값으로 바로 접근할 수 있다. 정석은 아님
var chkUpwElem = frmElem.chkUpw;
var unmElem = frmElem.unm;
var chkUidResultElem = document.querySelector('#chkUidResult');

// var btnChkIdElem = frmElem.btnChkId;
// var btnChkIdElem = document.querySelector('#btnChkId');
var btnChkIdElem = frmElem.querySelector('#btnChkId'); // 이렇게도 된다.

/* ID 중복체크 */
// btnChkIdElem.onclick = function() {}; // jsp에서 onclick='...()'속성을 준것과 같은 효과
btnChkIdElem.addEventListener('click', function() { // (이벤트명, 실행할 함수)
	idChkAjax(uidElem.value);
})
function idChkAjax(uid) {
	
	fetch('/user/idChk?uid=' + uid) // 따로 설정 안해주면 get 방식으로 간다.
	.then(function(res) {return res.json();})
	.then(function(myJson) {
		console.log(myJson); // 서블릿에서 pw에 붙여준 아이가 json(자바스크립트 객체)으로 넘어온다.
		switch(myJson.result) {
			case 0:
				chkUidResultElem.innerHTML = '이 아이디는 사용할 수 있습니다.';
				break;
			case 1:
				chkUidResultElem.innerHTML = '이미 존재하는 아이디입니다.';
				break;
		};
		
	})
}


function frmChk() {
	// 이상이 생기면 return false; 할꺼얌
	var uidVal = uidElem.value; // 이거시 바로 속성으로 접근하는 거시다.
	// frm 아이디를 가진 태그의 자식 중 uid 네임을 가진 input태그의 value 속성에 접근
	var upwVal = upwElem.value;
	var chkUpwVal = chkUpwElem.value;
	var unmVal = unmElem.value;
	
	if(uidVal.length == 0) {
		alert('ID를 적어주겠어?');
		return false;
	} else if(uidVal.length <= 2) {
		alert('ID는 3글자 이상 입력하라구~')
		return false;
	}
	if(upwVal.length <= 2) {
		if(upwVal.length == 0) alert('비밀번호를 적어주겠어?');
		else alert('비밀번호는 3자 이상 입력하라구~');
		return false;
	}else if(upwVal !== chkUpwVal) {
		alert('비밀번호를 다르게 적었자너~');
		return false;
	}
	
	if(unmVal.length < 2) {
		alert('이름은 2자이상 작성하시옹');
		return false;
	}
	
	console.log(uidVal);
}

