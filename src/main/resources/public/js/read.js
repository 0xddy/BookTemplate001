if (window.navigator.webdriver === undefined || window.navigator.webdriver === false) {
    let contentDom = document.getElementById('content');
    let sContentDom = document.getElementById('s-content');
    let cipherContent = Base64.decode(contentDom.innerHTML);
    sContentDom.innerHTML = '';
    let bytes = CryptoJS.AES.decrypt(cipherContent, '++novel4j++');
    contentDom.innerHTML = bytes.toString(CryptoJS.enc.Utf8);
}