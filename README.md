# Habit Tracker

Aplikasi **Habit Tracker** adalah aplikasi Android yang membantu pengguna membentuk dan memonitor kebiasaan harian mereka. Aplikasi ini dirancang dengan tampilan yang sederhana namun fungsional, memungkinkan pengguna untuk menambahkan, melihat, dan menghapus kebiasaan sesuai dengan rutinitas mereka.

---

## 📲 Link APK

Kamu bisa mengunduh APK terbaru dari aplikasi ini melalui link berikut:

👉 [Download APK Habit Tracker](https://drive.google.com/drive/folders/169nOLeKQyCLCbxiX7mSL9IHIxGFoDQQa?usp=drive_link)  

---

## 📖 Dokumentasi

### Fitur Utama

- ✅ Tambah kebiasaan baru dengan nama dan durasi
- 📆 Habit Tracking dan Habit Stacking
- 📊 Statistik sederhana untuk melihat progres
- 🗑️ Hapus kebiasaan yang tidak lagi relevan

### Struktur Proyek (Modul Utama)
├── ui
│ ├── MainActivity.kt
│ ├── AddHabitActivity.kt
│ └── ViewModelFactory.kt
├── data
│ ├── Habit.kt
│ ├── HabitDao.kt
│ ├── HabitDatabase.kt
│ └── HabitRepository.kt
├── utils
│ └── SortType.kt

### Teknologi yang Digunakan

- Kotlin
- Android Jetpack (ViewModel, StateFlow, Room, Data Binding)
- MVVM Architecture
- Material Design Components

---

## 🧭 Manual Book (Panduan Penggunaan)

### 1. Install Aplikasi
Unduh APK dari link di atas, lalu install di perangkat Android kamu. Pastikan pengaturan “Install from unknown sources” telah diaktifkan.

### 2. Mengisi Nama Pengguna
- Isi nama pengguna
- Simpan kebiasaan untuk mulai Habit Tracker App.

### 2. Menambahkan Habit
- Klik tombol **Add Habit (+)**.
- Isi nama kebiasaan dan durasi waktu (dalam menit).
- Simpan kebiasaan untuk mulai tracking.

### 3. Habit Tracking dan Habit Stacking
- Habit Tracking akan ditampilkan dalam daftar. bisa add, edit, delete
- Habit Stacking akan ditampilkan dalam daftar. bisa add, edit, delete

### 4. Menghapus Habit
- Tap ikon **hapus** pada item habit / habitstack yang ingin dihapus.

### 5. Mengedit Habit
- Tap ikon **edit** pada item habit / habitstack yang ingin diedit.

---

## 💡 Catatan Tambahan

- Aplikasi berjalan secara offline.
- Tidak memerlukan login atau koneksi internet.
- Data tersimpan lokal di perangkat menggunakan Room Database.

---

## 🧑‍💻 Kontributor

Proyek ini dikembangkan oleh tim **DB5-PS001 2025**  
📍 Repo: [Habit Tracker GitHub](https://github.com/DB-05-2025/Habit-Tracker)

---
