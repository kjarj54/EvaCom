document.getElementById('activarBtn').addEventListener('click', function() {
    // Obtener el ID de la URL
    const urlParams = new URLSearchParams(window.location.search);
    const usuId = urlParams.get('id');

    if (usuId) {
        // Obtener el host de la página actual
        const host = window.location.host;
        
        // Construir la URL para la petición SOAP
        const soapUrl = `http://${host}/EvaComUNAWs/EvaComUNAWs?wsdl`;

        // Realizar la petición SOAP
        const soapRequest = `
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:con="http://controller.evacomunaws.una.ac.cr/">
                <soapenv:Header/>
                <soapenv:Body>
                    <con:activacionUsu>
                        <usuId>${usuId}</usuId>
                    </con:activacionUsu>
                </soapenv:Body>
            </soapenv:Envelope>
        `;

        const xhr = new XMLHttpRequest();
        xhr.open('POST', soapUrl, true);
        xhr.setRequestHeader('Content-Type', 'text/xml');
        
        xhr.onload = function() {
            if (xhr.status >= 200 && xhr.status < 400) {
                // Éxito
                const response = xhr.responseXML;
                const resultado = response.querySelector('resultado').textContent;
                alert(`Resultado: ${resultado}`);
            } else {
                // Error en la petición
                alert('Error en la petición');
            }
        }

        xhr.send(soapRequest);
    } else {
        alert('ID no encontrado en la URL');
    }
});
