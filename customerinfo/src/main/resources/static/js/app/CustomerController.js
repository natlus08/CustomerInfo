'use strict';
angular.module('CustomerInfoApp').controller('CustomerController',
    ['CustomerService', '$scope', 'constants', function( CustomerService, $scope, constants) {

        var self = this;
        self.customer = {};
        self.customers=[];

        self.submit = submit;
        self.getAllCustomers = getAllCustomers;
        self.createCustomer = createCustomer;
        self.updateCustomer = updateCustomer;
        self.removeCustomer = removeCustomer;
        self.editCustomer = editCustomer;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
        self.onlyAlphabets = /^[a-zA-Z ?]*$/;
        
        self.states = constants.STATES;
        
        self.validate = false;
        
        function submit() {
        	self.validate = true;
        	if($scope.customerForm.$valid){
        		self.validate = false;
				console.log('Submitting');
	            if (self.customer.id === undefined || self.customer.id === null) {
	                console.log('Saving New Customer', self.customer);
	                createCustomer(self.customer);
	            } else {
	                updateCustomer(self.customer, self.customer.id);
	                console.log('Customer updated with id ', self.customer.id);
	            }
			}
        }

        function createCustomer(customer) {
            console.log('About to create customer');
            CustomerService.createCustomer(customer)
                .then(
                    function (response) {
                        console.log('Customer created successfully');
                        self.successMessage = 'Customer created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.customer={};
                        $scope.customerForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Customer');
                        self.errorMessage = 'Error while creating Customer: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateCustomer(customer, id){
            console.log('About to update customer');
            CustomerService.updateCustomer(customer, id)
                .then(
                    function (response){
                        console.log('Customer updated successfully');
                        self.successMessage='Customer updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.customerForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Customer');
                        self.errorMessage='Error while updating Customer '+errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function removeCustomer(id){
            console.log('About to remove Customer with id '+id);
            CustomerService.removeCustomer(id)
                .then(
                    function(){
                        console.log('Customer '+id + ' removed successfully');
                        self.successMessage='Customer removed successfully';
                        self.errorMessage='';
                    },
                    function(errResponse){
                    	self.errorMessage='Error while removing Customer '+id +', Error :'+errResponse.data.errorMessage;
                        self.successMessage='';
                        console.error('Error while removing Customer '+id +', Error :'+errResponse.data.errorMessage);
                    }
                );
        }


        function getAllCustomers(){
            return CustomerService.getAllCustomers();
        }

        function editCustomer(id) {
            self.successMessage='';
            self.errorMessage='';
            CustomerService.getCustomer(id).then(
                function (customer) {
                    self.customer = customer;
                },
                function (errResponse) {
                    console.error('Error while editing Customer ' + id + ', Error :' + errResponse.data.errorMessage);
                }
            );
        }
        function reset(){
        	self.validate = false;
            self.successMessage='';
            self.errorMessage='';
            self.customer={};
            $scope.customerForm.$setPristine(); //reset Form
        }
        
    }
    ]);