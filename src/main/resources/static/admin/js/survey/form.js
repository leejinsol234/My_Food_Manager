const template = {
    tpl: null,
    tplEach : null,
    domParser: new DOMParser(),
    /**
    * 질문 추가
    *
    */
    add() {
        const targetEl = document.getElementById("question_html");
        const trs = document.querySelectorAll("#question_html tr");
        const qNum = trs.length || 1;
        const html = this.getTpl({qNum});

        const dom = this.domParser.parseFromString(html, "text/html");
        const tr = dom.querySelector("tr");
        targetEl.appendChild(tr);

        const removeQsEl = tr.querySelector(".remove_qs");
        removeQsEl.addEventListener("click", template.remove);

        const addEl = tr.querySelector(".add");
        const removeEl = tr.querySelector(".remove");

        // 문항 추가
        addEl.addEventListener("click", template.addEach);

        // 문항 제거
        removeEl.addEventListener("click", template.removeEach);
    },
    /**
    * 질문 제거
    *
    */
    remove() {
        const num = this.dataset.num;
        const el = document.getElementById(`question_box_${num}`);
        if (el) el.parentElement.removeChild(el);
    },
    /**
    * 문항 추가
    *
    */
    addEach() {
        const qNum = this.dataset.num;
        let html = template.getTplEach({qNum});
        const dom = template.domParser.parseFromString(html, "text/html");
        const inputEl = dom.querySelector("input");
        const targetEl = document.querySelector(`#question_box_${qNum} .questions`);
        targetEl.appendChild(inputEl);
    },
    /**
    * 문항 제거
    *
    */
    removeEach() {
        const qNum = this.dataset.num;
        const inputEls = document.querySelectorAll(`#question_box_${qNum} .questions input`);
        if (inputEls.length > 1) {
            const el = document.querySelector(`#question_box_${qNum} .questions input:last-of-type`);
            el.parentElement.removeChild(el);
        }
    },
    /**
    * 질문 템플릿 추출
    *
    */
    getTpl(replaceCodes) {
        this.tpl = this.tpl || document.getElementById("tpl").innerHTML;

        let html = this.tpl;
        if (replaceCodes) {
            html = html.replace(/\[\qNum\]/g, replaceCodes.qNum);
        }

        return html;
    },
    /**
    * 문항 템플릿 추출
    *
    */
    getTplEach(replaceCodes) {
        this.tplEach = this.tplEach || document.getElementById("tpl_each").innerHTML;

        let html = this.tplEach;
        if (replaceCodes) {
            html = html.replace(/\[\qNum\]/g, replaceCodes.qNum);
        }

        return html;
    }
};


window.addEventListener("DOMContentLoaded", function() {
    const addQsEl = document.querySelector(".add_qs");
    const removeQsEl = document.querySelector(".remove_qs");

    const addEl = document.querySelector(".add");
    const removeEl = document.querySelector(".remove");

    // 문항 추가
    addEl.addEventListener("click", template.addEach);

    // 문항 제거
    removeEl.addEventListener("click", template.removeEach);

    // 질문 추가
    addQsEl.addEventListener("click", function() {
        template.add();
    });

    // 질문 제거
    removeQsEl.addEventListener("click", template.remove);

});