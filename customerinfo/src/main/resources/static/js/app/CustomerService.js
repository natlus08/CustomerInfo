'use strict';
angular.module('CustomerInfoApp').factory('CustomerService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllCustomers: loadAllCustomers,
                getAllCustomers: getAllCustomers,
                getCustomer: getCustomer,
                createCustomer: createCustomer,
                updateCustomer: updateCustomer,
                removeCustomer: removeCustomer
            };

            return factory;

            function loadAllCustomers() {
                console.log('Fetching all customers');
                var deferred = $q.defer();
                $http.get(urls.CUSTOMER_SERVICE_API + urls.READ_ALL)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all customers');
                            $localStorage.customers = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading customers');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllCustomers(){
                return $localStorage.customers;
            }

            function getCustomer(id) {
                console.log('Fetching Customer with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.CUSTOMER_SERVICE_API + urls.READ_ONE + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully customer with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading customer with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createCustomer(customer) {
                console.log('Creating Customer');
                var deferred = $q.defer();
                $http.post(urls.CUSTOMER_SERVICE_API + urls.CREATE, customer)
                    .then(
                        function (response) {
                            loadAllCustomers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Customer : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateCustomer(customer, id) {
                console.log('Updating Customer with id '+id);
                var deferred = $q.defer();
                $http.put(urls.CUSTOMER_SERVICE_API + urls.UPDATE, customer)
                    .then(
                        function (response) {
                        	loadAllCustomers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Customer with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeCustomer(id) {
                console.log('Removing Customer with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.CUSTOMER_SERVICE_API + urls.DELETE + id)
                    .then(
                        function (response) {
                        	loadAllCustomers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Customer with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);