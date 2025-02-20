document.getElementById('employeeForm').addEventListener('submit', ()=> {


    const nom = document.getElementById('nom').value.trim();
    const prenom = document.getElementById('prenom').value.trim();
    const naissance = document.getElementById('naissance').value.trim();
    const email = document.getElementById('email').value;

    if (!validateEmail(email)) {
        alert('Please enter a valid email address.');
        return;
    }


    if (!validateNaissance(naissance)) {
        alert('Please enter a valid naissance number.');
        return;
    }


    console.log({ nom, email, naissance, prenom });
    alert('Employee data submitted successfully!');
});


function validateEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
}


function validateNaissance(naissance) {
    const re = /^\+?\d{7,15}$/;
    return re.test(naissance);
}
