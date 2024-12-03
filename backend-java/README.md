# DuurzameWebshop app info

## Docker containers info:

### Start the containers

The containers can be started with the `start_conatainer.bat` script.

To remove the containers, use `clean_up_containers.bat` script.

**Containers names:**

The app has following two containers:
1. `java_mysql_exam_container`: a mysql container (port: 3306)
2. `java_phpmyadmin_exam_container`: a phpmyadmin container (port: 8085)


**Docker networkname:** `java_exam_network`

**Docker volume name:** `java_mysql_exam_volume`



### Database names:
- products_db
- shoppingcart_db
- logbook_db

### phpmyadmin credentials:

user: user <br>
password: password

## Microservices info:

### productcatalogus_service
port: 8081

### winkelwagen-service
port: 8082

### logboek-service
port: 8083