<img src="https://raw.githubusercontent.com/stefanusj/NotesMe/master/app/src/main/ic_launcher-playstore.png" width="100">

# Notes Me [![NotesMe](https://img.shields.io/badge/APK-brown?style=for-the-badge&logo=android)](https://github.com/stefanusj/NotesMe/raw/master/apk/NotesMe.apk)

[![platform](https://img.shields.io/badge/platform-Android-yellow.svg?style=flat-square)](https://www.android.com)
[![API](https://img.shields.io/badge/API-21%2B-green?style=flat-square)](https://android-arsenal.com/api?level=21)

**NotesMe** adalah contoh aplikasi yang mengimplementasikan *Modern Android App Architecture* dengan backend Firebase. 
Aplikasi ini dibuat dengan menggunakan kotlin dan MVVM pattern serta Architecture Componen

## Tentang Aplikasi
Aplikasi ini merupakan aplikasi sederhana dimana kita bisa menyimpan catatan ke dalam cloud.
Aplikasi ini menampilkan daftar notes pada halaman awal, yang apabila salah satu ditekan akan menampilkan isi dari notes tersebut yang juga bisa untuk mengubahnya.
Aplikasi ini bisa bekerja dalam mode offline dan akan otomatis tersinkronisasi ke cloud ketika terhubung oleh internet.

Aplikasi ini dibuat dengan harapan bisa sebagai contoh dalam pembuatan aplikasi terintegrasi dengan firebase yang juga ingin menerapkan best practice dari google.

## Fitur aplikasi:
- Menyimpan catatan keseharian.
- Terdapat kustomisasi warna untuk setiap notes.
- Terhubung dengan akun google yang diintegrasikan melalui firebase.
- dan masih banyak lagi.

## Screenshots

Beberapa screenshot aplikasi

<kbd><img src="https://stefanusj.com/storage/portfolios/notes-me.jpg"  width="500" height="500"></kbd>

## Library pendukung
- [Kotlin](https://kotlinlang.org/) - Bahasa pemrograman android resmi dari Google.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Kumpulan komponen arsitektur pendukung untuk membuat aplikasi.
  - [DataBinding](https://developer.android.com/topic/libraries/data-binding) - Membuat class untuk setiap layout yang bisa menyimpan dan menampilkan tipe data terkait untuk UI.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Tipe data observable yang mendukung lifecycle android.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Tempat menyimpan dan mengelola data terkait UI. 
  - [Coroutines](https://developer.android.com/topic/libraries/architecture/coroutines) - Asynchronous dan lainnya
  - [Navigation](https://developer.android.com/guide/navigation) - Interaksi navigasi antar fragment yang terdapat dalam aplikasi. 
- [Firebase](https://firebase.google.com/) - Backend utama aplikasi.
  - [Analytics](https://firebase.google.com/docs/analytics) - Google Analytics untuk mendeteksi aktivitas pengguna.
  - [Authentication](https://firebase.google.com/docs/auth) - Autentikasi OAuth google.
  - [Firestore](https://firebase.google.com/docs/firestore) - Database utama aplikasi yang terdapat di cloud.
  - [Crashlytics](https://firebase.google.com/docs/crashlytics) - Analisis crash yang terjadi pada aplikasi.
- [Material Components for Android](https://material.io/develop/android/) - Material Design UI untuk Android.
- [Koin](https://insert-koin.io) - Dependency Injection
- [ColorSheet](https://github.com/msasikanth/ColorSheet) - Bottom Sheet untuk pemilihan warna notes.
- [Coil](https://coil-kt.github.io/coil/) - Loader gambar yang support coroutines.
