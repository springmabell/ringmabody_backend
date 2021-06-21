const passwordUpdateBtn = document.querySelector('.pwdUpdateBtn');
const passwordValidation = document.querySelector('.pwd_valid');
const passwordCheckValidation = document.querySelector('.pwd_check_valid');
const passwordInput = document.querySelector('#pwdInput');
const passwordCheckInput = document.querySelector('#pwdCheckInput');

passwordUpdateBtn.addEventListener('click', validate);

function validate(){
    const re = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+])(?!.*[^a-zA-z0-9$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/

    if(!check(re,passwordInput)) {
        return false;
    }
    if(passwordInput.value != passwordCheckInput.value) {
        passwordCheckValidation.style.display = "block";
        passwordCheckInput.value = "";
        passwordCheckInput.focus();
        return false;
    }
    passwordCheckValidation.style.display = "none";
    passwordInput.value = '';
    passwordCheckInput.value = '';
    alert("비밀번호 변경이 완료되었습니다.");
}

function check(re, what) {
    if(re.test(what.value)) {
        passwordValidation.style.display = "none";
        return true;
    }
    passwordValidation.style.display = "block";
    what.focus();
}

function setThumbnail(event) {
    const imgMain = document.querySelector(".picture");
    imgMain.style.display = "block";
    let reader = new FileReader();
    reader.onload = function (event) {
        const img = document.createElement('img');
        img.className = 'thumbnail';
        imgMain.style.border = 'none';
        imgMain.innerHTML = '';
        imgMain.append(img);
        img.setAttribute("src", event.target.result);
    };
    reader.readAsDataURL(event.target.files[0]);
}