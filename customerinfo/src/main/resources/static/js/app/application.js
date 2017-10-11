var customerApplication = angular.module('CustomerInfoApp',['ngStorage','ui.bootstrap']);

customerApplication.constant('urls', {
    BASE: 'http://localhost:8080/widget',
    CUSTOMER_SERVICE_API : 'http://localhost:8080/widget/customer/',
    READ_ALL : 'read-all',
    READ_ONE : 'read/',
    CREATE : 'create',
    UPDATE : 'update',
    DELETE : 'delete/'    
});

customerApplication.constant('constants', {
    STATES : ['Andaman & Nicobar Islands','Andhra Pradesh','Arunachal Pradesh','Assam','Bihar','Chandigarh','Chhattisgarh','Dadra & Nagar Haveli','Daman & Diu','Delhi (National Capital Territory of Delhi or NCT)','Goa','Gujarat','Haryana','Himachal Pradesh','Jammu & Kashmir','Jharkhand','Karnataka','Kerala','Lakshadweep','Madhya Pradesh','Maharashtra','Manipur','Meghalaya','Mizoram','Nagaland','Orissa','Puducherry','Punjab','Rajasthan','Sikkim','Tamil Nadu','Telangana','Tripura','Uttar Pradesh','Uttarakhand','West Bengal']    
});
