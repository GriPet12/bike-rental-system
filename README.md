# City bike rental management system
## [C4 Model](https://drive.google.com/file/d/1B0YqFhIPC5O9Iab_oIx6Y92gEqxcaK4J/view?usp=sharing)

## Main components:
- **API Gateway (Spring Cloud Gateway)**: Proxy requests to different microservices.
- **Service Account**: Manages user registration, authentication, and profile management.
- **Service Bike**: Responsible for managing bicycles, their status and availability.
- **Service Map**: Displays the location of bicycles and tracks them in real time.
- **Service Payment**: Manages payments, including calculating rental costs and processing transactions.
- **Kafka**: Mechanism for exchanging events between services.
- **Vue.js**: A front-end framework for displaying the user interface and interacting with microservices.

## Technologies
- Spring Boot 3.4.4
- Spring Cloud Gateway
- Kafka
- Docker
- Maven
- Vue.js

### Functional Requirements:

**User:**
- Can register an account in the system
- Can log in and log out of the system
- Can view available bikes on the map
- Can rent a bike by scanning a QR code
- Can end rental and return a bike at any station
- Can view personal rental history
- Can view current rental status and time
- Can report problems with a bike
- Can manage payment methods
- Can purchase subscription plans

**Admin:**
- Can log in to the admin panel
- Can add a new admin to the system
- Can add, edit, and remove bikes from the system
- Can manage bike stations and their capacity
- Can view reports on bike usage and availability
- Can track bike locations in real-time
- Can manage user accounts and resolve issues
- Can generate usage reports and analytics
- Can set pricing and promotional offers
- Can mark bikes for maintenance

**System:**
- Must track bike locations in real-time
- Must calculate rental fees based on usage time
- Must manage bike locks (unlock/lock)
- Must notify users about rental expiration
- Must generate invoices for completed rentals

### Non-Functional Requirements:

**Performance:**
- System must handle at least 1000 concurrent users
- API response time should be under 200ms for 95% of requests
- Location updates must occur at least every 30 seconds

**Reliability:**
- System availability must be 99%
- Data backup must occur hourly
- System must function offline for critical operations when connectivity is lost

**Security:**
- Payment information must comply with PCI DSS
- User authentication must use secure protocols
- System must prevent unauthorized bike unlocking

**Usability:**
- Interface must be accessible for users with disabilities
- System must support at least 3 languages

**Scalability:**
- System must scale to support 10,000 bikes and 100,000 users
- Must allow for easy addition of new stations and service areas
