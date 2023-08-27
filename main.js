
const apiUrl = 'http://localhost:8383/api/customers';


//Getting data
// Function to fetch data from the Spring Boot API and populate the table
function fetchDataAndPopulateTable() {
    fetch(apiUrl) // Replace '/api/endpoint' with your actual endpoint
        .then(response => response.json())
        .then(data => {
            console.log(data);

            // Process the data returned from the API and build the table rows
            const tableData = data.map((customer, index) => `
                <tr>
               
                    <td>${customer.first_name}</td>
                    <td>${customer.last_name}</td>
                    <td>${customer.street}</td>
                    <td>${customer.address}</td>
                    <td>${customer.city}</td>
                    <td>${customer.state}</td>
                    <td>${customer.email}</td>
                    <td>${customer.phone}</td>
                    <td>
                                <button  onclick="editCustomer(${index})" style="color: blue; border: none;">Edit</button>
                                <button onclick="deleteCustomer(${customer.id})" style="color: red; border: none;">Delete</button>
                            </td>

                </tr>
            `).join('');



            // Add the table rows to the tbody element
            document.getElementById('data').innerHTML = tableData;



        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
}




// Function to handle edit action to fill data 
function editCustomer(index) {
  
    const customerData = document.getElementById('data').rows[index++].cells;
    const editForm = document.getElementById('updateCustomer');
   // Populate the form with the customer data
   editForm.customerId.value = index;
    editForm.first_name.value = customerData[0].innerText;
    editForm.last_name.value = customerData[1].innerText;
    editForm.street.value = customerData[2].innerText;
    editForm.address.value = customerData[3].innerText;
    editForm.city.value = customerData[4].innerText;
    editForm.state.value = customerData[5].innerText;
    editForm.email.value = customerData[6].innerText;
    editForm.phone.value = customerData[7].innerText;

    // Display the form
    // editForm.style.display = 'block';
    const editModal = new bootstrap.Modal(document.getElementById('exampleModal1'));
    editModal.show();
}




function handleEditForm(event) {
    event.preventDefault();

    const formData = new FormData(event.target);
    const customerId = formData.get('id'); // Assuming there is an input field with name/id="id" in the form
    

    const requestData = {
        // Extract the rest of the form data as before
        first_name: formData.get('first_name'),
        last_name: formData.get('last_name'),
        street: formData.get('street'),
        address: formData.get('address'),
        city: formData.get('city'),
        state: formData.get('state'),
        email: formData.get('email'),
        phone: formData.get('phone')
    };

    const apiUrl = 'http://localhost:8383/api/customers'; // Replace this with your actual API endpoint

    fetch(apiUrl + '/' + customerId, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestData),
    })
        .then((response) => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error('Network response was not ok');
            }
        })
        .then((data) => {
            // Handle the API response after successful update
        console.log('Response from server:', data);

    

        // Reset the form after successful update
        // document.getElementById('updateCustomer').reset();
      
    

        // Reload the page to refresh the table with updated data
        location.reload();
        // Refresh the table with updated data
        fetchDataAndPopulateTable();
       
     
            
       
        })
        
        .catch((error) => {
            console.error('Error updating data:', error);
        });
}








//deleting 
// Function to handle delete action
function deleteCustomer(id) {
    fetch(apiUrl + '/' + id, {
        method: 'DELETE',
    })
        .then(response => {
            if (response.ok) {
                // Delete was successful, refresh the table to show updated data
                fetchDataAndPopulateTable();
            } else {
                // Handle the error case
                console.error('Failed to delete customer');
            }
        })
        .catch(error => {
            console.error('Error while deleting customer', error);
        });
}








//create
function handleFormSubmit(event) {
    event.preventDefault(); // Prevent the default form submission behavior

    const formData = new FormData(event.target);
    const requestData = {
        first_name: formData.get('first_name'),
        last_name: formData.get('last_name'),
        street: formData.get('street'),
        address: formData.get('address'),
        city: formData.get('city'),
        state: formData.get('state'),
        email: formData.get('email'),
        phone: formData.get('phone')
    };

    const apiUrl = 'http://localhost:8383'; // Replace this with your actual API endpoint

    fetch(apiUrl + '/api/customers', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            document.getElementById('createNewCustomer').style.display = 'none';

            // Reload the page to refresh the table with updated data
            location.reload();
            return response.json();
        })
        .then(data => {
            // Handle the API response after successful creation
            console.log('Data created successfully:', data);
            // You can perform additional actions after successful creation here
        })

        .catch(error => {
            console.error('Error creating data:', error);
            // Handle errors here if any
        });
}



