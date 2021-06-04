var cmtFrmElem = document.querySelector('#cmtFrm');
var cmtListElem = document.querySelector('#cmtList');
var cmtModModalElem = document.querySelector('#modal');

function regCmt() {
    var cmtVal = cmtFrmElem.cmt.value;
    var param = {
        iboard: cmtListElem.dataset.iboard,
        cmt: cmtVal
    };

    regAjax(param);
}

function regAjax(param) {
    const init = {
        method: 'POST',
        body: JSON.stringify(param)
    }; // init 은 설정해주는것.

    fetch('cmtInsSel', init)
        .then(function (res) {
            return res.json();
        })
        .then(function (myJson) {
            console.log(myJson);

            switch (myJson.result) {
                case 0:
                    alert('등록실패!');
                    break;
                case 1:
                    cmtFrmElem.cmt.value = '';
                    getListAjax();
                    break;
            }
        });
}


// 서버에 댓글 리스트 자료 달라고 요청하는 함수
function getListAjax() {
    var iboard = cmtListElem.dataset.iboard;
    // data-ibaord="" 로 jsp 에서 설정해준 속성값을 가져오는 방법
    // init 없이 쓰면 자동으로 GET 방식이다.
    fetch('cmtInsSel?iboard=' + iboard)
        .then(function (res) {
            return res.json(); // String 을 객체로 만들어준다.(String 자체가 JSON 형태로 날아오기 때문에 가능)
        }).then(function (myJson) {
        console.log(myJson);
        makeCmtElemList(myJson);
    })
}

function makeCmtElemList(data) {

    cmtListElem.innerHTML = ''; // 만들기 전에 기존에 있던거 싹 지워주기
    var tableElem = document.createElement('table'); // 메모리상에 <table></table>이 들어간다. a, span, div 다 가능
    var trElemTitle = document.createElement('tr');
    var thElemCtnt = document.createElement('th');
    var thElemWriter = document.createElement('th');
    var thElemRegdt = document.createElement('th');
    var thElemBigo = document.createElement('th');

    thElemCtnt.append('내용'); // 이 방법도 되고
    thElemWriter.innerText = '작성자'; // 이 방법도 된다.
    thElemRegdt.innerText = '작성일';
    thElemBigo.innerText = '비고';

    trElemTitle.append(thElemCtnt); // 각각 따로 있는 태그들을 넣어주는거 (th를 tr 태그 안으로)
    trElemTitle.append(thElemWriter); // 자식을 가질 수 없는 태그는 안됨
    trElemTitle.append(thElemRegdt); // append 는 순서대로 뒤에 넣어줌 prepend 는 앞쪽에 넣어줌(노래방 우선 예약)
    trElemTitle.append(thElemBigo);

    tableElem.append(trElemTitle); // tr을 table 태그 안으로

    cmtListElem.append(tableElem);

    var loginUserPk = cmtListElem.dataset.loginUserPk; // dataset 쓸때는 소문자만 쓴다!!(수정: data-login-user-pk라 쓰면 loginUserPk로 받을 수 있다.)
    // 그리고 가져올때 문자열로 가져옴

    data.forEach(function (item) { // forEach : 배열객체가 가지고 있는 함수. data(배열자료) 안에 있는 아이템 수 만큼 function 안에 아이템을 넣어서 반복해준다.
        var trElemItem = document.createElement('tr');
        var tdElemCmt = document.createElement('td');
        var tdElemUnm = document.createElement('td');
        var tdElemRegdt = document.createElement('td');
        var tdElemBigo = document.createElement('td');

        tdElemCmt.append(item.cmt);
        tdElemUnm.append(item.writerNm);
        tdElemRegdt.append(item.regdt);

        if (loginUserPk == item.iuser) {
            var delBtn = document.createElement('button');
            var modBtn = document.createElement('button');

            // 삭제 버튼 클릭시
            delBtn.addEventListener('click', function () {
                if (confirm('삭제하시겠습니까?')) {
                    delAjax(item.icmt);
                }
            });
            // 수정 버튼 클릭시
            modBtn.addEventListener('click', function () {
                // 댓글 수정 모달창 띄우기
                openModModal(item);
            });

            delBtn.innerText = '삭제';
            modBtn.innerText = '수정';

            tdElemBigo.append(delBtn);
            tdElemBigo.append(modBtn);
        }

        trElemItem.append(tdElemCmt);
        trElemItem.append(tdElemUnm);
        trElemItem.append(tdElemRegdt);
        trElemItem.append(tdElemBigo);

        tableElem.append(trElemItem);
    })

}

function delAjax(icmt) {
    fetch('cmtDelUpd?icmt=' + icmt)
        .then(function (res) {
            return res.json();
        })
        .then(function (myJson) {
            console.log(myJson);

            switch (myJson.result) {
                case 0:
                    alert('댓글 삭제 중 오류 발생');
                    break;
                case 1:
                    getListAjax();
                    break;
            }
        })
}

function openModModal({icmt, cmt}) { // 파라미터를 이렇게 해놓으면 객체를 넘겼을때 그 객체중 받고 싶은 멤버필드만 받아 저장한다.
    var cmtModFrmElem = document.querySelector('#cmtModFrm');
    cmtModFrmElem.icmt.value = icmt;
    cmtModFrmElem.cmt.value = cmt;
    cmtModModalElem.className = '';
}

function closeModModal() {
    cmtModModalElem.className = 'displayNone';
}

function modAjax() {
    var cmtModFrmElem = document.querySelector('#cmtModFrm');
    var param = {
        icmt: cmtModFrmElem.icmt.value,
        cmt: cmtModFrmElem.cmt.value
    }
    const init = {
        method: 'POST',
        body: new URLSearchParams(param)
    }
    fetch('cmtDelUpd', init)
        .then(function (res) {
            return res.json();
        })
        .then(function (myJson) {
            if (myJson.result == 1) {
                closeModModal();
                getListAjax();
            } else {
                alert('댓글 수정 실패');
            }
        })
}

getListAjax(); // 이 파일이 임포트 되면 함수 1회 호출


// 좋아요 싫어요 작업중..
function slapLike(iboard) {
    var likeElem = document.querySelector('#likeIcon');
    var loginUserPk = cmtListElem.dataset.login_user_pk;
    var param = {
        likeElem,
        iboard,
        loginUserPk
    }
    slapLikeAjax(param);
}

function slapLikeAjax(param) {

}
