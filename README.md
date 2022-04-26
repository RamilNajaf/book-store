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
             
           - Publisher :  müəllifləri (author) yarada, silə və editləyə bilər .
                          Kitab yarada bilər,ancaq, yalnız və yalnız öz yaratdığı kitabı silə və ya editləyə bilər
                          (Publisher id -si öz id -si olan kitabları)
           
           - Admin :  Uygun olmayan Kitabları silə və ya editləyə bilər. İstənilən istifadəçiyə publisher rolu verə bilər,hətta özünə belə . 
                      Bütün istifadəçiləri görə bilər
                      Kitab və ya Müəlllifi yalnız özünü publisher etdikdən sonra yarada bilər. 
                      Default olaraq Publisherin edtiklərin edə bilmir.
       
      App run olunduqda command line runner ilə bir publisher və  bir admin insert olunur.
        - Publisher: username - "ramil" , password - "ramil"
        - Admin:     username - "hesen" , password - "hesen"

## Endpointlərə detallı baxış:
 task-ı rahat test edə bilmək üçün üçün yuxarıdakı postman collectionu postmana import edin və aşağıda göstərilən şəkildə yoxlayın
         
         1. Postmanda  auth folderi -  AuthContoller
         
                -- /api/signup (POST)  -> Qeydiyyatdan keçmək üçün.Username və Email unique olmalıdır.Yaranan istifadəçinin rolu olmur.
                
                -- /api/login  (POST)  -> Login olmaq üçün.
                
                -- /api/me     (GET)   -> Authenticated olmuş istifadəçiyə baxış üçün.Authentication teleb edir
                
         2. Postmanda admin folderi - AdminController
                -- /api/admin/users  (GET)  ->  Bütün istifadəçilərin məlumatların görmək üçün. Ancaq Admin role -ilə access var
                
                -- /api/admin/{userİd}/add_publisher_role  (PUT) -> Istifadəçilərə pubisher rolu vermək üçündür.Ancaq Admin role -ilə access var.Admin özünü də publisher edə bilər.
                
         3.Postmanda author folderi - AuthorController
            --    /api/authors (POST) -> müəllif paylaşmaq üçündür.Publisher Role - ilə access var
            
            --    /api/authors (GET) -> müəllifləri  görmək üçündür . Authentication teleb edir.
            
            --    /api/authors/{id} (GET) -> tək müəllifi görmək üçün . Authentication teleb edir
            
            --    /api/authors/{id} (DELETE) -> Silmək üçün. Publisher Role - ilə access var
            
            --    /api/authors/{id} (PUT) -> Update üçün. Publisher Role - ilə access var
            
         4.Postmanda book folderi - BookController
         
            --    /api/books (POST) -> kitab paylaşmaq üçündür. Publisher Role - ilə access var.
            
            --    /api/books (GET) -> kitabları  görmək üçündür . Authentication teleb edir.
            
            --    /api/books/{id} (GET) -> tək kitabI görmək üçün . Authentication teleb edir
            
            --    /api/books/find_specific_book (GET) -> kitabları  parametrlə axtarmaq və ya filter üçün.Pagination Support.Authenticatin tələb edir.
            
            --    /api/books/{id} (DELETE) -> Silmək üçün.Admin Role - ilə access var.Sadəcə admin istənilən kitabı silə bilər
           
            --    /api/books/{id} (PUT) -> Update üçün.Admin Role - ilə access var.Sadəcə admin istənilən kitabı update edə bilər
            
            --    /api/books/{id}/update_by_publisher (PUT) -> Publisherin öz paylaşdığı kitabı yeniləməsi üçün.Publisher role tələb edir.
            
            --    /api/books/{id}/delete_by_publisher (Delete) -> Publisherin öz paylaşdığı kitabı silməsi üçün.Publisher role tələb edir.
            
