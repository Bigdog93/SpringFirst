const listElem = document.querySelector('#list');
const pageElem = document.querySelector('#paging');

function getListAjax(page = 1) {
    fetch('like?page=' + page)
        .then(res => res.json()) // 인자 두개 이상일 경우 {}로 묶어 주면 된다. => 후 {} 없이 바로 쓰면 return 으로 취급
        .then(result => {
            makeView(result.list);
            makePaging(result.maxPage, result.cPage);
        })

}

function makePaging(maxPage, cPage) {
    pageElem.innerHTML='';
    for(let i = 1; i <= maxPage; i++) {
        var pageNumElem = document.createElement('span');
        pageElem.append(pageNumElem);
        if(i == cPage.page) {
            pageNumElem.classList.add('selected');
            pageNumElem.innerHTML=` ${i} `;
        } else {
            pageNumElem.classList.add('unselected');
            pageNumElem.addEventListener('click', function() {
                getListAjax(i);
            });
            pageNumElem.innerHTML=` ${i} `;
        }
    }
}

function makeView(data) {
    listElem.innerHTML = '';
    const table = document.createElement('table');
    listElem.append(table);

    table.innerHTML = `<tr>
        <th>번호</th>
        <th>제목</th>
        <th>글쓴이</th>
        <th>작성일</th>
    </tr>`;

    data.forEach(item => {
        const tr = document.createElement('tr');
        tr.classList.add('record');
        tr.addEventListener('click', () => {
            moveToDetail(item.iboard);
        })
        table.append(tr);
        let srcPath = '/res/img/defaultprofile.jpg'
        if(item.profileImg != null) {
            srcPath = `/img/user/${item.iuser}/${item.profileImg}`; // js 문법($) 쓸려면 ' 아니고 ` 써야 한다.
        }
        tr.innerHTML = `
        <td>${item.iboard}</td>
        <td>${item.title}<i id="likeIcon" class="fas fa-heart pointer"></i></td>
        <td><img src=${srcPath} class="profileImg">${item.writerNm}</td>
        <td>${item.regdt}</td>`; // EL 식 아니고 js 문법
    })
}

function moveToDetail(iboard) {
    location.href = '/board/detail?iboard=' + iboard;
}

getListAjax();