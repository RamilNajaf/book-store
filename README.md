# ingress-task

## run etmək üçün :

           1: mvn clean package
           2: docker-compose up
           
           
## Readme ile davam edə bilmək  üçün postman collectionu postmana import edin : 

   Postman Collections to import [click here](https://github.com/RamilNajaf/ingress-task/blob/Main/bookstore_main.postman_collection)

## Istifadə olunan texnologiyalar:
           
      - Maven
      - Java 11
      - Spring BOOT (Web, Security)
      - JWT
      - JPA
      - Docker  
      - H2 in-memory relational database
      - JUnit & Mockito  - (Unit & Integration Tests)
      - Mapstruct   
      
## Ümumi baxış:
      3 tip user role var : Rolu olmayan  , 'Publisher' - rolu olan, 'ADMIN' -rolu olan
           - Rolu olmayan istifidəçi : sadəcə kitab (book entity ) və müəlliflərə (author entity) baxa bilər. Silə və ya editləyə bilməz.
             Qeydiyyatdan keçdikdə yaranan user rolu olmayan istifadəçidir
             
           - Publisher :  müəllifləri (author) yarada, silə və editləyə bilər . Kitab yarada bilər,ancaq, yalnız və yalnız öz yaratdığı kitabı silə və ya editləyə bilər (Publisher id -si öz id -si olan kitabları)
           
           - Admin :  Uygun olmayan Kitabları silə və ya editləyə bilər .İstənilən istifadəçiyə publisher rolu verə bilər,hətta özünə belə . 
                      Kitab və ya Müəlllifi yalnız özünü publisher etdikdən sonra yarada bilər. Default olaraq Publisher roluna malik deyik.
       
      App run olunduqda command line runner ilə bir publisher və  bir admin insert olunur.
        - Publisher: username - "ramil" , password - "ramil"
        - Admin:     username - "hesen" , password - "hesen"

          
      
    
