Correr:
New Reservation:
    http://localhost:8080/api/flights/reservations/reservation

{

"passengers": [
{
"firstName": "Said",
"lastName": "Olano",
"documentNumber": "abc123",
"documentType": "INE",
"birthday": "2000-01-01"
},
{
"firstName": "Saida",
"lastName": "Olano",
"documentNumber": "abc1234",
"documentType": "INE",
"birthday": "2001-02-02"
}
],
"itinerary": {
"segment": [
{
"origin": "RIO",
"destination": "COR",
"departure": "SPAIN",
"arrival": "JAMAICA",
"carrier": "AA"
},
{
"origin": "MEX",
"destination": "BOG",
"departure": "FRANCE",
"arrival": "JAMAICA",
"carrier": "AA"
}
],
"price": {
"totalPrice": "100",
"totalTax": "200",
"basePrice": "90"
}
},
"creationDate": "2000-01-01"
}

---------
Get All reservations:
Asincronamente:
curl --location 'http://localhost:8080/api/flights/reservations/reservation' \
--header 'Accept: application/stream+json'


