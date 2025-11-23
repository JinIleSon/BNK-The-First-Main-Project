const $ = (sel, root = document) => root.querySelectorAll(sel);
function fillTerms(pterms) {
    const termsTitle = [...$('#page2 .terms-list span')];
    const termsURL = [...$('#page2 .terms-list a')];
    for (let i = 0; i < termsTitle.length; i++) {
        termsTitle[i].innerText = pterms[i].title;
        termsURL[i].setAttribute('href', pterms[i].link);
        termsURL[i].setAttribute('target', '_blank');
        termsURL[i].setAttribute('rel', 'noopener noreferrer');
    }
}

export function initProdInfo(data) {
    try {
        const pterms = JSON.parse(data.pterms);
        fillTerms(pterms);
    } catch (e) {
        console.error(e);
    }
}