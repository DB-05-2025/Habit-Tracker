# Habit Tracker

Aplikasi **Habit Tracker** adalah aplikasi Android yang membantu pengguna membentuk dan memonitor kebiasaan harian mereka. Aplikasi ini dirancang dengan tampilan yang sederhana namun fungsional, memungkinkan pengguna untuk menambahkan, melihat, dan menghapus kebiasaan sesuai dengan rutinitas mereka.

---

## 📲 Link APK

Kamu bisa mengunduh APK terbaru dari aplikasi ini melalui link berikut:

👉 [Download APK Habit Tracker](https://example.com/link-to-apk)  
*(Ganti link di atas dengan link Google Drive/Dropbox sebenarnya)*

---

## 📖 Dokumentasi

### Fitur Utama

- ✅ Tambah kebiasaan baru dengan nama dan durasi
- 📆 Filter kebiasaan berdasarkan urutan: prioritas atau berdasarkan waktu
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
- Android Jetpack (ViewModel, LiveData, Room, Data Binding)
- MVVM Architecture
- RecyclerView
- Material Design Components

---

## 🧭 Manual Book (Panduan Penggunaan)

### 1. Install Aplikasi
Unduh APK dari link di atas, lalu install di perangkat Android kamu. Pastikan pengaturan “Install from unknown sources” telah diaktifkan.

### 2. Menambahkan Habit
- Klik tombol **Add Habit (+)**.
- Isi nama kebiasaan dan durasi waktu (dalam menit).
- Simpan kebiasaan untuk mulai tracking.

### 3. Melihat & Menyaring Habit
- Habit akan ditampilkan dalam daftar.
- Gunakan opsi penyaringan di atas daftar untuk mengurutkan berdasarkan durasi atau prioritas.

### 4. Menghapus Habit
- Tap ikon **hapus** pada item habit yang ingin dihapus.

---

## 💡 Catatan Tambahan

- Aplikasi berjalan secara offline.
- Tidak memerlukan login atau koneksi internet.
- Data tersimpan lokal di perangkat menggunakan Room Database.

---

## 🧑‍💻 Kontributor

Proyek ini dikembangkan oleh tim **DB-05-2025**  
📍 Repo: [Habit Tracker GitHub](https://github.com/DB-05-2025/Habit-Tracker)

---
