function addTabItem(title, url) {
    if (!parent) {
        return;
    }
    parent.addTabItem(title, url, true)
}