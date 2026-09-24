package Koleksiyon;

public class Lego {
    private String setAdı, seriNum, Tema, açıklama, resimYolu;
    private int parçaSayısı, AlışFiyat, GüncelFiyat, adet;

    public Lego(String Tema, String setAdı, String seriNum, int parçaSayısı, int AlışFiyat, int GüncelFiyat, int adet, String açıklama, String resimYolu) {
        this.Tema = Tema;
        this.setAdı = setAdı; 
        this.seriNum = seriNum;
        this.parçaSayısı = parçaSayısı;
        this.AlışFiyat = AlışFiyat;
        this.GüncelFiyat = GüncelFiyat;
        this.adet = adet;
        this.açıklama = açıklama;
        this.resimYolu = resimYolu;
    }

    public String getInfo() {
        return "Tema: "+Tema+" | Setin Adı: "+setAdı+" | Seri Numarası: "+seriNum+" | Parça Sayısı: "+parçaSayısı+" | Alış Fiyatı: "+AlışFiyat+" TL | Güncel Fiyatı: "+GüncelFiyat+" TL | Adet Miktarı: "+adet+" | Açıklama: "+açıklama+" | Resim Yolu: "+resimYolu;
    }
    
    public String toFileFormat() {
        return Tema+";"+setAdı+";"+seriNum+";"+parçaSayısı+";"+AlışFiyat+";"+GüncelFiyat+";"+adet+";"+açıklama+";"+resimYolu;
    }
}