// src/main/resources/static/js/script.js
document.addEventListener('DOMContentLoaded', function() {
    setupEndpointLinks();
});

function setupEndpointLinks() {
    const endpoints = [



        
        // Add more endpoints as needed
        { name: 'Get All Banners', path: '/banners', method: 'GET' },
    { name: 'Get Banner by ID', path: '/banners/{id}', method: 'GET' },
    { name: 'Get All Movies', path: '/movies', method: 'GET' },
    { name: 'Get Featured Movies', path: '/movies?featured=true', method: 'GET' },
    { name: 'Get Movie by ID', path: '/movies/{id}', method: 'GET' },
    { name: 'Update Movie', path: '/movies/{id}', method: 'PUT', body: {
        "rentPrice": "12.99"
      }
       },
    { name: 'Delete Movie', path: '/movies/{id}', method: 'DELETE' },
    { name: 'Search Movies by Title', path: '/search/movies?title={title}', method: 'GET' },
    { name: 'Search TV Shows by Title', path: '/search/tvshows?title={title}', method: 'GET' },
    { name: 'Get All TV Shows', path: '/tvshows', method: 'GET' },
    { name: 'Get Featured TV Shows', path: '/tvshows/featured', method: 'GET' },
    { name: 'Get TV Show by ID', path: '/tvshows/{id}', method: 'GET' },
    { name: 'Update TV Show', path: '/tvshows/{id}', method: 'PUT', body: {
        "rentPrice": "12.99"
      }
       },
    { name: 'Delete TV Show', path: '/tvshows/{id}', method: 'DELETE' },
    { name: 'Create User', path: '/users', method: 'POST', body: {   "id":"15",
    "firstName": "nikita",
    "lastName": "m",
    "email": "nikitam@example.com",
      "password": "nikitam@11"
  } },
    { name: 'Authenticate User', path: '/auth/login', method: 'POST', body: {
        "email": "nikitam@example.com",
        "password": "nikitam@11"
    } },
    { name: 'Get User by ID', path: '/users/{id}', method: 'GET' },
    { name: 'Get User by Email', path: '/users/email/{email}', method: 'GET' },
    ];

    const list = document.getElementById('endpoint-list');
    endpoints.forEach((endpoint, index) => {
        let listItem = document.createElement('li');
        let link = document.createElement('a');
        link.textContent = endpoint.name;
        link.href = '#';
        link.onclick = function() {
            if (endpoint.method === 'POST') {
                fetchAndDisplayData(endpoint.path, endpoint.method, endpoint.body);
            } else {
                // Replace {id} with an actual ID or remove it from path if not applicable
                let path = endpoint.path.replace("{id}", "someId");
                fetchAndDisplayData(path, endpoint.method);
            }
            return false;
        };
        listItem.appendChild(link);
        list.appendChild(listItem);
    });
}

function fetchAndDisplayData(path, method, body = null) {
    const options = {
        method: method,
        headers: {
            'Content-Type': 'application/json'
        }
    };

    if (body) {
        options.body = JSON.stringify(body);
    }

    fetch(path, options)
        .then(response => response.json())
        .then(data => displayData(data))
        .catch(error => {
            console.error('Error fetching data:', error);
            displayError('Failed to load data.');
        });
}

function displayData(data) {
    const displayDiv = document.getElementById('data-display');
    displayDiv.innerHTML = ''; // Clear previous content
    const pre = document.createElement('pre');
    pre.textContent = JSON.stringify(data, null, 2); // Pretty print JSON data
    displayDiv.appendChild(pre);
}

function displayError(message) {
    const displayDiv = document.getElementById('data-display');
    displayDiv.innerHTML = `<p>${message}</p>`;
}
