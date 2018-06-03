function onProfileLoad(user) {
    clearMessages();
    showContents(['profile-content', 'logout-content']);

    const userEmailSpandEl = document.getElementById('user-email');
    const userFirstNameSpandEl = document.getElementById('user-firstname');

    userEmailSpandEl.textContent = user.email;
    userFirstNameSpandEl.textContent = user.firstName;
}
