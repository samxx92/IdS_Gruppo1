package it.uniclam.ids.gruppo1.registrazioneesami.entity;

import java.util.ArrayList;
import java.util.List;

public class Commissione {
	private String id_commissione;
	private String id_esame;
	private List<String> id_docenti;
	
	
	public String getId_commissione() {
		return id_commissione;
	}
	public void setId_commissione(String id_commissione) {
		this.id_commissione = id_commissione;
	}
	public String getId_esame() {
		return id_esame;
	}
	public void setId_esame(String id_esame) {
		this.id_esame = id_esame;
	}
	public List<String> getId_docenti() {
		return id_docenti;
	}
	public void setId_docenti(List<String> id_docenti) {
		this.id_docenti = id_docenti;
	}
	
	public List<Commissione> getAllCommissioni(){
		List<Commissione> commissioni = new ArrayList<Commissione>();
		
		Commissione test1 = new Commissione();
		test1.setId_commissione("c1");
		
		List<String> id_docenti = new ArrayList<String>();
						
		id_docenti.add("id1");
		id_docenti.add("id2");
		
		test1.setId_docenti(id_docenti);
		test1.setId_esame("e1");
		
		Commissione test2 = new Commissione();
		test2.setId_commissione("c2");
		
		List<String> id_docenti2 = new ArrayList<String>();
		id_docenti2.add("id2");
		id_docenti2.add("id3");
		
		test2.setId_docenti(id_docenti2);
		test2.setId_esame("e2");
		
		Commissione test3 = new Commissione();
		test3.setId_commissione("c3");
		
		List<String> id_docenti3 = new ArrayList<String>();
		id_docenti3.add("id3");
		
		test3.setId_docenti(id_docenti3);
		test3.setId_esame("e3");
		
		commissioni.add(test1);
		commissioni.add(test2);
		commissioni.add(test3);
		
		return commissioni;
		
	}


}
