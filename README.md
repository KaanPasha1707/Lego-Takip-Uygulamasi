# LEGO Koleksiyon Takip Uygulaması

Bu proje, LEGO koleksiyonerlerinin sahip oldukları setleri düzenli bir şekilde kaydetmelerini, maliyet analizlerini yapmalarını ve görselleriyle birlikte görüntülemelerini sağlayan, Java Swing tabanlı bir masaüstü uygulamasıdır. Proje, GUI tasarımı, dosya girdi/çıktı (File I/O) işlemleri ve Event Listeners konularında pratik yapmak amacıyla geliştirilmiştir.

## 🏗️ Özellikler ve Kullanım

Uygulama, temel bir CRUD (Oluştur, Oku, Güncelle, Sil) mantığıyla çalışarak yerel bir metin dosyası üzerinden veri kalıcılığı sağlar:

*   **Set Ekleme & Güncelleme:** Tema, Set Adı, Seri Numarası, Parça Sayısı, Alış/Güncel Fiyat, Adet ve Açıklama gibi tüm detayları forma girerek koleksiyonunuza yeni setler ekleyebilir veya mevcut olanları güncelleyebilirsiniz.
*   **Finansal Takip (Kar/Zarar):** Uygulama, kayıtlı setlerin alış fiyatı ile güncel fiyatını karşılaştırarak koleksiyonun toplam kar/zarar durumunu anlık olarak hesaplar ve renkli (yeşil/kırmızı) metin ile kullanıcıya sunar.
*   **Görsel Desteği:** `JFileChooser` kullanılarak her set için bilgisayardaki yerel bir resim dosyası seçilebilir. Listeden bir sete çift tıklandığında, ilgili LEGO setinin resmi bağımsız yeni bir pencerede açılır.
*   **Otomatik Sıralama:** Koleksiyona yeni bir öğe eklendiğinde veya güncellendiğinde, liste otomatik olarak alfabetik sıraya dizilir.
*   **Veri Kalıcılığı:** Tüm bilgiler `.txt` formatında (`Koleksiyon.txt`) yerel diske kaydedilir; uygulama yeniden başlatıldığında eski veriler otomatik olarak yüklenir.

## 💻 Kullanılan Teknolojiler

*   **Dil:** Java
*   **Arayüz (GUI):** Java Swing (JFrame, JPanel, JTextField, JList, JScrollPane vb.) ve AWT bileşenleri
*   **Veri Depolama:** File I/O (Dosya Okuma/Yazma) işlemleri için `FileReader`, `FileWriter`, `BufferedReader` sınıfları
*   **Olay Yönetimi:** Kullanıcı etkileşimlerini yakalamak için `ActionListener` ve `MouseListener` arayüzleri

## 🚀 Kurulum ve Çalıştırma

1. Bu depoyu bilgisayarınıza klonlayın (Terminal):
   git clone https://github.com/KaanPasha1707/Lego-Takip-Uygulamasi.git

Not: Yaptığınız her değişiklikten sonra (Resim seçme, Bilgileri güncelleme) `Seçiliyi Güncelle` butonuna basmayı unutmayınız. Ancak o zaman yaptığınız değişiklikler ya da ekledikleriniz kalıcı olur.   
