window.addEventListener("DOMContentLoaded", function() {
    /**
    *  Prev, Next 버튼 처리
    *
    */
    const naviEls = document.getElementsByClassName("navi");
    const surveyPages = document.getElementsByClassName("survey_page");
    for (const el of naviEls) {
        el.addEventListener("click", function() {
            /** 현재 보이는 화면 인덱스 번호 확인 */
            let current = 0;
            for (let i = 0; i < surveyPages.length; i++) {
                if (surveyPages[i].classList.contains("dn")) {
                    continue;
                }

                current = i;
            }

            let next = this.classList.contains("prev") ? --current : ++current;

            if (next < 0) next = surveyPages.length - 1;
            else if (next >= surveyPages.length) next = 0;

            for (let i = 0; i < surveyPages.length; i++) {
                const el = surveyPages[i];
                const classList = el.classList;
                classList.remove("dn")
                classList.add("dn");

                if (i == next) {
                    classList.remove("dn");
                }
            }

            const prevEl = document.querySelector(".navi.prev");
            const nextEl = document.querySelector(".navi.next");
            prevEl.classList.remove("dn");
            nextEl.classList.remove("dn");
            /* 첫 페이지 인 경우 prev 버튼 미노출 */
            if (next == 0) {
                prevEl.classList.add("dn");
            }

            /* 마지막 페이지 인 경우 next 버튼 미노출 */
            if (next === surveyPages.length - 1) {
                nextEl.classList.add("dn");
            }
        });
    }
});