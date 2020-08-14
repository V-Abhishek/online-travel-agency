# Online Travel Agency

<img alt="home" src="https://github.com/v-abhishek/online-travel-agency/blob/master/images/home.JPG" />

### PROJECT DESCRIPTION

Online Travel Agency (OTA) is a Web Application, designed and incorporated to allow multiple airliners to host and manage their flights on the website. Customers can view and search for flights and make reservations on the site.

---

### ARCHITECTURE

<img alt="architecture" src="https://github.com/v-abhishek/online-travel-agency/blob/master/images/architecture.JPG" />

---

### APPLICATION FEATURES AND USE CASES

1. Encrypted user passwords, leveraging BCryt, before storing user details

    <img alt="encrypt" src="https://github.com/v-abhishek/online-travel-agency/blob/master/images/encrypt.JPG" />

2. Administrator has the privilege to approve Airliner accounts and can manage to deactivate any Airliner account at any given time

    <img alt="approval" src="https://github.com/v-abhishek/online-travel-agency/blob/master/images/approval.JPG" />

    <img alt="deactivate" src="https://github.com/v-abhishek/online-travel-agency/blob/master/images/deactivate.JPG" />

3. Administrator can view all the reservations made on the site and export this data in Excel format

    <img alt="booking" src="https://github.com/v-abhishek/online-travel-agency/blob/master/images/booking.png" />

4. An activation mail is triggered to the Airliner, upon account approval from the Administrator

    <img alt="mail" src="https://github.com/v-abhishek/online-travel-agency/blob/master/images/approvalemail.png" />

5. Airliner can add new aircrafts and manage their fleet of aircrafts

    <img alt="fleet" src="https://github.com/v-abhishek/online-travel-agency/blob/master/images/fleet.JPG" />

 6. Airliner can add and manage flight schedules associated with an Aircraft

    <img alt="flight" src="https://github.com/v-abhishek/online-travel-agency/blob/master/images/flight.JPG" />

7. Customer can search for flights and filter the results based on available filters

    <img alt="search" src="https://github.com/v-abhishek/online-travel-agency/blob/master/images/search.JPG" />

8. A registered customer can make a reservation on the desired flight

    <img alt="book" src="https://github.com/v-abhishek/online-travel-agency/blob/master/images/book.JPG" />

9. A reservation confirmation mail is recieved by the customer, upon booking a flight

    <img alt="customermail" src="https://github.com/v-abhishek/online-travel-agency/blob/master/images/customermail.png" />

10. A downloadable PDF of the ticket is generated that be accesed within the user account under `My Bookings`

    <img alt="myBooking" src="https://github.com/v-abhishek/online-travel-agency/blob/master/images/mybooking.JPG" />

    <img alt="ticket" src="https://github.com/v-abhishek/online-travel-agency/blob/master/images/ticket.JPG" />

### LANGUAGE, TOOLS and FRAMEWORK

- Java
- MySQL
- Spring Framework

### SERVER

- Apache Tomcat

### TEAM INFORMATION

NAME | GIT ID
------------ | -------------
ABHISHEK VISHWANATH | [v-abhishek](https://github.com/v-abhishek)

### APPLICATION DEPLOYMENT

- Ensure that you have installed Java (Java 8 or above) on your machine
- Clone or download this repository on to your machine
- Install NetBeans IDE, if not present
- Import the cloned or downloaded project
- Download and install Apache Tomcat 8 or above
- Integrate Tomcat with Netbeans and run the application
                    OR
  build and generate a WAR file of the application and deploy it in the tomcat server by placing the previously generated WAR file in `webapps` folder of the server                  
- Register an Airliner and a Customer
- Login as Administrator, enter Username and Password as `admin@travel.com` and `admin1` respectively and approve previously registered airliner account 