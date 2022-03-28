
// 1.이벤트 리스너
$("#btn-join").click(() => {
    join();
});

$("#btn-update").click(() => {
    update();
});

$("#btn-login").click(() => {
    login();
});

// 2. 기능
// let join = ()=>{ 변수로 담아서 람다 함수 만들기

// }

// 회원가입 요청 함수
async function join() {
    // 1. username, password, email, addr를 찾아서 오브젝트로 만든다.
    let userDto = {
        username: $("#username").val(),
        password: $("#password").val(),
        email: $("#email").val(),
        addr: $("#addr").val()
    }

    // 2. JOSN으로 변환한다. 통신을 위해서.
    // 3. fetch 요청
    let response = await fetch("/join", {
        method: "POST",
        body: JSON.stringify(userDto),
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        },
    });

    // 4. 회원가입이 잘되면 알림창 띄우고 로그인 페이지로 이동한다.
    let responseParse = await response.json(); // 파싱
    console.log(responseParse);

    if (responseParse.code == 1) {
        alert("회원가입 완료되었습니다.");
        location.href = "/login";
    } else {
        alert("회원가입에 실패했습니다." + responseParse.msg);
        alert(`${responseParse.data}`);
    }
}

// 1. 버튼에 이벤트 달기


// 유저네임 기억하기
function usernameRemember() {
    let cookies = document.cookie.split("="); // =로 파싱해서 username 값 들고 오기
    $("#username").val(cookies[1]); // 첫 번째 쿠키값 유저네임에 넣기
}

usernameRemember();

// 로그인 요청 함수
// 2. username과 password 찾기
async function login() {

    // 체크박스의 체크여부를 제이쿼리에 확인
    let checked = $("#remember").is(":checked"); // remember가 checked됐는 지 안 됐는 지 확인하는 is 메서드

    let loginDto = {
        username: $("#username").val(),
        password: $("#password").val(),
        remember: checked ? "on" : "off" // 삼항연산자 사용
    }


    // 3. JSON으로 변환해서 fetch 요청
    // 4. /login, POST 메서드로
    let response = await fetch("/login", {
        method: "POST",
        body: JSON.stringify(loginDto),
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        },
    });

    let responseObject = await response.json(); // 오브젝트로 파싱

    if (responseObject.code == 1) {
        alert("로그인 성공");
        location.href = "/";
    } else {
        alert("로그인 실패");
    }
}




async function update() {
    let id = $("#id").val();
    let updateDto = {
        password: $("#password").val(),
        email: $("#email").val(),
        addr: $("#addr").val()
    }

    let response = await fetch(`/s/api/user/${id}`, {
        method: "PUT",
        body: JSON.stringify(updateDto),
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        }
    });
    let responseParse = await response.json();

    if (responseParse.code == 1) {
        alert("업데이트 성공");
        location.href = `/s/user/${id}`;
    } else {
        alert("업데이트 실패");
    }
}