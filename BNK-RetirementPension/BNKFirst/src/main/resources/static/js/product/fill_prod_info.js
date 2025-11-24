const $ = (sel, root = document) => root.querySelector(sel);
const $$ = (sel, root = document) => root.querySelectorAll(sel);
function fillTerms(pterms) {
    const termsTitle = [...$$('#page2 .terms-list span')];
    const termsURL = [...$$('#page2 .terms-list a')];
    for (let i = 0; i < termsTitle.length; i++) {
        termsTitle[i].innerText = pterms[i].title;
        termsURL[i].setAttribute('href', pterms[i].link);
        termsURL[i].setAttribute('target', '_blank');
        termsURL[i].setAttribute('rel', 'noopener noreferrer');
    }
}

function fillPname(pname) {
    const page2ProdTitle = $('.product-name');
    const page4Pname = $('input[name="pname"]');

    page2ProdTitle.innerText = pname;
    page4Pname.value = pname;
}

function parseTermRule(ruleRaw) {
    if (!ruleRaw) return { type: 'text-only', message: '' };

    const rule = ruleRaw.replace(/\s+/g, ''); // 공백 제거

    // 5) n개월~n개월(일 단위)
    // 예: "1개월~60개월(일단위)", "1개월~60개월(일단위,만기일자)"
    let m = rule.match(/^(\d+)개월~(\d+)개월\((일단위(?:,만기일자)?)\)$/);
    if (m) {
        return {
            type: 'range-date',
            unit: 'day',
            min: parseInt(m[1], 10),
            max: parseInt(m[2], 10),
            raw: ruleRaw
        };
    }

    // 1) n개월~n개월 (월 범위)
    // 예: "1개월~12개월", "6개월~36개월"
    m = rule.match(/^(\d+)개월~(\d+)개월$/);
    if (m) {
        return {
            type: 'range-month',
            unit: 'month',
            min: parseInt(m[1], 10),
            max: parseInt(m[2], 10),
            raw: ruleRaw
        };
    }

    // 2) n개월(고정)
    // 예: "12개월(고정)"
    m = rule.match(/^(\d+)개월\(고정\)$/);
    if (m) {
        return {
            type: 'fixed-month',
            unit: 'month',
            value: parseInt(m[1], 10),
            raw: ruleRaw
        };
    }

    // 4) n개월, n개월, ..., n개월
    // 예: "1개월,3개월,6개월,12개월,36개월"
    if (/^\d+개월(,\d+개월)+$/.test(rule)) {
        const options = rule.split(',')
            .map(s => parseInt(s.replace('개월', ''), 10));
        return {
            type: 'list-month',
            unit: 'month',
            options,
            raw: ruleRaw
        };
    }

    // 3) 그 외 → 그냥 안내 문자열로 취급
    return {
        type: 'text-only',
        message: ruleRaw
    };
}



function initPcprdRange(pcprd) {
    const termRuleStr = pcprd; // "1개월~60개월(일 단위, 만기일자)" 같은 문자열

    const chipsBox = $('#termChips');
    const months   = $('#termMonths');
    const monthsWrapper = months.closest('div');
    const termDate = $('#termDate');
    const termHelp = $('#termHelp');

    const termConfig = parseTermRule(termRuleStr);
    console.log(termConfig);
    console.log(monthsWrapper);

    function buildTermChips(config) {
        chipsBox.innerHTML = '';

        if (config.type === 'list-month') {
            // 예: 1,3,6,12,36개월
            config.options.forEach((m, idx) => {
                const btn = document.createElement('button');
                btn.type = 'button';
                btn.className = 'Chip' + (idx === 0 ? ' active' : '');
                btn.dataset.month = m;
                btn.textContent = m + '개월';
                chipsBox.appendChild(btn);
            });
        } else if (config.type === 'range-month') {
            // 예: 1~12개월, 6~36개월
            // 대표 값 몇 개만 chip으로 제공
            const candidates = [config.min, 12, 24, config.max];
            const uniq = [...new Set(candidates.filter(v => v >= config.min && v <= config.max))];
            uniq.forEach((m, idx) => {
                const btn = document.createElement('button');
                btn.type = 'button';
                btn.className = 'Chip' + (idx === 0 ? ' active' : '');
                btn.dataset.month = m;
                btn.textContent = m + '개월';
                chipsBox.appendChild(btn);
            });
        } else if (config.type === 'range-date') {
            // 일 단위는 만기일자 chip만
            const btn = document.createElement('button');
            btn.type = 'button';
            btn.className = 'Chip active';
            btn.dataset.type = 'date';
            btn.textContent = '만기일자';
            chipsBox.appendChild(btn);
        }

        const spacer = document.createElement('div');
        spacer.style.flex = '1 0 120px';
        chipsBox.appendChild(spacer);
    }

    function initTermUI(config) {
        if (config.type === 'fixed-month') {
            chipsBox.style.display = 'none';
            monthsWrapper.style.display   = 'block';
            termDate.style.display = 'none';

            months.value    = config.value;
            months.min      = config.value;
            months.max      = config.value;
            months.readOnly = true;
            termHelp.textContent = `${config.value}개월 고정입니다.`;
            return;
        }

        if (config.type === 'text-only') {
            chipsBox.style.display = 'none';
            monthsWrapper.style.display   = 'none';
            termDate.style.display = 'none';
            termHelp.textContent   = config.message; // 예: "납입기간은 가입일로부터 입주자로 선정된 날까지"
            return;
        }

        // list-month, range-month, range-date
        chipsBox.style.display = 'flex';
        buildTermChips(config);

        if (config.type === 'range-month' || config.type === 'list-month') {
            monthsWrapper.style.display   = 'block';
            termDate.style.display = 'none';
            months.readOnly = false;
            months.min = config.min || Math.min(...(config.options || [1]));
            months.max = config.max || Math.max(...(config.options || [999]));
            months.value = months.min;
            termHelp.textContent = `${months.min}개월 이상 ~ ${months.max}개월 이내로 입력해 주세요.`;
        }

        if (config.type === 'range-date') {
            monthsWrapper.style.display   = 'none';
            termDate.style.display = 'block';
            termHelp.textContent = `${config.min}개월 이상 ~ ${config.max}개월 이내의 만기일자를 선택해 주세요.`;
        }
    }

    initTermUI(termConfig);

    chipsBox.addEventListener('click', (e) => {
        const chip = e.target.closest('.Chip');
        if (!chip) return;
        if (termConfig.type === 'fixed-month' || termConfig.type === 'text-only') return;

        [...chipsBox.querySelectorAll('.Chip')].forEach(c => c.classList.remove('active'));
        chip.classList.add('active');

        const m = chip.dataset.month;
        const isDate = chip.dataset.type === 'date';

        if (termConfig.type === 'range-date' || isDate) {
            monthsWrapper.style.display = 'none';
            termDate.style.display = 'block';
            termDate.focus();
        } else {
            monthsWrapper.style.display = 'block';
            termDate.style.display = 'none';
            if (m) months.value = m;
            months.focus();
        }
    });

    months.addEventListener('change', () => {
        if (termConfig.type !== 'range-month' && termConfig.type !== 'list-month') return;

        let v = parseInt(months.value || 0, 10);
        if (isNaN(v)) v = termConfig.min || (termConfig.options ? termConfig.options[0] : 1);

        if (termConfig.type === 'list-month') {
            if (!termConfig.options.includes(v)) {
                alert('허용된 기간만 선택 가능합니다: ' + termConfig.options.join(', ') + '개월');
                v = termConfig.options[0];
            }
        } else {
            v = Math.max(termConfig.min, Math.min(termConfig.max, v));
        }
        months.value = v;
    });

    function validateTermDate() {
        if (termConfig.type !== 'range-date') return true;
        const joinInput = document.getElementById('joinDate');
        if (!joinInput || !joinInput.value || !termDate.value) return true;

        const start = new Date(joinInput.value);
        const end   = new Date(termDate.value);
        if (isNaN(start) || isNaN(end)) return true;

        const diffMonths =
            (end.getFullYear() - start.getFullYear()) * 12 +
            (end.getMonth() - start.getMonth());

        if (diffMonths < termConfig.min || diffMonths > termConfig.max) {
            alert(`${termConfig.min}개월 이상 ~ ${termConfig.max}개월 이내의 만기일자를 선택해 주세요.`);
            termDate.focus();
            return false;
        }
        return true;
    }

    termDate.addEventListener('change', validateTermDate);
}

export function initProdInfo(data) {
    try {
        const pterms = JSON.parse(data.pterms);
        fillTerms(pterms);
        fillPname(data.pname);
        initPcprdRange(data.pcprd);
    } catch (e) {
        console.error(e);
    }
}