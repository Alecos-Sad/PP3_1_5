async function auth() {
    let res = await fetch('http://localhost:8080/user/gateway');
    return await res.json();
}

upperPanel();
refreshUserPanel();

//Заполнение верхней панели
async function upperPanel() {
    let user = await auth();
    document.getElementById("userUsername").textContent = user.username;
    let roles = "";
    user.roleSet.forEach(role => {
        roles += role.name.substring(5, role.name.length) + " ";
    })
    document.getElementById("userRoles").textContent = roles;
}

async function refreshUserPanel() {
    const tbody = document.querySelector('#userTBody');

    let user = await auth();
    let roles = user.roleSet.map(role => role.name.substring(5, role.name.length));
    let rolesInTable = '';
    roles.forEach(role => {
        rolesInTable += `<div>${role}</div>`
    });

    tbody.innerHTML =
        `<tr>
            <td class="align-middle">${user.id}</td>
            <td class="align-middle">${user.firstName}</td>
            <td class="align-middle">${user.secondName}</td>
            <td class="align-middle">${user.age}</td>
            <td class="align-middle">${user.username}</td>
            <td class="align-middle">${rolesInTable}</td>
            </tr>`;
}

