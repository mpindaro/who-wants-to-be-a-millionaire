package pMilionario;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ArchivioR {
	private String nome;
	private int lkey;
	private int lRec;

	ArchivioR(String n, int ln, int ln2) {
		nome = n;
		lRec = ln;
		lkey = ln2;
	}

	public void addR2(String buffer) {
		try {
			RandomAccessFile f = new RandomAccessFile(nome, "rw");
			f.seek(f.length());
			f.write(buffer.getBytes());
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addR(String buffer) {
		try {
			RandomAccessFile f = new RandomAccessFile(nome, "rw");
			f.seek(f.length());
			f.writeUTF(buffer);
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	

	public Domande[] leggi() {
		String b;
		int i = 0;
		Domande[] d = new Domande[15];
		RandomAccessFile f = null;
		try {
			f = new RandomAccessFile(nome, "r");
			f.seek(0);
			// long x=f.length();
			while (true) {
				b = f.readUTF();
				d[i] = new Domande(b);
				i++;
			}

		} catch (IOException e) {
			// f.close();
			System.out.println("Errore: " + e.getMessage());
		} catch (Exception e) {
		}
		return d;
	}

	public String getR(int pos) {

		try {
			RandomAccessFile f = new RandomAccessFile(nome, "rw");

			// Ecc qua lo strunz lo so potevo usare i multi ma me li ha messi
			// eclipse e chi ha la voglia di metterli ordinati
			String x;
			f.seek(pos * lRec);
			x = f.readUTF();
			f.close();
			return x;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Eccezione: " + e.getMessage());
			e.printStackTrace();
			return null;
		}

	}
	
	public String getR2(int pos) {

		try {
			byte arr[]=new byte[lRec];

			RandomAccessFile f = new RandomAccessFile(nome, "rw");

			// Ecc qua lo strunz lo so potevo usare i multi ma me li ha messi
			// eclipse e chi ha la voglia di metterli ordinati
			String x;
			f.seek(pos * lRec);
			f.read(arr);
			x=new String(arr);
			f.close();
			return x;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Eccezione: " + e.getMessage());
			e.printStackTrace();
			return null;
		}

	}
	
	
	public int ricR(String key) {
		try {
			int n = this.numR();
			RandomAccessFile f = new RandomAccessFile(nome, "r");
			boolean trovato = false;
			int i = 0;
			String b, chiave;
			f.seek(0);
			while (!trovato && i < n) {
				b = f.readUTF();
				chiave = b.substring(0, lkey).trim();
				if (key.equals(chiave))
					trovato = true;
				else
					i++;
			}

			f.close();
			if (trovato)
				return i;
			else
				return -1;

		} catch (Exception e) {
			return -1;
		}
	}

	public int ricR2(String key) {
		try {
			int n = this.numR();
			RandomAccessFile f = new RandomAccessFile(nome, "r");
			boolean trovato = false;
			int i = 0;
			String b, chiave;
			byte arr[]=new byte[lRec];
			f.seek(0);
			while (!trovato && i < n) {
					f.read(arr);
					b=new String(arr);
				chiave = b.substring(0, lkey).trim();
				if (key.equals(chiave))
					trovato = true;
				else
					i++;
			}

			f.close();
			if (trovato)
				return i;
			else
				return -1;

		} catch (Exception e) {
			return -1;
		}
	}

	public int numR() {
		try {
			RandomAccessFile f = new RandomAccessFile(nome, "r");
			int x = (int) (f.length() / lRec);
			f.close();
			return x;
		} catch (Exception e) {
			return 0;

		}

	}

	public void modR(int pos, String buffer) {
		try {
			RandomAccessFile f = new RandomAccessFile(nome, "rw");
			f.seek(pos * lRec);
			f.writeUTF(buffer);
			f.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void cancR(int pos) {

		try {
			int n = this.numR();
			RandomAccessFile f = new RandomAccessFile(nome, "rw");
			int i = 0;
			f.seek(pos * lRec);
			f.writeUTF("");
			while (i < pos * lRec) {
				i++;
			}
			String b;
			while (i < n) {
				b = f.readUTF();
				f.seek(i - 1 * lRec);
				f.writeUTF(b);
			}
			f.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
