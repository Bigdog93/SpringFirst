const recordCntFrmElem = document.querySelector('#recordCntFrm');
const selectElem = recordCntFrmElem.recordCnt;

selectElem.addEventListener('change', function () {
	recordCntFrmElem.page.value = 1;
	recordCntFrmElem.submit();
})

function moveToDetail(iboard) {
	location.href = 'detail?iboard=' + iboard;
//	location.replace(); 는 이동하면서 history 에 쌓지 않는다.
}