
    // 1.이벤트 리스너
    $("#btn-join").click(() => {
        join();
    });
    // 2. 기능
    // let join = ()=>{ 변수로 담아서 람다 함수 만들기

    // }

    // 회원가입 요청 메서드
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
        let response = await fetch("/api/join", {
            method: "POST",
            body: JSON.stringify(userDto),
            headers:{
                'Content-Type': 'application/json; charset=utf-8'
            },
        });

        // 4. 회원가입이 잘되면 알림창 띄우고 로그인 페이지로 이동한다.
        let responseParse = await response.json(); // 파싱
        console.log(responseParse);

        if(responseParse.code == 1){
            alert("회원가입 완료되었습니다.");
            location.href = "/login";
        } else {
            alert("회원가입에 실패했습니다." + responseParse.msg);
            alert(`${responseParse.data}`);
        }
    }

        // 1. 버튼에 이벤트 달기
        $("#btn-login").click(() => {
            login();
        });

        // 로그인 요청 메서드
        // 2. username과 password 찾기
        async function login() {
            let loginDto = {
                username: $("#username").val(),
                password: $("#password").val()
            }
    
            // 3. JSON으로 변환해서 fetch 요청
            // 4. /login, POST 메서드로
            let response = await fetch("/api/login", {
                method: "POST",
                body: JSON.stringify(loginDto),
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                },
            });
    
            let responseObject = await response.json(); // 오브젝트로 파싱
    
            if(responseObject.code == 1){
                alert("로그인 성공");
                location.href = "/";
            } else {
                alert("로그인 실패");
            }
        }