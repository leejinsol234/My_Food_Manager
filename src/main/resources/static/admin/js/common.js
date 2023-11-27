window.addEventListener("DOMContentLoaded", function() {
    const checkalls = document.getElementsByClassName("checkall");
    for (const el of checkalls) {
        el.addEventListener("click", function() {
            const name = el.dataset.targetName;
            const chks = document.getElementsByName(name);
            for (const chk of chks) {
                chk.checked = this.checked;
            }
        });
    }
});