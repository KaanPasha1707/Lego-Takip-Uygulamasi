package Koleksiyon;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class LegoTakipUygulaması {

	private static String[] secilenResimYolu = {"-"};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("LEGO Koleksiyon Takibi");
        frame.setSize(1500, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 2, 20, 0));
	
        JTextField Tema = new JTextField(15);
        JTextField Ad = new JTextField(15);
        JTextField Seri = new JTextField(15);
        JTextField Parça = new JTextField(15);
        JTextField Alış = new JTextField(15);
        JTextField Güncel = new JTextField(15);
        JTextField Adet = new JTextField(15);
        JTextField Açıklama = new JTextField(15);
        JButton btnResimSec = new JButton("Resim Seç");
        JButton buton = new JButton("Koleksiyona Ekle");
        JButton butonSil = new JButton("Seçili Seti Sil");
        JButton btnGuncelle = new JButton("Seçiliyi Güncelle");
        
        
        Tema.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        Ad.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        Seri.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        Parça.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        Alış.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        Güncel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        Adet.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        Açıklama.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
	
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> liste = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(liste);
       
        JPanel solPanel = new JPanel();
        solPanel.setLayout(new BoxLayout(solPanel, BoxLayout.Y_AXIS));
        solPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel lblBaslik = new JLabel("Yeni Set Bilgileri");
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 16));
        solPanel.add(lblBaslik);
        solPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        solPanel.add(new JLabel("Tema:"));
        solPanel.add(Tema);
        solPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        solPanel.add(new JLabel("Set Adı:"));
        solPanel.add(Ad);
        solPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        solPanel.add(new JLabel("Seri Numarası:"));
        solPanel.add(Seri);
        solPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        solPanel.add(new JLabel("Parça Sayısı:"));
        solPanel.add(Parça);
        solPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        solPanel.add(new JLabel("Alış Fiyatı:"));
        solPanel.add(Alış);
        solPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        solPanel.add(new JLabel("Güncel Fiyatı:"));
        solPanel.add(Güncel);
        solPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        solPanel.add(new JLabel("Adet Miktarı:"));
        solPanel.add(Adet);
        solPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        solPanel.add(new JLabel("Açıklama:"));
        solPanel.add(Açıklama);
        solPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JPanel sagPanel = new JPanel(new BorderLayout(10, 10));
        sagPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel lblListeBaslik = new JLabel("Mevcut Koleksiyon");
        lblListeBaslik.setFont(new Font("Arial", Font.BOLD, 16));
        sagPanel.add(lblListeBaslik, BorderLayout.NORTH);
        sagPanel.add(scrollPane, BorderLayout.CENTER);
        
        JLabel lblToplamKar = new JLabel("Toplam Kar/Zarar: 0 TL");
        lblToplamKar.setFont(new Font("Arial", Font.BOLD, 14));
        sagPanel.add(lblToplamKar, BorderLayout.SOUTH); 
        
        Runnable guncelleToplamKar = () -> {
            int toplamAlis = 0;
            int toplamGuncel = 0;

            for (int i = 0; i < listModel.getSize(); i++) {
                String satir = listModel.getElementAt(i);
               
                String[] veriler = satir.replace("Tema: ", "").replace(" | Setin Adı: ", ";")
                                        .replace(" | Seri Numarası: ", ";").replace(" | Parça Sayısı: ", ";")
                                        .replace(" | Alış Fiyatı: ", ";").replace(" TL | Güncel Fiyatı: ", ";")
                                        .replace(" TL | Adet Miktarı: ", ";").replace(" | Açıklama: ", ";")
                                        .replace(" | Resim Yolu: ",";")
                                        .split(";");
                
                if (veriler.length == 9) {
                    toplamAlis += Integer.parseInt(veriler[4])*Integer.parseInt(veriler[6]);
                    toplamGuncel += Integer.parseInt(veriler[5])*Integer.parseInt(veriler[6]);
                }
            }

            int kar = toplamGuncel - toplamAlis;
            lblToplamKar.setText("Toplam Kar/Zarar: " + kar + " TL");

            if (kar > 0) lblToplamKar.setForeground(new Color(0, 128, 0));
            else if (kar < 0) lblToplamKar.setForeground(Color.RED);
            else lblToplamKar.setForeground(Color.BLACK);
        };
        
        btnResimSec.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                secilenResimYolu[0] = chooser.getSelectedFile().getAbsolutePath();
                JOptionPane.showMessageDialog(frame, "Resim Seçildi!");
            }
        });
        
        liste.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = liste.getSelectedIndex();
                if (index != -1) {
                	String seciliSatir = listModel.getElementAt(index);
                	
                	String[] veriler = seciliSatir.replace("Tema: ", "").replace(" | Setin Adı: ", ";")
                            .replace(" | Seri Numarası: ", ";").replace(" | Parça Sayısı: ", ";")
                            .replace(" | Alış Fiyatı: ", ";").replace(" TL | Güncel Fiyatı: ", ";")
                            .replace(" TL | Adet Miktarı: ", ";").replace(" | Açıklama: ", ";")
                            .replace(" | Resim Yolu: ", ";")
                            .split(";");
                	
                	if (e.getClickCount() == 2) { 
                		
                		System.out.println("Veri Sayısı: " + veriler.length);
                		
                        if (veriler.length == 9 && !veriler[8].equals("-")) {
                            resmiYeniPenceredeAc(veriler[8], veriler[1]); 
                        }else {
                        	JOptionPane.showMessageDialog(null, "Resim verisi ayrıştırılamadı veya yol hatalı!");
                        }
                    } else { 
                    	if(veriler.length>=9) {
                    		Tema.setText(veriler[0]);
                            Ad.setText(veriler[1]);
                            Seri.setText(veriler[2]);
                            Parça.setText(veriler[3]);
                            Alış.setText(veriler[4]);
                            Güncel.setText(veriler[5]); 
                            Adet.setText(veriler[6]);
                            Açıklama.setText(veriler[7]);
                            secilenResimYolu[0] = veriler[8];
                    	}
                    }
                }
            }
        });
        
        Runnable otomatikSirala = () -> {
            java.util.List<String> tempListe = new java.util.ArrayList<>();
            
            for (int i = 0; i < listModel.getSize(); i++) {
                tempListe.add(listModel.getElementAt(i));
            }

            java.util.Collections.sort(tempListe);

            listModel.clear();
            for (String s : tempListe) {
                listModel.addElement(s);
            }
        };
        
        buton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String ParçaMetni=Parça.getText().trim();
            	String alisMetni = Alış.getText().trim();
            	String GüncelMetni=Güncel.getText().trim();
            	String AdetMetni=Adet.getText().trim();
            	int ParçaMiktarı;
            	int alisFiyatSayı;
            	int güncelFiyatSayı;
            	int adetMiktarı;
            	

            	if (alisMetni.equals("-") || alisMetni.isEmpty()) {
            	    alisFiyatSayı = 0;
            	} else {
            	    try {
            	        alisFiyatSayı = Integer.parseInt(alisMetni);
            	    } catch (NumberFormatException e4) {
            	        alisFiyatSayı = 0; 
            	    }
            	}
            	
            	if (ParçaMetni.isEmpty()) {
            	    ParçaMiktarı = 0;
            	} else {
            	    try {
            	    	ParçaMiktarı = Integer.parseInt(ParçaMetni);
            	    } catch (NumberFormatException e4) {
            	    	ParçaMiktarı = 0; 
            	    }
            	}
            	
            	if (GüncelMetni.isEmpty()) {
            		güncelFiyatSayı = 0;
            	} else {
            	    try {
            	    	güncelFiyatSayı = Integer.parseInt(GüncelMetni);
            	    } catch (NumberFormatException e4) {
            	    	güncelFiyatSayı = 0; 
            	    }
            	}
            	
            	if (AdetMetni.isEmpty()) {
            	    adetMiktarı = 0;
            	} else {
            	    try {
            	    	adetMiktarı = Integer.parseInt(AdetMetni);
            	    } catch (NumberFormatException e4) {
            	    	adetMiktarı = 0; 
            	    }
            	}
            	
            	Lego yeniLego = new Lego(Tema.getText(), Ad.getText(), Seri.getText(), 
            	                         ParçaMiktarı, alisFiyatSayı,güncelFiyatSayı,adetMiktarı,Açıklama.getText(),secilenResimYolu[0]);               
            	listModel.addElement(yeniLego.getInfo());
            	otomatikSirala.run();
            	
                try (FileWriter fw = new FileWriter("Koleksiyon.txt", true)) {
                    fw.write(yeniLego.toFileFormat() + "\n");
                    guncelleToplamKar.run();
                    secilenResimYolu[0] = "-";
                } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
                Tema.setText("");
                Ad.setText("");
                Seri.setText("");
                Parça.setText("");
                Alış.setText("");
                Güncel.setText("");
                Adet.setText("");
                Açıklama.setText("");
            }
        });
        
        butonSil.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e) {
                int seciliIndex = liste.getSelectedIndex();
                
                if (seciliIndex != -1) { 
                    listModel.remove(seciliIndex);

                    try (FileWriter fw = new FileWriter("Koleksiyon.txt", false)) {
                        for (int i = 0; i < listModel.getSize(); i++) {
                          
                            String satir = listModel.getElementAt(i);
                            
                            fw.write(satir.replace("Tema: ", "").replace(" | Setin Adı: ", ";").replace(" | Seri Numarası: ", ";").replace(" | Parça Sayısı: ", ";").replace(" | Alış Fiyatı: ", ";").replace(" TL | Güncel Fiyatı: ", ";").replace(" TL | Adet Miktarı: ", ";").replace(" | Açıklama: ", ";").replace(" | Resim Yolu: ", ";") + "\n");
                        }
                        JOptionPane.showMessageDialog(frame, "Seçili öğe silindi ve dosya güncellendi.");
                        guncelleToplamKar.run();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Lütfen silmek için listeden bir set seçin!");
                }
            }
        });
        
        liste.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = liste.getSelectedIndex();
                if (index != -1) {                	
                    String seciliSatir = listModel.getElementAt(index);
                  
                    String[] veriler = seciliSatir.replace("Tema: ", "").replace(" | Setin Adı: ", ";").replace(" | Seri Numarası: ", ";").replace(" | Parça Sayısı: ", ";").replace(" | Alış Fiyatı: ", ";").replace(" TL | Güncel Fiyatı: ", ";").replace(" TL | Adet Miktarı: ", ";").replace(" | Açıklama: ", ";").replace(" | Resim Yolu: ", ";").split(";");
                    
                    if (veriler.length == 9) {
                    	Tema.setText(veriler[0]);
                        Ad.setText(veriler[1]);
                        Seri.setText(veriler[2]);
                        Parça.setText(veriler[3]);
                        Alış.setText(veriler[4]);
                        Güncel.setText(veriler[5]);
                        Adet.setText(veriler[6]);
                        Açıklama.setText(veriler[7]);
                        secilenResimYolu[0] = veriler[8];
                    }
                }
            }
        });
        
        btnGuncelle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int seciliIndex = liste.getSelectedIndex();
                if (seciliIndex != -1) {
                    
                	String ParçaMetni=Parça.getText().trim();
                	String alisMetni = Alış.getText().trim();
                	String GüncelMetni=Güncel.getText().trim();
                	String AdetMetni=Adet.getText().trim();
                	int ParçaMiktarı;
                	int alisFiyatSayı;
                	int güncelFiyatSayı;
                	int adetMiktarı;

                	if (alisMetni.equals("-") || alisMetni.isEmpty()) {
                	    alisFiyatSayı = 0;
                	} else {
                	    try {
                	        alisFiyatSayı = Integer.parseInt(alisMetni);
                	    } catch (NumberFormatException e4) {
                	        alisFiyatSayı = 0; 
                	    }
                	}
                	
                	if (ParçaMetni.isEmpty()) {
                	    ParçaMiktarı = 0;
                	} else {
                	    try {
                	    	ParçaMiktarı = Integer.parseInt(ParçaMetni);
                	    } catch (NumberFormatException e4) {
                	    	ParçaMiktarı = 0; 
                	    }
                	}
                	
                	if (GüncelMetni.isEmpty()) {
                		güncelFiyatSayı = 0;
                	} else {
                	    try {
                	    	güncelFiyatSayı = Integer.parseInt(GüncelMetni);
                	    } catch (NumberFormatException e4) {
                	    	güncelFiyatSayı = 0; 
                	    }
                	}
                	
                	if (AdetMetni.isEmpty()) {
                	    adetMiktarı = 0;
                	} else {
                	    try {
                	    	adetMiktarı = Integer.parseInt(AdetMetni);
                	    } catch (NumberFormatException e4) {
                	    	adetMiktarı = 0; 
                	    }
                	}
                	
                	Lego guncelLego = new Lego(Tema.getText(), Ad.getText(), Seri.getText(), 
                	                         ParçaMiktarı, alisFiyatSayı,güncelFiyatSayı,adetMiktarı,Açıklama.getText(),secilenResimYolu[0]);                          
                    listModel.set(seciliIndex, guncelLego.getInfo());
                    otomatikSirala.run();
                    
                    try (FileWriter fw = new FileWriter("Koleksiyon.txt", false)) {
                        for (int i = 0; i < listModel.getSize(); i++) {
                            String satir = listModel.getElementAt(i);
                            String formatli = satir.replace("Tema: ","").replace(" | Setin Adı: ", ";").replace(" | Seri Numarası: ", ";").replace(" | Parça Sayısı: ", ";").replace(" | Alış Fiyatı: ", ";").replace(" TL | Güncel Fiyatı: ", ";").replace(" TL | Adet Miktarı: ", ";").replace(" | Açıklama: ", ";").replace(" | Resim Yolu: ", ";");
                            fw.write(formatli + "\n");
                        }
                        JOptionPane.showMessageDialog(frame, "Set bilgileri güncellendi!");
                        guncelleToplamKar.run();
                        
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                
                    Tema.setText("");
                    Ad.setText("");
                    Seri.setText("");
                    Parça.setText("");
                    Alış.setText("");
                    Güncel.setText("");
                    Adet.setText("");
                    Açıklama.setText("");
                
                }
                
            }
        });
        
        try (BufferedReader reader = new BufferedReader(new FileReader("Koleksiyon.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	String[] parcalar = line.split(";");
                if (parcalar.length == 9) {
                	int alisFiyati;
                	int parçaMiktarı;
                	int güncelFiyati;
                	int adetMiktarı;
                	try {
                		parçaMiktarı = Integer.parseInt(parcalar[3]);
                	    alisFiyati = Integer.parseInt(parcalar[4]);
                	    güncelFiyati = Integer.parseInt(parcalar[5]);
                	    adetMiktarı = Integer.parseInt(parcalar[6]);
                	} catch (NumberFormatException e) {
                	    alisFiyati = 0; 
                	    parçaMiktarı =0;
                	    güncelFiyati=0;
                	    adetMiktarı=0;
                	    
                	}
                    Lego l = new Lego(parcalar[0],parcalar[1],parcalar[2],parçaMiktarı,alisFiyati,güncelFiyati,adetMiktarı,parcalar[7],parcalar[8]);
                    listModel.addElement(l.getInfo());
                }
            }
        } catch (FileNotFoundException e3) {
            System.out.println("Henüz kayıtlı dosya yok, yeni oluşturunuz.");
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        otomatikSirala.run();
        guncelleToplamKar.run();
        
        solPanel.add(btnGuncelle); 
        solPanel.add(Box.createRigidArea(new Dimension(0, 5))); 
        solPanel.add(buton);
        solPanel.add(Box.createRigidArea(new Dimension(0, 5))); 
        solPanel.add(butonSil);
        solPanel.add(Box.createRigidArea(new Dimension(0, 5))); 
        solPanel.add(btnResimSec);
        frame.add(solPanel);
        frame.add(sagPanel);

        frame.setVisible(true);
	
	}
	
	private static void resmiGoster(int index) {
        try (BufferedReader r = new BufferedReader(new FileReader("Koleksiyon.txt"))) {
            String line; int i = 0;
            while ((line = r.readLine()) != null) {
                if (i++ == index) {
                    String[] p = line.split(";");
                    if (p.length == 9 && !p[8].equals("-")) {
                        JFrame f = new JFrame("Görsel: " + p[1]);
                        f.setSize(600, 600);
                        ImageIcon icon = new ImageIcon(p[8]);
                        Image img = icon.getImage().getScaledInstance(580, 550, Image.SCALE_SMOOTH);
                        f.add(new JLabel(new ImageIcon(img)));
                        f.setVisible(true);
                        f.toFront();
                    } else { JOptionPane.showMessageDialog(null, "Resim bulunamadı!"); }
                    break;
                }
            }
        } catch (IOException ex) { ex.printStackTrace(); }
    }
	
	private static void resmiYeniPenceredeAc(String yol, String ad) {
		try {
	    JFrame f = new JFrame("Görsel: " + ad);
	    f.setSize(600, 600);
	    f.setLocationRelativeTo(null);
	    
	    File resimDosyasi = new File(yol);
        if (!resimDosyasi.exists()) {
            JOptionPane.showMessageDialog(null, "Dosya bulunamadı: " + yol);
            return;
        }
	    
	    ImageIcon icon = new ImageIcon(yol);
	    Image img = icon.getImage().getScaledInstance(580, 550, Image.SCALE_SMOOTH);
	    f.add(new JLabel(new ImageIcon(img)));
	    f.setVisible(true);
	    f.toFront();
		} catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}

}
