const apiUrl = 'http://localhost:9090/v1/registration';
let editId = null;

// Fetch all registrations and display them in the table
async function fetchRegistrations() {
    try {
        const response = await fetch(apiUrl);
        const data = await response.json();

        const tableBody = document.querySelector('#registrationsTable tbody');
        tableBody.innerHTML = ''; // Clear the table body

        data.forEach(reg => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${reg.id}</td>
                <td>${reg.name}</td>
                <td>${reg.email}</td>
                <td>${reg.dateOfBirth}</td>
                <td>${reg.phoneNumber}</td>
                <td>
                    <button class="edit" onclick="editRegistration(${reg.id})">Edit</button>
                    <button class="delete" onclick="deleteRegistration(${reg.id})">Delete</button>
                </td>
            `;
            tableBody.appendChild(row);
        });
    } catch (error) {
        console.error('Error fetching registrations:', error);
    }
}

// Create or update a registration
document.querySelector('#registrationForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const formData = new FormData(this);
    const registration = {
        name: formData.get('name'),
        email: formData.get('email'),
        dateOfBirth: formData.get('dob'),
        phoneNumber: formData.get('phone'),
    };

    let method = 'POST';
    let url = apiUrl;

    // If editing an existing registration
    if (editId !== null) {
        method = 'PUT';
        url = `${apiUrl}/${editId}`;
    }

    try {
        const response = await fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(registration)
        });
        
        const result = await response.json();
        // Reset form and fetch updated registrations
        document.querySelector('#registrationForm').reset();
        editId = null;
        fetchRegistrations();
        document.querySelector('#submitButton').textContent = 'Create Registration';
    } catch (error) {
        console.error('Error:', error);
    }
});

// Edit a registration (pre-fill form)
async function editRegistration(id) {
    try {
        const response = await fetch(`${apiUrl}/${id}`);
        const data = await response.json();

        document.querySelector('#name').value = data.name;
        document.querySelector('#email').value = data.email;
        document.querySelector('#dob').value = data.dateOfBirth;
        document.querySelector('#phone').value = data.phoneNumber;
        document.querySelector('#submitButton').textContent = 'Update Registration';
        editId = data.id;
    } catch (error) {
        console.error('Error fetching registration:', error);
    }
}

// Delete a registration
async function deleteRegistration(id) {
    try {
        const response = await fetch(`${apiUrl}/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            fetchRegistrations();
        } else {
            console.error('Failed to delete registration');
        }
    } catch (error) {
        console.error('Error deleting registration:', error);
    }
}

// Initial load of registrations
fetchRegistrations();
