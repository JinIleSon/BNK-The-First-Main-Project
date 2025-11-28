// 탭 전환 스크립트
document.addEventListener('DOMContentLoaded', function () {
    const tabButtons = document.querySelectorAll('.tab-btn');
    const panels = document.querySelectorAll('.tab-panel');

    tabButtons.forEach(btn => {
        btn.addEventListener('click', () => {
            const target = btn.getAttribute('data-tab-target');

            // 버튼 상태
            tabButtons.forEach(b => b.classList.remove('is-active'));
            btn.classList.add('is-active');

            // 패널 상태
            panels.forEach(p => {
                p.classList.toggle('is-active', p.id === target.replace('#', ''));
            });
        });
    });

    // 매수 카드: '매수하기' 버튼을 누르면 입력 영역 열기/닫기
    const buyCards = document.querySelectorAll('.buy-card');

    buyCards.forEach(card => {
        const toggleBtn = card.querySelector('.btn-toggle-buy');
        const cancelBtn = card.querySelector('.btn-cancel-buy');

        if (toggleBtn) {
            toggleBtn.addEventListener('click', () => {
                card.classList.add('is-buy-open');
            });
        }

        if (cancelBtn) {
            cancelBtn.addEventListener('click', () => {
                card.classList.remove('is-buy-open');
            });
        }
    });

    // 일부매도: 버튼 클릭 시 입력창 열기/닫기
    const sellCards = document.querySelectorAll('.sell-card');

    sellCards.forEach(card => {
        const partialBtn = card.querySelector('.btn-partial-toggle');
        const cancelBtn = card.querySelector('.btn-partial-cancel');

        if (partialBtn) {
            partialBtn.addEventListener('click', () => {
                card.classList.add('is-part-open');
            });
        }

        if (cancelBtn) {
            cancelBtn.addEventListener('click', () => {
                card.classList.remove('is-part-open');
            });
        }
    });

});