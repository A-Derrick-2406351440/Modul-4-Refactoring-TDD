# Advanced Programming EShop

# Reflection

## Module 1

### Reflection 1.1

- Dalam pengerjaan modul ini, saya telah menerapkan beberapa prinsip Clean Code dan Secure Coding.

- Pertama, saya menerapkan prinsip Single Responsibility Principle (SRP) dengan memisahkan kode ke dalam lapisan yang berbeda seperti Controller untuk menangani request HTTP, Service untuk logika bisnis, dan Repository untuk interaksi data. Penamaan variabel dan fungsi juga saya buat deskriptif, seperti createProductPost dan findAll, sehingga kode menjadi self explain tanpa perlu comment.

- Dari sisi Secure Coding dan standar web, saya tidak hanya menggunakan GET dan POST, melainkan juga memanfaatkan HTTP method yang lebih spesifik yaitu PUT untuk edit dan DELETE untuk menghapus produk, sesuai dengan prinsip RESTful. Saya juga menggunakan UUID untuk ID produk guna memastikan keunikan dan keamanan data yang lebih baik dibanding integer biasa.

- Saya juga mendapatkan pelajaran bahwa clean code dimulai dari penamaan, setiap function harus memiliki tugasnya masing-masing dan jelas, comment menjelaskan secukupnya. 

- Kekurangan yang saya temukan adalah belum adanya validasi input yang ketat (misalnya mencegah nama produk kosong di sisi server) dan belum adanya mekanisme autentikasi/otorisasi. Saat ini, siapa saja bisa menghapus atau mengedit produk.

- Untuk kedepannya error dapat di handle dengan memberikan informasi kepada pengguna apa yang terjadi pada setiap fitur yang dijalankan. Lalu, dapat diimplementasikan autentikasi login untuk pengguna sehingga setiap orang memiliki list productnya sendiri. Untuk bagian delete dan edit dapat ditambah autorisasi sehingga hanya pengguna yang memiliki akses yang dapat melakukan action tersebut.

### Reflection 1.2

- Setelah menulis unit test, saya merasa lebih percaya diri untuk melakukan perubahan atau refactoring karena saya memiliki jaring pengaman yang akan memberitahu jika ada fitur yang rusak. Namun, saya menyadari bahwa 100% code coverage tidak menjamin kode bebas bug. Coverage hanya menghitung persentase baris kode yang dieksekusi saat tes, namun tidak memastikan bahwa logika bisnis benar dalam segala skenario. Logical Error atau edge cases yang tidak terpikirkan saat menulis tes masih bisa terjadi meskipun baris kodenya sudah ter-cover. Oleh karena itu, code review dan pengujian skenario negatif tetap krusial.

- Jika saya membuat class functional test baru dengan menyalin prosedur setup dan variabel instance yang sama persis dari CreateProductFunctionalTest.java, itu akan menurunkan kualitas kode karena melanggar prinsip Don't Repeat Yourself. Terjadi duplikasi kode pada bagian konfigurasi port, base URL, dan setup server. 

- Isu ini akan menyulitkan pemeliharaan, karena jika suatu saat mekanisme setup berubah, saya harus mengubahnya di semua file test satu per satu.

## Module 2
- Ketika saya mengerjakan tugas ini, saya mendapatkan code quality issue. Pertama, penggunaan method yang tidak ada isinya di ProductRepository.java yang saya fix dengan menghapus methodnya. Kedua, rename method Homepage menjadi homePage di HomepageController.java. Ketiga, mengganti variabel lokal di method testHomePage dari controller menjadi controll agar nama variabelnya tidak bertabrakan dengan variabel global yang sudah di set di InjectMocks. Keempat, menghilangkan public modifier yang ada di ProductTeset.java agar kodenya menjadi compliant. Kelima, mengganti posisi jawaban testcase antara dari actual,expected menjadi expected,actual agar sesuai dengan penggunaan assertEquals
- Saya juga menambahkan berbagai test case baru agar semua coveragenya 100%
- Menurut saya implementasi CI/CD workflow saya sudah mengikuti definisi Continuous Integration and Continuous Deployment. Code saya akan melalui proses yang berkelanjutan sesuai definisi CI/CD. Kode ini akan secara otomatis secara Continuous Integration menggunakan Github Actions. Kode akan dilakukan pengecekan dan analisis secara otomotasi menggunakan Scorecard dan Sonarcloud. Untuk Continuous Deployment sendiri saya sudah menggunakan koyeb sebagai PaaS untuk CD nya. Jadi untuk setiap perubahan akan dilakukan proses ini. Oleh karena itu, saya merasa kode saya sudah menerapkan implementasi CI/CD workflow.

## Module 3
- Explain what principles you apply to your project!
    - S (Single Responsibility Principle)
        - Pertama, pada bagian `ProductController` awalnya saya meletakkan bagian `HomePage()` pada `ProductController`. Ini tidak menerapkan SRP karena `HomePage` seharusnya memiliki tanggung jawab tersendiri untuk halaman home yang berbeda fungsionalitas . Oleh karena itu saya membuat class baru `HomeController`
        - Kedua, pada bagian `ProductController` terdapat class `CarController` yang extends `ProductController`.`CarController` seharusnya dipisah menjadi bagian tersendiri karena `CarController` seharusnya memiliki tanggung jawab tersendiri untuk halaman car yang berbeda fungsionalitas.

    - O (Open-Closed Principle)
        - Penerapan OCP terlihat pada penggunaan interface `CarService` di dalam `CarController`. Dengan menghubungkan controller ke interface, kode saya menjadi "Open for Extension" namun "Closed for Modification". Artinya, jika di masa depan saya ingin mengubah cara penyimpanan data mobil, saya cukup membuat class implementasi baru, misalnya `CarServiceDatabase` tanpa perlu mengubah satu baris kode pun di `CarController`.

    - L (Liskov Substitution Principle)
        - Pada code saya, awalnya `CarController` merupakan subclass `ProductController`. Namun, kedua hal ini berbeda fungsi dan `CarController` tidak dapat menggantikan `ProductController`. Oleh karena itu, saya pisah menjadi dua class yang berbeda.

    - I (Interface segregation Principle)
        - Dengan memisahkan implementasi `CarService` dan `ProductService`, kita dapat memenuhi prinsip ini, yang mana setiap interface memenuhi tanggung jawab nya masing-masing sesuai klien tertentu.

    - D (Dependency Inversions Principle)
        - Pada bagian `CarController` terdapat `private CarServiceImpl carService`. Berdasarkan prinsip DIP seharusnya saya bergantung pada level sebuah interface atau fungsi abstrak dibandingkan level class atau function yang concrete. Oleh karena itu, saya ubah menjadi `private CarService carService` yang bergantung pada interface.

-  Explain the advantages of applying SOLID principles to your project with examples.
    - Kode saya menjadi lebih terstruktur. Contohnya dengan SRP, jika saya perlu mengubah logika validasi pembuatan mobil, saya tahu persis saya hanya perlu membuka `CarController` atau `CarService` tanpa takut merusak fitur `Product` atau `HomePage`.
    - Prinsip DIP dan SRP membuat Unit Test jauh lebih mudah dibuat. Karena `CarController` sekarang bergantung pada interface `CarService`, saya bisa dengan mudah membuat Mock Object untuk `CarService` saat testing, tanpa perlu memikirkan implementasi database atau logika bisnis yang rumit di `CarServiceImpl`. 
    - Kode lebih mudah dipahami oleh orang lain karena class yang kecil dengan nama yang deskriptif seperti `CarController` yang hanya mengurus mobil sehingga jauh lebih mudah dibaca daripada satu `ProductController` yang mengurus semua hal. 
    - Menambah fitur baru menjadi lebih aman. Jika nanti ada fitur `Motorcycle`, saya bisa membuatnya tanpa mengganggu kode `Car` atau `Product` yang sudah berjalan stabil.

-  Explain the disadvantages of not applying SOLID principles to your project with examples.
    - Apabila tidak menerapkan prinsip solid, code kita akan lebih susah untuk dijaga karena code sangat complex dan susah dipahami. Contohnya, ketika kita tidak menerapkan SRP, maka kita akan kebingungan terkait fungsi code tersebut karena adanya hubungan dengan method lain.
    - Code kita akan lebih susah diuji karena tiap fungsionalitas tidak independen yang menyebabkan proses pencarian bug akan sulit karena banyaknya bagian yang terlibat. Contohnya, kita ingin test suatu fungsi di `CarController`. Namun dengan bergantungnya pada fungsi lain kita akan kebingungan mencari bagian mana yang menyebabkan bug.
    - Penambahan fitur baru akan lebih sulit karena ada kemungkinan kita dapat memunculkan bug baru yang disebabkan bagian lain. Hal ini tentunya akan menjadi kesulitan saat ingin menelusuri bagian yang mana menyebabkan fitur tidak berjalan. 

## Module 4
-   Menurut saya, dengan penerapan TDD flow pengerjaan jadi lebih jelas dan cepat. Dengan membuat test terlebih dahulu, maka saat kita implementasi kita sudah benar-benar bisa memastikan dan memaintain kebenaran code kita.
-   Menurut saya, code saya sudah mengimplementasikan F.I.R.S.T 
  - Fast: Karena sudah membuat testing dengan efisien. 
  - Isolated: Tes berdiri tidak bergantung pada tes yang lain. 
  - Repeatable: Tes dapat diulang dengan hasil yang konsisten. 
  - Self-validating: Tes memberikan hasil pass atau fail melalui asersi tanpa intervensi manual. 
  - Timely: Tes dibuat sebelum implementasi kode.
