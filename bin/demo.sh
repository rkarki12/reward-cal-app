#!/bin/bash
echo "Creating customers ... "
curl --location --request POST 'localhost:8080/customers' \
--header 'Content-Type: application/json' \
--data-raw '[
    {
        "name": "customer-1"
    },
    {
        "name": "customer-2"
    },
    {
        "name": "customer-3"
    },
    {
        "name": "customer-4"
    }
]'
echo
echo "Listing customers ..."
curl --location --request GET 'localhost:8080/customers' \
--data-raw ''
echo
echo "Adding purchases ..."
curl --location --request POST 'localhost:8080/customers/1/purchases' \
--header 'Content-Type: application/json' \
--data-raw '[
    {
        "amount": 120.00,
        "timestamp": "2022-07-22T02:41:37.909240026Z"
    },
    {
        "amount": 450.00,
        "timestamp": "2022-05-22T02:41:37.909240026Z"
    },
    {
        "amount": 360.00,
        "timestamp": "2022-06-22T02:41:37.909240026Z"
    },
    {
        "amount": 180.00,
        "timestamp": "2022-07-02T02:41:37.909240026Z"
    }
]'
curl --location --request POST 'localhost:8080/customers/2/purchases' \
--header 'Content-Type: application/json' \
--data-raw '[
    {
        "amount": 720.00,
        "timestamp": "2022-06-20T02:41:37.909240026Z"
    }
]'
curl --location --request POST 'localhost:8080/customers/3/purchases' \
--header 'Content-Type: application/json' \
--data-raw '[
    {
        "amount": 90.00,
        "timestamp": "2022-05-05T02:41:37.909240026Z"
    }
]'
curl --location --request POST 'localhost:8080/customers/4/purchases' \
--header 'Content-Type: application/json' \
--data-raw '[
    {
        "amount": 540.00,
        "timestamp": "2022-07-18T02:41:37.909240026Z"
    }
]'
echo
echo "Getting rewards ..."
echo "Getting all rewards ..."
curl --location --request GET 'localhost:8080/rewards' \
--data-raw '' -w "\n"r
echo "Getting  rewards for customerId 1 ..."
curl --location --request GET 'localhost:8080/rewards?customerId=1' \
--data-raw '' -w "\n"
echo "Getting  rewards for customerId 2 ..."
curl --location --request GET 'localhost:8080/rewards?customerId=2' \
--data-raw '' -w "\n"
echo "Getting  rewards for customerId 3 ..."
curl --location --request GET 'localhost:8080/rewards?customerId=3' \
--data-raw '' -w "\n"
echo "Getting  rewards for customerId 4 ..."
curl --location --request GET 'localhost:8080/rewards?customerId=4' \
--data-raw ''
echo
